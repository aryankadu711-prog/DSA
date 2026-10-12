class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        final int BITS = 30;
        int []count= new int [BITS];
        for(int num: nums){
            for(int i=0;i<BITS;i++){
                if((num & (1<<i))!=0){
                    count[i]++;
                }
            }
        }
        long result=0;
        while(k--> 0){
            long curr=0;
            for(int i=0;i<BITS;i++){
                if(count[i]>0){
                    count[i]--;
                    curr |= 1L<< i;
                }
            }
            result=(result + curr *curr) % MOD;
        }
        return (int)result;
    }
}