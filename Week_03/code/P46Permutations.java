//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法

package cn.leetcode.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution46 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();

        dfs(nums,0,path,used);


        return res;

    }

    private void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used) {
        if ( depth == nums.length ) {
            // 在 Java 中，因为都是值传递，对象类型变量在传参的过程中，复制的都是变量的地址
            // 这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此我们会看到 6 个空的列表对象。
            // 解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。

            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ( used[i] ) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,depth+1,path,used);
            used[i] = false;
            path.removeLast();
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
