#include <iostream>
using namespace std;
class LinkList {

};
int main() {
	void insertsort(int a[], int n);
	int a[] = { 0,1,3,2,4,5,6,7,9,8,0 };
	short n = 0xA1B6;
	printf("以十六进制输入的数是：%u(无符号),%d(有符号)\n", n,n);
	unsigned short m = n;
	printf("m的十六进制为：%x,%x,%d", m,n,n);
	m = m >> 1;

	printf(" m右移后为%x", m);
	insertsort(a, 10);
	/*for (int i = 1; i < 11; i++) {
		cout << a[i] << " " << endl;
	}*/
}
//1.插入排序
//1.1直接插入排序
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
