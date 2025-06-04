//#include<iostream>
//using namespace std;
//class circularLinkedPetrolPumps
//{
//    struct PetrolPump
//    {
//        PetrolPump* nextPump;
//        int petrol;
//        int distance;
//        PetrolPump(int petrol, int distance) :nextPump(nullptr), petrol(petrol), distance(distance) {}
//    };
//    PetrolPump* head;
//    int noOfPumps;
//public:
//    circularLinkedPetrolPumps(int n)
//    {
//        head = nullptr;
//        noOfPumps = n;
//    }
//    ~circularLinkedPetrolPumps()
//    {
//        if (!head) return;
//
//        PetrolPump* current = head->nextPump;
//        PetrolPump* nextPump;
//
//        while (current != head) {
//            nextPump = current->nextPump;
//            delete current;
//            current = nextPump;
//        }
//
//        delete head;
//        head = nullptr;
//    }
//    void addPump(int petrol, int distance)
//    {
//        if (!head)
//        {
//            head = new PetrolPump(petrol, distance);
//            head->nextPump = head;
//        }
//        else AddPump(petrol, distance, head);
//
//    }
//    void AddPump(int petrol, int distance, PetrolPump* pump)
//    {
//        if (pump->nextPump == head)
//        {
//            pump->nextPump = new PetrolPump(petrol, distance);
//            pump->nextPump->nextPump = head;
//        }
//        else AddPump(petrol, distance, pump->nextPump);
//    }
//    void print()
//    {
//        PetrolPump* startPump = head;
//        int i = 0;
//        do
//        {
//            cout << "Petrol Pump No [" << i++ << "] Has " << startPump->petrol << " Literes of Petrol && " << "The Distance from this Petrol Pump to the next is: " << startPump->distance << " KM" << endl;
//            startPump = startPump->nextPump;
//        } while (startPump != head);
//    }
//    int completeTour()
//    {
//        int totalPetrolAvailable = 0, totalDistanceToCover = 0, petroLInTruck = 0, currentPetrolPump = 0;
//        PetrolPump* CurrrentPump = head;
//        for (int index = 0; index < noOfPumps; index++)
//        {
//            totalPetrolAvailable += CurrrentPump->petrol;
//            totalDistanceToCover += CurrrentPump->distance;
//            petroLInTruck += CurrrentPump->petrol - CurrrentPump->distance;
//            if (petroLInTruck < 0)
//            {
//                currentPetrolPump = index + 1;
//                petroLInTruck = 0;
//            }
//            CurrrentPump = CurrrentPump->nextPump;
//        }
//        if (totalPetrolAvailable >= totalDistanceToCover) return currentPetrolPump;
//        else return -1;
//    }
//};
//int main()
//{
//    size_t size = 0, petrol = 0, distance = 0;
//    /*cout << "Enter No Of The Petrol Pumps: ";
//    while (true)
//    {
//        cin >> size;
//        if (!cin || size > INT_MAX || size < 0)
//        {
//            cout << "Enter A Valid No Of The Petrol Pumps!: ";
//            cin.clear();
//            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//            continue;
//        }
//        else break;
//    }
//    cin.ignore();
//    system("cls");
//    circularLinkedPetrolPumps PetrolPumps(size);
//    for (int i = 0; i < size; i++)
//    {
//        cout << "Enter No Of Literes Of Petrol in This Petrol Pump: ";
//        while (true)
//        {
//            cin >> petrol;
//            if (!cin || size > INT_MAX || size < 0)
//            {
//                cout << "Enter A Valid No Of Literes Of Petrol in This Petrol Pump!: ";
//                cin.clear();
//                cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//                continue;
//            }
//            else break;
//        }
//        cin.ignore();
//        cout << "Enter How Many No Of KM's Is The Next Petrol Pump From Here: ";
//        while (true)
//        {
//            cin >> distance;
//            if (!cin || size > INT_MAX || size < 0)
//            {
//                cout << "Enter A Valid No Of Of KM's !: ";
//                cin.clear();
//                cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
//                continue;
//            }
//            else break;
//        }
//        cin.ignore();
//        PetrolPumps.addPump(petrol, distance);
//    }*/
//    circularLinkedPetrolPumps PetrolPumps(4);
//    PetrolPumps.addPump(4, 6);
//    PetrolPumps.addPump(6, 5);
//    PetrolPumps.addPump(7, 3);
//    PetrolPumps.addPump(4, 5);
//    PetrolPumps.print();
//    if (PetrolPumps.completeTour() == -1) cout << "Even Einstien Had A Stroke Finding A Possible Complete Tour!" << endl;
//    else cout << "Start from PetrolPump No [" << PetrolPumps.completeTour() << "] For A Complete Tour!" << endl << endl;
//}