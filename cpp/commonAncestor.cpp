#include <iostream>
#include <cstdlib>
#include <queue>

#define TREE_SIZE 13
#define NOT_DEFINED -1

using namespace std;

struct Node{
	int id;
	struct Node  *l;
	struct Node *r;
};

struct Node* NewNode(int value) {
  struct Node* node = new(struct Node);    // "new" is like "malloc"
  node->id = value;
  node->l = NULL;
  node->r = NULL;

  return(node);
} 

struct Node* insert(struct Node* node, int value){
	if(node==NULL){
		node = NewNode(value);
		return node;
	}
	if(node->id >= value) node->l = insert(node->l, value);
	else  node->r = insert(node->r, value);
	return node;
}

void bfs(struct Node* root){
	queue<struct Node*> q;
	struct Node* current;
	q.push(root);
	while(!q.empty()){
		current = q.front();
		if(current->l != NULL)q.push(current->l);
		if(current->r != NULL)q.push(current->r);
		cout << current->id << " ";
		q.pop();
	}
}

int main(void){
	cin.sync_with_stdio(false);
	cout.sync_with_stdio(false);

	int tree[TREE_SIZE] = {11,5,12,4,7,10,14,1,3,6,9,13,15};
	
	struct Node* root = NewNode(tree[0]);

	for(int i=1; i< TREE_SIZE;++i){
		insert(root,tree[i]);
	}
	bfs(root);
	return EXIT_SUCCESS;
}

