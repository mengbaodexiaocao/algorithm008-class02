//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
package cn.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {

        // 前序： 根-左-右
//        List<Integer> res = new ArrayList<>();
//        dfs(res,root);
//        return res;


        // 利用栈
        // 头节点先入栈，打印，弹出，后续应该是先输出左节点，在输出右节点，因此右节点先
        // 入栈，左节点在入栈。
        List<Integer> res = new ArrayList<>();
        if ( root == null ) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while ( !stack.isEmpty() ) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if ( pop.right != null ) {
                stack.push(pop.right);
            }
            if ( pop.left != null ) {
                stack.push(pop.left);
            }
        }

        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {

        if ( root != null ) {
            res.add(root.val);
            dfs(res,root.left);
            dfs(res,root.right);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
