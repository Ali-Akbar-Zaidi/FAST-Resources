#include <iostream>
#include <vector>
#include<algorithm>
#include <iomanip>
using namespace std;

// Custom sort function to sort contestants on basis of larger (bike+run times), 
//in other words an alternate of finish time as used for sorting in activity selection problem 
void sortContestants(vector<vector<double>>& contestants)
{
   int n = contestants.size();
   bool swapped;

   for (int i = 0; i < n - 1; i++)
   {
       swapped = false;
       for (int j = 0; j < n - i - 1; j++)
       {
           // Sort in descending order by (bike + run)
           if (contestants[j][1] + contestants[j][2] < contestants[j + 1][1] + contestants[j + 1][2])
           {
               swap(contestants[j], contestants[j + 1]);
               swapped = true;
           }
       }
       if (!swapped)
           break;
   }
}

// Function to schedule and calculate total completion time
void scheduleTriathlon(vector<vector<double>>& contestants)
{
   double currentSwimEnd = 0;
   double totalCompletion = 0;

   for (int i = 0; i < contestants.size(); i++)
   {
       double swim = contestants[i][0];
       double bike = contestants[i][1];
       double run = contestants[i][2];

       double startSwim = currentSwimEnd;
       double finishSwim = startSwim + swim;
       double finishTotal = finishSwim + bike + run;

       currentSwimEnd = finishSwim; // next contestant starts swimming after this
       totalCompletion = max(totalCompletion, finishTotal);

       cout << "Contestant " << i + 1 << " finishes at time: " << finishTotal << endl;
   }

   cout <<endl<< "Total competition ends at time: " << totalCompletion << endl;
   cout << "======================================" << endl;
}

//Function to print order of starting contestants
void optimalSchedule(vector<vector<double>>& contestants)
{
   cout << fixed << setprecision(2);
   cout << "===== Optimal Triathlon Schedule =====" << endl;
   cout << "Order of contestants (Swim, Bike, Run):" << endl;

   for (int i = 0; i < contestants.size(); i++)
   {
       cout << i + 1 << ". (" << contestants[i][0] << ", "
           << contestants[i][1] << ", " << contestants[i][2] << ")" << endl;
   }

   cout <<endl<< "Individual Finish Times:"<<endl;
}
int main()
{
   vector<vector<double>> contestants =
   {
       // Each vector = { swim, bike, run }
       {3, 10, 8},
       {6, 4, 5},
       {2, 9, 7},
       {5, 3, 2}
   };
   sortContestants(contestants);
   optimalSchedule(contestants);
   scheduleTriathlon(contestants);

   return 0;
}
