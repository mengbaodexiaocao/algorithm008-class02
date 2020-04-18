//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组

package cn.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution66 {
    public static int[] plusOne(int[] digits) {

        // 暴力法 先求出原始数组中所表示的数字  会有数字越界的情况
//        int sourceData = 0;
//        int curData = 0;
//        int pow = digits.length - 1;
//        for (int i = 0; i < digits.length; i++,pow--) {
//            int tmpData = (int)(digits[i] * Math.pow(10, pow));
//            sourceData = sourceData + tmpData;
//        }
//
//
//
//        curData = sourceData + 1;
//
//        String s = String.valueOf(curData);
//        int[] result = new int[s.length()];
//
//        for (int i = 0; i < result.length - 1; i++) {
//            result[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
//        }
//
//        return result;

        //从后往前遍历 若有进位，向前移动一位，接着进行+1操作，无进位，返回数组
        for ( int i = digits.length - 1; i >= 0; i-- )  {
            digits[i]++;
            digits[i] %= 10;
            if ( digits[i] != 0 ) {
                return digits;
            }
        }

        digits = new int[digits.length+1];
        digits[0] = 1;
        // 若数组中数字为 9 9 9 ，最终会变成 0 0 0 ，需要将长度加 1 ，将首位赋值为 1
        // 最终输出结果 1 0 0 0 ，数组中默认元素值为 0 ，所以下面给最后一个元素的赋值操作可以省略
        //digits[digits.length - 1] = 0;
        return digits;

    }

    public static void main(String[] args) {
        int[] a = new int[] {9,8,7,6,5,4,3,2,1,0};
        int[] ints = Solution66.plusOne(a);
        System.out.print(ints);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
