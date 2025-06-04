//#include<iostream>
//using namespace std;
//class Stack
//{
//private:
//	int* array;
//	int top;
//	int size;
//
//public:
//	Stack(int Size)
//	{
//		array = new int[Size];
//		size = Size;
//		top = -1;
//	}
//
//	~Stack()
//	{
//		delete[] array;
//	}
//
//	void push(int element)
//	{
//		if (top == size - 1)
//		{
//			cout << "stack is full" << endl;
//			return;
//		}
//		array[++top] = element;
//	}
//
//	int pop()
//	{
//		if (top == -1)
//		{
//			cout << "stack is empty" << endl;
//			return -1;
//		}
//		return array[top--];
//	}
//
//	bool isEmpty()
//	{
//		return top == -1;
//	}
//};
//bool IsDisjoint(int* set1, int* set2, size_t size1, size_t size2, size_t& commonElement)
//{
//	Stack stack1(size1);
//	Stack stack2(size2);
//
//	for (int i = 0; i < size1; i++)
//		stack1.push(set1[i]);
//
//	for (int i = 0; i < size2; i++)
//		stack2.push(set2[i]);
//
//	while (!stack1.isEmpty())
//	{
//		int element1 = stack1.pop();
//		Stack tempStack(size2);
//
//		while (!stack2.isEmpty())
//		{
//			int element2 = stack2.pop();
//			if (element1 == element2)
//			{
//				commonElement = element1;
//				return false;
//			}
//			tempStack.push(element2);
//		}
//		while (!tempStack.isEmpty())
//			stack2.push(tempStack.pop());
//	}
//	return true;
//}
//
//int main()
//{
//    size_t size1, size2, commonElement;
//	cout << "Enter the size of the first Array: ";
//	while (true)
//	{
//		
//		cin >> size1;
//		if (!cin || size1 > INT_MAX || size1 < 0)
//		{
//			cout << "Enter A Valid Size Of The Array!: ";
//			cin.clear();
//			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//			continue;
//		}
//		else break;
//	}
//	cin.ignore();
//	//system("cls");
//	int* set1 = new int[size1];
//	cout << "Enter the elements of the first set: " << endl;
//	for (int i = 0; i < size1; i++) {
//		while (true)
//		{
//			cout << "Index [" << i << "] : ";
//			cin >> set1[i];
//			if (!cin || set1[i] > INT_MAX || set1[i] < INT_MIN)
//			{
//				cout << "Enter A Valid Element Of The Array!: ";
//				cin.clear();
//				cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//				continue;
//			}
//			else break;
//		}
//	}
//
//	cout << "Enter the size of the second set: ";
//	while (true)
//	{
//		cin >> size2;
//		if (!cin || size2 > INT_MAX || size2 < 0)
//		{
//			cout << "Enter A Valid Size Of The Array!: ";
//			cin.clear();
//			cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//			continue;
//		}
//		else break;
//	}
//	cin.ignore();
//	//system("cls");
//	int* set2 = new int[size2];
//	cout << "Enter the elements of the second set: "<< endl;
//	for (int i = 0; i < size2; i++) {
//		while (true)
//		{
//			cout << "Index [" << i << "] : ";
//			cin >> set2[i];
//			if (!cin || set2[i] > INT_MAX || set2[i] < INT_MIN)
//			{
//				cout << "Enter A Valid Element Of The Array!: ";
//				cin.clear();
//				cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//				continue;
//			}
//			else break;
//		}
//	}
//	//system("cls");
//
//    if (IsDisjoint(set1, set2, size1, size2, commonElement)) cout << "Yes, Disjoint. There is no common element in the two sets." << endl;
//    else cout << "Not Disjoint. " << commonElement << " is common in the two sets." << endl;
//
//	delete[] set1;
//	delete[] set2;
//}