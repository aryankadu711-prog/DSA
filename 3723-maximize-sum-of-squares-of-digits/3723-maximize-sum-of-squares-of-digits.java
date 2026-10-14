class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum)
            return "";
        int nines = sum / 9;
        num -= nines;
        if (num == 0)
            return "9".repeat(nines);
        num--;
        int mod = sum % 9;
        String start = "9".repeat(nines);
        String last = "0".repeat(num);
        String ans = start + mod + last;
        return ans;
    }
}