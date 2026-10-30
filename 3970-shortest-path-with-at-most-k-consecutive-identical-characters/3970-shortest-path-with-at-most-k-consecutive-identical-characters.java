import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        
        // dist[u][c] = minimum weight to reach node u with c consecutive characters of labels[u]
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        // Priority Queue stores: {distance, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Start at node 0 with count 1
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});
        
        char[] lbls = labels.toCharArray();
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int c = curr[2];
            
            if (u == n - 1) {
                return d;
            }
            
            if (d > dist[u][c]) {
                continue;
            }
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                
                int newC = (lbls[u] == lbls[v]) ? c + 1 : 1;
                if (newC > k) {
                    continue;
                }
                
                if (d + w < dist[v][newC]) {
                    dist[v][newC] = d + w;
                    pq.offer(new int[]{d + w, v, newC});
                }
            }
        }
        
        return -1;
    }
}