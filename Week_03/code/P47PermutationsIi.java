//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
package cn.leetcode.leetcode.editor.cn;

import java.util.*;

class Solution47 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {


        int len = nums.length;
        if (len == 0) return res;

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];

        Arrays.sort(nums);

        dfs(nums,len,0,path,used);
        return res;

    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used) {

        if ( depth == len ){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if ( used[i]  ) continue;
            if ( i > 0 && nums[i] == nums[i-1] && !used[i-1] ) continue;

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,len,depth+1,path,used);
            used[i] = false;
            path.removeLast();

        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
