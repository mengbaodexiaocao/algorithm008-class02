//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法

package cn.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    ArrayList[] cache = new ArrayList[100];
    public List<String> generateParenthesis(int n) {

//        List<String> result = new ArrayList<>();
//        _generate(result,0,0,n,"" );
//        return result;

        // 暴力法
//        List<String> result = new ArrayList<>();
//        char[] chars = new char[ 2 * n ];
//        _generate2(chars,0,result);
//        return result;

        // 回溯
//        List<String> result = new ArrayList<>();
//        _generate3(result,new StringBuilder(),0,0,n);
//        return result;

        // 方法三：按括号序列的长度递归
        return generate(n);

    }

    private List<String> generate(int n) {

        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generate(c))
                    for (String right: generate(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        cache[n] = ans;
        return ans;

    }

    private void _generate3(List<String> result,
                            StringBuilder sb,
                            int left,
                            int right,
                            int n) {

        if ( sb.length() == 2 * n ) {
            result.add(sb.toString());
            return;
        }

        if ( left < n ) {
            sb.append("(");
            _generate3(result,sb,left+1,right,n);
            sb.deleteCharAt(sb.length()-1);
        }
        if ( right < left ) {
            sb.append(")");
            _generate3(result,sb,left,right+1,n);
            sb.deleteCharAt(sb.length()-1);
        }

    }

    private void _generate2(char[] chars, int pos, List<String> result) {

        if ( pos == chars.length ) {

            if ( valid(chars) ) {
                System.out.println(String.valueOf(chars));
                result.add(String.valueOf(chars));
            }

            return;
        }
        chars[pos] = '(';
        _generate2(chars,pos+1,result);
        chars[pos] = ')';
        _generate2(chars,pos+1,result);



    }

    private boolean valid(char[] chars) {

        int balance = 0;

        for (char c:chars) {

            if ( c == '(' ) balance++;
            else balance--;

            if ( balance < 0 ) return false;
        }
        return (balance == 0);

    }

    private void _generate(List result,int left, int right, int n, String s) {

        if ( left == n && right == n ) {
            result.add(s);
            return;
        }

        String leftString = s + "(";
        String rightString = s + ")";

        if ( left < n ) _generate(result,left+1,right,n,leftString);
        if ( right < left ) _generate(result,left,right+1,n,rightString);


    }

    public static void main(String[] args) {
        new Solution22().generateParenthesis(3);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
