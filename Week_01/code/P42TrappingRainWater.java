//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针

package cn.leetcode.leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution42 {
    public int trap(int[] height) {

        // 看完题解后按照列求解
        // 解法一
//        int sum = 0;

        // 依次遍历每一根柱子，由于第一列与最后一列不会装水（第一列没有左边界，最后一列没有又边界）
        // 因此从 1 到 length - 1 列
//        for ( int i = 1; i < height.length - 1; i++ ) {
//            int max_left = 0;
//
//            for (int j = i - 1; j >= 0 ; j--) {
//                max_left = Math.max(max_left,height[j]);
//            }
//
//            int max_right = 0;
//
//            for (int j = i + 1; j < height.length ; j++) {
//                max_right = Math.max(max_right,height[j]);
//            }
//
//            int min = Math.min( max_left,max_right );
//
//            if ( min > height[i] ) {
//                sum = sum + ( min - height[i] );
//            }
//
//        }
//
//        return sum;


        // 解法二
        // 动态规划，不太理解动态规划的概念，按照题意就是把重复计算的抽取出来，避免重复计算

//        int sum = 0;
//        int[] max_left = new int[height.length];
//        int[] max_right = new int[height.length];
//
//
//        // 此处先计算每个柱子左右边界的最大值，存入到数组中去
//        // 于第一列与最后一列不会装水（第一列没有左边界，最后一列没有又边界）,因此排除第一列与最后一列
//        for (int i = 1; i < height.length - 1; i++) {
//            max_left[i] = Math.max( max_left[i-1],height[i-1] );
//        }
//
//        // 看题解，此处 i >= 0，感觉没必要等于0，等于0说明求第一根柱子右边的最大值，
//        // 根据提议已知，第一根柱子是不可能装水的。
//        for (int i = height.length - 2; i > 0 ; i--) {
//            max_right[i] = Math.max( max_right[i+1],height[i+1] );
//        }
//
//        for (int i = 1; i < height.length - 1; i++) {
//            int min = Math.min( max_left[i],max_right[i] );
//            if ( min > height[i] ) {
//                sum = sum + ( min - height[i] );
//            }
//        }
//        return sum;

        // 方法三 栈

        Stack<Integer> stack = new Stack();
        int cur = 0;
        int sum = 0;

        while ( cur < height.length ) {

            while ( !stack.empty() && height[cur] > height[stack.peek()] ) {
                int h = height[stack.peek()];
                stack.pop();
                if ( stack.empty() ) {
                    break;
                }
                int distance = cur - stack.peek() - 1;
                int min = Math.min( height[stack.peek()],height[cur] );
                sum = sum + distance * ( min - h );

            }

            stack.push(cur);
            cur++;
        }

        return sum;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
