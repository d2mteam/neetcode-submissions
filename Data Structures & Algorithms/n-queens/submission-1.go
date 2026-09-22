func solveNQueens(n int) [][]string {
	return solve([]int{}, n)
}

func solve(q []int, n int) [][]string {
	// đã đặt đủ n quân hậu
	if len(q) == n {
		return [][]string{buildBoard(q)}
	}

	res := [][]string{}

	// hàng hiện tại chính là len(q)
	for col := 0; col < n; col++ {
		if valid(q, col) {
			// đặt hậu của hàng hiện tại ở col
			next := append(q, col)

			// giải hàng tiếp theo
			res = append(res, solve(next, n)...)
		}
	}

	return res
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