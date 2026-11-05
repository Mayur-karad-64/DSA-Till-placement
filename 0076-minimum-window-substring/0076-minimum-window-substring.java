class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] map = new int[128];

        for (char c : t.toCharArray())
            map[c]++;

        int left = 0, start = 0;
        int minLen = Integer.MAX_VALUE;
        int count = t.length();

        for (int right = 0; right < s.length(); right++) {
            if (map[s.charAt(right)] > 0)
                count--;

            map[s.charAt(right)]--;

            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                map[s.charAt(left)]++;

                if (map[s.charAt(left)] > 0)
                    count++;

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" :
            s.substring(start, start + minLen);
    }
}