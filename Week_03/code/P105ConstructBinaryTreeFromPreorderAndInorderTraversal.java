//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


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

import java.util.HashMap;

class Solution105 {

    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder = preorder;
        this.inorder = inorder;
        int index = 0;
        // 将中序遍历的结果以及对应的数组下标放到map中，
        // 以便通过先序遍历的根节点找到中序遍历中根节点的下标位置
        for (int num:inorder) {
            idx_map.put(num,index++);
        }
        return helper(0,inorder.length);

    }

    private TreeNode helper(int iStart, int iEnd) {

        if ( iStart == iEnd ) return null;

        int rootVal = preorder[pre_idx];
        TreeNode root = new TreeNode(rootVal);

        Integer iIndex = idx_map.get(rootVal);

        pre_idx++;

        root.left = helper(iStart,iIndex);
        root.right = helper(iIndex+1,iEnd);

        return root;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
