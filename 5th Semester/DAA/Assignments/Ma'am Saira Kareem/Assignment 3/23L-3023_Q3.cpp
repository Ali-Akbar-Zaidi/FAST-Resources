#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int jobScheduling(vector<int> jobs, int noOfJobs, int totalTime)
{
    vector<int> dp(totalTime + 1, 0);

    for (int i = 0; i < noOfJobs; i++)
    {
        for (int j = totalTime; j >= jobs[i]; j--)
        {
            dp[j] = max(dp[j], jobs[i] + dp[j - jobs[i]]);
        }
    }
    for (int i = 0; i <= totalTime; i++)
    {
        cout << dp[i] << " ";
    }

    return dp[totalTime];
}

int main()
{
    vector<int> jobs = { 2, 5, 9, 4, 7 };
    int noOfJobs = 5;
    int totalTime = 20;

    cout << "Maximum busy time: " << jobScheduling(jobs, noOfJobs, totalTime) << endl;
    return 0;
}
