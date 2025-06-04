#include<iostream>
using namespace std;
struct node
{
    node* next;      // Pointer to the next node in the adjacency list
    int neighbour;   // Value of the neighboring vertex

    node(int n)
    {
        neighbour = n;
        next = nullptr;
    }
};

class graph
{
private:
    int vertex;       // Number of vertices in the graph
    node* list[100] = { nullptr };  // Array of adjacency lists (maximum 100 vertices)

public:
 
    graph(int n) 
    {
        vertex = n; 
        
    }

    // Function to create an edge in the adjacency list
    // A kind of insert at head
    void createlist(int n, int w)
    {
        node* newnode = new node(w); // Create a new node with the neighbor vertex 'w'
        newnode->next = list[n];     // Point the new node to the current head of the list
        list[n] = newnode;           // Update the head of the list to the new node
    }

    // Helper function to start DFS traversal from a given vertex
    void dfshelper(int start)
    {
        // -1 = unvisited , 0 = visited and inside the stack/queue , 
        //  1 = visited and poped out of the stack/queue

        // Array to keep track of the status of vertices
        int* status = new int[vertex];

        fill_n(status, vertex, -1);

        dfs(start, status); // Call DFS for the starting vertex
        cout << endl;

        delete[] status; 
    }

    // Recursive DFS function
    void dfs(int start, int status[])
    {
        // since we are not considering stack here,it is a recursive solution
        // but according to my understanding pushing the starting node into the stack i.e in this case i.e recursive case
        // status[start] = 0 (otherwise status = 0 indicates pushed inside the stack and visted)
        // and then look for its adjecent neighbours i.e while traversing its adjency list , the first
        // unvisted neighbour is pushed into the stack and futher its neighbours
        // i.e the one pushed into the stack ( the one at the top of the stack) are looked for and this continues untill 
        // a dead end i.e a node whose all adjacent neighbours are already visited , is reached 
        // then there comes a need to pop from the stack 
        // the poped elements' status will be now marked as 1 (where 1 indicates poped from the stack and visited) , after poping then check the top of stack
        // and again traverse the adjency list for the top element of the stack if there are any unvisited neighbours remaining
        // dfs ends when stack becomes empty .Remember that elements are only poped from the stack when dead end is reached

        status[start] = 0;    // Mark the current vertex as visiting 
        cout << start << " "; // Print the current vertex

        // Traverse the adjacency list of the current vertex
        // same wohi concept hai jab hum ne linked list ko traverse karna hota tha from head till end
        // to hum current bna k head se start kartay thay 
        node* sidenode = list[start];
        while (sidenode != nullptr)
        {
            // If the neighboring vertex is unvisited, call DFS recursively
            // the first unvisited neighbour 
            if (status[sidenode->neighbour] == -1)
            {
                // recursive call to dfs
                dfs(sidenode->neighbour, status);
            }

            sidenode = sidenode->next; 
        }
        // poora while traverse karnay k baad bhi if condition true nahi hui
        // yani current list ka koi easa vertex nahi jo unvisited ho i.e indication of dead end therefore 
        // status =1 i.e pop from the stack 

        // Mark the vertex as fully visited (1)
        status[start] = 1; 
    }

    // Function to find and print connected components in the graph
    // Number of connected components = how many times dfs is called 

    void connectedcomponents(int v)
    {
        // integer array to track the status of visited and unvisited vertices in this case unvisited is 
        // indicated by -1 and visited by 1

        int* status = new int[vertex]; 
        fill_n(status, vertex, -1);

        int count = 0; // to count the number of connected components
        for (int i = 0; i < vertex; i++)
        {
            if (status[i] == -1) 
            {   // If the vertex is unvisited, start a new DFS
                dfs(i, status);
                // to differentiate between connected components 
                cout << endl; 
                count++;
            }
        }

        cout << "The count is " << count << endl; 
        delete[] status; 

    }

    // Function to detect a cycle in the graph starting from a given vertex
    // hum ne ne obseve kia k kis node ka adjacent neighbour easa bhi hai jo visited to hai 
    // but stack mei exist karta hai i.e status = 0 , agar easi koi condition hai to cucle exist karta hai

