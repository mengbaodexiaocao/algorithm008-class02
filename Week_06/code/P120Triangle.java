//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
package cn.leetcode.leetcode.editor.cn;

import java.util.List;

class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {

//        if ( triangle == null || triangle.size() == 0 ){
//            return 0;
//        }
//
//        int[] dp = new int[triangle.size()];
//        dp[0] = triangle.get(0).get(0);
//
//        int prev = 0;
//        int cur;
//        for (int i = 1; i < triangle.size() ; i++) {
//            List<Integer> list = triangle.get(i);
//            for (int j = 0; j <= i; j++) {
//                cur = dp[j];
//                if ( j == 0 ){
//                    dp[j] = cur + list.get(j);
//                }else if ( j == i ){
//                    dp[j] = prev + list.get(j);
//                } else {
//                    dp[j] = Math.min(cur,prev) + list.get(j);
//                }
//                prev = cur;
//            }
//        }
//        int res = Integer.MAX_VALUE;
//
//        for (int i = 0; i < triangle.size(); i++) {
//            res = Math.min(res,dp[i]);
//
//        }
//        return res;


//        1、状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
//        2、状态分析
//            2.1.初始化：
//              dp最后一行=triangle最后一行
//            2.2.常规：
//              triangle[i][j]一定会到达triangle[i+1][j]或者triangle[i+1][j+1],
//              所以状态dp[i][j]一定等于dp[i+1][j]或者dp[i+1][j+1]的最小值+triangle[i][j]
//        3、转换方程：dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]

        // 自底向上
        if ( triangle == null || triangle.size() == 0 ){
            return 0;
        }

        int[] dp = new int[triangle.size()+1];

        for (int i = triangle.size()-1; i >=0 ; --i) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // 此处当i等于最后一行时，相当于对状态数组dp进行最后一行的赋值
                // dp[0] = triangle.get(triangle.size()).get(0)
                // dp[1] = triangle.get(triangle.size()).get(1)
                // dp[2] = triangle.get(triangle.size()).get(2)
                // ...
                dp[j] = Math.min(dp[j],dp[j+1])+list.get(j);
            }
        }

        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
