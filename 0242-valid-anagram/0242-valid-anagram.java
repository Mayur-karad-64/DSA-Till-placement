class Solution {
    public boolean isAnagram(String s, String t) {
       if(s.length()!=t.length()){
        return false;
       }
       char arrS[]=s.toCharArray();
       char arrT[]=t.toCharArray();
       Arrays.sort(arrS);
       Arrays.sort(arrT);
       int a = 0;
       int b = 0;
       int len = s.length();

       while(a< len && b<len){
        if(arrS[a]!=arrT[b]){
            return false;
        }
        a++;
        b++;
       }
       return true;
    }
}