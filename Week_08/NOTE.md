# 学习笔记

## 位运算

**位运算符：**  

|  **含义**   | **运算符**  |  **示例**   | 
|  ----  | ----  | ----  |
| 左移  | << | 0011 => 0110 | 
| 删除  | >> | 0011 => 0001| 
| 按位或  | \| | 0011 \| 1011 => 1011 | 
| 按位与 | & | 0011 & 1011 => 0011 | 
| 按位取反  | ~ | 0011 => 1100 | 
| 按位异或 | ^ | 0011 ^ 1011 => 1000 |


**XOR -异或：**  
异或：相同为 0，不同为 1。也可用“不进位加法”来理解。  
x ^ 0 = x  
x ^ 1s = ~x // 注意 1s = ~0  
x ^ (~x) = 1s  
x ^ x = 0  

**位运算要点**     

- 将 x 最右边的 n 位清零：x& (~0 << n)  
- 获取 x 的第 n 位值（0 或者 1）： (x >> n) & 1  
- 获取 x 的第 n 位的幂值：x& (1 <<n)  
- 仅将第 n 位置为 1：x | (1 << n)  
- 仅将第 n 位置为 0：x & (~ (1 << n))    
- 将 x 最高位至第 n 位（含）清零：x& ((1 << n) -1)  
- 将第 n 位至第 0 位（含）清零：x& (~ ((1 << (n + 1)) -1))  
- X = X & (X-1) 清零最低位的 1  
- X & -X => 得到最低位的 1  
- X & ~X => 0  

***

## 布隆过滤器、LRU Cache  

**Bloom Filter**一个很长的**二进制**向量和一系列**随机映射函数**。布隆过滤器可以用于检索一个元素是否在
一个集合中。  
优点：空间效率和查询时间远远超过一般的算法。  
缺点：有一定的误识别率和删除困难。  

java实现：  
```
package com.github.lovasoa.bloomfilter;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

public class BloomFilter implements Cloneable {
  private BitSet hashes;
  private RandomInRange prng;
  private int k; // Number of hash functions
  private static final double LN2 = 0.6931471805599453; // ln(2)

  /**
   * Create a new bloom filter.
   * @param n Expected number of elements
   * @param m Desired size of the container in bits
   **/
  public BloomFilter(int n, int m) {
    k = (int) Math.round(LN2 * m / n);
    if (k <= 0) k = 1;
    this.hashes = new BitSet(m);
    this.prng = new RandomInRange(m, k);
  }

  /**
   * Create a bloom filter of 1Mib.
   * @param n Expected number of elements
   **/
  public BloomFilter(int n) {
    this(n, 1024*1024*8);
  }

  /**
  * Add an element to the container
  **/
  public void add(Object o) {
    prng.init(o);
    for (RandomInRange r : prng) hashes.set(r.value);
  }
  /** 
  * If the element is in the container, returns true.
  * If the element is not in the container, returns true with a probability ≈ e^(-ln(2)² * m/n), otherwise false.
  * So, when m is large enough, the return value can be interpreted as:
  *    - true  : the element is probably in the container
  *    - false : the element is definitely not in the container
  **/
  public boolean contains(Object o) {
    prng.init(o);
    for (RandomInRange r : prng)
      if (!hashes.get(r.value))
        return false;
    return true;
  }

  /**
   * Removes all of the elements from this filter.
   **/
  public void clear() {
    hashes.clear();
  }

  /**
   * Create a copy of the current filter
   **/
  public BloomFilter clone() throws CloneNotSupportedException {
    return (BloomFilter) super.clone();
  }

  /**
   * Generate a unique hash representing the filter
   **/
  public int hashCode() {
    return hashes.hashCode() ^ k;
  }

  /**
   * Test if the filters have equal bitsets.
   * WARNING: two filters may contain the same elements, but not be equal
   * (if the filters have different size for example).
   */
  public boolean equals(BloomFilter other) {
    return this.hashes.equals(other.hashes) && this.k == other.k;
  }

  /**
   * Merge another bloom filter into the current one.
   * After this operation, the current bloom filter contains all elements in
   * other.
   **/
  public void merge(BloomFilter other) {
    if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
      throw new IllegalArgumentException("Incompatible bloom filters");
    }
    this.hashes.or(other.hashes);
  }

  private class RandomInRange
      implements Iterable<RandomInRange>, Iterator<RandomInRange> {

    private Random prng;
    private int max; // Maximum value returned + 1
    private int count; // Number of random elements to generate
    private int i = 0; // Number of elements generated
    public int value; // The current value

    RandomInRange(int maximum, int k) {
      max = maximum;
      count = k;
      prng = new Random();
    }
    public void init(Object o) {
      prng.setSeed(o.hashCode());
    }
    public Iterator<RandomInRange> iterator() {
      i = 0;
      return this;
    }
    public RandomInRange next() {
      i++;
      value = prng.nextInt() % max;
      if (value<0) value = -value;
      return this;
    }
    public boolean hasNext() {
      return i < count;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}

```



