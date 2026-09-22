class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new StringBuilder(), 0, 0, n, result);
        return result;
    }

    private void backtrack(StringBuilder current, int open, int close, int n, List<String> result) {
        // Base case: đã đủ 2n ký tự -> chuỗi hoàn chỉnh
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // Nhánh thêm '(' - chỉ khi chưa dùng hết n dấu mở
        if (open < n) {
            current.append('(');
            backtrack(current, open + 1, close, n, result);
            current.deleteCharAt(current.length() - 1); // quay lui
        }

        // Nhánh thêm ')' - chỉ khi số đóng < số mở (đảm bảo hợp lệ)
        if (close < open) {
            current.append(')');
            backtrack(current, open, close + 1, n, result);
            current.deleteCharAt(current.length() - 1); // quay lui
        }
    }
}