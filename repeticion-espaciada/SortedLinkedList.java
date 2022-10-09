 public class SortedLinkedList<T extends Comparable<T>> {
    public int length;
    Node<T> head;
    Node<T> tail;

    public SortedLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    private boolean isEmpty() {
        return length == 0;
    }

    public boolean insert(T newItem) {
        boolean inserted;
        Node<T> pointer, previousPointer = null;
        inserted = false;
        pointer = head;
        while (pointer != null && pointer.data.compareTo(newItem) < 0) {
            previousPointer = pointer;
            pointer = pointer.next;
        }
        if (pointer == null || !(pointer.data.equals(newItem))) {
            inserted = true;
            this.length++;
            Node<T> newItemNode = new Node<T>(newItem);
            newItemNode.data = newItem;
            newItemNode.next = pointer;
            if (previousPointer == null)
                head = newItemNode;
            else
                previousPointer.next = newItemNode;
        }
        return inserted;
    }

    public T pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("list is empty💀");
        } else {
            this.length--;
            Node<T> nodeToPop = this.head;
            this.head = nodeToPop.next;
            return nodeToPop.data;
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public boolean equals(SortedLinkedList<T> list) {
        if (this.length != list.length) {
            return false;
        } else {
            Node<T> currentThisElement = this.head;
            Node<T> currentListElement = list.head;

            for (int i = 0; i < this.length; i++) {
                if (currentThisElement != currentListElement) {
                    return false;
                }
                currentThisElement = currentThisElement.next;
                currentListElement = currentListElement.next;
            }
            return true;
        }
    }

    public String toString() {
        String result = "[";
        if (this.isEmpty()) {
            return "[]";
        } else {
            Node<T> currentNode = head;
            while (true) {
                result += currentNode.data.toString();
                if (currentNode.next != null) {
                    result += ", ";
                } else {
                    break;
                }
                currentNode = currentNode.next;
            }
            result += "]";
        }
        return result;
    }

    public void printInLines() {
        if (this.isEmpty()) {
            System.out.print("");
        } else {
            Node<T> currentNode = head;
            while (true) {
                System.out.println(currentNode.data.toString());
                if (currentNode.next == null) {
                    break;
                }
                currentNode = currentNode.next;
            }
        }

    }
    public int size(){
        return length;
    }

    public static void main(String[] args) {
        System.out.println("a");
    }

}
