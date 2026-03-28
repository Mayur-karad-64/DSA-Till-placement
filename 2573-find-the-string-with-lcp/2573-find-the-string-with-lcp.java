import java.util.*;

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Check diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // Step 2: DSU
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Find
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x)
                    parent[x] = applyAsInt(parent[x]);
                return parent[x];
            }
        };

        // Union
        java.util.function.BiConsumer<Integer, Integer> union = (a, b) -> {
            int pa = find.applyAsInt(a);
            int pb = find.applyAsInt(b);
            if (pa != pb) parent[pb] = pa;
        };

        // Merge groups
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union.accept(i, j);
                }
            }
        }

        // Step 3: Assign characters
        char[] res = new char[n];
        Map<Integer, Character> map = new HashMap<>();
        char ch = 'a';

        for (int i = 0; i < n; i++) {
            int p = find.applyAsInt(i);
            if (!map.containsKey(p)) {
                if (ch > 'z') return "";
                map.put(p, ch++);
            }
            res[i] = map.get(p);
        }

        // Step 4: Validate LCP
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected;
                if (res[i] == res[j]) {
                    expected = 1;
                    if (i + 1 < n && j + 1 < n)
                        expected += lcp[i + 1][j + 1];
                } else {
                    expected = 0;
                }

                if (lcp[i][j] != expected)
                    return "";
            }
        }

        return new String(res);
    }
}