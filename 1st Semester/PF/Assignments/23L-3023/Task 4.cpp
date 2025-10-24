//#include<iostream>
//using namespace std;
//int main()
//{
//	for (int row = 1; row <= 44; row++) {
//		for (int col = 1; col <= 10; col++) {
//			if (row == 1 || row == 6 || row == 12 || row == 17 || row == 23 || row == 28 || row == 32 || row == 34)
//			{
//				cout << "*";
//			}
//			else if ((row < 11 && col == 1) || ((row >= 23 && row < 28) && col == 1))
//			{
//				cout << "*         ";
//			}
//			else if ((row > 12 && row < 22) && col == 1)
//			{
//				cout << "*        *";
//			}
//			else if (col == 1 && (row > 28 && row < 32))
//			{
//				cout << "         *";
//			}
//			else if (col == 5 && row > 33)
//			{
//				cout << "    *     ";
//			}
//		}
//		cout << endl;
//	}
//}