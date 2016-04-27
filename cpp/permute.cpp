#include <iostream>
#include <cstdlib>

using namespace std;

//Tell us the highest number
#define N 5

void back(int pos);
void backIt(int pos);
//+1 becuase of zero index
int P[N+1];
bool isTaken[N+1];

int main(){
	cin.sync_with_stdio(false);
	cout.sync_with_stdio(false);
	back(1);
	return EXIT_SUCCESS;
}

void back(int pos){
	uint i,j;
	//From one because we dont consider zero.
	for(i=1;i<=N;++i){
		if(!isTaken[i]){
			P[pos] = i;
			isTaken[i] = true;
			if(pos==N){
				for(j=1;j<=N;++j){
					cout << P[j] << (j<N ? ", " : "\n");
				}
			}else{
				back(pos+1);
			}
			isTaken[i] = false;
		}
	}
}

void backIt(){
	
}



