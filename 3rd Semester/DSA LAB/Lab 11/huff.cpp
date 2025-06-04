#include <iostream>

using namespace std;

// HashItem struct to represent an item in the hash table
template <class V>
struct HashItem {
    int key;
    V value;
    int status; // 0 = empty, 1 = deleted, 2 = occupied

    HashItem() : key(0), value(V()), status(0) {}
};

// HashMap class with Linear Probing for collision resolution
template <class V>
class HashMap {
private:
    HashItem<V>* hashArray;
    int capacity;
    int currentElements;

    // Function to get the next index for probing
    int getNextCandidateIndex(int key, int i) const {
        return (key + i) % capacity;
    }

    // Function to double the capacity of the hash array and rehash elements
    void doubleCapacity() {
        int oldCapacity = capacity;
        capacity *= 2;
        HashItem<V>* oldArray = hashArray;

        hashArray = new HashItem<V>[capacity];
        currentElements = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldArray[i].status == 2) { // if the item is occupied
                insert(oldArray[i].key, oldArray[i].value);
            }
        }
        delete[] oldArray;
    }

public:
    // Constructors
    HashMap() : capacity(10), currentElements(0) {
        hashArray = new HashItem<V>[capacity];
    }

    HashMap(int initialCapacity) : capacity(initialCapacity > 1 ? initialCapacity : 10), currentElements(0) {
        hashArray = new HashItem<V>[capacity];
    }

    // Insert function
    void insert(int key, const V& value) {
        int index = key % capacity;
        int i = 0;

        while (hashArray[index].status == 2 && hashArray[index].key != key) {
            index = getNextCandidateIndex(key, i++);
        }

        if (hashArray[index].status != 2) {
            hashArray[index].key = key;
            hashArray[index].value = value;
            hashArray[index].status = 2;
            currentElements++;

            // Double capacity if load factor exceeds 0.75
            if ((float)currentElements / capacity >= 0.75) {
                doubleCapacity();
            }
        }
        else {
            hashArray[index].value = value; // Update value if key already exists
        }
    }

    // Delete function
    bool deleteKey(int key) {
        int index = key % capacity;
        int i = 1;

        while (hashArray[index].status != 0) {
            if (hashArray[index].status == 2 && hashArray[index].key == key) {
                hashArray[index].status = 1; // Mark as deleted
                currentElements--;
                return true;
            }
            index = getNextCandidateIndex(key, i++);
        }
        return false;
    }

    // Get function
    V* get(int key) const {
        int index = key % capacity;
        int i = 1;

        while (hashArray[index].status != 0) {
            if (hashArray[index].status == 2 && hashArray[index].key == key) {
                return &hashArray[index].value;
            }
            index = getNextCandidateIndex(key, i++);
        }
        return nullptr;
    }

    // Destructor
    ~HashMap() {
        delete[] hashArray;
    }
};

int main() {
    HashMap<string> map;

    // Test insertions
    map.insert(1, "One");
    map.insert(2, "Two");
    map.insert(3, "Three");

    // Display retrieved values
    cout << "Key 1: " << *(map.get(1)) << endl;
    cout << "Key 2: " << *(map.get(2)) << endl;
    cout << "Key 3: " << *(map.get(3)) << endl;

    // Test deletion
    map.deleteKey(2);
    if (map.get(2) == nullptr) {
        cout << "Key 2 deleted successfully." << endl;
    }

    return 0;
}
