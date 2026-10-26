class Solution {
    int m, n;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    long dfs(int i, int j, int[][] grid) {
        long sum = grid[i][j];
        grid[i][j] = 0;

        for (int[] dir : directions) {
            int i_ = i + dir[0];
            int j_ = j + dir[1];

            if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && grid[i_][j_] > 0) {
                sum += dfs(i_, j_, grid);
            }
        }

        return sum;
    }
    long bfs(int si, int sj, int[][] grid) {
        long sum = grid[si][sj];
        grid[si][sj] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{si, sj});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : directions) {
                int i_ = cur[0] + dir[0];
                int j_ = cur[1] + dir[1];

                if (i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && grid[i_][j_] > 0) {
                    sum += grid[i_][j_];
                    grid[i_][j_] = 0;
                    queue.offer(new int[]{i_, j_});
                }
            }
        }

        return sum;
    }
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(i, j, grid);
                    if (sum % k == 0)
                        count++;
                }
            }
        }

        return count;
    }
}

