// rough draft for implementing the binary heap by scratch: refactor pq with the binary heap

import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T> {
    private Object[] heap;
    private int size;
    private Comparator<? super T> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<? super T> comparator) {
        this.heap = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    // methods for typical heap operations
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    private T leftChild(int index) { return (T) heap[getLeftChildIndex(index)]; }
    private T rightChild(int index) { return (T) heap[getRightChildIndex(index)]; }
    private T parent(int index) { return (T) heap[getParentIndex(index)]; }

    // heap internal operations

    private void swap(int indexOne, int indexTwo) {
        Object temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    // usable methods
    // NEED REMOVE, ADD

    public void clear() {
        heap = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (size == 0) return null;
        return (T) heap[0];
    }

    public T poll() {
        if (size == 0) return null;
        T item = (T) heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(T item) {
        ensureExtraCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    // more helper methods

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && comparator.compare(parent(index), (T) heap[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && comparator.compare(rightChild(index), leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (comparator.compare((T) heap[index], (T) heap[smallerChildIndex]) <= 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }
}
