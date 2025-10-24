//#include<iostream>
//#include<iomanip>
//using namespace std;
//int main()
//{
//	int num, option = 1;
//	do
//	{
//		do
//		{
//			cout << "Please Enter The Middle Digit Of Your Diamond.Do Note Your Digit Can't Be Less Than 2 Or Greater Than 9." << endl;
//			cin >> num;
//		} while (num < 2 || num > 20);
//
//		int left = (num * 2) - 2;
//		int right = (num * 2) - 2;
//		int center = (num * 2) - 1;
//		int width_height = center + right;
//
//		int number_length = 0;
//		int num_copy1 = num;
//		while (num_copy1 > 0)
//		{
//			number_length++;
//			num_copy1 = num_copy1 / 10;
//		}
//
//
//		for (int row = 1; row <= width_height; row++) {
//			int x = 1;
//			for (int col = 1; col <= width_height; col++) {
//				if ((row < center) && (col <= center - row || col >= center + row)) //top left spacing triangle and top right spacing triangle. 
//				{
//					cout << setw(number_length) << " ";
//				}
//				else if ((row > center) && (col <= (row - center) || col > width_height - (row - center))) // bottom left spacing triangle and bottom right spacing triangle.
//				{
//					cout << setw(number_length) << " ";
//				}
//				else if ((row % 2 == 0 && col % 2 != 0) || (row % 2 != 0 && col % 2 == 0)) // diamond spacing
//				{
//					cout << setw(number_length) << " ";
//				}
//				else // print numbers
//				{
//					if (row % 2 == 0 && col % 2 == 0)//even 
//					{
//						cout << setw(number_length) << x;
//						if (col >= center + 1)
//						{
//							x = x - 1;
//						}
//						else if (col < center - 1)
//						{
//							x = x + 1;
//						}
//
//					}
//					else if (row % 2 != 0 && col % 2 != 0)//odd
//					{
//						cout << setw(number_length) << x;
//
//						if (col >= center)
//						{
//							x = x - 1;
//						}
//						else {
//							x = x + 1;
//						}
//
//					}
//
//
//				}
//
//			}
//			cout << endl;
//		}
//
//
//		cout << endl << "Do you want to continue? Enter 1 for YES or any other key for NO." << endl;
//		cin >> option;
//	} while (option == 1);
//}