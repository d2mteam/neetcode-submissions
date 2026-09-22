func solveNQueens(n int) [][]string {
	res := [][]string{}
	q := make([]int, 0, n)

	solve(q, n, &res)

	return res
}

func solve(q []int, n int, res *[][]string) {
	// len(q) cũng chính là hàng hiện tại
	if len(q) == n {
		*res = append(*res, buildBoard(q))
		return
	}

	for col := 0; col < n; col++ {
		if !valid(q, col) {
			continue
		}

		// chọn
		q = append(q, col)

		// sang hàng tiếp theo
		solve(q, n, res)

		// quay lui: bỏ lựa chọn vừa rồi
		q = q[:len(q)-1]
	}
}

func valid(q []int, col int) bool {
	row := len(q)

	for oldRow, oldCol := range q {
		// cùng cột
		if oldCol == col {
			return false
		}

		// cùng đường chéo
		if abs(oldCol-col) == row-oldRow {
			return false
		}
	}

	return true
}

func buildBoard(q []int) []string {
	n := len(q)
	board := make([]string, n)

	for row, col := range q {
		line := make([]byte, n)

		for i := range line {
			line[i] = '.'
		}

		line[col] = 'Q'
		board[row] = string(line)
	}

	return board
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}