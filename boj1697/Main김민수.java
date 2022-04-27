package baekjoon.no1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        bfs(n, k);
    }

    static void bfs(int start, int target) {
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int current = q.poll();

                if(current == target) {
                    System.out.println(time);
                    return;
                }

                if(isIn(current + 1) && !visited[current + 1]) {
                    q.add(current + 1);
                    visited[current + 1] = true;
                }

                if(isIn(current - 1) && !visited[current - 1]) {
                    q.add(current - 1);
                    visited[current - 1] = true;
                }

                if(isIn(current * 2) && !visited[current * 2]) {
                    q.add(current * 2);
                    visited[current * 2] = true;
                }
            }

            time++;
        }
    }

    static boolean isIn(int target) {
        return target >=0 && target <= 100000;
    }
}
