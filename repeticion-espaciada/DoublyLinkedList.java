class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    public int length = 0;
    
    public DoublyLinkedList(){
    }
    
    public void push(T newItem){
        Node<T> newItemNode = new Node<T>(newItem);
        if(length == 0){
            this.head = newItemNode;
            this.tail = newItemNode;
        } else {
            newItemNode.next = this.head;
            this.head.back = newItemNode;
            this.head = newItemNode;
        }
        this.length++;
    }

    public void add(T newItem){
        Node<T> newItemNode = new Node<T>(newItem);
        if(length == 0){
            this.head = newItemNode;
            this.tail = newItemNode;
        } else {
            this.tail.next = newItemNode;
            newItemNode.back = this.tail;
            this.tail = newItemNode;
        }
        this.length++;
    }
    
    public T pop(){
        if(this.length == 0){
            throw new RuntimeException("The list is empty 💀💀🤙");
        } else {
            Node<T> nodeToPop = this.head;
            this.head = this.head.next;
            this.head.back = null;
            this.length--;
            return nodeToPop.data;
        }
    }

    public T getLastItem(){
        if(this.length == 0){
            throw new RuntimeException("The list is empty 💀💀🤙");
        } else {
            return tail.data;
        }
    }

    public T getBeforeLastItem(){
        if(this.length < 2){
            throw new RuntimeException("The list only has 1 element 😳🤙");
        } else {
            return tail.back.data;
        }
    }

    public int size(){
        return length;
    }
    
}