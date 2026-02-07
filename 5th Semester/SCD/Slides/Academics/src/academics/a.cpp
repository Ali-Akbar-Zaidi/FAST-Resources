#include<iostream>
using namespace std;  
#include<vector>
#include <algorithm>
class Solution {
public:
        int countCoveredBuildings(int n, vector<vector<int>>& b) {
            sort(b.begin(),b.end());
            
            for(int i=1;i<n-1;i++)
            {
                if(b[i][0]==b[i-1][0] && b[i][0]==b[i+1][0] && b[i][1]>b[i-1][1] && b[i][1]<b[i+1][1]) return 1;
            }
            return 0;
        }
    };
    
    int main()
    {
        int n = 3;
        vector<vector<int>> b = {{1,2},{2,2},{3,2},{2,1},{2,3}};
        Solution sol;
        sol.countCoveredBuildings(n, b);
        return 0;
    }