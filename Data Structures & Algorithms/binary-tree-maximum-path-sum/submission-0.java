/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
 private int maxPathSum;

    public int maxPathSum(TreeNode root) {
        // Khởi tạo giá trị nhỏ nhất có thể vì cây có thể chứa toàn nút âm
        maxPathSum = Integer.MIN_VALUE;
        // Bắt đầu duyệt cây từ gốc
        calculateMaxGain(root);
        return maxPathSum;
    }

    private int calculateMaxGain(TreeNode node) {
        // Trường hợp cơ bản: nút rỗng đóng góp giá trị bằng 0
        if (node == null) {
            return 0;
        }

        // Tính toán đóng góp lớn nhất từ nhánh trái và phải
        // Sử dụng Math.max(0, ...) để bỏ qua các nhánh có tổng âm
        int leftGain = Math.max(calculateMaxGain(node.left), 0);
        int rightGain = Math.max(calculateMaxGain(node.right), 0);

        // Giá trị của đường đi đi qua nút hiện tại và kết nối cả hai nhánh trái/phải
        int currentPathSum = node.val + leftGain + rightGain;

        // Cập nhật kết quả tổng lớn nhất toàn cục
        maxPathSum = Math.max(maxPathSum, currentPathSum);

        // Trả về giá trị lớn nhất của MỘT nhánh đơn (trái HOẶC phải) cộng với nút hiện tại
        // để nút cha phía trên có thể sử dụng tiếp tục kéo dài đường đi
        return node.val + Math.max(leftGain, rightGain);
    }
}
