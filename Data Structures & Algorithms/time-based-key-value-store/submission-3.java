
class TimeMap {
    
    // Tạo một lớp Pair nội bộ để lưu trữ theo cặp {timestamp, value}
    class Pair {
        int timestamp;
        String value;
        
        public Pair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    // Map lưu key -> Danh sách các Pair
    private HashMap<String, ArrayList<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        // Nếu key chưa tồn tại thì tạo một ArrayList mới
        map.putIfAbsent(key, new ArrayList<>());
        // Thêm giá trị vào cuối mảng (Độ phức tạp O(1))
        map.get(key).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        // Nếu không có key này thì trả về chuỗi rỗng
        if (!map.containsKey(key)) {
            return "";
        }
        
        ArrayList<Pair> list = map.get(key);
        
        // Cài đặt Tìm kiếm nhị phân (Binary Search)
        int left = 0;
        int right = list.size() - 1;
        String result = "";
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (list.get(mid).timestamp <= timestamp) {
                // Nếu tìm thấy mốc thời gian <= timestamp yêu cầu, 
                // đây là một đáp án tiềm năng. Lưu lại giá trị.
                result = list.get(mid).value;
                // Tiếp tục thu hẹp phạm vi sang nửa TẢI để tìm mốc thời gian lớn hơn nữa (gần với timestamp yêu cầu nhất)
                left = mid + 1;
            } else {
                // Nếu mốc thời gian ở mid đang lớn hơn timestamp yêu cầu, 
                // ta phải lùi lại tìm ở nửa TRÁI
                right = mid - 1;
            }
        }
        
        return result;
    }
}