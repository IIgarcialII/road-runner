/*
 *  falseCoins.cpp
 *  
 *
 *  Created by Alejandro Garcia on 02/10/10.
 *
 */
#include <iostream>
#include <set>
#include <algorithm>

using namespace std;

#define _CRT_SECURE_NO_WARNINGS
#define FOR(i,a,b) for (int i(a),_b(b); i < _b; ++i)
#define FORE(i,a,b) for (int i(a),_b(b); i <= _b; ++i)
#define FORIT(i,a,b) for (i(a);b;++i)
#define FORD(i,a,b) for (int i(a),_b(b); i >= _b; --i)
#define REP(i,n) for (int i(0),_n(n); i < _n; ++i) 
#define REPD(i,n) for (int i((n)-1); i >= 0; --i)
#define FILL(a,c) memset(&a, c, sizeof(a)) 
#define MP(x, y) make_pair((x), (y)) 
#define ALL(v) (v).begin(), (v).end()


void intersect(set<int> &lh, set<int> &plate, set<int> &i) {
	set_intersection(plate.begin(), plate.end(),
					 lh.begin(), lh.end(), inserter(i,i.begin()));
	lh.clear();
	lh.insert(i.begin(),i.end());
}

void printSet(set<int> &s) {
	set<int>::iterator it;
	for(it=s.begin(); it != s.end(); ++it){
		cout << *it << endl;
	}
}


int main() {
	
	int M,N,K;
	int numberOfCoinsPerPlate, coin;
	char result;
	set<int> all,light, heavy, leftPlate, rightPlate;
	set<int>::iterator lp, rp;
	
	cin >> M;
	
	FOR(i,0,M) {
		cin >> N >> K;
		FORE(j,1,N) {
			light.insert(j);
			heavy.insert(j);
		}
		FOR(y,0,K){
			cin >> numberOfCoinsPerPlate;
			FOR(z,0,numberOfCoinsPerPlate*2) {
				cin >> coin;
				if(z<numberOfCoinsPerPlate) leftPlate.insert(coin);
				else rightPlate.insert(coin);
			}
			//printSet(leftPlate);
			//printSet(rightPlate);
			cin >> result;
			set<int> intersection;
			if(result == '=') {
				for (lp = leftPlate.begin(), rp = rightPlate.begin(); lp != leftPlate.end(); ) {
					light.erase(*lp);
					heavy.erase(*lp);
					light.erase(*rp);
					heavy.erase(*rp);
					++lp;
					++rp;
				}
			}else if(result == '<') {
				intersect(light,leftPlate,intersection);
				intersection.clear();
				intersect(heavy,rightPlate,intersection);
			}else {
				intersect(light,rightPlate,intersection);
				intersection.clear();
				intersect(heavy,leftPlate,intersection);
			}
			leftPlate.clear();
			rightPlate.clear();
		}
		int ls = light.size();
		int hs = heavy.size();
		if(ls > 1 || hs > 1) cout << 0 << endl;
		else if ((ls == 1 && hs == 1) && (*(light.begin()) != *(heavy.begin()))) cout << 0 << endl;
		else if(ls > 0) cout << *(light.begin()) << endl;
		else cout << *(heavy.begin()) << endl;
		light.clear();
		heavy.clear();
	}

	
	return 0;
}