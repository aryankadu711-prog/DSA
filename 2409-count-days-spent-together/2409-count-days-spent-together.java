class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int month[]={0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days[]= new int[366];
        int m1=Integer.parseInt(arriveAlice.substring(0,2));
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        for(int i=0;i<m1;i++){
            a+=month[i];
        }
        a+=Integer.parseInt(arriveAlice.substring(3,5));
        int m2=Integer.parseInt(leaveAlice.substring(0,2));
        for(int i=0;i<m2;i++){
            b+=month[i];
        }
        b+=Integer.parseInt(leaveAlice.substring(3,5));
        int m3=Integer.parseInt(arriveBob.substring(0,2));
        for(int i=0;i<m3;i++){
            c+=month[i];
        }
        c+=Integer.parseInt(arriveBob.substring(3,5));
        int m4=Integer.parseInt(leaveBob.substring(0,2));
        for(int i=0;i<m4;i++){
            d+=month[i];
        }
        d+=Integer.parseInt(leaveBob.substring(3,5));
        int max=Math.max(a,c);
        int min=Math.min(b,d);
        return Math.max(0, min - max + 1);
    }
}