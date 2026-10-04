class Solution {
    public boolean checkValidString(String s) {
        Stack <Integer> st= new Stack<>();
        Stack <Integer> ast= new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push(i);
            }
            else if(s.charAt(i)=='*'){
                ast.push(i);
            }
            else{
                if(!st.isEmpty()){
                    st.pop();
                }
                else if(!ast.isEmpty()){
                    ast.pop();
                }
                else{
                    return false;
                }
            }
        }
        while(!st.isEmpty() && !ast.isEmpty()){
            if (st.peek() > ast.peek()) {
                return false;
            }
            st.pop();
            ast.pop();
        }
        return st.isEmpty();
    }
}