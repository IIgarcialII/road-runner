#include <cstdlib>
#include <cstdio>
#include <iostream>

using namespace std;

typedef struct List {
	int value;
	struct List* next;
}List;

void insert(List **l, int value);
List* recursiveReverse(List **l, List* prev);
void iterativeReverse(List **l);

void print(List **l);

int main(int argc, const char *argv[]) {
	int value = 0;
	List* root;
	root = (List*) malloc(sizeof(List));
	root->next = NULL;
	root->value = value++;
	
	insert(&root, value++);
	insert(&root, value++);
	print(&root);
	
	root = recursiveReverse(&root, NULL);
	print(&root);
	iterativeReverse(&root);
	print(&root);
	
	free(root);
	
	return EXIT_SUCCESS;
}

void print(List** l) {
	while(NULL != (*l)){
		cout << (*l)->value << " ";
		l = &((*l)->next);
	}
	cout << endl;
}

void insert(List **l, int value) {
	List* next;
	next = (List*) malloc(sizeof(List));
	next->next = *l;
	next->value = value;
	*l = next;
}

List* recursiveReverse(List **l, List* prev) {
	if(NULL == (*l)->next) {
		(*l)->next = prev;
		return *l;
	}
	List* next = (*l)->next;	// 1,0
	(*l)->next = prev;			// NULL, 2
	//cout << (*l) << " " << (*l)->next << " " << (*l)->value << " " << next << " " << next->value << endl;
	return recursiveReverse(&next,(*l)); // 1,2 - 
}


void iterativeReverse(List **l) {
	List* prev = NULL;
	List* next = (*l)->next;
	while(NULL != (*l)) {
		(*l)->next = prev;
		prev = (*l);
		if(NULL == next) break;
		(*l) = next;
		next = (*l)->next;
	}
}