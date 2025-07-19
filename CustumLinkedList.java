class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CustomLinkedList {
    Node head;

    // Insert at the end
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Insert at a specific position
    public void insertAt(int index, int data) {
        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node temp = head;
        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete a node by value
    public void delete(int data) {
        if (head == null) return;
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    // Search for an element
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }

    // Sorting the linked list using Bubble Sort
    public void sort() {
        if (head == null) return;
        Node current, index;
        int temp;
        for (current = head; current != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.data > index.data) {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
            }
        }
    }

    // Merging two linked lists
    public void merge(CustomLinkedList list2) {
        if (head == null) {
            head = list2.head;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = list2.head;
    }

    // Traversing the linked list
    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

public class LinkedListOperations {
    public static void main(String[] args) {
        CustomLinkedList list1 = new CustomLinkedList();
        
        // Inserting elements
        list1.insert(10);
        list1.insert(30);
        list1.insert(20);
        list1.insert(50);
        list1.insert(40);
        System.out.print("Initial List: ");
        list1.traverse();

        // Insert at specific index
        list1.insertAt(2, 25);
        System.out.print("After Insertion: ");
        list1.traverse();

        // Deleting a node
        list1.delete(30);
        System.out.print("After Deletion: ");
        list1.traverse();

        // Sorting the list
        list1.sort();
        System.out.print("After Sorting: ");
        list1.traverse();

        // Searching for an element
        System.out.println("Searching for 40: " + (list1.search(40) ? "Found" : "Not Found"));

        // Merging with another list
        CustomLinkedList list2 = new CustomLinkedList();
        list2.insert(60);
        list2.insert(70);
        list2.insert(80);
        list1.merge(list2);
        System.out.print("After Merging: ");
        list1.traverse();
    }
}