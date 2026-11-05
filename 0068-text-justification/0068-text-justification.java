class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i + 1;
            int letters = words[i].length();

            while (j < words.length &&
                   letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1)
                        line.append(" ");
                }

                while (line.length() < maxWidth)
                    line.append(" ");

            } else {
                int spaces = (maxWidth - letters) / gaps;
                int extra = (maxWidth - letters) % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        int count = spaces + (k - i < extra ? 1 : 0);

                        while (count-- > 0)
                            line.append(" ");
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}