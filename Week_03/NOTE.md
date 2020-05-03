# 学习笔记

## 第七节

**递归：**  
在函数内部不断的调用自身，层层调用。  

**递归代码模板**  

Python 代码模板  
```
def recusion(level,param1,param2,...){
	# recursion terminator
	# 递归终止条件
	if level > MAX_VALUE：
		process_result
		return
		
	# process logic in current level
	# 递归处理过程
	process(level, data...)
	
	
	# drill down
	# 向下一层递归
	self.recursion(level + 1, p1, ...) 
	
	
	# reverse the current level status if needed
	# 清理当前层状态值

}

```  

Java 代码模板   
```
public void recur(int level, int param) { 
	// terminator 
	if (level > MAX_LEVEL) { 
	// process result 
		return; 
	}
	// process current logic 
	process(level, param); 
	// drill down 
	recur( level: level + 1, newParam); 
	// restore current status 
 
}

```  

**思维要点**  
- 不要进行人肉递归  
- 找最近重复子问题  
- 数学归纳法思维  

***


## 第八节  

**分治**本质就是递归，找最近重复性子问题。本质上就是找重复性及分解问题和最后组合每个子问题的结果。 
**分治代码模板：**   
同泛型递归最大区别就是，最后要将子结果merge到一个最终结果。   
```
def divide_conquer(problem,param1,param2,...)
	# recursion terminator
	if problem is None:
		print_result
		return
	# prepare data
	data = prepare_data(problem)
	subproblems = split_problem(problem,data)
	# conquer subproblems
	subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
	subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
	subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
	
	# process and generate the final result 
	result = process_result(subresult1, subresult2, subresult3, …)
	# revert the current level states

```

**回溯**本质是递归的思想，把所有可能的结果都计算出来，最后判断哪些结果是不合理的。可以通过在中间进行判断过滤掉错误的结果，减少
计算的次数。  


[牛顿迭代法](http://www.matrix67.com/blog/archives/361)
 

