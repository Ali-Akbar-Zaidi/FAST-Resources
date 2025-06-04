//#include <iostream>
//#include <vector>
//#include <string>
//#include <fstream>
//
//using namespace std;
//
//// Node structure for Huffman Tree
//struct Node {
//    char data;
//    int frequency;
//    Node* left;
//    Node* right;
//
//    Node() : data('\0'), frequency(0), left(nullptr), right(nullptr) {}
//};
//
//// User-defined Queue for Priority Queue Functionality
//struct Queue {
//    vector<Node*> nodes;
//
//    void push(Node* node) {
//        int i = 0;
//        while (i < nodes.size() && nodes[i]->frequency <= node->frequency) {
//            i++;
//        }
//        nodes.insert(nodes.begin() + i, node);
//    }
//
//    Node* pop() {
//        Node* temp = nodes.front();
//        nodes.erase(nodes.begin());
//        return temp;
//    }
//
//    bool isEmpty() {
//        return nodes.empty();
//    }
//
//    size_t size() {
//        return nodes.size();
//    }
//
//    Node* front() {
//        return nodes.front();
//    }
//};
//
//// Function to build Huffman Codes
//void buildCodes(Node* root, string code, vector<pair<char, string>>& huffmanCodes) {
//    if (!root) return;
//
//    if (!root->left && !root->right) {
//        huffmanCodes.push_back(make_pair(root->data, code));
//    }
//    buildCodes(root->left, code + "0", huffmanCodes);
//    buildCodes(root->right, code + "1", huffmanCodes);
//}
//
//// Huffman Encoding function
//void huffmanEncoding(vector<Node*>& nodes) {
//    // Build Huffman Tree
//    Queue pq;
//    for (int i = 0; i < nodes.size(); i++) {
//        pq.push(nodes[i]);
//    }
//
//    while (pq.size() > 1) {
//        Node* left = pq.pop();
//        Node* right = pq.pop();
//        Node* merged = new Node();
//        merged->frequency = left->frequency + right->frequency;
//        merged->left = left;
//        merged->right = right;
//        pq.push(merged);
//    }
//
//    // Generate Huffman Codes
//    vector<pair<char, string>> huffmanCodes;
//    buildCodes(pq.front(), "", huffmanCodes);
//
//    // Display Huffman Codes
//    cout << "Huffman Codes:" << endl;
//    for (int i = 0; i < huffmanCodes.size(); i++) {
//        cout << "Character '" << huffmanCodes[i].first << "' : Code " << huffmanCodes[i].second << endl;
//    }
//}
//
//// Main function to read file, calculate frequencies, and run Huffman encoding
//int main() {
//    string text;
//    vector<Node*> v;
//    ifstream file("abc.txt");
//
//    // Read file contents
//    while (getline(file, text)) {
//        cout << text << endl;
//    }
//    file.close();
//
//    // Calculate character frequencies
//    int len = text.length();
//    for (int i = 0; i < len; i++) {
//        bool found = false;
//        for (int j = 0; j < v.size(); j++) {
//            if (v[j]->data == text[i]) {
//                v[j]->frequency++;
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            Node* temp = new Node();
//            temp->data = text[i];
//            temp->frequency = 1;
//            v.push_back(temp);
//        }
//    }
//
//    // Display character frequencies
//    cout << "Character frequencies:" << endl;
//    for (int i = 0; i < v.size(); i++) {
//        cout << "Character '" << v[i]->data << "' : Frequency " << v[i]->frequency << endl;
//    }
//
//    // Perform Huffman encoding
//    huffmanEncoding(v);
//
//    return 0;
//}
