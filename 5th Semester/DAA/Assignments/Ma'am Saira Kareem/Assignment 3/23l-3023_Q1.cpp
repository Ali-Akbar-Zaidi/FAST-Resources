#include<iostream>
#include <algorithm> 
#include<vector>
using namespace std;
int zigZagSubSequence(vector<int> A, int N)
{
	int pos = 1, neg = 1,maximum=INT_MIN;

	if (N < 3) return N;

	for(int i=0;i<A.size()-1;i++)
	{
		if (A[i + 1] - A[i] > 0)
		{
			pos = neg + 1;
			maximum = max({ pos, neg, maximum });
		}

		else if (A[i + 1] - A[i] < 0)
		{
			neg = pos + 1;
			maximum = max({ pos, neg, maximum });
		}
		else neg = pos = 1;
	}

	return maximum;
}
void print(vector<int> arr)
{
	if(arr.size()==0) cout<< "EMPTY! ]";
	else
	{
		for (int i = 0; i < arr.size() - 1; i++)
			cout << arr[i] << " , ";
		cout << arr[arr.size() - 1] << " ]";
	}
}
int main()
{
	int size = 0;
	vector<int> arr = { 1,7,4,5,5,1, 7, 4, 9, 2, 5 };
	vector<int> arr1 = { 1,7,4,5,5};
	vector<int> arr2 = {1};
	vector<int> arr3 = { };

	cout << "The Longest Zig-Zag Subsequence Of The Sequence: [ ";  print(arr);
	cout << " Is : " << zigZagSubSequence(arr, arr.size());
	cout << endl << endl;

	cout << "The Longest Zig-Zag Subsequence Of The Sequence: [ ";  print(arr1);
	cout << " Is : " << zigZagSubSequence(arr1, arr1.size());
	cout << endl << endl;

	cout << "The Longest Zig-Zag Subsequence Of The Sequence: [ ";  print(arr2);
	cout << " Is : " << zigZagSubSequence(arr2, arr2.size());
	cout << endl << endl;

	cout << "The Longest Zig-Zag Subsequence Of The Sequence: [ ";  print(arr3);
	cout << " Is : " << zigZagSubSequence(arr3, arr3.size());
	cout << endl << endl;
}