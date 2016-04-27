#include <cstdlib>
#include <iostream>
#include <math.h>

using namespace std;

void quicksort(int *a, int left, int right);
int partition(int *a, int left, int right);

int main(void) {
	int arr[] = {6,5,3,1,8,10,2};
	int left = 0;
	int right = (sizeof(arr) / sizeof(int)) - 1;
	quicksort(arr, left, right);
	int i;
	for(i = 0; i < right + 1; ++i) {
		cout << arr[i] << " ";
	}
	cout << endl;
}


void quicksort(int *a, int left, int right) {
	if(left < right) {
		int pivot  = partition(a, left, right);
		quicksort(a, left, pivot-1);
		quicksort(a, pivot+1, right);
	}
}

int partition(int *a, int left, int right){
	int pivot = ((right-left) / 2) + left;
	//cout << left << " " << right << " " << pivot << endl;
	int temp = a[pivot];
	a[pivot] = a[right];
	a[right] = temp;
	int store = left;
	int i;
	for(i = left; i < right; ++i) {
		if(a[i] <= a[right]) {
			temp = a[i];
			a[i] = a[store];
			a[store] = temp;
			store++;
		}
	}
	temp = a[store];
	a[store] = a[right];
	a[right] = temp;
	return store;
}