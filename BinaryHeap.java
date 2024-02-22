// rough draft for implementing the binary heap by scratch: refactor pq with the binary heap
// not using a generic version
// CURRENTLY ONLY FORWARD A STAR IMPLEMENTS BINARYHEAP

import java.util.Arrays;

public class BinaryHeap {
    private MazeBox[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this.heap = new MazeBox[DEFAULT_CAPACITY];
    }

    // methods for typical heap operations
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    private MazeBox leftChild(int index) { return heap[getLeftChildIndex(index)]; }
    private MazeBox rightChild(int index) { return heap[getRightChildIndex(index)]; }
    private MazeBox parent(int index) { return heap[getParentIndex(index)]; }

    // heap internal operations

    private void swap(int indexOne, int indexTwo) {
        MazeBox temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    // usable methods

    public void clear() {
        heap = new MazeBox[DEFAULT_CAPACITY];
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public MazeBox peek() {
        if (size == 0) return null;
        return heap[0];
    }

    public MazeBox poll() {
        if (size == 0) return null;
        MazeBox item = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(MazeBox item) {
        ensureExtraCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }
    public boolean remove(MazeBox item) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(item)) {
                // Swap with the last item
                swap(i, size - 1);
                size--;
                // Ensure the heap property is maintained
                heapifyDown(i);
                heapifyUp(i);
                return true; // Item was found and removed
            }
        }
        return false;
    }


    // more helper methods

    private void heapifyUp() {
        heapifyUp(size - 1);
    }

    private void heapifyDown() {
        heapifyDown(0);
    }

    // overloading for finding mazeboxes in the array
    private void heapifyUp(int startIndex) {
        int index = startIndex;
        while (hasParent(index) && parent(index).compareTo(heap[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }


    private void heapifyDown(int startIndex) {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if ((heap[index]).compareTo(heap[smallerChildIndex]) <= 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }
}
