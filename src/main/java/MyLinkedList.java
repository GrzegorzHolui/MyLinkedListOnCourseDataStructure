public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            tail = tail.previous;
            tail.next = null;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }
        size--;
    }

    public void remove(T element) {
        if (head == null) {
            return;
        }
        if (head.data.equals(element)) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(element)) {
                current.next = current.next.next;
                if (current.next != null) {
                    current.next.previous = current;
                } else {
                    tail = current;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }


    public Node<T> get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        return null;
    }

    public T getFirst() {
        return null;
    }

    public void add(int index, T element) {
        if (size == 0) {
            add(element);
        } else {
            Node<T> newElement = new Node<>(element);
            Node<T> elementOfGivenIndex = get(index);
            Node<T> next = elementOfGivenIndex.next;
            Node<T> previous = elementOfGivenIndex.previous;
            newElement.next = next;
            newElement.previous = previous;
        }
        size++;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        public Node(T data) {
            this.data = data;
            next = null;
            previous = null;
        }

        public T getData() {
            return data;
        }
    }
}
