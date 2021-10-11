#include <iostream>
using namespace std;
class LinkList {

};
int main() {
	//��������
	void insertsort(int a[], int n);
	void binary_insert_sort(int a[], int n);
	void shell_sort(int a[], int n);
	//��������
	int a[] = { 0,1,3,2,4,5,6,7,9,8,0 };

	//��������
	//insertsort(a, 10);
	//binary_insert_sort(a, 10);
	shell_sort(a, 10);
	for (int i = 1; i < 11; i++) {
		cout << a[i] << " " << endl;
	}
}
//1.��������
/*
1.1ֱ�Ӳ������� 
	�㷨˼�룺�������飬���ν���ǰԪ�ز��뵽ǰ���������� 
	���뷽��Ϊ�߱Ƚϱ��ƶ����ȽϵĹ������ڳ�λ��
*/
void insertsort(int a[],int n) { //a[0]Ϊ�ڱ������������
	for (int i = 2; i <= n; i++) { //��a[2]-a[n]���뵽ǰ�����������
		if (a[i] < a[i - 1]) {	//������Ҫ���н���
			a[0] = a[i];//����ǰԪ�ظ��Ƶ��ڱ���
			int j;
			for ( j = i - 1; a[0] < a[j]; j--) {
				a[j + 1] = a[j]; //����
			}
			a[j + 1] = a[0];
		}
	}
}
/* 
1.2�۰��������
���۰�����ҵ�������Ԫ�ص�λ�� 
��ͳһ���ƶ�λ��
*/
void binary_insert_sort(int a[], int n) {
	int i, low, high,mid;
	for (i = 2; i <= n; i++) {
		if (a[i] < a[i - 1]) {//���� ��Ҫ���л�λ
			a[0] = a[i];
			low = 1; high = i - 1;
			while (low <= high) {//�۰����Ѱ��λ��
				mid = (low + high) / 2;
				if (a[0] < a[mid]) high = mid - 1;//���������Ѱ��
				else low = mid + 1;//�ؼ�ֵ���ڵ�����ֵԪ�� ���Ұ�����Ѱ�� ��֤�㷨���ȶ���
			}//while
			//���ҵ�������λ��Ϊlow
			for (int j = i; j > low; j--) {
				a[j] = a[j - 1];
			}
			a[low] = a[0];
		}//if
	}
}
/*
1.3 ϣ������ 
�㷨˼�� ѡ��һ�������������һ�����������ɼ�¼���һ���ӱ��Ը����ӱ����ֱ�Ӳ�������
����С���������ն�ȫ���¼����һ�β�������
*/
void shell_sort(int a[], int n) {
	for (int d = n / 2; d >= 1; d = d / 2) { //dΪ���� ��ʼֵΪ�ܳ��ȵ�һ�룬֮���𽥼�����1
		for (int i = d + 1; i <= n; i++) {//�ӵ�һ�������Ԫ�ؿ�ʼ�������򣨼���ÿ���һ��Ԫ��Ϊ����״̬��
			if (a[i] < a[i - d]) {//���� ��Ҫ����
				a[0] = a[i];
				int j;
				for (j = i - d; j > 0 && a[0] < a[j]; j = j - d) {//���Ҵ�����λ��
					a[j + d] = a[j];
				}//for
				a[j+d] = a[0];
			}//if ���һ��Ԫ�ص�����
		}//for ���ĳ�������¸������������
	}
}
