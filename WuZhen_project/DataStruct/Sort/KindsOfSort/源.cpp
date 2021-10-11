#include <iostream>
using namespace std;
class LinkList {

};
int main() {
	//函数声明
	void insertsort(int a[], int n);
	void binary_insert_sort(int a[], int n);
	void shell_sort(int a[], int n);
	//变量定义
	int a[] = { 0,1,3,2,4,5,6,7,9,8,0 };

	//函数测试
	//insertsort(a, 10);
	//binary_insert_sort(a, 10);
	shell_sort(a, 10);
	for (int i = 1; i < 11; i++) {
		cout << a[i] << " " << endl;
	}
}
//1.插入排序
/*
1.1直接插入排序 
	算法思想：遍历数组，依次将当前元素插入到前面的有序表中 
	插入方法为边比较边移动，比较的过程中腾出位置
*/
void insertsort(int a[],int n) { //a[0]为哨兵，不存放数据
	for (int i = 2; i <= n; i++) { //将a[2]-a[n]插入到前面的有序序列
		if (a[i] < a[i - 1]) {	//逆序，需要进行交换
			a[0] = a[i];//将当前元素复制到哨兵处
			int j;
			for ( j = i - 1; a[0] < a[j]; j--) {
				a[j + 1] = a[j]; //后移
			}
			a[j + 1] = a[0];
		}
	}
}
/* 
1.2折半插入排序
先折半查找找到待插入元素的位置 
再统一的移动位置
*/
void binary_insert_sort(int a[], int n) {
	int i, low, high,mid;
	for (i = 2; i <= n; i++) {
		if (a[i] < a[i - 1]) {//逆序 需要进行换位
			a[0] = a[i];
			low = 1; high = i - 1;
			while (low <= high) {//折半查找寻找位置
				mid = (low + high) / 2;
				if (a[0] < a[mid]) high = mid - 1;//往左半区域寻找
				else low = mid + 1;//关键值大于等于中值元素 往右半区域寻找 保证算法的稳定性
			}//while
			//查找到待插入位置为low
			for (int j = i; j > low; j--) {
				a[j] = a[j - 1];
			}
			a[low] = a[0];
		}//if
	}
}
/*
1.3 希尔排序 
算法思想 选择一个增量，将相隔一个增量的若干记录组成一个子表，对各个子表进行直接插入排序
逐步缩小增量，最终对全体记录进行一次插入排序
*/
void shell_sort(int a[], int n) {
	for (int d = n / 2; d >= 1; d = d / 2) { //d为增量 初始值为总长度的一半，之后逐渐减半至1
		for (int i = d + 1; i <= n; i++) {//从第一个无序的元素开始插入排序（假设每组第一个元素为有序状态）
			if (a[i] < a[i - d]) {//逆序 需要交换
				a[0] = a[i];
				int j;
				for (j = i - d; j > 0 && a[0] < a[j]; j = j - d) {//查找待插入位置
					a[j + d] = a[j];
				}//for
				a[j+d] = a[0];
			}//if 完成一个元素的排序
		}//for 完成某个增量下各个分组的排序
	}
}