**LRU Cache**  
- 两个要素：大小（相当于人脑能够记住多少东西）、替换策略（Last Recently Use，最近最少使用）  
- Hashtable+Double LinkedList  
- O(1) 查询；O(1)修改、更新  

LRU Cache python  
```
class LRUCache(object): 

	def __init__(self, capacity): 
		self.dic = collections.OrderedDict() 
		self.remain = capacity

	def get(self, key): 
		if key not in self.dic: 
			return -1 
		v = self.dic.pop(key) 
		self.dic[key] = v   # key as the newest one 
		return v 

	def put(self, key, value): 
		if key in self.dic: 
			self.dic.pop(key) 
		else: 
			if self.remain > 0: 
				self.remain -= 1 
			else:   # self.dic is full
				self.dic.popitem(last=False) 
		self.dic[key] = value
```

***  

## 排序算法

```
// 插入排序
    public static List<Integer> insertSort(int[] arry) {

        int preIndex,cur;

        for (int i = 1; i < arry.length; i++) {

            preIndex = i - 1;
            cur = arry[i];

            while ( preIndex >= 0 && arry[preIndex] > cur ){
                // 如果当前值大于前一个元素，将前一个元素后移一位，前置下标减一继，继续比较
                // 之前的数据
                arry[preIndex+1] = arry[preIndex];
                preIndex--;
            }
            arry[preIndex+1] = cur;

        }

        List<Integer> list = Ints.asList(arry);
        return list;

    }

```  
```
    // 冒泡
    public static List<Integer> bubbleSort(int[] arry){

        int len = arry.length;

        for (int i = 0;i < len - 1; i++){
            for(int j = i+1; j < len; j++){
                if ( arry[i] > arry[j] ){
                    int tmp = arry[j];
                    arry[j] = arry[i];
                    arry[i] = tmp;
                }

            }

        }

        List<Integer> list = Ints.asList(arry);
        return list;


    }
```

```

    // 选择
    public static List<Integer> selectSort(int[] arry){

        int len = arry.length - 1;
        int minIndex;

        for (int i = 0;i < len - 1; i++){
            minIndex = i;
            for(int j = i+1; j < len; j++){
                if ( arry[j] < arry[minIndex] ){
                    minIndex = j;
                }

            }
            int tmp = arry[i];
            arry[i] = arry[minIndex];
            arry[minIndex] = tmp;

        }

        List<Integer> list = Ints.asList(arry);
        return list;

    }
```

```

    //快速排序
    public static void quickSort(int[] array) {

        int len;
        if(array == null || (len = array.length) == 0 || len == 1) {
            return ;
        }

        sort(array, 0, len - 1);

    }
    public static void sort(int[] array, int left, int right) {

        if ( left > right ) {
            return;
        }
        int base = array[left];
        int i = left,j = right;

        while ( i != j ) {

            while (array[j] >= base && i < j) {
                j--;
            }
            while( array[i] <= base && i < j) {
                i++;
            }

            if ( i < j ) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        array[left] = array[i];
        array[i] = base;

        sort(array,left,i-1);
        sort(array,i+1,right);

    }
```


```
    //归并排序
    public static List<Integer> mergeSort(int [] arry,int letf,int right){
        if (letf < right){
            int mid = ( letf + right ) / 2;
            mergeSort(arry,letf,mid);
            mergeSort(arry,mid+1,right);
            merge(arry,letf,mid,right);
        }

        return Ints.asList(arry);

    }

    private static void merge(int[] arry, int left, int mid, int right) {

        int []tmp=new int[arry.length];//辅助数组
        int p1=left,p2=mid+1,k=left;

        while(p1<=mid && p2<=right){
            if(arry[p1]<=arry[p2])
                tmp[k++]=arry[p1++];
            else
                tmp[k++]=arry[p2++];
        }

        while(p1<=mid) tmp[k++]=arry[p1++];
        while(p2<=right) tmp[k++]=arry[p2++];

        //复制回原素组
        for (int i = left; i <=right; i++)
            arry[i]=tmp[i];



    }

```  























 