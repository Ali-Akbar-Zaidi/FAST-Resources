//#include <iostream>
//using namespace std;
//
//struct node {
//    int key;
//    node* lchild, * rchild, * ps;
//};
//
//void connectSuccessors(node* root, node*& prev) {
//    if (!root) return;
//
//    // In-order traversal: left subtree -> current node -> right subtree
//    connectSuccessors(root->lchild, prev);
//
//    // Connect the previous node's ps to the current node
//    if (prev) prev->ps = root;
//    prev = root; // Update the previous node to the current node
//
//    connectSuccessors(root->rchild, prev);
//}
//
//void connectSuccessors(node* root) {
//    node* prev = nullptr; // Initialize the previous pointer
//    connectSuccessors(root, prev);
//}
//
//int main() {
//    // Example tree creation
//    node* root = new node{ 8, nullptr, nullptr, nullptr };
//    root->lchild = new node{ 3, nullptr, nullptr, nullptr };
//    root->rchild = new node{ 10, nullptr, nullptr, nullptr };
//    root->lchild->lchild = new node{ 1, nullptr, nullptr, nullptr };
//    root->lchild->rchild = new node{ 6, nullptr, nullptr, nullptr };
//    root->lchild->rchild->lchild = new node{ 4, nullptr, nullptr, nullptr };
//    root->lchild->rchild->rchild = new node{ 7, nullptr, nullptr, nullptr };
//    root->rchild->rchild = new node{ 14, nullptr, nullptr, nullptr };
//    root->rchild->rchild->lchild = new node{ 13, nullptr, nullptr, nullptr };
//
//    // Connect successors
//    connectSuccessors(root);
//
//    // Print ps connections (in-order traversal)
//    node* current = root;
//    while (current && current->lchild) current = current->lchild; // Go to the smallest node
//
//    cout << "In-order ps connections: ";
//    while (current) {
//        cout << current->key << " -> ";
//        if (!current->ps) cout << "null" << " ";
//        //else cout << "null";
//        current = current->ps;
//    }
//    cout << endl;
//
//    return 0;
//}
