//#include<iostream>
//using namespace std;
//int main()
//{
//	int height, option = 1;
//	cout << "This Is A Program To Display A Pattern." << endl;
//
//	do
//	{
//		do
//		{
//			cout << "Enter The Height Of The Pattern. Do Note Height can't be less than 1." << endl;
//			cin >> height;
//		} 
//		while (height < 1);
//
//		for (int row = 1; row <= height; row++)
//		{
//			for (int col = 1; col <= 4 * height+3; col++)
//			{
//				if (col <= height)
//				{
//					if (col <= row)
//					{
//						cout << "*";
//					}
//					else
//					{
//						cout << " ";
//					}			
//				}
//				else if (col > height && col <= 2*height+1)
//				{
//					if (col == height+1)
//					{
//						cout << " *";
//					}
//					else if (col > height + 1 && col < (2 * height + 2) - row)
//					{
//						cout << "*";
//					}
//					else
//						cout << " ";
//				}
//				else if (col >= 2 * height+2 && col <= 3 * height+2)
//				{
//					if (col == 2 * height + 2)
//					{
//						cout << "";
//					}
//					else 
//						if (col >=(row + (2 * height + 2)) && col <= (3 * height+2))
//					{
//						cout << "*";
//					}
//					else
//						cout << " ";
//				}
//				else if (col >= 3 * height + 3 && col <= ((4 * height + 3)-row))
//				{
//					cout<<" ";
//				}
//				else cout<<"*";
//
//
//				
//
//			}
//			cout << endl;
//		}
//
//        cout << endl << "Do you want to continue? Enter 1 for YES or any other key for NO." << endl;
//	    cin >> option;
//	} 
//	while (option == 1);
//}




	
	