class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hash = new HashMap<>();
        for (String str : strs ) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            hash.computeIfAbsent(sortedS, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(hash.values());
    }
}
