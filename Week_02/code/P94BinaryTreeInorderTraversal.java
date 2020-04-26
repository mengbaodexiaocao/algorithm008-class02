//给定一个二叉树，返回它的中序 遍历。 
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
package cn.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {

        //递归求解
//        List<Integer> res = new ArrayList<>();
//        dfs(res,root);
//        return res;

        // 利用栈求解
//        List<Integer> res = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//
//        while ( stack.size() > 0 || root != null ) {
//
//            if ( root != null ) {
//                stack.push(root);
//                root = root.left;
//            } else {
//                TreeNode tmp = stack.pop();
//                res.add(tmp.val);
//                root = tmp.right;
//            }
//
//        }
//
//        return res;

        // 莫里斯遍历

        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;

        while ( root != null ){

            if ( root.left != null ) {
                pre = root.left;
                while ( pre.right != null ) {
                    pre = pre.right;
                }
                pre.right = root;
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            } else {
                res.add(root.val);
                root = root.right;
            }
        }

        return res;

    }

    private void dfs(List<Integer> res, TreeNode root) {
        if ( root == null ) {
            return;
        }
        dfs(res,root.left);
        res.add(root.val);
        dfs(res,root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
