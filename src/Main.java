import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static class Node implements Comparable<Node> {
        int vertex,weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N,M,start;
    static ArrayList<Node>[] array;
    static int[] map;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] number = bf.readLine().split(" ");
        N = Integer.parseInt(number[0]);
        M = Integer.parseInt(number[1]);

        start = Integer.parseInt(bf.readLine());

        map = new int[N + 1];
        array = new ArrayList[N + 1];
        Arrays.fill(map, INF);

        for(int i = 0; i <= N; i++) {
            array[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            String[] num = bf.readLine().split(" ");
            int a = Integer.parseInt(num[0]);
            int b = Integer.parseInt(num[1]);
            int c = Integer.parseInt(num[2]);
            array[a].add(new Node(b,c));
        }

        FindSortestPath();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(map[i] == INF) {
                sb.append("INF\n");
            }
            else {
                sb.append(map[i] + "\n");
            }
        }
        System.out.print(sb);
    }

    public static void FindSortestPath() {
        PriorityQueue<Node> pri = new PriorityQueue<>();
        pri.offer(new Node(start, 0));
        map[start] = 0;

        while(!pri.isEmpty()) {
            Node node = pri.poll();
            int x = node.vertex;
            int y = node.weight;

            if(map[x] > y) {
                continue;
            }

            for(Node i : array[x]) {
                int xx = i.vertex;
                int yy = i.weight + y;

                if(map[xx] > yy) {
                    map[xx] = yy;
                    pri.offer(new Node(xx, yy));
                }
            }
        }
    }

}
