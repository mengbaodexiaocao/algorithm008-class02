# 学习笔记

## 字典树与并查集  
**字典树**，即 **Trie** 树，又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），
所以经常被搜索引擎系统用于文本词频统计。   
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。  

**基本性质：**  
1、结点本身不存完整单词  
2、从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串  
3、每个结点的所有子结点路径代表的字符都不相同  


**核心思想**  

Trie 树的核心思想是空间换时间  
利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的  


trie代码模板：  
**java**    

```
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}

```


**并查集**适用于组团、配对等问题。  
基本操作：  
- makeSet(s)：建立一个新的并查集，其中包含 s 个单元素集合  
- unionSet(x, y)：把元素 x 和元素 y 所在的集合合并，要求 x 和 y 所在的集合不相交，如果相交则不合并  
- find(x)：找到元素 x 所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了  

***

## 高级搜索  

**剪枝**剪掉不必要的分支，加快搜索效率。当发现当前分支不是最优解的时候将不再进行该分支的计算。  
**双向BFS**代码模板：  

```
public void biBfs() {
    Set<T> left = new LinkedList<>();
    Set<T> right = new LinkedList<>();
    Set<T> remain = new HashSet<>(list);
    left.add(start);
    right.add(end);
    
    // set some flag
    // example: count = 0;
    
    while (!left.isEmpty() && !right.isEmpty()) {
        if (left.size() > right.size) {
            // left -> right -> left -> right
            swap(left, right);
        }
        remain.removeAll(left);
        Set<T> next = new LinkedList<>();
        for (Node node : left) {
            // do something
            for (Node newNode : generateNextLevel()) {
                // node must in the path given by question
                if (remain.contains(newNode)) {
                    if (newNode == end) {
                        return;
                    }
                    next.add(newNode);
                }
            }
        }
        left = next;
        // do something: 
        // example: count++;
    }
}
```
**启发式搜索**通过优先级队列进行优先级搜索，“智能”的进行搜索，每次都能取出最优的路径。  
估价函数：它用来评价哪些结点最有希望的是一个我们要找的结点，h(n) 会返回一个非负实数,也可以认为是从结点n的目标结点路径的估计成本。    
启发式函数是一种告知搜索方向的方法。它提供了一种明智的方法来猜测哪个邻居结点会导向一个目标。    

***

## 红黑树和AVL树


复习二叉搜索树的遍历：  

1. 前序(Pre-order)：根-左-右  
2. 中序(In-order)：左-根-右  
3. 后序(Post-order)：左-右-根  

**重点：**二叉搜索树的中序遍历是升序排序的  

**AVL树**的特性：  
- Balance Factor（平衡因子）：是它的左子树的高度减去它的右子树的高度（有时相反），balance factor = {-1, 0, 1}。  
- 通过旋转操作来进行平衡（四种）  

旋转操作：  
1. 左旋  -->	右右子树 —> 左旋  
2. 右旋  -->	左左子树 —> 右旋  
3. 左右旋  -->	左右子树 —> 左右旋    
4. 右左旋  -->	左右子树 —> 左右旋    


**红黑树**是一种**近似平衡的二叉搜索树（Binary Search Tree）**，它能够确保任何一个结点的左右子树的**高度差小于两倍**。
具体来说，红黑树是满足如下条件的二叉搜索树：  

- 每个结点要么是红色，要么是黑色  
- 根节点是黑色  
- 每个叶节点（NIL节点，空节点）是黑色的  
- 不能有相邻接的两个红色节点   
- 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点  














