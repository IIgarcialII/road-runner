#include <cstdlib>
#include <cstdio>
#include <vector>

using namespace std;

#define ARRAY_SIZE(a) sizeof(a)/sizeof(a[0])

int select(int* array, size_t arraySize, size_t k) {
	int result = 0;
	vector<int> al, av, ar;
	int v = array[(rand() % arraySize)]; 
	size_t i; 
	int currentValue;
	for(i=0; i < arraySize; ++i) {
		currentValue = array[i];
		if(currentValue < v) {
			al.push_back(currentValue);
		}else if(currentValue == v) {
			av.push_back(currentValue);
		}else {
			ar.push_back(currentValue);
		}
	}
	
	if(k <= al.size()) {
		result = select(&al[0], al.size(), k);
	}else if(k > (al.size() + av.size())) {
		result = select(&ar[0], ar.size(), k - (al.size() + av.size()));
	}else {
		result = av[0];
	}
	return result;
}

int main(int argc, char* argv[]) {
	
	int array[8] = {1,26,5,13,6,10,21,5};
	int result = select(&array[0],8,4);
	printf("%d \n\r", result);
	
	return EXIT_SUCCESS;
}
