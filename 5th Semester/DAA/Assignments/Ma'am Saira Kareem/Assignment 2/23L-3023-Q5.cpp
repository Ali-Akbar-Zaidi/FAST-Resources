#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() 
{
   vector<int> customerTimes = { 4, 5, 2 }; 
                                        
   sort(customerTimes.begin(), customerTimes.end());

   int totalWaitingTime = 0;
   int currentTimeOfProcess = 0;

   cout << "Optimal Order: [";

   for (int i = 0; i < customerTimes.size(); i++)
   {
       if (i == customerTimes.size() - 1) cout << customerTimes[i];
       else cout << customerTimes[i] << " , ";
       currentTimeOfProcess += customerTimes[i];
       totalWaitingTime += currentTimeOfProcess;
   }

   cout <<"]" << endl << "Minimum Total Wait Time = " << totalWaitingTime << endl;
   return 0;
}
