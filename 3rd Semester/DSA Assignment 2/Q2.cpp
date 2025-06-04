//#include<iostream>
//#include<string>
//using namespace std;
//
//template<class T>
//class Queue
//{
//	T arr[50];
//	int front = -1;
//	int rear = -1;
//
//public:
//
//	bool isEmpty()
//	{
//		return front == -1;
//	}
//
//	bool isFull()
//	{
//		return (rear + 1) % 50 == front;
//	}
//
//	void EnQueue(T value)
//	{
//		if (isFull())
//		{
//			cout << "Queue is full, cannot enqueue." << endl;
//			return;
//		}
//
//		if (isEmpty())
//		{
//			front = 0;  // If the queue is empty, set front to 0
//			// r3ar is incrementing later
//		}
//
//		// Circular increment of the rear index
//		rear = (rear + 1) % 50;
//		arr[rear] = value;
//
//	}
//
//	T DeQueue()
//	{
//		if (isEmpty())
//		{
//			cout << "Queue is empty, cannot dequeue." << endl;
//		}
//
//		T dequeuedValue = arr[front];
//		if (front == rear) {  // If there's only one element left, reset front and rear
//			front = -1;
//			rear = -1;
//		}
//		else {
//			front = (front + 1) % 50;  // Circular increment of the front index
//		}
//
//		return dequeuedValue;
//	}
//
//};
//
//
//
//int NoOfDigits(int* arr, size_t size)
//{
//	int key = 0;
//	for (int index = 0; index < size; index++)
//	{
//		int digits = 1;
//		int num = arr[index];
//		while (num / 10 != 0)
//		{
//			num = num / 10;
//			digits++;
//		}
//		if (digits > key)
//			key = digits;
//	}
//	return key;
//}
//
//template<class T>
//void print(T* arr, const size_t size)
//{
//	for (int index = 0; index < size; index++)
//	{
//		cout << arr[index] << " ";
//	}
//	cout << endl;
//
//}
//void radixSort(int* arr, size_t sizeofArr)
//{
//	int key = NoOfDigits(arr, sizeofArr);
//
//	Queue<int> arrayOfQueues[10];  // 0 to 9
//	int k = 1;
//	while (k <= key)
//	{
//		for (int i = 0; i < 10; i++)// extracting elements of array into queues
//		{
//
//			for (int j = 0; j < sizeofArr; j++)
//			{
//				int digit = arr[j];
//				for (int div = 1; div < k; div++)
//				{
//					digit = digit / 10;
//				}
//				digit = digit % 10;
//				if (digit == i)
//				{
//					arrayOfQueues[i].EnQueue(arr[j]);
//				}
//			}
//		}
//		int index = 0;
//		for (int i = 0; i < 10; i++) // placing elements back into array
//		{
//			while (!(arrayOfQueues[i].isEmpty()))
//			{
//				arr[index] = arrayOfQueues[i].DeQueue();
//				index++;
//			}
//		}
//		cout << endl << "Cycle " << k++ << " :";
//		print(arr, sizeofArr);
//	}
//}
//int StrKeyfinder(string* arr, size_t sizeofArr)
//{
//	int key = 0;
//	for (int i = 0; i < sizeofArr; i++)
//	{
//		if ((arr[i].size()) > key)
//		{
//			key = (arr[i]).size();
//		}
//	}
//	return key;
//}
//
//
//void radixSort(string* arr, size_t sizeofArr)
//{
//	int key = StrKeyfinder(arr, sizeofArr);
//	Queue<string> ArrayOfQueues[53]; // 0: space, 1-26: A-Z, 27-52: a-z
//
//	// Append spaces to make all strings of equal length
//	for (int i = 0; i < sizeofArr; i++)
//	{
//		while (arr[i].size() < key)
//		{
//			arr[i] += " ";
//		}
//	}
//	//		 if (" " == char)then 0 
//	//  else if (65<=int(char)&&int(char<=90)    then (65)-64 = 1 
//	//  else if (97<=int(char) &&int(char)<=122) then (97)-70 = 27 
//	for (int k = 1; k <= key; k++)
//	{
//		// Distribute strings into queues based on the current character
//		for (int j = 0; j < sizeofArr; j++)
//		{
//			char ch = arr[j][key - k];
//			if (ch == ' ')
//			{
//				ArrayOfQueues[0].EnQueue(arr[j]);
//			}
//			else if (65 <= int(ch) && int(ch) <= 90) // 'A' to 'Z'
//			{
//				int index = int(ch) - 64; // 1 to 26
//				ArrayOfQueues[index].EnQueue(arr[j]);
//			}
//			else if (97 <= int(ch) && int(ch) <= 122) // 'a' to 'z'
//			{
//				int index = int(ch) - 70; // 27 to 52
//				ArrayOfQueues[index].EnQueue(arr[j]);
//			}
//			// Optionally handle other characters or ignore them
//		}
//
//		// Collect strings from queues back into the array
//		int index = 0;
//		for (int i = 0; i < 53; i++) // 0 to 52
//		{
//			while (!ArrayOfQueues[i].isEmpty())
//			{
//				arr[index++] = ArrayOfQueues[i].DeQueue();
//			}
//		}
//
//		cout << "Cycle [" << k << "] :";
//		print(arr, sizeofArr);
//	}
//}
//
//int main()
//{
//	const int size = 3;
//	int* arr = new int[size];
//	for (int i = 0; i < size; i++)
//	{
//		cout << "Int [" << i << "] = ";
//		cin >> arr[i];
//	}
//	radixSort(arr, size);
//	cin.ignore();
//	string* str = new string[size];
//	cout << endl << endl << "Either write in-Upper or In-Lower case for exact sort\n";
//	
//	for (int i = 0; i < size; i++)
//	{
//		cout << "Str [" << i << "] = ";
//		getline(cin, str[i]);
//	}
//	radixSort(str, size);
//
//
//}