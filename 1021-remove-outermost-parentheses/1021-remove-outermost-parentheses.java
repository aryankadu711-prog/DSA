class Solution {
    public String removeOuterParentheses(String s) {
        int count=0;
        int in=0;
        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') {
                if(count>0){
                    result.append('(');
                }
                count++;
            }
            else{
                count--;
                if(count>0){
                    result.append(')');
                }
            }
        }
        return result.toString();
    }
}