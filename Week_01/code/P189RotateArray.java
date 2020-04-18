//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组

package cn.leetcode.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution89 {
    public void rotate(int[] nums, int k) {


        // 暴力法
//        int tmp,pre;
//        int len = nums.length;
//        for ( int i=0; i< k; i++ ) {
//            pre = nums[len - 1];
//            for ( int j = 0; j < len; j++ ) {
//                tmp = nums[j];
//                nums[j] = pre;
//                pre = tmp;
//            }
//        }

        // 使用额外的数组
//        int[] nums_2 = new int[nums.length];
//
//        for ( int i = 0; i < nums.length; i++ ){
//            nums_2[ (i+k) % nums.length] = nums[i];
//        }
//
//        //System.arraycopy(nums_2,0,nums,0,nums.length);
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = nums_2[i];
//        }

        // 使用环状替换

//        k = k % nums.length;
//        int count = 0;
//        for ( int start = 0; count < nums.length; start++ ) {
//            int prev = nums[start];
//            int current = start;
//
//            do {
//                int next = (k + current) % nums.length;
//                int tmp = nums[next];
//                nums[next] = prev;
//                prev = tmp;
//                current = next;
//                count++;
//            } while ( current != start );
//        }

        //使用反转

        k = k % nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public void reverse(int[] nums,int start,int end){
        while ( start < end ) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
