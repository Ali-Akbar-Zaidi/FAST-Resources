#include <iostream>
#include <queue>
using namespace std;
const int V = 5;

int findDiameter(int G[][V], int src)
{

}
//find diameter to all nodes.
int computeDiameter(int G[][V])
{
	int diameter = -1;
	for (int i = 0; i < V; i++)
	{
		int d = findDiameter(G, i);
		if (d > diameter)
			diameter = d;
	}
	return diameter;
}
int main()
{
	int G[][V] ={ 
	{ 0, 1, 1, 1, 0 },
	{ 1, 0, 1, 0, 0 },
	{ 1, 1, 0, 0, 0 },
	{ 1, 0, 0, 0, 1 },
	{ 0, 0, 0, 1, 0 }, };
	cout << "Diameter of Graph is : "<< computeDiameter(G) << endl;
	system("Pause");
}
