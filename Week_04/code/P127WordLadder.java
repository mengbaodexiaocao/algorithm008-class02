//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索

package cn.leetcode.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution127 {

    private int L;
    private HashMap<String,List<String>> allComboDict;

    public Solution127() {
        this.L = 0;
        this.allComboDict = new HashMap<>();
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        /*if (!wordList.contains(endWord)) return 0;
        int len = beginWord.length();
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        wordList.forEach(curWord -> {

            for (int i = 0; i < len; i++) {
                String comboWord = curWord.substring(0,i) + "*" + curWord.substring(i+1,len);
                ArrayList<String> comboWordList = allComboDict.getOrDefault(comboWord, new ArrayList<>());
                comboWordList.add(curWord);
                allComboDict.put(comboWord,comboWordList);
            }

        });

        Queue<Pair<String,Integer>> queue = new LinkedList<>();
        HashMap<String,Boolean> hasVisitedList = new HashMap<String, Boolean>();
        queue.add(new Pair<>(beginWord,1));
        hasVisitedList.put(beginWord,true);
        while (!queue.isEmpty()) {

            Pair<String, Integer> node = queue.remove();
            String curWord = node.getKey();
            Integer level = node.getValue();

            for (int i = 0; i < len; i++) {
                String curComboWord = curWord.substring(0,i)+"*"+curWord.substring(i+1,len);
                ArrayList<String> curComboWordList = allComboDict.getOrDefault(curComboWord, new ArrayList<>());
                for (String word:curComboWordList) {

                    if (word.equals(endWord)) {
                        return level+1;
                    }
                    if ( !hasVisitedList.containsKey(word) ){
                        queue.add(new Pair<>(word,level+1));
                        hasVisitedList.put(word,true);
                    }

                }
            }

        }

        return 0;*/

        // 方法 2：双向广度优先搜索
        if (!wordList.contains(endWord)) return 0;
        L = beginWord.length();

        // 预处理，将wordlist中的字符串转换为通配字符串，存入字典“hashMap”
        wordList.forEach(word->{
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0,i)+"*"+word.substring(i+1,L);
                List<String> comboList = allComboDict.getOrDefault(newWord, new ArrayList<>());
                comboList.add(word);
                allComboDict.put(newWord,comboList);
            }
        });

        Queue<Pair<String,Integer>> beginQueue = new LinkedList<>();
        Queue<Pair<String,Integer>> endQueue = new LinkedList<>();
        HashMap<String,Integer> beginVisited = new HashMap<>();
        HashMap<String,Integer> endVisited = new HashMap<>();
        beginQueue.add(new Pair<>(beginWord,1));
        endQueue.add(new Pair<>(endWord,1));
        beginVisited.put(beginWord,1);
        endVisited.put(endWord,1);

        while ( !beginQueue.isEmpty() && !endQueue.isEmpty() ) {

            int res = visitWordNode(beginQueue,beginVisited,endVisited);
            if (res > -1) return res;
            res = visitWordNode(endQueue,endVisited,beginVisited);
            if (res > -1) return res;
        }

        return 0;
    }

    private int visitWordNode(Queue<Pair<String, Integer>> queue,
                              HashMap<String, Integer> visited,
                              HashMap<String, Integer> otherVisited) {

        Pair<String, Integer> node = queue.remove();
        String curWord = node.getKey();
        Integer level = node.getValue();

        for (int i = 0; i < L; i++) {

            String newWord = curWord.substring(0,i)+"*"+curWord.substring(i+1,L);
            List<String> curWordList = allComboDict.getOrDefault(newWord, new ArrayList<>());

            for (String str:curWordList) {
                if (otherVisited.containsKey(str)){
                    return otherVisited.get(str)+level;
                }

                if (!visited.containsKey(str)){
                    queue.add(new Pair<>(str,level+1));
                    visited.put(str,level+1);
                }
            }
        }

        return -1;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
