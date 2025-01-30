
/**
 * Time complexity: O(V + E)
 * Space complexity: O(V + E)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[numCourses];
        int count = 0;

        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];
            indegree[to]++;
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(to);
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                count++;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!map.containsKey(current)) {
                continue;
            }
            List<Integer> courses = map.get(current);
            for (int c : courses) {
                indegree[c]--;
                if (indegree[c] == 0) {
                    count++;
                    queue.add(c);
                }
            }
        }

        return count == numCourses;
    }
}
