class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] tCount = new int[256];
        int[] windowCount = new int[256];
        int required = 0;
        
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (tCount[c] == 0) {
                required++;
            }
            tCount[c]++;
        }

        int l = 0;
        int tk = 0;
        int minlen = Integer.MAX_VALUE;
        int startIdx = 0; // Dùng để lưu vị trí bắt đầu của chuỗi con tốt nhất

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            windowCount[c]++;

            // Chỉ tăng tk khi ký tự c trong cửa sổ đạt ĐÚNG số lượng yêu cầu của t
            if (tCount[c] > 0 && windowCount[c] == tCount[c]) {
                tk++;
            }
            
            // Đổi từ IF thành WHILE để thu hẹp tối đa cửa sổ từ bên trái
            while (tk == required) {
                // Cập nhật chiều dài nhỏ nhất và vị trí bắt đầu
                if (i - l + 1 < minlen) {
                    minlen = i - l + 1;
                    startIdx = l;
                }
                
                char c1 = s.charAt(l);
                windowCount[c1]--;
                
                // Nếu sau khi giảm, số lượng ký tự c1 ít hơn yêu cầu trong t -> Cửa sổ hết hợp lệ
                if (tCount[c1] > 0 && windowCount[c1] < tCount[c1]) {
                    tk--;
                }
                l++; // Dịch con trỏ trái sang phải
            }
        }

        // Cắt chuỗi chính xác dựa trên startIdx và minlen
        return minlen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minlen);
    }
}
