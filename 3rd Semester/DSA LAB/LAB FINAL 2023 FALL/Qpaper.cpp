#include <iostream>

struct Node {
    int data;
    Node* next;
};

// Function to delete a node given only the pointer to it
void deleteNode(Node* nodeToDelete) {
    if (nodeToDelete == nullptr || nodeToDelete->next == nullptr) {
        // It should never be nullptr or the tail node based on the constraints
        std::cerr << "Error: Invalid node to delete" << std::endl;
        return;
    }

    // Copy the data from the next node to the current node
    Node* nextNode = nodeToDelete->next;
    nodeToDelete->data = nodeToDelete->next->data;

    // Adjust the link to skip the next node
    nodeToDelete= nodeToDelete->next->next;

    // Delete the next node
    delete nextNode;
}

// Helper function to print the linked list
void printList(Node* head) {
    while (head != nullptr) {
        std::cout << head->data << " -> ";
        head = head->next;
    }
    std::cout << "nullptr" << std::endl;
}

// Helper function to create a new node
Node* createNode(int data) {
    Node* newNode = new Node;
    newNode->data = data;
    newNode->next = nullptr;
    return newNode;
}

int main() {
    // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> nullptr
    Node* head = createNode(1);
    head->next = createNode(2);
    head->next->next = createNode(3);
    head->next->next->next = createNode(4);

    std::cout << "Original List: ";
    printList(head);

    // Delete node 3 (pointer to node with value 3 is provided)
    Node* nodeToDelete = head->next->next;
    deleteNode(nodeToDelete);

    std::cout << "List after deleting node: ";
    printList(head);

    return 0;
}
