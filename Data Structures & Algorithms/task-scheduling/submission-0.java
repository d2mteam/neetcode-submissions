class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int numMaxFreq = 0;
        for (int f : freq) {
            if (f == maxFreq) numMaxFreq++;
        }

        int minLen = (maxFreq - 1) * (n + 1) + numMaxFreq;
        return Math.max(tasks.length, minLen);
    }
}