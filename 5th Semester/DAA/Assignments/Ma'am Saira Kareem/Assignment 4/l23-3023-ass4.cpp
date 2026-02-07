//Q1

#include<iostream>
#include<vector>
#include<queue>
using namespace std;

vector<long long> countShortestRoutes(const vector<vector<int>>& graph, int startPoint)
{
    int N = graph.size();

    vector<int> distance(N, -1);         // shortest distance from source
    vector<long long> ways(N, 0);        // number of shortest paths
    queue<int> q;

    distance[startPoint] = 0;
    ways[startPoint] = 1;
    q.push(startPoint);

    while (!q.empty())
    {
        int curr = q.front();
        q.pop();

        for (int nxt : graph[curr])
        {
            // Node discovered first time
            if (distance[nxt] == -1)
            {
                distance[nxt] = distance[curr] + 1;
                ways[nxt] = ways[curr];
                q.push(nxt);
            }
            // Found another shortest route
            else if (distance[nxt] == distance[curr] + 1)
            {
                ways[nxt] += ways[curr];
            }
        }
    }

    return ways;
}

int main()
{
    int V, E;
    cin >> V >> E;

    vector<vector<int>> graph(V);

    for (int i = 0; i < E; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    int source;
    cin >> source;

    vector<long long> result = countShortestRoutes(graph, source);

    for (int i = 0; i < V; i++)
    {
        cout << "Shortest paths from " << source
            << " to " << i << " : "
            << result[i] << "\n";
    }

    return 0;
}

//Q2

 #include<iostream>
#include<vector>
#include<queue>
#include<stack>
using namespace std;

struct SCC_Decomposer {
    int N, tick = 0, compCount = 0;
    vector<vector<int>> adj;
    vector<int> enter, low, onStack, compID;
    stack<int> stk;

    SCC_Decomposer(int n) : N(n) {
        adj.resize(N);
        enter.assign(N, -1);
        low.resize(N);
        onStack.assign(N, 0);
        compID.assign(N, -1);
    }

    void link(int u, int v) { adj[u].push_back(v); }

    void explore(int node) {
        enter[node] = low[node] = tick++;
        stk.push(node);
        onStack[node] = 1;

        for (int nxt : adj[node]) {
            if (enter[nxt] == -1) {
                explore(nxt);
                low[node] = min(low[node], low[nxt]);
            }
            else if (onStack[nxt]) {
                low[node] = min(low[node], enter[nxt]);
            }
        }

        // root of SCC
        if (low[node] == enter[node]) {
            while (true) {
                int v = stk.top();
                stk.pop();
                onStack[v] = 0;
                compID[v] = compCount;
                if (v == node) break;
            }
            compCount++;
        }
    }

    int run() {
        for (int i = 0; i < N; i++)
            if (enter[i] == -1)
                explore(i);
        return compCount;
    }
};

bool isChain(const vector<vector<int>>& compEdges, int C) {
    vector<int> indeg(C, 0), outdeg(C, 0);
    int edgeCount = 0;

    for (int u = 0; u < C; u++) {
        for (int v : compEdges[u]) {
            outdeg[u]++;
            indeg[v]++;
            edgeCount++;
        }
    }

    int roots = 0, sinks = 0;

    for (int i = 0; i < C; i++) {
        if (indeg[i] == 0) roots++;
        if (outdeg[i] == 0) sinks++;
    }

    if (roots == 1 && sinks == 1 && edgeCount == C - 1)
        return true;

    return false;
}

int main() {
    
    int V, E;
    cin >> V >> E;

    SCC_Decomposer scc(V);

    for (int i = 0; i < E; i++) {
        int a, b;
        cin >> a >> b;
        scc.link(a, b);
    }

    int C = scc.run();

    if (C == 1) {
        cout << "YES\n";
        return 0;
    }

    vector<vector<int>> compGraph(C);

    for (int u = 0; u < V; u++) {
        for (int v : scc.adj[u]) {
            if (scc.compID[u] != scc.compID[v]) {
                compGraph[scc.compID[u]].push_back(scc.compID[v]);
            }
        }
    }

    cout << (isChain(compGraph, C) ? "YES\n" : "NO\n");

    return 0;
}

//Q3

 #include<iostream>
#include<vector>
#include<queue>
#include<stack>
using namespace std;

int main() {

    int n, m;
    cin >> n >> m;

    vector<vector<int>> outgoing(n);
    vector<int> indeg(n, 0);

    // read edges
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        outgoing[a].push_back(b);
        indeg[b]++;
    }

    queue<int> zeroBag;
    for (int i = 0; i < n; i++)
        if (indeg[i] == 0)
            zeroBag.push(i);

    vector<int> topo; topo.reserve(n);

    while (!zeroBag.empty()) {
        int u = zeroBag.front();
        zeroBag.pop();
        topo.push_back(u);

        // "remove" edges u -> v
        for (int v : outgoing[u]) {
            indeg[v]--;
            if (indeg[v] == 0)
                zeroBag.push(v);
        }
    }

    if ((int)topo.size() != n) {
        cout << "Graph is not a DAG.\n";
        return 0;
    }

    for (int x : topo)
        cout << x << " ";

    cout << "\n";
    return 0;
}

//Q4

#include<iostream>
#include<vector>
#include<queue>
#include<stack>
using namespace std;

struct Edge {
    int from, to;
    long long w;
};

int main() {

    int n, m;
    cin >> n >> m;

    vector<Edge> edges(m);
    for (int i = 0; i < m; i++)
        cin >> edges[i].from >> edges[i].to >> edges[i].w;

    int start, goal;
    cin >> start >> goal;

    const long long INF = (1LL << 60);

    vector<vector<long long>> dist(11, vector<long long>(n, INF));
    dist[0][start] = 0;

    for (int k = 1; k <= 10; k++) {
        dist[k] = dist[k - 1];

        for (auto& e : edges) {
            if (dist[k - 1][e.from] != INF) {
                long long cand = dist[k - 1][e.from] + e.w;
                dist[k][e.to] = min(dist[k][e.to], cand);
            }
        }
    }

    long long ans = INF;
    for (int k = 0; k <= 10; k++)
        ans = min(ans, dist[k][goal]);

    if (ans == INF)
        cout << "No path exists\n";
    else
        cout << ans << "\n";

    return 0;
}




