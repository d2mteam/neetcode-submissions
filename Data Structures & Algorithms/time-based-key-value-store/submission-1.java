

class TimeMap {
    // Map lưu key -> TreeMap(timestamp -> value)
    private HashMap<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value); // O(log N)
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> treeMap = map.get(key);
        // floorEntry trả về entry có key <= timestamp lớn nhất
        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp); // O(log N)
        
        if (entry == null) {
            return "";
        }
        return entry.getValue();
    }
}