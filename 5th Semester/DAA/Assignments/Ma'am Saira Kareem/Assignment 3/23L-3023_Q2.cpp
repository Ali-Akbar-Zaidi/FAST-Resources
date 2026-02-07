#include<iostream>
#include <algorithm> 
#include<vector>
#include <iomanip>
#include<string>
using namespace std;

int GameTheory(vector<int>cards, int start, int end, vector<vector<int>>& DP)
{
	if (start + 1 == end)
		return max(cards[start], cards[end]);

	if (DP[start][end] != -1)
		return DP[start][end];

	DP[start][end] = 
		max(
			cards[start] + min(GameTheory(cards, start + 2, end, DP),
				GameTheory(cards, start + 1, end - 1, DP)),
			cards[end]  +  min(GameTheory(cards, start + 1,end - 1, DP),
				GameTheory(cards, start, end - 2, DP))
	);

	return DP[start][end];
}

void initialiseDP(vector<vector<int>>& DP,const int size)
{
	for (int i = 0; i < size; i++)
		for (int j = 0; j < size; j++)
			DP[i][j] = -1;
}

void outputTable(vector<vector<int>> arr)
{
	if (arr.empty()) return;

	// Determine the maximum width of any element for proper alignment
	int maxWidth = 0;
	for (const auto& row : arr) {
		for (int val : row) {
			int len = to_string(val).length();
			if (len > maxWidth) maxWidth = len;
		}
	}

	// Print the table
	for (const auto& row : arr) {
		for (int val : row) {
			cout << setw(maxWidth + 2) << val; // add padding
		}
		cout << endl;
	}

}

int main()
{
	
	vector<int>cards = { 8,15,3,7 };
	int noOfCards = cards.size();

	vector<vector<int>> DP(noOfCards, vector<int>(noOfCards));
	initialiseDP(DP, noOfCards);

	cout << "Maximum amount Player 1 can win: "<< GameTheory(cards, 0, noOfCards - 1, DP) << endl;

	outputTable(DP);
}