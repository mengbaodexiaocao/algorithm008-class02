//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法

package cn.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution17 {

    List<String> res = new ArrayList<>();
    String[] letter_map  = {"","!@#","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {

        if ( digits == null || digits.length() == 0) {
            return res;
        }

        // 1、递归，回溯
//        dfs("",digits,0);
//        return res;

        // 2、队列
        res.add("");

        for (int i = 0; i < digits.length(); i++) {

            String letter = letter_map[digits.charAt(i) - '0'];

            int size = res.size();
            for (int j = 0; j < size; j++) {
                String queueString = res.remove(0);
                for (int k = 0; k < letter.length(); k++) {
                    res.add(queueString+letter.charAt(k));
                }
            }

        }
        return res;

    }

    private void dfs(String s, String digits, int index) {

        // 终止条件
        if ( index == digits.length() ) {
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        int pos = c - '0';
        String letter = letter_map[pos];

        for (int i = 0; i < letter.length(); i++) {
            dfs(s+letter.charAt(i),digits,index+1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
