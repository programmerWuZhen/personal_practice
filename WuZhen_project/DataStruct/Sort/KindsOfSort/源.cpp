#include <iostream>
using namespace std;
class LinkList {

};
int main() {
	void insertsort(int a[], int n);
	int a[] = { 0,1,3,2,4,5,6,7,9,8,0 };
	short n = 0xA1B6;
	printf("��ʮ��������������ǣ�%u(�޷���),%d(�з���)\n", n,n);
	unsigned short m = n;
	printf("m��ʮ������Ϊ��%x,%x,%d", m,n,n);
	m = m >> 1;

	printf(" m���ƺ�Ϊ%x", m);
	insertsort(a, 10);
	/*for (int i = 1; i < 11; i++) {
		cout << a[i] << " " << endl;
	}*/
}
//1.��������
//1.1ֱ�Ӳ�������
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
