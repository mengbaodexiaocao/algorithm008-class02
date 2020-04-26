//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表

package cn.leetcode.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {

//        List<Integer> res = new ArrayList();
//        HashMap<Integer,Integer> map = new HashMap<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
//        }
//
//
//        List<Integer>[] lists = new List[nums.length+1];
//
//        for ( int key :  map.keySet()) {
//
//            int count = map.get(key);
//            if (lists[count] == null) {
//                lists[count] = new ArrayList<>();
//            }
//            lists[count].add(key);
//
//        }
//
//        for (int i = lists.length - 1; i >= 0 && res.size() < k; i--) {
//            if ( lists[i] == null ) continue;
//            res.addAll(lists[i]);
//        }
//

//        return result;


        // 利用堆实现
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (int key :map.keySet()) {
            priorityQueue.add(key);
            if ( priorityQueue.size() > k ) {
                priorityQueue.poll();
            }
        }

        List<Integer> resList = new LinkedList<>();
        while ( !priorityQueue.isEmpty() ) {
            resList.add(priorityQueue.poll());
        }
        Collections.reverse(resList);
        int[] res = new int[resList.size()];

        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
