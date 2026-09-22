func solveNQueens(n int) [][]string {
	res := [][]string{}
	q := make([]int, n)

	var backtrack func(row int)

	backtrack = func(row int) {
		if row == n {
			res = append(res, solve(q))
			return
		}

		for col := 0; col < n; col++ {
			if valid(q, row, col) {
				q[row] = col
				backtrack(row + 1)
			}
		}
	}

	backtrack(0)

	return res
}

func valid(q []int, row int, col int) bool {
	for r := 0; r < row; r++ {
		// cùng cột
		if q[r] == col {
			return false
		}

		// cùng đường chéo
		if abs(q[r]-col) == row-r {
			return false
		}
	}

	return true
}

func solve(q []int) []string {
	n := len(q)
	board := make([]string, n)

	for row := 0; row < n; row++ {
		line := make([]byte, n)

		for i := 0; i < n; i++ {
			line[i] = '.'
		}

		line[q[row]] = 'Q'
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