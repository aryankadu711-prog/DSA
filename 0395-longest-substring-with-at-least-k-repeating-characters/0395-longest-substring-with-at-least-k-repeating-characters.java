class Solution {
    public int longestSubstring(String s, int k) {
        if(s.length()<k){
            return 0;
        }
        
        int count[]= new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i) - 'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(count[s.charAt(i)-'a']<k){
                int left= longestSubstring(s.substring(0,i),k);
                int right= longestSubstring(s.substring(i+1),k);
                return Math.max(left,right);
            }
        }
        return s.length();
    }
}