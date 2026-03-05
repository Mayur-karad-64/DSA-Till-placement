class Solution {
    public int minOperations(String s) {
        int pattern1 = 0; // starting with '0' -> 0101...
        int pattern2 = 0; // starting with '1' -> 1010...

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // pattern 0101...
            if (c != (i % 2 == 0 ? '0' : '1')) {
                pattern1++;
            }

            // pattern 1010...
            if (c != (i % 2 == 0 ? '1' : '0')) {
                pattern2++;
            }
        }

        return Math.min(pattern1, pattern2);
    }
}