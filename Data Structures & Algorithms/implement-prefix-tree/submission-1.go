type TrieNode struct {
	children [26]*TrieNode
	isEnd    bool
}

type PrefixTree struct {
	root *TrieNode
}

func Constructor() PrefixTree {
	return PrefixTree{
		root: &TrieNode{},
	}
}

func (this *PrefixTree) Insert(word string) {
	node := this.root

	for i := 0; i < len(word); i++ {
		index := word[i] - 'a'

		if node.children[index] == nil {
			node.children[index] = &TrieNode{}
		}

		node = node.children[index]
	}

	node.isEnd = true
}

func (this *PrefixTree) Search(word string) bool {
	node := this.root

	for i := 0; i < len(word); i++ {
		index := word[i] - 'a'

		if node.children[index] == nil {
			return false
		}

		node = node.children[index]
	}

	return node.isEnd
}

func (this *PrefixTree) StartsWith(prefix string) bool {
	node := this.root

	for i := 0; i < len(prefix); i++ {
		index := prefix[i] - 'a'

		if node.children[index] == nil {
			return false
		}

		node = node.children[index]
	}

	return true
}