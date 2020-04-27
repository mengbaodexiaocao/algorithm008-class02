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


## 第八节  


