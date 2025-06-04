#include <iostream>
using namespace std;

class Node
{
public:
    int data;
    Node* left;
    Node* right;

    Node(int val) : data(val), left(nullptr), right(nullptr) {}
};

class BinarySearchTree
{
private:
    Node* root;

    Node* insert(Node* node, int val)
    {
        if (node == nullptr)
            return new Node(val);
        if (val < node->data)
            node->left = insert(node->left, val);
        else node->right = insert(node->right, val);
        return node;
    }

    int height(Node* node)
    {
        if (node == nullptr) 
            return 0;
        int leftHeight = height(node->left);
        int rightHeight = height(node->right);
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
    }

    Node* rotateRight(Node* node)
    {
        Node* newRoot = node->left;
        node->left = newRoot->right;
        newRoot->right = node;
        return newRoot;
    }

    Node* rotateLeft(Node* node) 
    {
        Node* newRoot = node->right;
        node->right = newRoot->left;
        newRoot->left = node;
        return newRoot;
    }

    Node* balance(Node* node)
    {
        if (node == nullptr) return nullptr;

        int leftHeight = height(node->left);
        int rightHeight = height(node->right);

        if (leftHeight > rightHeight + 1)
        {
            return rotateRight(node);
        }
        else if (rightHeight > leftHeight + 1)
        {
            return rotateLeft(node);
        }
        return node;
    }

    void preorderTraversal(Node* node)
    {
        if (node != nullptr) {
            cout << node->data << " ";
            preorderTraversal(node->left);
            preorderTraversal(node->right);
        }
    }

    bool isLeftSkewed(Node* node)
    {
        while (node != nullptr && node->right == nullptr)
        {
            node = node->left;
        }
        return node == nullptr;
    }

    bool isRightSkewed(Node* node) {
        while (node != nullptr && node->left == nullptr) 
        {
            node = node->right;
        }
        return node == nullptr;
    }

public:
    BinarySearchTree() : root(nullptr) {}

    void insert(int val) {
        root = insert(root, val);
    }

    string skewed() {
        if (isLeftSkewed(root)) return "Left-skewed";
        if (isRightSkewed(root)) return "Right-skewed";
        return "Neither left-skewed nor right-skewed";
    }

    void balance() {
        root = balance(root);
    }

    void preorder() {
        preorderTraversal(root);
        cout << endl;
    }
};

int main() 
{
    BinarySearchTree bst;
    int values[] = { 3, 2, 1 };
    for (int i = 0; i < 3; i++) {
        bst.insert(values[i]);
    }

    cout << "Skewed status: " << bst.skewed() << endl;
    bst.balance();
    cout << "Preorder traversal after balancing: ";
    bst.preorder();

    return 0;
}