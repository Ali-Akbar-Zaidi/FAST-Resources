//#include<iostream>
//using namespace std;
//
//template<class T>
//class stack
//{
//	T* arr;
//	int top = -1;
//	int size;
//
//public:
//
//	stack(int s)
//	{
//		arr = new T[s];
//		size = s;
//	}
//	~stack()
//	{
//		delete[] arr;
//	}
//	void push(T data)
//	{
//		if (top == size - 1)
//			cout << "Lalach Bori Bala hai" << endl;
//		else
//			arr[++top] = data;
//	}
//	T pop()
//	{
//		if (top == -1)
//			cout << "Kuch isi tarha uskey khayal bhi zehn sey nikal nahi paty :(" << endl;
//		else
//			return arr[top--];
//	}
//
//	void input(int size)
//	{
//		T value{};
//		cout << endl << endl << "Enter your elements: " << endl;
//		for (int i = 0; i < size; i++)
//		{
//			cin >> value;
//			push(value);
//		}
//	}
//
//	T isPalindrome()
//	{
//		if (top == size / 2 && size % 2 == 0) return (pop() == pop()); // if size even
//		else if (top == size / 2) // if size odd
//		{
//			pop();
//			return 1;
//		}
//		else if (top < size / 2) return pop();
//		return (pop() * isPalindrome()) == isPalindrome();
//	}
//};
//
//int main()
//{
//	int choice=0;
//	do
//	{
//		cout << "Enter what data type you would like the program to run a palindrome check on:" << endl;
//		cout << "Enter 1 for INT" << endl;
//		cout << "Enter 2 for CHAR" << endl;
//		cout << "Enter 3 for STRING" << endl;
//		cout << "Enter 4 for BOOL" << endl;
//		cout << "Enter your choice: ";
//
//		do
//		{
//			cin >> choice;
//			if (choice < 1 || choice>4) cout << endl << "Enter a valid number! ";
//		} while (choice < 1 || choice>4);
//
//		int size = 0;
//
//		cout << "Enter the size of your input: ";
//		do
//		{
//			cin >> size;
//			if (size < 1) cout << endl << endl << "Enter a valid size! ";
//		} while (size < 1);
//
//		if (choice == 1)
//		{
//			stack<int> S(size);
//			S.input(size);
//			cout << "Is it a Palindrome? " << (S.isPalindrome() ? "YES" : "NO") << endl << endl;
//		}
//
//		else if (choice == 2)
//		{
//			stack<char> S(size);
//			S.input(size);
//			cout << "Is it a Palindrome? " << (S.isPalindrome() ? "YES" : "NO") << endl << endl;
//		}
//
//		else if (choice == 3)
//		{
//			stack<string> S(size);
//			S.input(size);
//			//cout << "Is it a Palindrome? " << (S.isPalindrome() ? "YES" : "NO") << endl << endl;
//		}
//
//		else
//		{
//			stack<bool> S(size);
//			S.input(size);
//			cout << "Is it a Palindrome? " << (S.isPalindrome() ? "YES" : "NO") << endl << endl;
//		}
//		cout << "Repeat? press 0 for yes: ";
//		cin >> choice;
//		cout << endl << endl;
//	}
//	while (choice == 0);
//}