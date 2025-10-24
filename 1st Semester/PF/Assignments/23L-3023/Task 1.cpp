//#include<iostream>
//using namespace std;
//int main()
//{
//	cout << "This is a program to print a rectangle made of '#'." << endl;
//	int option = 1, width, length;
//	do
//	{
//		do
//		{
//			cout << endl << "Enter the width,Note width cannot be negative or less than 2 or greater than length." << endl;
//			cin >> width;
//		} while (width < 2);
//
//		do
//		{
//			cout << endl << "Enter the length,Note length cannot be negative or less than width+1." << endl;
//			cin >> length;
//		} while (length < (width + 1));
//
//		for (int line = 1; line <= width; line++)
//		{
//			if (line == 1)
//				cout << endl;
//
//			if (line == 1 || line == width)
//			{
//				for (int hashcounter = 1; hashcounter <= length; hashcounter++)
//				{
//					cout << "# ";
//					if (hashcounter == length)
//						cout << endl;
//				}
//			}
//
//			if (line > 1 && line < width)
//			{
//				cout << "#";
//				for (int SpaceCounter = 2; SpaceCounter <= (2 * length) - 1; SpaceCounter++)
//				{
//					if (SpaceCounter <= (2 * length) - 2)
//						cout << " ";
//					if (SpaceCounter == (2 * length) - 1)
//						cout << "#" << endl;
//				}
//			}
//		}
//
//		cout << endl << "Do you want to continue? Enter 1 for YES or any other key for NO." << endl;
//		cin >> option;
//	} while (option == 1);
//}