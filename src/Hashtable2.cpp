//============================================================================
// Name        : HashTable.cpp
// Author      : Ariana Williams
// Version     : 1.0
// Copyright   : Copyright © 2017 SNHU COCE
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <algorithm>
#include <climits>
#include <iostream>
#include <string> // atoi
#include <time.h>
#include <cmath>
#include "CSVparser.hpp"

using namespace std;

//============================================================================
// Global definitions visible to all methods and classes
//============================================================================

const unsigned int DEFAULT_SIZE = 179;

// forward declarations
double strToDouble(string str, char ch);

// define a structure to hold bid information
struct Bid {
    string bidId; // unique identifier
    string title;
    string fund;
    double amount;
    Bid() {
        amount = 0.0;
    }
};

void displayBid(Bid bid);


//============================================================================
// Hash Table class definition
//============================================================================

/**
 * Define a class containing data members and methods to
 * implement a hash table with chaining.
 */
class HashTable {
private:
    // Define structures to hold bids
	struct Node {
		Bid object; // declared as a type of bid
		unsigned key;
		Node* nodePtr;
		// constructor
		Node() {
			key = UINT_MAX;
			nodePtr = nullptr;
		}
		// initialize node with a bid
		Node(Bid myBid) : Node() {
			object = myBid;
		}
		Node(Bid myBid, unsigned newKey) : Node(myBid) {
			key = newKey;
		}
	};
	vector<Node> nodeObject;
	unsigned setSizeofNode = DEFAULT_SIZE;
    unsigned int hash(int key);

public:
    HashTable();
    HashTable(unsigned size);
    virtual ~HashTable();
    void Insert(Bid bid);
    void PrintAll();
    void Remove(string bidId);
    Bid Search(string bidId);
};

/**
 * Default constructor
 */
HashTable::HashTable() {
    // Initialize the structures used to hold bids
	nodeObject.resize(setSizeofNode);
}
HashTable::HashTable(unsigned size) {
	this->setSizeofNode = size;
	nodeObject.resize(setSizeofNode);
}

/**
 * Destructor
 */
HashTable::~HashTable() {
    // Implement logic to free storage when class is destroyed
	nodeObject.erase(nodeObject.begin());
}

/**
 * Calculate the hash value of a given key.
 * @param key The key to hash
 * @return The calculated hash
 */
unsigned int HashTable::hash(int key) {
    // Implement logic to calculate a hash value
	int hash1 = key % setSizeofNode;
	int hash2 = key % 7;
	key = (hash1 * hash2)% setSizeofNode;
	return key;
}

/**
 * Insert a bid
 *
 * @param bid The bid to insert
 */

void HashTable::Insert(Bid bid) {
    //Implement logic to insert a bid
	unsigned key = hash(atoi(bid.bidId.c_str()));

	// search for a node with the key
	Node* previousNode = &(nodeObject.at(key));
	if (previousNode == nullptr) {
		Node* nextNode = new Node(bid, key);
		nodeObject.insert(nodeObject.begin() + key, (*nextNode));

	} else {
		 // if the node is found
		if (previousNode->key == UINT_MAX) {
			previousNode->key = key;
			previousNode->object = bid;
			previousNode->nodePtr = nullptr;
		} else {
			// if the node is not found, find the next available node
			while (previousNode->nodePtr != nullptr) {
				previousNode = previousNode->nodePtr;
			}
		}
	}
}

/**
 * Print all bids
 */
void HashTable::PrintAll() {
    // Implement logic to print all bids
	for (unsigned int i = 0; i < nodeObject.size(); ++i) {
		displayBid(nodeObject[i].object);
	}
}

/**
 * Remove a bid
 *
 * @param bidId The bid id to search for
 */

void HashTable::Remove(string bidId) {
    //  Implement logic to remove a bid
	unsigned key = hash(atoi(bidId.c_str()));
	nodeObject.erase(nodeObject.begin() + key);
}

/**
 * Search for the specified bidId
 *
 * @param bidId The bid id to search for
 */

