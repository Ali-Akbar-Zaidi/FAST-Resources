#include <iostream>
#include <queue>
using namespace std;

struct Node 
{
    int neighbor;
    Node* next;

    Node(int n)
    {
        neighbor = n;
        next = nullptr;
    }
};

class Graph 
{
private:
    int vertices;        // Number of vertices in the graph
    Node** adjList;      // Adjacency list representation

public:
    // Constructor
    Graph(int v)
    {
        vertices = v;
        adjList = new Node * [vertices];
        for (int i = 0; i < vertices; i++)
        {
            adjList[i] = nullptr;
        }
    }

    // Function to create an edge
    void addEdge(int u, int v)
    {
        Node* newNode = new Node(v);
        newNode->next = adjList[u];
        adjList[u] = newNode;

        // Add reverse edge for undirected graph
        newNode = new Node(u);
        newNode->next = adjList[v];
        adjList[v] = newNode;
    }

    // BFS function
    void bfs(int start)
    {
        bool* visited = new bool[vertices] {0};
        queue<int> q;
        visited[start] = true;
        q.push(start);

        cout << "BFS Traversal starting from vertex " << start << ": ";
        while (!q.empty())
        {
            int current = q.front();
            q.pop();
            cout << current << " ";

            // Visit all neighbors
            Node* temp = adjList[current];
            while (temp != nullptr) 
            {
                if (!visited[temp->neighbor]) 
                {
                    visited[temp->neighbor] = true;
                    q.push(temp->neighbor);
                }
                temp = temp->next;
            }
            delete temp;
        }
        cout << endl;
        delete[] visited;
    }

    // Function to count connected components
    int connectedComponents()
    {
        bool* visited = new bool[vertices] {0};
        int components = 0;
        for (int i = 0; i < vertices; i++) 
        {
            if (!visited[i]) 
            {
                bfsForConnectedComponents(i, visited);
                components++;
            }
        }
        delete[] visited;
        return components;
    }

    void bfsForConnectedComponents(int start, bool visited[]) 
    {
        queue<int> q;
        visited[start] = true;
        q.push(start);

        while (!q.empty())

        {
            int current = q.front();
            q.pop();

            Node* temp = adjList[current];
            while (temp != nullptr)
            {
                if (!visited[temp->neighbor]) 
                {
                    visited[temp->neighbor] = true;
                    q.push(temp->neighbor);
                }
                temp = temp->next;
            }
        }
    }

    bool detectCycle()
    {
        bool* visited = new bool[vertices] {0};

        // Aapka sawaal bilkul valid hai.Cycle detection ke liye loop lagana zaruri kyun hai jab humein sirf yeh check karna hai k eik hi graph mein cycle exist karta hai ya nahi ?
        // Iska jawab graph ke connected components ki wajah se hai.Agar graph ek single connected component hai, tab loop unnecessary lagta hai.
        // Lekin agar graph disconnected hai(multiple connected components hain), toh har component ko separately check karna padta hai.
        for (int i = 0; i < vertices; i++)
        {
            if (!visited[i])
            {
                if (bfsForCycleDetection(i, visited))
                {
                    delete[] visited;
                    return true;
                }
            }
        }

        delete[] visited;
        return false;
    }

    bool bfsForCycleDetection(int start, bool visited[])
    {
        queue<int> q;       // Queue to track current nodes
        int* parent = new int[vertices]; // Array to track parents
        for (int i = 0; i < vertices; i++)
        {
            parent[i] = -1; // Initialize all parent nodes as -1
        }

        visited[start] = true;
        q.push(start);

        while (!q.empty())
        {
            int current = q.front();
            q.pop();

            Node* temp = adjList[current];
            while (temp != nullptr)
            {
                if (!visited[temp->neighbor])
                {
                    visited[temp->neighbor] = true;
                    parent[temp->neighbor] = current;
                    q.push(temp->neighbor);
                }
                else if (temp->neighbor != parent[current])
                {
                    // If the neighbor is visited and not the parent, cycle detected
                    delete[] parent;
                    return true;
                }
                temp = temp->next;
            }
        }

        delete[] parent;
        return false;
    }

    // Print adjacency list
    void printAdjList() 
    {
        cout << "Adjacency List:" << endl;
        for (int i = 0; i < vertices; i++) 
        {
            cout << i << ": ";
            Node* temp = adjList[i];
            while (temp != nullptr) 
            {
                cout << temp->neighbor << " ";
                temp = temp->next;
            }
            cout << endl;
        }
    }

    // Destructor to free memory i.e destructor for linked list 
    ~Graph() 
    {
        for (int i = 0; i < vertices; i++) 
        {
            Node* current = adjList[i];
            while (current != nullptr) 
            {
                Node* temp = current;
                current = current->next;
                delete temp;
            }
        }
        delete[] adjList;
    }
};

int main() 
{
    Graph g(7);

    // Adding edges to the graph
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 0); // Cycle

    g.addEdge(4, 5);
    g.addEdge(5, 6);

    // Perform BFS
    g.bfs(0);

    // Print adjacency list
    g.printAdjList();

    // Count connected components
    cout << "Number of connected components: " << g.connectedComponents() << endl;

    // Detect cycle
    if (g.detectCycle()) 
    {
        cout << "Cycle detected in the graph." << endl;
    }
    else 
    {
        cout << "No cycle detected in the graph." << endl;
    }

    return 0;
}