#include <iostream>
#include <list>
#include<string>
using namespace std;

struct Node
{
public:
    int id;
    string name;
    int weight;
    Node(int Id, string Name, int Weight)
    {
        id = Id;
        name = Name;
        weight = Weight;
    }
};

class Graph
{

private:
    bool cycleDetected;

public:
    int v;           // Number of vertices
    list<Node>* adj; // Adjacency lists
    Graph(int n)     // Constructor
    {
        v = n;
        adj = new list<Node>[v]; // Create an array of lists

        for (int i = 0; i < v; i++)
        {
            Node* newNode = new Node(i, "Node" + to_string(i), 0);
        }
    }

    void input(int n, int w) // Take input (edges) from the user
    {
        if (n >= v || w >= v)
        {
            cout << "Invalid Input" << endl;
            return;
        }
        Node* newNode = new Node(w, "Node" + to_string(w), 0);
        adj[n].push_back(*newNode); // Add edge from n to w
        Node* newNode2 = new Node(n, "Node" + to_string(n), 0);
        adj[w].push_back(*newNode2); // Add edge from w to n
    }

    void Exp()
    {
        // adjacency list
        for (int i = 0; i < v; i++)
        {
            cout << "Adjacency list of vertex " << i << " : ";
            for (Node node : adj[i])
            {
                cout << node.id << " ";
            }
            cout << endl;
        }
    }

    // Depth First Search

    void DFS()
    {
        bool* visited = new bool[v];
        string result = "";
        for (int i = 0; i < v; i++)
        {
            visited[i] = false;
        }
        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                DFSrecursion(i, visited, result, -1); // Pass -1 as parent of root node
            }
        }

        cout << result << endl;
    }

    void DFSrecursion(int v, bool visited[], string& result, int parent)
    {
        visited[v] = true;
        result += to_string(v) + " ";
        for (Node node : adj[v])
        {
            if (!visited[node.id])
            {
                DFSrecursion(node.id, visited, result, v);
            }
            else if (node.id != parent)
            {
                cycleDetected = true;
            }
        }
    }

    // Detect Cycle in Graph

    bool cycle()
    {
        bool* visited = new bool[v];
        string res = "";
        for (int i = 0; i < v; i++)
        {
            visited[i] = false;
        }
        cycleDetected = false;
        // Perform DFS for all unvisited vertices
        for (int i = 0; i < v; i++)
        {
            if (!visited[i] && !cycleDetected)
            {
                DFSrecursion(i, visited, res, -1); // Start DFS
            }
        }
        return cycleDetected; // Return true if cycle is detected
    }

    //find connected components in graph

    void findvertex()
    {
        bool* visited = new bool[v];
        string res = "";
        for (int i = 0; i < v; i++)
        {
            visited[i] = false;
        }
        int count = 0;

        // Iterate over all vertices
        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                // If the vertex is not visited, it's the start of a new component
                cout << "Connected Component: ";
                DFSConnect(i, visited); // Perform DFS to find all nodes in this component
                cout << endl;
                count++;
            }
        }

        cout << "Total Connected Component Graphs : " << count << endl;
    }

    void DFSConnect(int v, bool visited[])
    {
        visited[v] = true;
        cout << v << " ";

        for (Node& node : adj[v])
        {
            if (!visited[node.id])
            {
                DFSConnect(node.id, visited);
            }
        }
    }

    ~Graph() // Destructor
    {
        delete[] adj;
    }
};

int main()
{
    Graph g(10);

    g.input(0, 1);
    g.input(1, 2);
    g.input(2, 3);
    g.input(4, 5);
    g.input(6, 7);
    g.input(7, 8);
    g.input(8, 9);
    g.input(6, 9);

    cout << "Adjacency List of the Graph:" << endl;
    g.Exp();

    cout << "\nDepth First Search Traversal:" << endl;
    g.DFS();

    cout << "\nCycle Detection Result: ";
    if (g.cycle())
        cout << "Cycle Detected" << endl;
    else
        cout << "No Cycle Detected" << endl;

    cout << "\nConnected Components:" << endl;
    g.findvertex();

    return 0;
}