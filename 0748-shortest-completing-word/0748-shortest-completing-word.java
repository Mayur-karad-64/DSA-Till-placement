class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] required = new int[26];

        for (char c : licensePlate.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                required[c - 'a']++;
            }
        }

        String result = "";

        for (String word : words) {
            int[] count = new int[26];

            for (char c : word.toLowerCase().toCharArray()) {
                count[c - 'a']++;
            }

            boolean valid = true;

            for (int i = 0; i < 26; i++) {
                if (count[i] < required[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid && (result.isEmpty() || word.length() < result.length())) {
                result = word;
            }
        }

        return result;
    }
}