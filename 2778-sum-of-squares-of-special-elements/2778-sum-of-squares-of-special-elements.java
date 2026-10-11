class Solution {
    public int sumOfSquares(int[] nums) {
        int n=nums.length;
        int result=0;
        for(int i=0;i<nums.length;i++){
            if(n%(i+1)==0){
                result+=Math.pow(nums[i],2);
            }
        }
        return result;
    }
}