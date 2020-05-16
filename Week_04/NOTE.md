# 学习笔记

## 第9节  

**深度优先搜索**  
深度优先搜索（Depth-First-Search），简称DFS，从跟节点开始，顺着一个节点一直走到底，然后再依次返回，访问后续节点。  
DFS代码模板：  
递归写法：  
```
visited = set()

def dfs(node,visited):
	if node in visited:
		return;
	visited.add(node)
	
	for next_node in node:
		if next_node not in visited:
			dfs(next_node,visited)

```

非递归写法：  

```
def DFS(self,tree):
	
	if tree.node is none:
		return []
	visited,stack = [],[tree.root]
	
	while stack:
		node = stack.pop()
		visited.add(node)
		
		process(node)
		nodes = generate_related_nodes(node)
		stack.push(nodes)
```  

**广度优先搜索**  


广度优先搜索（Breadth-First-Search），简称BFS，直观的讲，其实就是一种“地毯式”层层推进
的搜索策略，即先找离节点最近的，然后是次进的，依次往外搜索。

BFS代码模板：  

```
def BFS(graph,start,end):

	visited = set()
	queue = []
	queue.append([start])
	
	while queue:
		node = queue.pop()
		visited.add(node)
		
		process(node)
		nodes = generate_related_nodes(node)
		queue.push(nodes)
```

***  


## 第10节  

**贪心算法**  
- 贪心算法是一种在每一步选择中都采取在当前状态下的最好或最优的选择，从而希望导致结果是全局最优的算法。  
- 贪心法可以解决一些最优化问题，如：求图中的最小生成树、求哈夫曼编码等。然而对于工程和生活中的问题，贪心法一般不能得到我们所要求的答案。  
- 应用场景：问题能够分解成子问题，子问题的最优解能够递推到最终问题的最优解。这种子问题最优解称为最优子结构。  


**贪心与回溯的区别**  

- 贪心算法对问题的每个子问题都作出解决，不能进行回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。  
- 动态规划 = 贪心 + 回溯  


***  


## 第11节  

**二分查找**   
- 目标函数单调性（单调递增或单调递减）  
- 存在上下界  
- 能够通过索引访问   


代码模板：  
```
left,right = 0 , len(arry-1)
while left <= right:

	mid = (right-left)/2+left
	if arry[mid] == target: 
		return result or break;
	elif arry[mid] < target:
		left = mid + 1
	else:
		right = mid - 1
 

```  

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方：  
思路：二分查找，left = 0；right = nums.length - 1; 得到中间值mid，比较mid与左边界left的值  
	若mid > left,说明左边界有序，去右边界中查找无序的下标，若此时正好mid右边的第一个小于下标为mid的值，则返回坐标为mid+1的值；
	否则，接着二分查找。代码如下：  
```
public class findDisorderly {

    private static int find(int[] nums){

        int res = -1;
        if (nums.length == 0) return res;
        int start = 0;
        int end = nums.length-1;

        while (start < end) {
            int mid = (end-start) / 2 + start;     
            if ( nums[mid] > nums[start] ) { // 说明左半部分有序
                start = mid + 1;
                if ( nums[start] < nums[mid] ) return start;
            } else {
                end = mid - 1;
            }

            res = start+1;
        }

        return res == nums.length ? -1 : res;

    }

    public static void main(String[] args) {
        int a = findDisorderly.find(new int[]{7,8,9,1,2,3,4});
        int d = findDisorderly.find(new int[]{7,8,9,10,1,2,3,});
        int b = findDisorderly.find(new int[]{7,8,9,10,1,2,3,4});
        int c = findDisorderly.find(new int[]{7,8,9,10,11,12,13,1,2,3,4});
		int e = findDisorderly.find(new int[]{7,8,9,10,11,12,13});

        System.out.println(a);
        System.out.println(d);


        System.out.println(b);
        System.out.println(c);
		System.out.println(e);

    }

}
```























