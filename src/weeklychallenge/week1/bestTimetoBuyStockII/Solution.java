package weeklychallenge.week1.bestTimetoBuyStockII;

class Solution {
    public int maxProfit(int[] prices) {

        //peak valley solution

        // buy on valley and sell on peak
        //constraint sell only if you bought
        // first find valley

        int valley = prices[0]; // failing on
        int peak = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }

        return maxProfit;
    }

//    class Solution {
        public int maxProfitAccepted(int[] prices) {
            int maxprofit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    maxprofit += prices[i] - prices[i - 1];
            }
            return maxprofit;
        }
//    }

}