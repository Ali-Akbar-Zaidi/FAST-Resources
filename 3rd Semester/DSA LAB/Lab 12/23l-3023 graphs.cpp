#include <iostream>
using namespace std;

class Graph
{
public:
	int Node_No;
	int** matrix;
	int** list;
	int* count;

	Graph() : Node_No(0), matrix(nullptr), list(nullptr), count(nullptr) {}

	Graph(int n) : Node_No(n)
	{
		matrix = new int* [Node_No];
		list = new int* [Node_No];
		count = new int[Node_No] {0};
		for (int i = 0; i < Node_No; i++)
		{
			matrix[i] = new int[Node_No] {0}; list[i] = nullptr;
		}
	}

	~Graph()
	{
		for (int i = 0; i < Node_No; i++)
		{
			delete[] matrix[i]; delete[] list[i];
		}
		delete[] matrix; delete[] list; delete[] count;
	}

	void addConnections(int node, int* connections, int size)
	{
		list[node] = new int[size]; count[node] = size;

		for (int j = 0; j < size; j++)
		{
			int connectedNode = connections[j];
			matrix[node][connectedNode] = 1;
			list[node][j] = connectedNode;
		}
	}
	void displaylist()
	{
		for (int i = 0; i < Node_No; i++)
		{
			cout << "Node " << i << " is connected to: ";
			if (list[i] != nullptr)
				for (int j = 0; j < count[i]; j++) { cout << list[i][j] << " "; }
			cout << endl;
		}
	}
	void displaymatrix()
	{
		cout << "Adjacency Matrix: " << endl;
		for (int i = 0; i < Node_No; i++)
		{
			cout << "Connections of Node " << i << ": ";
			for (int j = 0; j < Node_No; j++) { cout << matrix[i][j] << " "; }
			cout << endl;
		}
	}
};
int main()
{
	Graph myGraph(4);
	int node0connection[2] = { 1, 2 }; myGraph.addConnections(0, node0connection, 2);

	int node1connection[1] = { 3 }; myGraph.addConnections(1, node1connection, 1);

	int node2connection[2] = { 0, 3 }; myGraph.addConnections(2, node2connection, 2);

	int node3connection[1] = { 1 }; myGraph.addConnections(3, node3connection, 1);

	myGraph.displaylist(); myGraph.displaymatrix();

	system("pause");
}