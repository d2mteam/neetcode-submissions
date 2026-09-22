func trap(height []int) int {
	left, right := 0, len(height)-1

	leftMax, rightMax := 0, 0
	water := 0

	for left <= right {
		if height[left] > leftMax {
			leftMax = height[left]
		}

		if height[right] > rightMax {
			rightMax = height[right]
		}

		if leftMax <= rightMax {
			water += leftMax - height[left]
			left++
		} else {
			water += rightMax - height[right]
			right--
		}
	}

	return water
}