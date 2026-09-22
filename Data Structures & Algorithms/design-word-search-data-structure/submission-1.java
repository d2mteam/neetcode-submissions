class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
                TrieNode node = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (node == null) return false;
        if (index == word.length()) return node.isEnd;

        char c = word.charAt(index);

        if (c == '.') {
            // Wildcard: thử TẤT CẢ 26 nhánh con
            for (TrieNode child : node.children) {
                if (dfs(word, index + 1, child)) {
                    return true; // chỉ cần 1 nhánh đúng là đủ
                }
            }
            return false; // không nhánh nào khớp
        } else {
            // Ký tự bình thường: chỉ đi đúng 1 nhánh
            return dfs(word, index + 1, node.children[c - 'a']);
        }
    }
}
