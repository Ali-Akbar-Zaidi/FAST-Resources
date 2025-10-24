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
