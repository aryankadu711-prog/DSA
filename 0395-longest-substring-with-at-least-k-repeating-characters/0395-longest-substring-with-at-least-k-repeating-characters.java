class Solution {
    public int longestSubstring(String s, int k) {
        return solve(s, 0, s.length() - 1, k);
    }
    private int solve(String s, int left, int right, int k) {
        if (right - left + 1 < k) {
            return 0;
        }
        int[] freq = new int[26];
        for (int i = left; i <= right; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = left; i <= right; i++) {
            if (freq[s.charAt(i) - 'a'] < k) {
                int next = i + 1; 
                while (next <= right &&
                        freq[s.charAt(next) - 'a'] < k) {
                    next++;
                } 
                int leftPart = solve(s, left, i - 1, k);
                int rightPart = solve(s, next, right, k);
                return Math.max(leftPart, rightPart);
            }
        }
        return right - left + 1;
    }
}