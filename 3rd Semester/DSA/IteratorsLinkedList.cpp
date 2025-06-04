#include <iostream>
using namespace std;

template <class T>
class node{
    public:
        T data;
        node<T> *next;
        node<T> *prev;
        node(){
            next = nullptr;
        }
};

template <class T>
class LinkedList{
    
    public:
    
    template<typename Type>
    class iterator{
        public:
        node<Type> *p;
            iterator(){
                p = nullptr;
            }
            
            iterator(node<T> * n){
                p = n;
            }
            
            iterator(const iterator<T>& i){
                this->p = i.p;
            }
            
            iterator& operator++ (){
                p = p->next;
                return *this;
            }
            
            iterator& operator-- (){
                p = p->prev;
                return *this;
            }
            
            Type operator* (){
                return p->data;
            }
            
            Type operator[] (int index){
                //p=head;
                for (int i=0; i<index; i++)
                    p=p->next;
                return p->data;
            }
            
            bool operator== (const iterator<Type>& i){
                return this->p == i.p;
            }
            bool operator!= (const iterator<Type>& i){
                return this->p != i.p;
            }
        
    };
    
    public:
        node<T>* head;
        node<T>* tail;
    LinkedList(){
        head = nullptr;
        tail = nullptr;
    }   
    
    iterator<T> Begin(){
        return iterator<T>(head);
    }
    
    iterator<T> Rbegin(){
        return iterator<T>(tail);
    }
    
    iterator<T> End(){
        return iterator<T>(nullptr);
    }
    
    void insert (T val){
        node<T>* newnode = new node<T>;
        newnode->data = val;
        newnode->next = nullptr;
        newnode->prev = nullptr;
        if (head==nullptr)
            head = tail = newnode;
        else{
            tail->next = newnode;
            newnode->prev = tail;
            tail = newnode;
        }
    }
};

int main()
{
    LinkedList<int> L;
    L.insert(1);
    L.insert(2);
    L.insert(3);
    L.insert(4);
    
    for (LinkedList<int>::iterator<int> i = L.Begin(); i!=L.End(); ++i)
    {
        cout<<*i<<"  ";
        
    }
    cout<<endl;
    
    for (LinkedList<int>::iterator<int> i = L.Rbegin(); i!=L.End(); --i)
    {
        cout<<*i<<"  ";
        
    }
    cout<<endl;
    LinkedList<int>::iterator<int> i= L.Rbegin();
    cout<<i[0];
    
    return 0;
}
