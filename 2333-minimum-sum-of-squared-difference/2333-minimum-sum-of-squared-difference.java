class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n= nums1.length;
        int diff[]= new int[n];
        int maxdiff=0;
        for(int i=0;i<n;i++){
            diff[i]=Math.abs(nums1[i]-nums2[i]);
            maxdiff= Math.max(diff[i],maxdiff);
        }
        int countmaxd[]= new int[maxdiff+1] ;
        for(int i: diff){
            countmaxd[i]++;
        }
        long k=(long) k1+k2;
        for(int i=maxdiff;i>0 && k>0;i--){
            int ops=(int) Math.min(countmaxd[i], k);
            countmaxd[i]-=ops;
            countmaxd[i-1]+=ops;
            k-=ops;
        }
        long result=0;
        for(long i=1;i<=maxdiff;i++){
            result+=i*i*countmaxd[(int)i];
        }
        return result;
    }
}