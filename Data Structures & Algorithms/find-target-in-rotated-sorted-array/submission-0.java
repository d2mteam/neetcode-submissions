class Solution {
    public int search(int[] nums, int target) {
         int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Xác định nửa nào đang sorted bình thường
            if (nums[left] <= nums[mid]) {
                // Nửa trái [left..mid] sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target nằm trong nửa trái
                } else {
                    left = mid + 1;  // target nằm ở nửa phải
                }
            } else {
                // Nửa phải [mid..right] sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target nằm trong nửa phải
                } else {
                    right = mid - 1; // target nằm ở nửa trái
                }
            }
        }

        return -1; // không tìm thấy  
    }
}
