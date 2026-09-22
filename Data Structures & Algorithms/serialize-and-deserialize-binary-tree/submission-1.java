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

public class Codec {

    // Encodes a tree to a single string.
    // Format: mỗi node được lưu dưới dạng "path=value", cách nhau bởi dấu ','
    // "path" là chuỗi L/R biểu diễn đường đi từ root tới node đó.
    // Root có path rỗng, ký hiệu bằng "#" để tránh chuỗi rỗng gây lẫn lộn khi parse.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, "#", sb);
        // Xóa dấu ',' thừa ở cuối
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, String path, StringBuilder sb) {
        if (node == null) return; // node null: không ghi gì cả (đây là điểm khác biệt cốt lõi so với cách B)

        sb.append(path).append(":").append(node.val).append(",");

        serializeHelper(node.left, path + "L", sb);
        serializeHelper(node.right, path + "R", sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        // Dùng map path -> value để tra cứu nhanh, rồi dựng cây bằng đệ quy
        Map<String, Integer> pathToVal = new HashMap<>();
        String[] tokens = data.split(",");
        for (String token : tokens) {
            int idx = token.indexOf(':');
            String path = token.substring(0, idx);
            int val = Integer.parseInt(token.substring(idx + 1));
            pathToVal.put(path, val);
        }

        if (!pathToVal.containsKey("#")) return null; // cây rỗng

        return buildTree("#", pathToVal);
    }

    private TreeNode buildTree(String path, Map<String, Integer> pathToVal) {
        if (!pathToVal.containsKey(path)) return null;

        TreeNode node = new TreeNode(pathToVal.get(path));
        node.left = buildTree(path + "L", pathToVal);
        node.right = buildTree(path + "R", pathToVal);
        return node;
    }

    private static void printPreorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }
}
