class Twitter {
    private int timestamp = 0;
    private Map<Integer, Set<Integer>> followMap;      // userId -> set followeeId
    private Map<Integer, List<int[]>> tweetMap;         // userId -> list of [time, tweetId]

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(new int[]{timestamp++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        // Max-heap theo timestamp: đỉnh heap = tweet MỚI nhất
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Tập người cần xét: chính mình + người đang follow
        Set<Integer> people = new HashSet<>();
        people.add(userId);
        if (followMap.containsKey(userId)) {
            people.addAll(followMap.get(userId));
        }

        // Với mỗi người, chỉ cần lấy tweet GẦN NHẤT của họ đưa vào heap trước
        // (vì list tweet của mỗi user đã sort tăng dần theo thời gian)
        for (int person : people) {
            List<int[]> tweets = tweetMap.get(person);
            if (tweets == null || tweets.isEmpty()) continue;
            int idx = tweets.size() - 1; // tweet mới nhất = phần tử cuối list
            maxHeap.offer(new int[]{tweets.get(idx)[0], tweets.get(idx)[1], person, idx});
        }

        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty() && result.size() < 10) {
            int[] top = maxHeap.poll();
            result.add(top[1]); // tweetId

            int person = top[2];
            int idx = top[3] - 1; // lùi về tweet trước đó của cùng người này
            if (idx >= 0) {
                List<int[]> tweets = tweetMap.get(person);
                maxHeap.offer(new int[]{tweets.get(idx)[0], tweets.get(idx)[1], person, idx});
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // không cần tự follow chính mình
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}