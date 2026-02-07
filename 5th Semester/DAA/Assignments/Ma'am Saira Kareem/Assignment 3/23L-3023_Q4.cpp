#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Product {
   int width;
   int height;
   int value;
};

int maxClothValue(int X, int Y, const vector<Product>& products)
{
   vector<vector<int>> dp(X + 1, vector<int>(Y + 1, 0));

   for (int x = 1; x <= X; ++x)
   {
       for (int y = 1; y <= Y; ++y)
       {
           // Vertical cuts
           for (int xcut = 1; xcut < x; ++xcut)
               dp[x][y] = max(dp[x][y], dp[xcut][y] + dp[x - xcut][y]);

           // Horizontal cuts
           for (int ycut = 1; ycut < y; ++ycut)
               dp[x][y] = max(dp[x][y], dp[x][ycut] + dp[x][y - ycut]);

           // Place products
           for (const auto& p : products)
           {
               if (p.width <= x && p.height <= y)
                   dp[x][y] = max(dp[x][y], p.value + dp[x - p.width][y - p.height]);
           }
       }
   }

   return dp[X][Y];
}

int main() {
   vector<Product> products = 
   {
       {1, 2, 3},
       {2, 2, 5},
       {3, 1, 4}
   };

   int X = 4, Y = 3;

   cout << "Maximum value: " << maxClothValue(X, Y, products) << endl;

   return 0;
}
