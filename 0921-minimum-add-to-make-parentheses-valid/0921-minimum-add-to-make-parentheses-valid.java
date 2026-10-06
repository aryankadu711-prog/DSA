class Solution {
    public int minAddToMakeValid(String s) {
        char ch[] = new char[s.length()];
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ch[++top] = '(';
            } else {
                if (top >= 0 && ch[top] == '(') {
                    top--;
                } else {
                    ch[++top] = ')';
                }
            }
        }
        return top + 1;
    }
}