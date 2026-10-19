class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int count=0;
        int lessthan=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<target){
                lessthan++;
            }
            if(nums[i]==target){
                count++;
            }
        }
        List <Integer> list= new ArrayList<>();
        for(int i=0;i<count;i++){
            list.add(lessthan+i);
        }
        return list;
    }
}