class Solution {
    public int lengthLongestPath(String input) {
        String[] lines = input.split("\n");
        int[] lengths = new int[lines.length + 1];

        int maxLength = 0;

        for (String line : lines) {
            int level = line.lastIndexOf("\t") + 1;
            int nameLength = line.length() - level;

            if (line.contains(".")) {
                maxLength = Math.max(
                    maxLength,
                    lengths[level] + nameLength
                );
            } else {
                lengths[level + 1] =
                    lengths[level] + nameLength + 1;
            }
        }

        return maxLength;
    }
}