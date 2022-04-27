package baekjoon.no10711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[][] map;
    static int[] dx = {-1, -1 ,0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<int[]> originQ = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for(int i = 0; i < H; i++) {
            String input = br.readLine();
            for(int j = 0; j < W; j++) {
                char c = input.charAt(j);
                if(c == '.') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = Character.getNumericValue(c);
                    if(map[i][j] != 9)
                        originQ.add(new int[] {i, j});
                }
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        int time = 0;

        while (!originQ.isEmpty()) {
            int size = originQ.size();
            // 무너질 좌표 저장
            Queue<int[]> willBeRemoved = new LinkedList<>();

            while (size-- > 0) {
                int[] current = originQ.poll();
                int x = current[0];
                int y = current[1];

                int zeroCnt = 0;

                //팔방 탐색
                for(int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(map[nx][ny] == 0) zeroCnt++;
                }

               if(zeroCnt >= map[x][y]) {
                    willBeRemoved.add(current);
                } else {
                    originQ.add(current);
                }
            }

            // 깎이지 않았다면 리턴
            if(willBeRemoved.isEmpty()) {
                return time;
            } else {
                time++;
                // 0으로 제거 작업
                while (!willBeRemoved.isEmpty()) {
                    int[] target = willBeRemoved.poll();
                    int x = target[0];
                    int y = target[1];

                    map[x][y] = 0;
                }
            }
        }

        return time;
    }
}
