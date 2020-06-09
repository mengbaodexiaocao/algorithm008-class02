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








 