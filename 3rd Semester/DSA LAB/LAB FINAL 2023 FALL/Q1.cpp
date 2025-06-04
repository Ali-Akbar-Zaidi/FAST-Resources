//#include<iostream>
//using namespace std;
//void ReheapUp(int root, int bottom, int*& arr)
//{
//    int parent;
//    if (bottom > root)
//    {
//        parent = (bottom - 1) / 2;
//        if (arr[parent] > arr[bottom])
//        {
//            int temp1 = arr[bottom];
//            arr[bottom] = arr[parent];
//            arr[parent] = temp1;
//            ReheapUp(root, parent, arr);
//        }
//    }
//}
//
//void printMinHeap(int*& arr, size_t size)
//{
//    cout << "Array after conversion to Min-Heap: ";
//    for (int i = 0; i < size; i++)
//    {
//        cout << arr[i] << " ";
//    }
//}
//
//void BSTtoMinHeap(int* arr,size_t size)
//{
//    for (int i = 0; i < size; i++)
//    {
//        while (true)
//        {
//            cout << "Enter A Value To Insert In The Array: ";
//            cin >> arr[i];
//            if (!cin || arr[i] > INT_MAX || arr[i] < INT_MIN)
//            {
//                cout << "Enter A Valid Value To Insert In The Array!: ";
//                cin.clear();
//                cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//                continue;
//            }
//            else break;
//        }
//        ReheapUp(0, i, arr);
//    }
//    system("cls");
//}
//
//int main()
//{
//	size_t size = 0;
//    cout << "Enter Size Of The Array: ";
//    while (true)
//    {
//        cin >> size;
//        if (!cin || size > INT_MAX || size < 0)
//        {
//            cout << "Enter A Valid Size Of The Array!: ";
//            cin.clear();
//            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//            continue;
//        }
//        else break;
//    }
//    cin.ignore();
//    system("cls");
//    int* arr = new int[size];
//    BSTtoMinHeap(arr, size);
//    printMinHeap(arr, size);
//    delete[] arr;//destroctor
//}