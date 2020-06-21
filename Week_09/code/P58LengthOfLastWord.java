//给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。 
//
// 如果不存在最后一个单词，请返回 0 。 
//
// 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。 
//
// 
//
// 示例: 
//
// 输入: "Hello World"
//输出: 5
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
package cn.leetcode.leetcode.editor.cn;
class Solution58 {
    public int lengthOfLastWord(String s) {

        // 从后往前遍历，先去除空格，然后遍历最后一个单词，当再次遇到空格，说明最后一个单词遍历结束
        int len = s.length() - 1;
        while (len >= 0 && s.charAt(len) == ' ') len--;
        if ( len < 0 ) return 0;
        int start = len;
        while ( start >= 0 && s.charAt(start) != ' '  ) start--;
        return len - start;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