    void detectcycle(int start) 
    {
        // integer array whose size is equal to the nymber of nodes i.e number of vertices 
        // to track the status of visited and unvisited vertices in this case unvisited is 
        // indicated by -1 and visited by 1
        int* status = new int[vertex]; 

        for (int i = 0; i < vertex; i++)
        {
            // initially all vertices are considered unvisited thus initialized as -1
            status[i] = -1;
        }

        bool cycleFound = detectsyclehelper(start, status); 

        if (cycleFound) 
        {
            cout << "The cycle exists" << endl;
        }
        else
        {
            cout << "Cycle doesn't exist" << endl;
        }

        delete[] status; 
    }

    bool detectsyclehelper(int start, int status[]) 
    {
        // sara dfs wala code hai par hum us ko yahan call nahi kar saktay q k
        // additional condition in between add honi hai 

        status[start] = 0;

        node* sidenode = list[start];

        while (sidenode != nullptr)
        {
            int span = sidenode->neighbour;

            // If a vertex is already visited and exists inside the stack, a cycle is detected
            if (status[span] == 0) 
            {
                cout << "cycle detected" << endl;
                // span adjacent neighbour ki wo position hai jissay check karnay se pata chala
                // k cycle exist karta hai k nahi lehaza agar hum issay nikaal detay hain to cycle 
                // existance khatam ho jaye gi na

                remove(start, span); // Remove the edge causing the cycle

                return true;
            }

            // Recursive call for unvisited vertices
            if (status[sidenode->neighbour] == -1)
            {
                if (detectsyclehelper(sidenode->neighbour, status))
                {
                    return true;
                }
            }

            sidenode = sidenode->next; // Move to the next neighbor
        }

        status[start] = 1; // Mark the vertex as fully visited (1)
        // mei ne while loop poora traverse kia magar koi easa index nahi mila 
        // jis ka neighbour already visited ho or stack k anar exixst karta ho i.e status = 0
        // is ka matlab cucle detected = false

        return false; 
    }

    void remove(int u, int v)
    {
        // u position of that particular vertex whose one of adjacent neighbour forms the cycle,
        // thus removing that neighbour to remove cycle because cycle exists because of it
        // v us node ki position i.e wo neighbour hai jissay nikalna hai i.e remove karna hai
        // v is the position of the particular neighbour of that vertex , cycle exists due to existence of v
        
        // Deletion process in singly linked list 
        node* prev = nullptr;
        // current = vertex whose neighbour forms a cycle 
        node* current = list[u];
        // Traverse the adjacency list to find and remove the edge
        while (current != nullptr) 
        {
            if (current->neighbour == v) 
            {
                if (prev == nullptr)
                {
                    // Delete from head 
                    list[u] = current->next; // Update the head if the first node is removed
                }
                else
                { 
                    // delete from middle
                    prev->next = current->next; // Update the previous node's next pointer
                }

                delete current; // Free the removed node's memory
                break;
            }
            prev = current;
            current = current->next;
        }
    }

    // Function to print the adjacency list of the graph
    void printlist()
    {
        // har vertex k liye hum list print kar rahay hain i.e wo node or us k neighbours

        cout << "Adjacency List:" << endl;
        for (int i = 0; i < vertex; i++) 
        {
            cout << "Vertex / Node [ " << i << " ] : ";
            node* current = list[i];// i.e current = list ka head
            while (current != nullptr) 
            {
                cout << current->neighbour << " ";
                current = current->next;
            }
            cout << endl;
        }
    }
};

int main()
{
    graph g(7); // Create a graph with 7 vertices

    // Add edges to the graph (undirected)
    g.createlist(0, 1); // A <-> B
    g.createlist(1, 0);
    g.createlist(1, 2); // B <-> C
    g.createlist(2, 1);
    g.createlist(2, 3); // C <-> D
    g.createlist(3, 2);
    g.createlist(3, 0); // D <-> A (Cycle)

    g.createlist(4, 5); // E <-> F
    g.createlist(5, 4);
    g.createlist(6, 7); // G <-> H
    g.createlist(7, 6);

    // Perform DFS starting from vertex 0
    cout << "DFS traversal starting from vertex A (0): ";
    g.dfshelper(0);

    // Print the adjacency list
    g.printlist();

    // Find and print the number of connected components
    cout << "Number of connected components in the graph: ";
    g.connectedcomponents(7);

    // Detect and handle cycles in the graph
    g.detectcycle(0);
    g.printlist();

    return 0;
}
