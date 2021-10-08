#通过函数参数传入斐波那切数列长度，使用 return 语句返回计算结果
#定义函数
def fibs(n): #n 代码斐波那切数列的长度
    #定义斐波那切数列的初识列表 
    result=[0,1]
	#通过循环计算，将结果添加到列表中 
    for x in range(n-2): 
        result.append(result[-1]+result[-2]) 
    #将计算结果返回 
    return result 
#调用函数 
re=fibs(10) 
print('斐波那切数列:',re)