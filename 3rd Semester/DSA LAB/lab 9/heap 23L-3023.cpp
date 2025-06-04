#include <iostream>
using namespace std;

template <typename k, typename v>
struct HeapItem {
    k key;
    v value;
};

template <typename k, typename v>
class MinHeap {
    HeapItem<k, v>* arr;
    int capacity, totalItems;

    void doubleCapacity() {
        HeapItem<k, v>* temp = new HeapItem<k, v>[capacity * 2];
        for (int i = 0; i < totalItems; ++i) temp[i] = arr[i];
        delete[] arr;
        arr = temp;
        capacity *= 2;
    }

    void heapifyUp(int idx) {
        while (idx > 0 && arr[idx].key < arr[(idx - 1) / 2].key) {
            HeapItem<k, v> temp = arr[idx];
            arr[idx] = arr[(idx - 1) / 2];
            arr[(idx - 1) / 2] = temp;
            idx = (idx - 1) / 2;
        }
    }

    void heapifyDown(int idx) {
        int smallest = idx, left = 2 * idx + 1, right = 2 * idx + 2;
        if (left < totalItems && arr[left].key < arr[smallest].key) smallest = left;
        if (right < totalItems && arr[right].key < arr[smallest].key) smallest = right;
        if (smallest != idx) {
            HeapItem<k, v> temp = arr[idx];
            arr[idx] = arr[smallest];
            arr[smallest] = temp;
            heapifyDown(smallest);
        }
    }

public:
    MinHeap() : arr(nullptr), capacity(0), totalItems(0) {}
    MinHeap(int _capacity) : capacity(_capacity), totalItems(0) { arr = new HeapItem<k, v>[capacity]; }
    ~MinHeap() { delete[] arr; }

    void insert(k key, v value) {
        if (totalItems == capacity) doubleCapacity();
        arr[totalItems++] = { key, value };
        heapifyUp(totalItems - 1);
    }

    bool isEmpty() const { return totalItems == 0; }

    void getMin(v& _value) {
        if (totalItems > 0) _value = arr[0].value;
    }

    void deleteMin() {
        if (totalItems > 0) {
            arr[0] = arr[--totalItems];
            heapifyDown(0);
        }
    }

    void increaseKey(int idx, k increment) {
        if (idx < totalItems) {
            arr[idx].key += increment;
            heapifyDown(idx);
        }
    }

    void decreaseKey(int idx, k decrement) {
        if (idx < totalItems) {
            arr[idx].key -= decrement;
            heapifyUp(idx);
        }
    }
};

struct Student {
    char name[50];
    int id;
    friend ostream& operator<<(ostream& os, const Student& s) {
        os << "ID: " << s.id << ", Name: " << s.name;
        return os;
    }
};

int main() {
    MinHeap<int, Student> heap(2);

    Student s1 = { "Alice", 1 };
    Student s2 = { "Bob", 2 };
    Student s3 = { "Charlie", 3 };
    Student s4 = { "Daisy", 4 };

    cout << "Inserting elements into the MinHeap:" << endl;
    heap.insert(5, s1);
    heap.insert(3, s2);
    heap.insert(8, s3);
    heap.insert(2, s4);

    cout << "\nTesting getMin and deleteMin:" << endl;
    while (!heap.isEmpty()) {
        Student s;
        heap.getMin(s);
        cout << s << endl;
        heap.deleteMin();
    }

    cout << "\nRe-inserting elements to test increaseKey and decreaseKey:" << endl;
    heap.insert(5, s1);
    heap.insert(3, s2);
    heap.insert(8, s3);
    heap.insert(2, s4);

    cout << "\nDecreasing key of index 2 by 4:" << endl;
    heap.decreaseKey(2, 4);
    Student minStudent;
    heap.getMin(minStudent);
    cout << "Current minimum: " << minStudent << endl;

    cout << "\nIncreasing key of index 1 by 10:" << endl;
    heap.increaseKey(1, 10);
    heap.getMin(minStudent);
    cout << "Current minimum after increasing key: " << minStudent << endl;

    cout << "\nDeleting min element:" << endl;
    heap.deleteMin();
    heap.getMin(minStudent);
    cout << "New minimum after deleteMin: " << minStudent << endl;

    return 0;
}
