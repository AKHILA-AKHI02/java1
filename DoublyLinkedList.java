import java.util.Scanner;

class DoublyLinkedList {
    class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    private Node head, tail;

    // Insert at the front
    public void insertAtFront(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Insert at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Insert at a specific position
    public void insertAtPosition(int data, int position) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }
        Node newNode = new Node(data);
        if (position == 1) {
            insertAtFront(data);
            return;
        }
        Node temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range");
            return;
        }
        newNode.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    // Delete from the front
    public void deleteFromFront() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
    }

    // Delete from the end
    public void deleteFromEnd() {
        if (tail == null) {
            System.out.println("List is empty!");
            return;
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
    }

    // Delete from a specific position
    public void deleteFromPosition(int position) {
        if (position <= 0 || head == null) {
            System.out.println("Invalid position or empty list!");
            return;
        }
        if (position == 1) {
            deleteFromFront();
            return;
        }
        Node temp = head;
        for (int i = 1; temp != null && i < position; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range");
            return;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
    }

    // Search for an element
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Sort the linked list (Bubble Sort)
    public void sort() {
        if (head == null) return;
        boolean swapped;
        Node temp;
        do {
            swapped = false;
            temp = head;
            while (temp.next != null) {
                if (temp.data > temp.next.data) {
                    int t = temp.data;
                    temp.data = temp.next.data;
                    temp.next.data = t;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    // Merge another DLL into this one
    public void merge(DoublyLinkedList list2) {
        if (list2.head == null) return;
        if (this.head == null) {
            this.head = list2.head;
            this.tail = list2.tail;
            return;
        }
        this.tail.next = list2.head;
        list2.head.prev = this.tail;
        this.tail = list2.tail;
    }

    // Traverse and print the linked list
    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList dll1 = new DoublyLinkedList();
        DoublyLinkedList dll2 = new DoublyLinkedList();
        int choice, data, position;

        do {
            System.out.println("\nDoubly Linked List Operations:");
            System.out.println("1. Insert at Front");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete from Front");
            System.out.println("5. Delete from End");
            System.out.println("6. Delete from Position");
            System.out.println("7. Search");
            System.out.println("8. Sort");
            System.out.println("9. Traverse");
            System.out.println("10. Merge another DLL");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter data: ");
                    data = scanner.nextInt();
                    dll1.insertAtFront(data);
                    break;
                case 2:
                    System.out.print("Enter data: ");
                    data = scanner.nextInt();
                    dll1.insertAtEnd(data);
                    break;
                case 3:
                    System.out.print("Enter data: ");
                    data = scanner.nextInt();
                    System.out.print("Enter position: ");
                    position = scanner.nextInt();
                    dll1.insertAtPosition(data, position);
                    break;
                case 4:
                    dll1.deleteFromFront();
                    break;
                case 5:
                    dll1.deleteFromEnd();
                    break;
                case 6:
                    System.out.print("Enter position: ");
                    position = scanner.nextInt();
                    dll1.deleteFromPosition(position);
                    break;
                case 7:
                    System.out.print("Enter data to search: ");
                    data = scanner.nextInt();
                    System.out.println(dll1.search(data) ? "Element found" : "Element not found");
                    break;
                case 8:
                    dll1.sort();
                    System.out.println("List sorted.");
                    break;
                case 9:
                    dll1.traverse();
                    break;
                case 10:
                    System.out.println("Enter elements for second DLL (Enter -1 to stop): ");
                    while (true) {
                        System.out.print("Enter data: ");
                        data = scanner.nextInt();
                        if (data == -1) break;
                        dll2.insertAtEnd(data);
                    }
                    dll1.merge(dll2);
                    System.out.println("Merged Successfully.");
                    break;
                case 11:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 11);

        scanner.close();
    }
}