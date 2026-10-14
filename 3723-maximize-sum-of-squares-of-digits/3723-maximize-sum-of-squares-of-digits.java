class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int nines=sum/9;
        int rem= sum%9;
        while(nines!=0){
            sb.append("9");
            nines--;
            num--;
        }
        if (rem>0) {
            sb.append(rem);
            num--;
        }
        while(num!=0){
            sb.append("0");
            num--;
        }
        return sb.toString();
    }
}