Bid HashTable::Search(string bidId) {
    Bid bid;

    // Implement logic to search for and return a bid
	unsigned key = hash(atoi(bidId.c_str()));
	// search for a node with the key
	Node* hasObject = &(nodeObject.at(key));
	// search for a node using key
	// if the node is found by the key
	if (hasObject != nullptr && hasObject->key != UINT_MAX
			&& hasObject-> object.bidId.compare(bidId) == 0) {
		return hasObject->object;
	}
	// if there is no node with the matching key
	if (hasObject == nullptr || hasObject->key == UINT_MAX) {
		return bid;
	}

	// traverse the list to look for a match
	while (hasObject != nullptr) {
		if (hasObject->key != UINT_MAX && hasObject->object.bidId.compare(bidId) == 0) {
			return hasObject-> object;
		}
		hasObject = hasObject-> nodePtr;
	}
	return bid;
}

//============================================================================
// Static methods used for testing
//============================================================================

/**
 * Display the bid information to the console (std::out)
 *
 * @param bid struct containing the bid info
 */
void displayBid(Bid bid) {
    cout << bid.bidId << ": " << bid.title << " | " << bid.amount << " | "
            << bid.fund << endl;
    return;
}


/**
 * Load a CSV file containing bids into a container
 *
 * @param csvPath the path to the CSV file to load
 * @return a container holding all the bids read
 */

void loadBids(string csvPath, HashTable* hashTable) {
    cout << "Loading CSV file " << csvPath << endl;

    // initialize the CSV Parser using the given path
    csv::Parser file = csv::Parser(csvPath);
    // read and display header row - optional
    vector<string> header = file.getHeader();
    for (auto const& c : header) {
        cout << c << " | ";
    }
    cout << "" << endl;
    try {
        // loop to read rows of a CSV file
        for (unsigned int i = 0; i < file.rowCount(); i++) {
            // Create a data structure and add to the collection of bids
            Bid bid;
            bid.bidId = file[i][1];
            bid.title = file[i][0];
            bid.fund = file[i][8];
            bid.amount = strToDouble(file[i][4], '$');
            hashTable->Insert(bid);
        }
    } catch (csv::Error &e) {
        std::cerr << e.what() << std::endl;
    }
}

/**
 * Simple C function to convert a string to a double
 * after stripping out unwanted char
 *
 * credit: http://stackoverflow.com/a/24875936
 *
 * @param ch The character to strip out
 */

double strToDouble(string str, char ch) {
    str.erase(remove(str.begin(), str.end(), ch), str.end());
    return atof(str.c_str());
}


/**
 * The one and only main() method
 */

int main(int argc, char* argv[]) {

    // process command line arguments
    string csvPath, searchVal;
    switch (argc) {
    case 2:
        csvPath = argv[1];
        searchVal = "98109";
        break;
    case 3:
        csvPath = argv[1];
        searchVal = argv[2];
        break;
    default:
        csvPath = "eBid_Monthly_Sales_Dec_2016.csv";
        searchVal = "98109";
    }

    // Define a timer variable
    clock_t ticks;

    // Define a hash table to hold all the bids
    HashTable* bidTable;

    Bid bid;

    int choice = 0;
    while (choice != 9) {

        cout << "Menu:" << endl;
        cout << "  1. Load Bids" << endl;
        cout << "  2. Display All Bids" << endl;
        cout << "  3. Find Bid" << endl;
        cout << "  4. Remove Bid" << endl;
        cout << "  9. Exit" << endl;
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {

        case 1:
            bidTable = new HashTable();

            // Initialize a timer variable before loading bids
            ticks = clock();

            // Complete the method call to load the bids
            loadBids(csvPath, bidTable);

            // Calculate elapsed time and display result
            ticks = clock() - ticks; // current clock ticks minus starting clock ticks
            cout << "time: " << ticks << " clock ticks" << endl;
            cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;
            break;

        case 2:
            bidTable->PrintAll();
            break;

        case 3:
        	//Initialize a timer variable before loading bids
            ticks = clock();

            bid = bidTable->Search(searchVal);

            if (!bid.bidId.empty()) {
                displayBid(bid);
            } else {
                cout << "Bid Id " << searchVal << " not found." << endl;
            }
            ticks = clock() - ticks; // current clock ticks minus starting clock ticks

            cout << "time: " << ticks << " clock ticks" << endl;
            cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;
            break;
        case 4:
            bidTable->Remove(searchVal);
            break;
        }
    }
    cout << "Good bye." << endl;
    return 0;


    }
