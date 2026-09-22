class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // chứa nửa nhỏ hơn, đỉnh = lớn nhất của nửa nhỏ
    private PriorityQueue<Integer> minHeap; // chứa nửa lớn hơn, đỉnh = nhỏ nhất của nửa lớn

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
        minHeap = new PriorityQueue<>(); // min-heap mặc định
    }

    public void addNum(int num) {
        // Bước 1: luôn đẩy vào maxHeap trước
        maxHeap.offer(num);

        // Bước 2: chuyển phần tử lớn nhất của maxHeap sang minHeap
        // đảm bảo mọi phần tử trong maxHeap luôn <= mọi phần tử trong minHeap
        minHeap.offer(maxHeap.poll());

        // Bước 3: cân bằng lại kích thước - maxHeap được phép nhiều hơn minHeap tối đa 1
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek(); // maxHeap nhiều hơn 1 phần tử -> đỉnh nó chính là median
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0; // bằng nhau -> trung bình 2 đỉnh
    }
}