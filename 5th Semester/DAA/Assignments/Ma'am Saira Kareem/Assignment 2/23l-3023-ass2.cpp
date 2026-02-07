#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;

class Node
{
public:
   int data;
   Node* left;
   Node* center;
   Node* right;
   Node(int x) : data(x), left(nullptr), center(nullptr), right(nullptr) {}
};

class Compare
{
public:
   bool operator()(Node* a, Node* b)
   {
       return a->data > b->data;
   }
};

void preOrder(Node* root, vector<string>& ans, string curr)
{
   if (!root) return;

   if (!root->left && !root->center && !root->right)
   {
       ans.push_back(curr);
       return;
   }

   preOrder(root->left, ans, curr + '0');
   preOrder(root->center, ans, curr + '1');
   preOrder(root->right, ans, curr + '2');
}

vector<string> huffmanCodes(string s, vector<int> freq) {
   int n = s.length();
   priority_queue<Node*, vector<Node*>, Compare> pq;

   for (int i = 0; i < n; i++)
       pq.push(new Node(freq[i]));

   // Adjust number of nodes so that (n - 1) % 2 == 0
   while ((pq.size() - 1) % 2 != 0)
       pq.push(new Node(0));

   while (pq.size() > 1) {
       Node* l = pq.top(); pq.pop();
       Node* c = pq.top(); pq.pop();
       Node* r = pq.top(); pq.pop();

       Node* newNode = new Node(l->data + c->data + r->data);
       newNode->left = l;
       newNode->center = c;
       newNode->right = r;

       pq.push(newNode);
   }

   Node* root = pq.top();
   vector<string> ans;
   preOrder(root, ans, "");
   return ans;
}

int main() {
   string s = "abcdef";
   vector<int> freq = {5, 9, 12, 13, 16, 45};

   vector<string> ans = huffmanCodes(s, freq);

   cout << "Ternary Huffman Codes:\n";
   for (int i = 0; i < s.size(); i++)
   {
       cout << s[i] << ": " << ans[i] << endl;
   }

   return 0;
}

#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
void placeThulla(vector<double> positions, vector<double>& Thulla, double d)
{
   if (positions.size() == 0) return; // no paintings no thullas needed

   double initialPlacement = (positions.size()>1)? positions[0] + d : positions[0]; // if only one painting then only one guard required

   Thulla.push_back(initialPlacement); // guard placed d from left of the initial painting to cover most ground

   for (int i = 1; i < positions.size(); i++)
   {
       if(initialPlacement+d<positions[positions.size() - 1]) // should work for all distances less than last painting position
       {
           if (initialPlacement + d < positions[i]) // if painting is out of reach of guard
           {
               if (positions[i] + d > positions[positions.size() - 1]) // if new position of guard outside the distance of last painting

                   initialPlacement = positions[positions.size() - 1]; // then initialise the guards position to the position of last painting

               else
                   initialPlacement = positions[i] + d; //else place the guard to the left of the new painting position to cover max ground

               Thulla.push_back(initialPlacement);
           }
       }
   }
}
void displayThullas(vector<double> Thulla)
{
   cout << "Total No of Thullas Needed: " << Thulla.size() << " , At The Following Positions: [ ";
   if (Thulla.size() == 0)
   {
       cout << "]"; return;
   }
   for (int i = 0; i < Thulla.size() - 1; i++)
   {
       cout << Thulla[i] << " , ";
   }

   cout << Thulla[Thulla.size() - 1] << " ]" << endl << endl;
}
int main()
{
   vector<double> positions = { 1,5,9 }; // positions of paintings
   double d = 2;

   //sort(positions.begin(),positions.end()); // Incase positions are not already sorted



   vector<double> Thulla; //stores positions of guards
   placeThulla(positions, Thulla,d); //function to place guards in thier optimal positions
   
   displayThullas(Thulla); // print positions of guards

   return 0;
}

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


#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void sortCustomers(vector<vector<double>>& Customers)
{
    int n = Customers.size();
    bool swapped;
    for (int i = 0; i < n - 1; i++)
    {
        swapped = false;
        for (int j = 0; j < n - i - 1; j++)
        {
            if (Customers[j][3] > Customers[j + 1][3])
            {
                swap(Customers[j], Customers[j + 1]);
                swapped = true;
            }
        }
        if (!swapped)
            break;
    }
}

int main()
 {
    vector<vector<double>> Customers = 
    {
        {1, 4, 2,0}, // 1st index stores customer's ID
        {2, 5, 1,0}, // 2nd index stores customer's Time
        {3, 2, 3,0}, // 3rd index stores customer's Priority
        {4, 3, 2,0}  // 4th index will be used to store customer's Time to Priority Ratio
    };

    for (int i = 0; i < Customers.size(); i++)
        Customers[i][3]=Customers[i][1] / Customers[i][2]; //calculating customer's Time to Priority Ratio
    

    sortCustomers(Customers); // sort customers based on thier Time to Priority Ratio

    double totalWait = 0;
    double currentTime = 0;

    cout << "Optimal Order: [";

    for (int i = 0; i < Customers.size(); i++)
    {
        
        if (i == Customers.size() - 1) cout << Customers[i][0];
        else cout << Customers[i][0] << " , ";
        currentTime += Customers[i][1];
        totalWait += currentTime;
    }

    cout << "]" << endl << "Minimum Total Wait Time = " << totalWait << endl;
    return 0;
}
