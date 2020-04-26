//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
package cn.leetcode.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution589 {

    List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {

        // 递归
//        helper(root);
//        return res;


        // 迭代  根-左-右
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Node> stack = new LinkedList<>();

        if ( root == null ) {
            return result;
        }
        stack.add(root);

        while ( stack.size() > 0 ) {

            Node poll = stack.pollLast();  // 此处必须使用pollLast，若使用poll可能导致出栈的不随机性
            result.add(poll.val);

            for (int i = 0; i < poll.children.size(); i++) {
                // 右边的子节点先入栈，左子节点最后入栈，实现 root - 左 - 右 的顺序
                stack.add(poll.children.get( (  poll.children.size() - 1 - i ) ));
            }
        }
        return result;
    }

    private void helper(Node root) {
        if ( root == null ){
            return;
        }
        res.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            helper(root.children.get(i));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
