class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // dp[i][j][0] = # stable arrays with i zeros, j ones, ending in 0
        // dp[i][j][1] = # stable arrays with i zeros, j ones, ending in 1
        long[][][] dp = new long[zero + 1][one + 1][2];
        
        // Base cases: arrays of all 0s (length <= limit)
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        // Base cases: arrays of all 1s (length <= limit)
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // End in 0: previous state had (i-1) zeros, j ones
                dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                // Subtract cases with limit+1 consecutive 0s
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i-limit-1][j][1] + MOD) % MOD;
                }
                
                // End in 1: previous state had i zeros, (j-1) ones
                dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
                // Subtract cases with limit+1 consecutive 1s
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j-limit-1][0] + MOD) % MOD;
                }
            }
        }
        
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}