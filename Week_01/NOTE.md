# 学习笔记

## 第一小节：
**1. 数组的原理与实现：**   
		数组是有内存连续的空间拼接而成的，当对数组进行随机访问的时候，可以通过具体下标进行访问，因此数组适合用于随机访问的场景,查询时间复杂度为O(1)。由于数组的内存分配是连续的，所以插入、删除数组中的数据就会比较麻烦，当删除数据时会进行数据的平移，效率会降低，复杂度为O(n)。当数组分配的空间不够的时候，会进行扩容，默认为原始数组的2倍，还会发生数组的copy。
**2. 链表的原理与实现：**  
		链表分配的内存空间是不连续的，不需要进行扩容，链表的每个节点都会存储数据与指针，指针用来记录下一个数据的内存地址，因此链表会消费更多的内存空间（空间换时间）。由于链表地址空间不连续，查找的时候需要从头开始遍历，因此查询的时间复杂度为Q(n)。由于链表有指针指向节点的内存地址，空间不是连续的，插入、删除操作不需要进行数据的平移，只需要更改指针的位置，所以时间复杂度为O(1)
**3.跳表的原理与实现：**  
		跳表是基于“有序”链表实现的，采用“升为思想+空间换时间”，在原有的链表上建立多级索引，从而降低查询的时间复杂度，从O(n) -> O(logn)。


## 时间复杂度：

|  操作   | 数组  |  链表   | 跳表  |
|  ----  | ----  | ----  | ----  |
| 插入  | O(n) | O(1) | O(logn) |
| 删除  | O(n) | O(1) | O(logn) |
| 查找  | O(1) | O(n) | O(logn) |

## 空间复杂度：

 | 数组  |  链表   | 跳表  |
 | ----  | ----  | ----  |
 | O(1) | O(1) | O(n) |


***
## 第二小节：

### 移动零题目解析：

		```
            // 方法一：双指针法
            // 1.遍历数组，指针j指向非0元素
            // 2.指针i依次增加，遇到0，i直接加1，遇到非0元素与指针j指向的元素进行交换，之后j进行加1操作
        
			
            int j = 0;
            for ( int i = 0; i < nums.length; i++ ) {
                if ( nums[i] != 0 ) {
                    nums[j] = nums[i];
                    if ( i != j ) {
                        nums[i] = 0;
                    }
                    j++;
                }
            }

            return;
		
			
           // 方法二：
           // 1.遍历数组，当遇到元素等于0时，记录0出现的次数 count
           // 2.若遇到非0元素时，判断 count 是否大于0；若大于0,将当前非0元素的指针向前移动 i-count个位置，再将当前
           //	位置设置为0
            

            int count = 0;

            for ( int i = 0; i<nums.length; i++ ) {
                
                if ( nums[i] == 0 ) {
                    count++;
                }

                else if ( count > 0 ) {
                    nums[i-count] = nums[i];
                    nums[i] = 0; 
                }
            }

            return;
		```
		
***			
## 第三小节：

### 乘最多水的容器:
	```
		// 数组 暴力法 枚举每种情况
		public int maxArea(int[] height) {
			int maxarea = 0;
			for ( int i = 0; i < height.length-1; i++ ) {
				for ( int j=i+1; j < height.length;j++ ) {
					maxarea = Math.max(maxarea, (j-i) * Math.min(height[i],height[j]) );
				}
			}

			return maxarea;
		}
		
		// 双指针法
		
		public int maxArea(int[] height) {
			int head = 0;
			int tail = height.length-1;
			int maxarea = 0;
			while ( head < tail ) {
				maxarea = height[head] <= height[tail]
					? Math.max( maxarea,( tail - head ) * height[head++] )
					: Math.max( maxarea,( tail - head ) * height[tail--] );
            
        }

        return maxarea;

		
		
		}
	```
	
### 爬楼梯问题：
	
