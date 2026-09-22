class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap theo khoảng cách: đỉnh heap = điểm XA nhất trong k điểm đang giữ
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // loại điểm xa nhất ra khỏi top-k
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}