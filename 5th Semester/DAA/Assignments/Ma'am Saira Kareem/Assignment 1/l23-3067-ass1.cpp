#include <iostream>
#include <queue>
using namespace std;
class task1
{
public:
    bool device(int a, int b)
    {
        return a == b;
    }
    int count(int *arr, int s, int e, int candidate)
    {
        int count = 0;
        for (int i = s; i <= e; i++)
        {
            if (device(arr[i], candidate))
                count++;
        }
        return count;
    }
    int resolution(int *arr, int start, int end)
    {
        if (start == end)
            return arr[end];
        int mid = start + (end - start) / 2;
        int leftCandidate = resolution(arr, start, mid);
        int rightCandidate = resolution(arr, mid + 1, end);

        if (device(leftCandidate, rightCandidate))
            return leftCandidate;
        if (leftCandidate != -1)
        { // if its not null
            int Lcount = count(arr, start, end, leftCandidate);
            if (Lcount > (end - start + 1) / 2)
                return leftCandidate;
        }
        if (rightCandidate != -1)
        { // if not null
            int Rcount = count(arr, start, end, rightCandidate);
            if (Rcount > (end - start + 1) / 2)
                return rightCandidate;
        }
        return -1;
    }
};
//--------------------------------------------------------------
class task2
{
public:
    int BnarySearchTailOccurences(char *arr, int n)
    {
        if (n == 0)
            return 0;
        int size = n - 1;
        int right = size - 1;
        int left = 0;
        char element = ' ';
        int index = 0;

        while (left <= right)
        {
            int mid = left + ((right - left) / 2);
            element = arr[mid];
            index = mid;
            if (arr[mid] == 'T')
            {
                right = mid - 1;
            }
            else if (arr[mid] == 'H')
            {
                left = mid + 1;
            }
        }
        if (element == 'H')
        {
            return ((size - 1) - index);
        }
        else
        {
            return ((size - 1) - index + 1);
        }
    }
};
//--------------------------------------------------------------
class task3
{
public:
    void print(int *arr, int s, int e)
    {
        cout << "arr: [";
        for (int i = s; i <= e; i++)
        {
            cout << arr[i] << " ";
        }
        cout << "\b] ";
    }
    float medianOfTwoSortedArr(int *arr1, int *arr2, int n)
    {
        if (n == 1)
            return (arr1[0] + arr2[0]) / 2;
        int start1 = 0, start2 = 0, end1 = n - 1, end2 = n - 1;
        while ((end1 - start1 + 1) > 2 && (end2 - start2 + 1) > 2)
        {
            int mid1 = start1 + ((end1 - start1) / 2);
            int mid2 = start2 + ((end2 - start2) / 2);

            if (arr1[mid1] == arr2[mid2])
                return arr1[mid1];
            else if (arr1[mid1] < arr2[mid2])
            {
                start1 = mid1;
                end2 = mid2;
            }
            else
            {
                end1 = mid1;
                start2 = mid2;
            }
            print(arr1, start1, end1);
            print(arr2, start2, end2);
            cout << "\n\n";
        }

        while ((end2 - start2 + 1) > 2) // inCase of rightArray still contains more than 2 elements
        {
            int mid2 = start2 + ((end2 - start2) / 2);
            if (arr1[start1] == arr2[mid2])
                return arr1[start1];
            else if (arr1[start1] < arr2[mid2])
                end2 = mid2;
            else
                start2 = mid2;
            print(arr1, start1, end1);
            print(arr2, start2, end2);
            cout << "\n\n";
        }

        while ((end1 - start1 + 1) > 2) // inCase of leftArray still contains more than 2 elements
        {
            int mid1 = start1 + ((end1 - start1) / 2);
            if (arr1[mid1] == arr2[start2])
                return arr2[start2];
            else if (arr1[mid1] < arr2[start2])
                start1 = mid1;
            else
                end1 = mid1;
            print(arr1, start1, end1);
            print(arr2, start2, end2);
            cout << "\n\n";
        }

        return float((max(arr1[start1], arr2[start2]) + min(arr1[end1], arr2[end2])) / 2.0);
    }
};
//--------------------------------------------------------------
class task4
{
public:
    int minimalLengthSubArray(int *arr, int n, int target)
    {
        if (n == 0)
            return 0;
        int i = 0;
        int j = 0;
        int sum = 0;
        int length = 0;
        int minLength = INT32_MAX;
        while (j < n)
        {
            sum += arr[j++];
            while (sum >= target)
            {
                minLength = min(minLength, j - i);
                cout << "Min length updated to " << minLength << " with sum " << sum << endl;
                sum -= arr[i++];
            }
        }
        return minLength == INT32_MAX ? 0 : minLength;
    }
};
//--------------------------------------------------------------
class task5
{
    struct node
    {
        int value;
        node *left;
        node *right;
        node(int value) : value(value), left(nullptr), right(nullptr) {}
    };
    node *root;
    queue<node *> que;

public:
    task5()
    {
        root = nullptr;
    }
    void insert(int value)
    {
        node *toInsert = new node(value);
        que.push(toInsert);
        if (!root)
        {
            root = toInsert;
            return;
        }
        node *parent = que.front();
        if (!parent->left)
        {
            parent->left = toInsert;
        }
        else if (!parent->right)
        {
            parent->right = toInsert;
            que.pop();
        }
    }
    void printTree()
    {
        queue<node *> myQue;
        myQue.push(root);
        int level = 0;
        while (!myQue.empty())
        {
            int size = myQue.size();
            cout << "Level " << level << " node:";
            for (int i = 0; i < size; i++)
            {
                node *temp = myQue.front();
                myQue.pop();
                if (temp->left)
                    myQue.push(temp->left);
                if (temp->right)
                    myQue.push(temp->right);
                cout << temp->value << " ";
            }
            level++;
            cout << endl;
        }
    }
    int getLocalMininmum()
    {
        if (!root)
            return -1;
        return helper(root);
    }
    int helper(node *rootNode)
    {
        int rootVal = rootNode->value, leftVal, rightVal;
        leftVal = probe(rootNode->left);
        rightVal = probe(rootNode->right);
        if (rootNode->left && rootNode->right) // if both present
        {
            if (rootVal < leftVal && rootVal < rightVal)
                return rootNode->value;
        }
        if (rootNode->left && rootVal > leftVal) // if leftChild is present
            return helper(rootNode->left);
        else if (rootNode->right && rootVal > rightVal) // if rightChild is present
            return helper(rootNode->right);
        else // in case of no one is its child
            return rootNode->value;
    }
    int probe(node *temp)
    {
        if (!temp)
            return -1;
        return temp->value;
    }
};
//--------------------------------------------------------------
class task6
{
public:
    int powInt(int base, int exp)
    {
        if (exp == 0)
            return 1;
        int half = powInt(base, exp / 2);
        if (exp % 2 == 0)
            return half * half;
        else
            return half * half * base;
    }
    int bitsManipulater(int k)
    {
        if (k == 16)
        {
            return 0xFFFF0000;
        }
        else if (k == 8)
        {
            return 0xFF00;
        }
        else if (k == 4)
        {
            return 0xF0;
        }
        else
        {
            return 0xF;
        }
    }
    int countNoOfSetBit(int n, int k)
    {
        if (n == 1)
            return 1;
        if (n == 0)
            return 0;
        int leftHalfBits = (n & (bitsManipulater(k))) / powInt(2, k);
        int rightHalfBits = (n - (leftHalfBits * powInt(2, k)));

        return countNoOfSetBit(leftHalfBits, k / 2) + countNoOfSetBit(rightHalfBits, k / 2);
    }
};
//--------------------------------------------------------------
//--------------------------------------------------------------
int main()
{
    cout<<endl<<"Task-1 :\n";
    //--------------------------------------------------------------
    task1 t1;
    int arr1[] = {6, 2, 3, 4, 4, 6, 6, 6, 6, 6};
    cout << "the national assemble passed from different resolution is " << t1.resolution(arr1, 0, 10) << endl<<"----------------------------------------"
         << endl<<"Task-2 :\n";

    //--------------------------------------------------------------
    task2 t2;
    char arr2[] = "HHHHHHHHHHHTTTTTTTTT";
    cout << t2.BnarySearchTailOccurences(arr2, sizeof(arr2) / sizeof(arr2[0])) << " is the number of tails forwarded to heads\n----------------------------------------\nTask-3 :\n";
    
    //--------------------------------------------------------------
    task3 t3;
    const int n = 6;
    int arr3[n] = {1, 2, 3, 4, 5, 6};
    int arr4[n] = {7, 8, 9, 10, 11, 12};
    cout << "median of two sorted arrays = " << t3.medianOfTwoSortedArr(arr3, arr4, n) << endl<<"----------------------------------------\nTask-4 :\n";
     
    //--------------------------------------------------------------
    task4 t4;
    const int size = 6;
    const int target = 13;
    int arr5[size] = {9, -4, 5, 6, -3, 2};
    cout << "Minimal Length of subarray with target " << target << " is " << t4.minimalLengthSubArray(arr5, size, target) << endl<<"----------------------------------------\nTask-5\n";

    //-------------------------------------------------------------
    task5 t5;
    int arr6[] = {20, 15, 30, 40, 10, 35, 50};
    for (int i = 0; i < 7; i++)
        t5.insert(arr6[i]);
    t5.printTree();
    cout << t5.getLocalMininmum() << endl;

    task5 t5b;
    int arr7[] = {2, 5, 7, 9, 10, 12, 15};
    for (int i = 0; i < 7; i++)
        t5b.insert(arr7[i]);

    t5b.printTree();
    cout << t5b.getLocalMininmum() << endl<<"----------------------------------------\nTask-6 :\n";
         
    //--------------------------------------------------------------    
    task6 t6;
    const int noOfStandardBits = 32 / 2;
    cout << t6.countNoOfSetBit(3, noOfStandardBits) << " (expected 2)" << endl;    // 0b11111111
    cout << t6.countNoOfSetBit(1023, noOfStandardBits) << " (expected 10)" << endl;  // 0b1111111111
    cout << t6.countNoOfSetBit(65535, noOfStandardBits) << " (expected 16)" << endl; // 0b1111111111111111
    cout << t6.countNoOfSetBit(2147483645, noOfStandardBits) << " (expected 30)" << endl;
}