//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法

package cn.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution77 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        
        if (n<=0 || k<=0 || n<k) return res;

        helper(n,k,1,new Stack<Integer>());
        return res;

    }

    private void helper(int n, int k, int index, Stack<Integer> stack) {
        if ( stack.size() == k ) {
            res.add(new ArrayList<>(stack));
            return;
        }

        // 剪枝操作
        //再如：如果 n = 6 ，k = 4，
        //pre.size() == 1 的时候，接下来要选择 3 个元素， i 最大的值是 4，最后一个被选的是 [4,5,6]；
        //pre.size() == 2 的时候，接下来要选择 2 个元素， i 最大的值是 5，最后一个被选的是 [5,6]；
        //pre.size() == 3 的时候，接下来要选择 1 个元素， i 最大的值是 6，最后一个被选的是 [6]；
        //
        //再如：如果 n = 15 ，k = 4，
        //pre.size() == 1 的时候，接下来要选择 3 个元素，i 最大的值是 13，最后一个被选的是 [13,14,15]；
        //pre.size() == 2 的时候，接下来要选择 2 个元素， i 最大的值是 14，最后一个被选的是 [14,15]；
        //pre.size() == 3 的时候，接下来要选择 1 个元素， i 最大的值是 15，最后一个被选的是 [15]；
        //
        //多写几遍（发现 max(i) 是我们倒着写出来），我么就可以发现 max(i) 与 接下来要选择的元素貌似有一点关系，很容易知道：
        //max(i) + 接下来要选择的元素个数 - 1 = n，其中， 接下来要选择的元素个数 = k - pre.size()，整理得到：
        //
        //max(i) = n - (k - pre.size()) + 1
        //所以，我们的剪枝过程就是：把 i <= n 改成 i <= n - (k - pre.size()) + 1
        //
        for ( int i = index; i <= n - (k-stack.size()) +1; i++ ){
            stack.add(i);
            helper(n,k,i+1,stack);
            stack.pop();
        }


    }
}
//leetcode submit region end(Prohibit modification and deletion)
