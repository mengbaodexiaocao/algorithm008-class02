//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法

package cn.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution169 {
    public static int majorityElement(int[] nums) {


        // 哈希
        /*int len = nums.length;
        int res = 0;
        int compare = len/2;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if ( map.containsKey(nums[i]) ) {
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i],1);
            }
        }


        for (int key:map.keySet()) {
            if ( map.get(key) > compare ) {
                res = key;
                break;
            }
        }

        return res;*/

        // 2、排序

        /*Arrays.sort(nums);
        return nums[nums.length/2];*/

        // 3、分治
//        return _generate(nums,0,nums.length-1);

        // 4、boyer-moore 算法

        int count = 0;
        Integer candidate = null;

        for (int num:nums) {

            if ( count == 0 ) candidate = num;
            count += ( candidate == num ) ? 1 : -1;

        }

        return candidate;

    }

    private static int _generate(int[] nums, int low, int high) {

        if (low == high) {
            return nums[low];
        }

        int mid = (high-low)/2 + low;
        int left = _generate(nums, low, mid);
        int right = _generate(nums, mid+1, high);

        if (left == right) {
            return left;
        }

        int leftCount = countNums(nums, left, low, high);
        int rightCount = countNums(nums, right, low, high);

        return leftCount > rightCount ? left : right;

    }

    private static int countNums(int[] nums, int num, int low, int high) {

        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;

    }

    public static void main(String[] args) {
        int[] a = new int[]{3,2,3};
        System.out.println(Solution169.majorityElement(a));


    }
}
//leetcode submit region end(Prohibit modification and deletion)
