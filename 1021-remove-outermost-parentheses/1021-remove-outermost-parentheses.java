class Solution {
    public String removeOuterParentheses(String s) {
        int count=0;
        int in=0;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(' && count==0){
                count++; 
            }
            else if(s.charAt(i)=='('  && count!=0){
                count++;
                result.append('(');
            }
            else if(s.charAt(i)==')' && count!=1){
                count--;
                result.append(')');
            }
            else if(s.charAt(i)==')' && count==1){
                count--;
            }
        }
        return result.toString();
    }
}