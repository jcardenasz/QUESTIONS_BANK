public class LinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int length = 0;

    public LinkedList() {
    }

    public void push(T newItem) {
        Node<T> newItemNode = new Node<T>(newItem);
        if (length == 0) {
            this.head = newItemNode;
            this.tail = newItemNode;
        } else {
            newItemNode.next = this.head;
            this.head = newItemNode;
        }
        this.length++;
    }

    public void add(T newItem) {
        Node<T> newItemNode = new Node<T>(newItem);
        if (length == 0) {
            this.head = newItemNode;
            this.tail = newItemNode;
        } else {
            this.tail.next = newItemNode;
            this.tail = newItemNode;
        }
        this.length++;
    }

    public T pop(){
        if(this.length == 0){
            throw new RuntimeException("list is emtpy 💀💀🤙");
        } else {
            Node<T> nodeToPop = this.head;
            this.head = this.head.next;
            return nodeToPop.data;
        }
    }

    public int size(){
        return length;
    }

}