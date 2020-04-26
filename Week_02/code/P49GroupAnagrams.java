//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

package cn.leetcode.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {


        // 方法一
//        List<List<String>> res = new ArrayList<>();
//        boolean[] used = new boolean[strs.length];
//
//        if ( strs == null || strs.length == 0 ) {
//            return res;
//        }
//
//        for (int i = 0; i < strs.length; i++) {
//
//            List<String> tmp = null;
//            if ( !used[i] ) {
//                tmp = new ArrayList<String>();
//                tmp.add(strs[i]);
//                // 两两比较字符串是否符合题意
//                for (int j = i+1; j < strs.length ; j++) {
//                    if ( !used[j] && equals( strs[i],strs[j] ) ) {
//                        used[j] = true;
//                        tmp.add(strs[j]);
//                    }
//                }
//            }
//            if ( tmp != null ) {
//                res.add(tmp);
//            }
//        }
//
//        return res;

        // 方法二

//        HashMap<String,List<String>> hash = new HashMap<>();
//
//        for (int i = 0; i < strs.length; i++) {
//
//            char[] tmpChars = strs[i].toCharArray();
//            Arrays.sort(tmpChars);
//
//            String key = String.valueOf(tmpChars);
//
//            if ( hash.containsKey(key) ){
//                hash.get(key).add(strs[i]);
//            } else {
//                List<String> tmp = new ArrayList<>();
//                tmp.add(strs[i]);
//                hash.put(key,tmp);
//            }
//
//        }
//
//        return new ArrayList<List<String>>(hash.values());

        // 方法三
        //算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，
        // 要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
        //例如 abc ，就对应 'a' - 'a'， 'b' - 'a'， 'c' - 'a'，即 0, 1, 2，
        // 也就是对应素数 2 3 5，然后相乘 2 * 3 * 5 = 30，就把 "abc" 映射到了 30。

//        HashMap<Long,List<String>> map = new HashMap<>();
//        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103 };
//
//        for (int i = 0; i < strs.length; i++) {
//            long key = 1;
//            for (int j = 0; j < strs[i].length(); j++) {
//                key *= prime[strs[i].charAt(j) - 'a'];
//            }
//            if ( map.containsKey(key) ) {
//                map.get(key).add(strs[i]);
//            }else {
//                List<String> list = new ArrayList<>();
//                list.add(strs[i]);
//                map.put(key,list);
//            }
//        }
//
//        return new ArrayList<List<String>>(map.values());


        // 方法四
        // 首先初始化 key = "0#0#0#0#0#"，数字分别代表 abcde 出现的次数，# 用来分割。
        //
        //这样的话，"abb" 就映射到了 "1#2#0#0#0"。
        //
        //"cdc" 就映射到了 "0#0#2#1#0"。
        //
        //"dcc" 就映射到了 "0#0#2#1#0"。


        HashMap<String,List<String>> hashMap = new HashMap<>();
        int[] num = null;
        for (int i = 0; i < strs.length; i++) {
            num = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                num[strs[i].charAt(j) - 'a']++;

            }

            String key = "";
            for (int j = 0; j < num.length; j++) {
                key = key + num[j] + "#";
            }

            if ( hashMap.containsKey(key) ) {
                hashMap.get(key).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                hashMap.put(key,list);
            }

        }

        return new ArrayList<List<String>>(hashMap.values());

    }

    private boolean equals(String str1,String str2) {

        if ( str1.length() != str2.length() ) {
            return false;
        }


        Map<Character,Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            if ( map.containsKey(str1.charAt(i)) ) {
                map.put(str1.charAt(i),map.get(str1.charAt(i))+1 );
            } else {
                map.put(str1.charAt(i),1);
            }

        }

        for ( int i = 0; i < str2.length(); i++ ) {
            if ( map.containsKey(str2.charAt(i)) ) {
                map.put( str2.charAt(i),map.get(str2.charAt(i))-1 );
            } else {
                return false;
            }
        }

        //判断每个字符的次数是不是 0 ，不是的话直接返回 false
        Set<Character> characters = map.keySet();
        for ( Character c: characters ) {
            if ( map.get(c) != 0 ) {
                return false;
            }
        }

        return true;

    }

}
//leetcode submit region end(Prohibit modification and deletion)
