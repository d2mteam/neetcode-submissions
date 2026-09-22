class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0,nums,new ArrayList<>(),res);
        return res;
    }
    

private void backtrack(int index, int[] nums, List<Integer> subset, List<List<Integer>> res) {
    if (index == nums.length) {
        res.add(new ArrayList<>(subset));
        return;
    }

    // Nhánh CHỌN nums[index] - luôn làm bình thường, không cần skip gì cả
    subset.add(nums[index]);
    backtrack(index + 1, nums, subset, res);
    subset.remove(subset.size() - 1);

    // Nhánh KHÔNG CHỌN nums[index] - phải nhảy qua HẾT các bản sao giống nums[index]
    int next = index + 1;
    while (next < nums.length && nums[next] == nums[index]) {
        next++;
    }
    backtrack(next, nums, subset, res);
}
}
