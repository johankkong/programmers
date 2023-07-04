package _2022_KAKAO_TECH_INTERNSHIP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 등산코스_정하기 {
    public static void main(String[] args) {

    }
    class Node {
        boolean isGate;
        boolean isSummit;
        ArrayList<int[]> adjNodes;
        Node() {
            isGate = false;
            isSummit = false;
            adjNodes = new ArrayList<>();
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        /*
        각 산봉우리에서 가장 작은 intensity로 출입구에 도달만 하면 된다.
        각 산봉우리에서 시작하는 프림 알고리즘으로 출입구를 만날때까지 진행
        가장 작은 intensity를 가진 산봉우리를 찾는다.
         */
        Node[] adjList = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new Node();
        }
        for (int[] edge : paths) {
            adjList[edge[0]].adjNodes.add(new int[]{edge[1], edge[2]});
            adjList[edge[1]].adjNodes.add(new int[]{edge[0], edge[2]});
        }
        for (int gate : gates) {
            adjList[gate].isGate = true;
        }
        for (int summit : summits) {
            adjList[summit].isSummit = true;
        }
        int[] answer = new int[2];
        Arrays.fill(answer, Integer.MAX_VALUE);
        nextSummit : for (int summit : summits) {
            int intensity = -1;
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            for (int[] edge : adjList[summit].adjNodes) {
                if (!adjList[edge[0]].isSummit) {
                    pq.add(edge);
                }
            }
            boolean[] visited = new boolean[n + 1];
            visited[summit] = true;
            while (true) {
                if (pq.isEmpty()) {
                    continue nextSummit;
                }
                int[] path = pq.poll();
                intensity = Math.max(intensity, path[1]);
                if (adjList[path[0]].isGate) break;
                for (int[] edge : adjList[path[0]].adjNodes) {
                    if (!adjList[edge[0]].isSummit && !visited[edge[0]]) {
                        pq.add(edge);
                    }
                }
                visited[path[0]] = true;
            }
            if (answer[1] > intensity) {
                answer[0] = summit;
                answer[1] = intensity;
            } else if (answer[1] == intensity && answer[0] > summit) {
                answer[0] = summit;
            }
        }
        return answer;
    }
}
