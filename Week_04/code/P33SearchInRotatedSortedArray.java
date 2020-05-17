//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
package cn.leetcode.leetcode.editor.cn;

class Solution33 {
    public int search(int[] nums, int target) {

        if (nums.length == 0 || nums == null ) return -1;
        return helper(nums,0,nums.length-1,target);

    }

    private int helper(int[] nums, int letf, int right, int target) {

        if ( letf > right ) {
            return -1;
        }
        int mid = letf + (right-letf)/2;
        int leftnum = nums[letf];
        int rightnum = nums[right];
        int midnum = nums[mid];

        if (leftnum == target){
            return leftnum;
        }
        if(rightnum == target){
            return rightnum;
        }
        if (midnum == target){
            return midnum;
        }


        if ( leftnum < midnum ){
            if ( target > leftnum && target < midnum ) {
                return helper(nums,letf,mid-1,target);
            }else {
                return helper(nums,letf+1,right,target);
            }
        } else {
            if ( target > midnum && target < rightnum ) {
                return helper(nums,mid+1,right,target);
            }else {
                return helper(nums,letf,mid-1,target);
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
