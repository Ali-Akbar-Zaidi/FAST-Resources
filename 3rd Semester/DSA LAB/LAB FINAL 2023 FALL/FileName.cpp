//#include<iostream>
//using namespace std;
//#include<string>
//class BST
//{
//struct Node2dBST
//{
// int x, y;
// //string shapeName;
// Node2dBST* lChild;
// Node2dBST* rChild;
// Node2dBST(int x,int y):x(x),y(y),lChild(nullptr),rChild(nullptr){}
//};
//Node2dBST* root;
//public:
//BST()
//{
//	root = nullptr;
//}
//void Insert(int x,int y)
//{
//	Node2dBST* node = new Node2dBST(x, y);
//	int n = 0;
//	insert(node, root, n);
//}
//void insert(Node2dBST* node, Node2dBST*& root,int& level)
//{
//	if (!root)
//		root = node;
//	else
//	{
//		if (root->x > node->x && level % 2 == 0)
//			insert(node, root->lChild, ++level);
//		else if (root->x < node->x && level % 2 == 0)
//			insert(node, root->rChild, ++level);
//		else if (root->y > node->y && level % 2 == 1)
//			insert(node, root->lChild, ++level);
//		else if (root->y < node->y && level % 2 == 1)
//			insert(node, root->rChild, ++level);
//	}
//}
//void print()
//{
//	preorder(root);
//}
//void preorder(Node2dBST* node)
//{
//	if(node)
//	{
//		cout << node->x << " ";
//		preorder(node->lChild);
//		preorder(node->rChild);
//	}
//}
//};
//int main()
//{
//	BST tree;
//	tree.Insert(5,3);
//	tree.Insert(3,5);
//	tree.Insert(0,2);
//	tree.Insert(3,8);
//	tree.Insert(7,7);
//	tree.Insert(1,6);
//	tree.Insert(7,2);
//	tree.print();
//	
//}