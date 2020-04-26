//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package cn.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public int[] twoSum(int[] nums, int target) {

//         暴力法
//        int[] result = new int[2];
//
//        for ( int i=0; i< nums.length-1; i++  ) {
//            for ( int j=i+1;j<nums.length; j++ ) {
//                if( target == nums[i] + nums[j] ) {
//                    result[0] = i;
//                    result[1] = j;
//                    break;
//                }
//            }
//        }
//
//        return result;


        // 哈希表
//        Map<Integer,Integer> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            if ( map.containsKey(target - nums[i]) ) {
//                return new int[] { map.get(target - nums[i]) , i };
//            }
//            map.put(nums[i],i);
//        }
//
//        throw new IllegalArgumentException(" no two num solution");


        // 两边遍历
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            if ( map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[] {i,map.get(target-nums[i])};
            }
        }

        throw  new IllegalArgumentException(" no two num solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15};
        int[] result = new Solution1().twoSum(nums, 9);
        System.out.println(Arrays.toString(result));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
