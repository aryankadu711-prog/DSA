class Solution {
    public int sumCounts(List<Integer> nums) {
        boolean val[] = new boolean[101];
        Arrays.fill(val, false);
        int result = 0;
        for (int i = 0; i < nums.size(); i++) {
            int count = 1;
            result += Math.pow(count, 2);
            val[nums.get(i)] = true;
            for (int j = i + 1; j < nums.size(); j++) {
                if (!val[nums.get(j)]) {
                    val[nums.get(j)] = true;
                    count++;
                    result += Math.pow(count, 2);
                } else {
                    result += Math.pow(count, 2);
                }
            }
            Arrays.fill(val, false);
        }
        return result;
    }
}