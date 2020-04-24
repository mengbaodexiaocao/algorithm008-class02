# 学习笔记


## 第五节  
**哈希表**  
哈希表是根据关键码值（Key value）而直接进行访问的数据结构，用的是数组支持按照下标随机访问的特性，
通过哈希函数将关键码值映射的数组中的一个下标的位置，从而达到快速访问的效果，加快查询的效率。哈希函数
的实现可以有多种，可以是复杂的，也可以是简单的，简单的哈希函数容易造成 “**散列冲突**”（不同关键码值
对应的哈希码值是一样），哈希表解决的方法一般是采用链表发，将相同的哈希值放到链表中来解决冲突。
查找、删除的时间复杂度为O(1)，当散列冲突比较大时，链表的装载因子过小时，可能造成链表过长，导致时间复杂度
升级为O(n)。  
**映射**  
映射（Map）底层是（Key value）的形式，实现可以基于哈希表，也可以基于数来实现。  
**集合**  
集合（Set）依赖与Map，相当于只保留Map中的Key值，value设置一个默认值，底层也是基于哈希表实现的。

***

## 第六节

**树、二叉树、二叉搜索树**  
**树**是二维的数据结构，一般想要提高时间复杂度，就可以通过生维的思想来解决，一维的数据结构一个节点一般只包含一个子节点，
树的一个节点可以包含多个子节点，子节点下面又可以包含多个子节点。Linked List 是特殊化的 Tree，Tree 是特殊化的 Graph。  
树有三个比较相似的概念：**高度、深度、层**  


**节点的高度：** 节点到叶子节点的最长路径（边数）。  
**节点的深度：** 根节点到这个节点的边数。  
**节点的层数：** 节点的深度+1。  
**树的高度：** 根节点的高度。  

**树的代码实现：** 
```
java  
	public class TreeNode {   
		public int val;   
		public TreeNode left, right;   
		public TreeNode(int val) {   
			this.val = val;   
			this.left = null;   
			this.right = null;   
		}   
	}  

python  
	class TreeNode:   
		def __init__(self, val):   
			self.val = val   
			self.left, self.right = None, None  

```
**二叉树**每个节点最多有两个分支，分别是左子节点跟右子节点，二叉树又分为**普通二叉树、满二叉树、完全二叉树**。  

**普通二叉树：** 每个节点最多只有两个子节点，依次往下延伸。  
**满二叉树：** 叶子节点全部在最底层，除了叶子加点外，每个节点都有左右两个子节点。  
**完全二叉树：** 叶子节点在最底层，最后一层的叶子节点都靠左排列，并且出了最后一层，其它层的节点个数都要达到最大。  

**递归实现二叉树的遍历**  

```
**前序：**打印-左-右  

def preorder(self,root):
	if root:
		self.traverse_path.append(root.val)
		self.preorder(root.left)
		self.preorder(root.right)
		
**中序：**左-打印-右  
def inorder(self,root):
	if root:
		self.inorder(root.left)
		self.traverse_path.append(root.val)
		self.inorder(root.right)
		
**后续：**左-右-打印  
def postorder(self,root):
	if root:
		self.postorder(root.left)
		self.postorder(root.right)
		self.traverse_path.append(root.val)
```		

**二叉搜索树**也称二叉搜索树、有序二叉树（Ordered Binary Tree）、排序二叉树（Sorted Binary Tree），
是指一棵空树或者具有下列性质的二叉树：  
1. 左子树上所有结点的值均小于它的根结点的值；  
2. 右子树上所有结点的值均大于它的根结点的值；  
3. 以此类推：左、右子树也分别为二叉查找树。（这就是 重复性！）   
中序遍历：就是这个数的升序遍历。  




**堆、二叉堆、图**  

**堆**可以快速的找到一个堆中的最大值或者最小值的数据结构。分为大顶堆与小顶堆。  
时间复杂度：  
find-max/find-min:O(1)  
delete-max/delete-min:O(logn)  
因为删除数组之后，堆中的数据要重新进行堆化操作。删除数据的操作是将最后一个元素移至堆顶，堆的长度-1，从顶向下堆化。  
insert：O(logn) or O(1)  
插入操作是在堆尾插入元素，自地向上进行堆化。  

**二叉堆**通过完全二叉树来实现，二叉堆满足以下性质：  
性质一：是一颗完全二叉树。  
性质二：树种的任意节点的值总是>=其子节点的值。  

二叉堆一般通过“数组”实现，假设第一个元素在数组中的索引为0，那么父节点与子节点的位置关系如下：  
索引为i的左孩子的索引：(2*i+1);  
索引为i的右孩子的索引：(2*i+2);  
索引为i的父节点的索引：floor((i-1)/2);














