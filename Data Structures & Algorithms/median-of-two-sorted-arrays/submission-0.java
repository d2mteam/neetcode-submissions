class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Luôn binary search trên mảng ngắn hơn
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int low = 0, high = m;
        int halfLen = (m + n + 1) / 2; // số phần tử cần cho "nửa trái"

        while (low <= high) {
            int i = (low + high) / 2;   // cắt trong nums1
            int j = halfLen - i;        // cắt trong nums2 (bị ép buộc theo i)

            if (i < m && nums2[j - 1] > nums1[i]) {
                low = i + 1;            // i quá nhỏ -> tăng i
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                high = i - 1;           // i quá lớn -> giảm i
            } else {
                // Đã tìm được điểm cắt hợp lệ
                int maxLeft;
                if (i == 0) maxLeft = nums2[j - 1];
                else if (j == 0) maxLeft = nums1[i - 1];
                else maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);

                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if (i == m) minRight = nums2[j];
                else if (j == n) minRight = nums1[i];
                else minRight = Math.min(nums1[i], nums2[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }

        throw new IllegalArgumentException("Input arrays không hợp lệ");
    }
}