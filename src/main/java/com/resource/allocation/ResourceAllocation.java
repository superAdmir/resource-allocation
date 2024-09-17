package com.resource.allocation;

public class ResourceAllocation {

    public static void main(String[] args) {
        int budget = 10;
        int[] costs = { 2, 3, 5, 7 }; // Costs of projects
        int[] benefits = { 4, 5, 8, 10 }; // Benefits of projects

        int n = costs.length;
        int[][] dp = new int[n + 1][budget + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= budget; j++) {
                if (costs[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i - 1]] + benefits[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The maximum benefit with the given budget
        int maxBenefit = dp[n][budget];
        System.out.println("Maximum Benefit: " + maxBenefit);

        // To find which projects are included in the optimal solution
        System.out.println("Projects included:");
        int w = budget;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Project " + i + " (Cost: " + costs[i - 1] + ", Benefit: " + benefits[i - 1] + ")");
                w -= costs[i - 1];
            }
        }
    }

}
