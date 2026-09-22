import "slices"

func threeSum(nums []int) [][]int {
	slices.Sort(nums)

	res := [][]int{}

	for i := 0; i < len(nums)-2; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		pairs := twoSum(nums, i+1, -nums[i])

		for _, pair := range pairs {
			res = append(res, []int{
				nums[i],
				pair[0],
				pair[1],
			})
		}
	}

	return res
}

func twoSum(nums []int, start int, target int) [][]int {
	res := [][]int{}

	left := start
	right := len(nums) - 1

	for left < right {
		sum := nums[left] + nums[right]

		if sum < target {
			left++
		} else if sum > target {
			right--
		} else {
			res = append(res, []int{
				nums[left],
				nums[right],
			})

			left++
			right--

			for left < right && nums[left] == nums[left-1] {
				left++
			}

			for left < right && nums[right] == nums[right+1] {
				right--
			}
		}
	}

	return res
}