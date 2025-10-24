#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
void placeThulla(vector<double> positions, vector<double>& Thulla, double d)
{
   if (positions.size() == 0) return; // no paintings no thullas needed

   double initialPlacement = (positions.size()>1)? positions[0] + d : positions[0]; // if only one painting then only one guard required

   Thulla.push_back(initialPlacement); // guard placed d from left of the initial painting to cover most ground

   for (int i = 1; i < positions.size(); i++)
   {
       if(initialPlacement+d<positions[positions.size() - 1]) // should work for all distances less than last painting position
       {
           if (initialPlacement + d < positions[i]) // if painting is out of reach of guard
           {
               if (positions[i] + d > positions[positions.size() - 1]) // if new position of guard outside the distance of last painting

                   initialPlacement = positions[positions.size() - 1]; // then initialise the guards position to the position of last painting

               else
                   initialPlacement = positions[i] + d; //else place the guard to the left of the new painting position to cover max ground

               Thulla.push_back(initialPlacement);
           }
       }
   }
}
void displayThullas(vector<double> Thulla)
{
   cout << "Total No of Thullas Needed: " << Thulla.size() << " , At The Following Positions: [ ";
   if (Thulla.size() == 0)
   {
       cout << "]"; return;
   }
   for (int i = 0; i < Thulla.size() - 1; i++)
   {
       cout << Thulla[i] << " , ";
   }

   cout << Thulla[Thulla.size() - 1] << " ]" << endl << endl;
}
int main()
{
   vector<double> positions = { 1,5,9 }; // positions of paintings
   double d = 2;

   //sort(positions.begin(),positions.end()); // Incase positions are not already sorted



   vector<double> Thulla; //stores positions of guards
   placeThulla(positions, Thulla,d); //function to place guards in thier optimal positions
   
   displayThullas(Thulla); // print positions of guards

   return 0;
}