//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索


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
import java.util.Queue;

class Solution429 {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {

//        List<List<Integer>> result = new ArrayList<>();
//        Queue<Node> queue = new LinkedList<>();
//        if ( root == null ) return result;
//        queue.add(root);
//
//        while ( queue.size() > 0 ){
//            List<Integer> level = new ArrayList<>();
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                Node poll = queue.poll();
//                level.add(poll.val);
//                queue.addAll(poll.children);
//            }
//            result.add(level);
//        }
//        return result;


        // 方法二
//        List<List<Integer>> result = new ArrayList<>();
//        if ( root == null ) return result;
//        List<Node> previousLayer = new ArrayList<>();
//        previousLayer.add(root);
//
//        while ( !previousLayer.isEmpty() ) {
//
//            List<Integer> previousVal = new ArrayList<>();
//            List<Node> curLayer = new ArrayList<>();
//
//            for (int i = 0; i < previousLayer.size(); i++) {
//                previousVal.add(previousLayer.get(i).val);
//                curLayer.addAll(previousLayer.get(i).children);
//            }
//
//            result.add(previousVal);
//            previousLayer = curLayer;
//
//        }
//        return result;


        // 方法三
        if ( root == null ) { return result;}
        traverseNode(root,0);

        return result;


    }

    private void traverseNode(Node node, int level) {

        if ( node == null ) return;

        if ( result.size() <= level ) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);

        for (int i = 0; i < node.children.size(); i++) {
            traverseNode(node.children.get(i),level+1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
