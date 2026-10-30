import java.util.*;

class Solution {
    private static final int LOG = 17;
    private int[] tin, tout, depth, nodeMasks;
    private int[][] up;
    
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        nodeMasks = new int[n];
        up = new int[n][LOG];
        
        char[] sChars = s.toCharArray();
        int[] pref = new int[n];
        int[] dfsOrder = new int[n + 1];
        
        // DFS to compute Euler tour enter/exit times, depth, and binary lifting table
        class DfsHelper {
            int timer = 0;
            void dfs(int u, int p, int currentXor) {
                nodeMasks[u] = 1 << (sChars[u] - 'a');
                pref[u] = currentXor ^ nodeMasks[u];
                
                timer++;
                tin[u] = timer;
                dfsOrder[timer] = pref[u];
                
                depth[u] = (u == 0) ? 0 : depth[p] + 1;
                up[u][0] = p;
                for (int i = 1; i < LOG; i++) {
                    up[u][i] = up[up[u][i - 1]][i - 1];
                }
                
                for (int v : adj[u]) {
                    if (v != p) {
                        dfs(v, u, pref[u]);
                    }
                }
                tout[u] = timer;
            }
        }
        
        new DfsHelper().dfs(0, 0, 0);
        
        // Build Fenwick Tree from prefix XORs in O(n)
        int[] A = new int[n + 1];
        A[1] = dfsOrder[1];
        for (int i = 2; i <= n; i++) {
            A[i] = dfsOrder[i] ^ dfsOrder[i - 1];
        }
        
        FenwickTree bit = new FenwickTree(n, A);
        List<Boolean> result = new ArrayList<>();
        
        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int oldMask = nodeMasks[u];
                int newMask = 1 << (c - 'a');
                if (oldMask != newMask) {
                    int delta = oldMask ^ newMask;
                    nodeMasks[u] = newMask;
                    // Subtree update for node u
                    bit.updateRange(tin[u], tout[u], delta);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);
                int maskU = bit.queryPoint(tin[u]);
                int maskV = bit.queryPoint(tin[v]);
                int pathMask = maskU ^ maskV ^ nodeMasks[w];
                
                // Path can form a palindrome if at most one bit is set
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        
        return result;
    }
    
    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
    
    private static class FenwickTree {
        private final int n;
        private final int[] tree;
        
        public FenwickTree(int n, int[] initial) {
            this.n = n;
            tree = new int[n + 1];
            System.arraycopy(initial, 0, tree, 0, n + 1);
            for (int i = 1; i <= n; i++) {
                int j = i + (i & -i);
                if (j <= n) {
                    tree[j] ^= tree[i];
                }
            }
        }
        
        public void updateRange(int l, int r, int delta) {
            updatePoint(l, delta);
            updatePoint(r + 1, delta);
        }
        
        private void updatePoint(int i, int delta) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= delta;
            }
        }
        
        public int queryPoint(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }
    }
}