class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Đếm tần suất
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 2. Tạo bucket: index = tần suất, value = list các số có tần suất đó
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        // 3. Duyệt từ tần suất cao nhất về thấp, gom đủ k phần tử
        List<Integer> result = new ArrayList<>();
        for (int freq = buckets.length - 1; freq >= 0 && result.size() < k; freq--) {
            if (buckets[freq] != null) {
                result.addAll(buckets[freq]);
            }
        }

        // Chuyển sang int[]
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}