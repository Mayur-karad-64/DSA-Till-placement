class Solution {
    public String solveEquation(String equation) {
        String[] parts = equation.split("=");

        int[] left = calculate(parts[0]);
        int[] right = calculate(parts[1]);

        int x = left[0] - right[0];
        int value = right[1] - left[1];

        if (x == 0 && value == 0)
            return "Infinite solutions";

        if (x == 0)
            return "No solution";

        return "x=" + (value / x);
    }

    private int[] calculate(String s) {
        int coeff = 0;
        int constant = 0;
        int sign = 1;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '+') {
                sign = 1;
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            int num = 0;
            boolean hasNum = false;

            while (i < s.length() &&
                   Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
                i++;
                hasNum = true;
            }

            if (i < s.length() && s.charAt(i) == 'x') {
                coeff += sign * (hasNum ? num : 1);
                i++;
            } else {
                constant += sign * num;
            }
        }

        return new int[]{coeff, constant};
    }
}