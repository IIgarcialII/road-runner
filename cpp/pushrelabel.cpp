#include <iostream>
#include <cstdlib>

#define MAXN 10
#define MAXINT 1000000
#define SOURCE 0

using namespace std;

int cappacities[MAXN][MAXN], flows[MAXN][MAXN];
bool visited[MAXN];

int N, SINK;

inline int min(int a, int b){
	return (a <= b) ? a : b;
}

void addEdge(int src, int dest, int c){ 
	cappacities[src][dest] = c;
	cappacities[dest][src] = c;
	flows[src][dest] = 0;
	flows[dest][src] = c;
}

int dfs(int node, int c){
	if(node == SINK) return c;
	visited[node] = true;
	for(int i =0; i<N; ++i){
		if(!visited[i] && flows[i][node] > 0){
			int r = dfs(i, min(c, flows[i][node]));
			if(r){
				flows[node][i] += r;
				flows[i][node] -= r;
				visited[node] = 0;
				return r;
			}
		}
	}
	visited[node] = 0;
	return 0;
}

int flow(){
	int result = 0;
	while(true){
		int flow = dfs(SOURCE, MAXINT-result);
		if(flow == 0){
			return result;
		}
		result += flow;
	}
	return result;
}

int main(void){
	cin.sync_with_stdio(false);
	cout.sync_with_stdio(false);
	int testCases, v, e, result = 0, v1, v2, cap;

	result = flow();
	cin >> testCases;
	while(testCases--){
		cin >> v >> e;
		N = v;
		SINK = v-1;
		for(int i = 0; i < e; ++i){
			cin >> v1 >> v2;
			cap = (v1 == SOURCE || v2 == SINK) ? 100 : 1;
			addEdge(v1,v2, cap);
		}
		result = flow();
		cout << "Flow = " << result << "\n";	
	}
	return EXIT_SUCCESS;
}