```	
 	class Solution {
		public int climbStairs(int n) {
	
			int[] memo = new int[n+1];
			return climb_Stairs(0,n,memo);
		
	}
	
	
	// 记忆化递归  减少中间的重复计算 ； 判断结束条件，以及推到递归公式
	// 由于i是当前阶，起初i=0，i每次只能爬1阶或者两阶，所以推导出每次向上爬的情况为
	// f(i) = f(i+1) + f(i+2)
	// 当i=n 是，说明已经到达，返回1，当i>n时，说明超出了第n阶，返回0；
	
	public int climb_Stairs (int i, int n,int[] memo) {

		if ( i == n ) {
			return 1;
		}
		if ( i > n ) {
			return 0;
		}
		if ( memo[i] > 0 ) {
			return memo[i];
		}

		memo[i] = climb_Stairs(i+1,n,memo) + climb_Stairs(i+2,n,memo);
            return memo[i];

    }
}

		
		// 斐波那契函数  
		// 分解子问题，由于在第3阶,想要到达第三阶，只有两种情况 ① 从第二阶向上爬一阶 ②从第一阶向上爬两阶
		// 推导出 到达第n阶的方法有 f(n) = f(n-1)+f(n-2)
		// 1:1
		// 2:2
		// 3:...... 3  
		// 4:...... 3 + 2 = 5
		
		class Solution {
			public int climbStairs(int n) {
			
				if ( n <= 2 ) {
		 
					return n;
				}

				int one = 1;
				int two = 2;
				int three = 0;

				for ( int i=3; i <= n; i++ ) {
					three = one + two;
					one = two;
					two = three;
				}

				return three;
				
			}
		}
```

***
## 第四小节：

### 三数求和
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();

			if ( nums == null || nums.length < 3 ) {
				return result;
			}

			Arrays.sort(nums);

			for (int i = 0; i < nums.length-2 ; i++) {
				//先判断排序后第一个元素是否大于0，大于0直接返回
				if ( nums[i] > 0 ) {
					break;
				}
				//判断当i>0时，后一个元素是否与前一个元素相等，若相等，会有重复的结果，跳过重复元素
				if ( i > 0 && nums[i] == nums[i-1] ) {
					continue;
				}

				int head = i+1;
				int tail = nums.length - 1;
				int sum = 0;
				while ( head < tail ) {
					sum = nums[i] + nums[head] + nums[tail];

					//判断 sum的三种情况，大于0，小于0，等于0
					if ( sum == 0 ) {
						result.add(Arrays.asList(nums[i] , nums[head] , nums[tail]));
						while ( head < tail && nums[head] == nums[head+1] ) {
						   head++;
						}while ( head < tail && nums[tail] == nums[tail-1] ) {
						   tail--;
						}
						head++;
						tail--;

					} else if ( sum > 0 ) {
						tail--;
					} else {
						head++;
					}

				}

			}

        return result;

    }
}

### 环形链表

/**
 *  思路一：遍历数组，将遍历到的元素放到Set中，如果为环形链表，当再次遍历到
 *          头结点的时候，发现set中已经包含该元素，证明链表中有环
 *          由于开辟了Set集合，长度为数组的长度，空间复杂度为O(n)，
 *          遍历了整个数组，时间复杂度为O(n)
 *  思路二：双指针，定义两个指针，一个每次移动一个节点，另一个每次移动2个节点
 *          当链表中有环，快指针总会追上慢指针
 *          由于每次移动指针只开辟了指针大小的空间，因此空间复杂度为O(1)
 *          时间复杂度为O(n)
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 
 public class Solution {
    public boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while ( head != null ) {

            if ( set.contains(head) ) {
                return true;
            }
            
            set.add(head);
            head = head.next;

        }

        return false;
        
    }
}
 
public class Solution {
    public boolean hasCycle(ListNode head) {

        if ( head == null ) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while ( fast != null && fast.next != null ) {

            slow = slow.next;
            fast = fast.next.next;

            if ( slow == fast ) {
                return true;
            }

        }
        return false;
        
    }
}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		