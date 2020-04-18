//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针

package cn.leetcode.leetcode.editor.cn;
//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public void moveZeroes(int[] nums) {

        //一次遍历

//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if ( nums[i] == 0 ) {
//                count++;
//            }else if ( count > 0 ) {
//                nums[i-count] = nums[i];
//                nums[i] = 0;
//            }
//        }

//        int j = 0;
//
//        for (int i = 0; i < nums.length ; i++) {
//            if ( nums[i] != 0 ){
//                int tmp = nums[i];
//                nums[i] = nums[j];
//                nums[j++] = tmp;
//
//
//            }
//        }

        int j = 0;

        for ( int i=0; i<nums.length; i++ ) {
            if ( nums[i] != 0 ) {
               nums[j] = nums[i];
               if ( i != j ) {
                   nums[i] = 0;
               }
               j++;
            }
        }


    }
}
//leetcode submit region end(Prohibit modification and deletion)
