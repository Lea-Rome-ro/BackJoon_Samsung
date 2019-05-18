import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Node BabyShark;
	static int Ordernum = 0,Sec=0,fishEA=0,n; 
	static int dx[] = {0,0,1,-1}, dy[] = {1,-1,0,0};
	static int MAP[][], visit[][];
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args)throws Exception {
		n = Integer.parseInt(br.readLine());
		MAP = new int[n][n];
		visit = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==9) {
					BabyShark = new Node(i,j,2);
					MAP[i][j] = 0;
				}else {
					if(tmp!=0) {
						fishEA++;
					}
					MAP[i][j] = tmp;
				}				
			}
		}
		q.add(BabyShark);
		boolean chk = false;
		while(!q.isEmpty() || fishEA !=0) {
		//	System.out.println("While巩 矫累!!");
			Node curr = q.poll();
			int dist[][] = dist(curr.x, curr.y, curr.size);
		//	System.out.println("货肺款 dist[n][n]积己!!");
		//	for(int i=0; i<n; i++) {
		//		for(int j=0;j<n;j++) {
		//			System.out.print(dist[i][j]+" ");
		//		}
		//		System.out.println();
		//	}
			ArrayList<Node> fish[] = new ArrayList[n*n];
			for(int i=0; i<fish.length; i++) fish[i] = new ArrayList<>();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(dist[i][j]==Integer.MAX_VALUE) continue;
					if(MAP[i][j] < BabyShark.size && MAP[i][j] !=0) {
						fish[dist[i][j]].add(new Node(i,j,MAP[i][j]));
					}
				}
			}
			
			chk = false;
			for(int i=0; i<fish.length; i++) {
				Collections.sort(fish[i]);
			}
			for(int i=0; i<fish.length; i++) {
				if(fish[i].size() != 0) {
					Sec += dist[fish[i].get(0).x][fish[i].get(0).y];
					//System.out.println("Sec : "+Sec);
					fishEA--;
					chk = true;
					MAP[fish[i].get(0).x][fish[i].get(0).y] = 0;
					
					if(curr.size-1==0) {
						BabyShark.size++;
						q.add(new Node(fish[i].get(0).x,fish[i].get(0).y,BabyShark.size));
					}
					else {
						q.add(new Node(fish[i].get(0).x,fish[i].get(0).y,curr.size-1));
					}
					break;
				}
			}
			if(!chk)break;
		}
		
		bw.write(String.valueOf(Sec));
		bw.flush();
		
	}
	private static int[][] dist(int x, int y, int size) {
		Queue<Node> qu = new LinkedList<>();
		int dist[][] = new int[n][n];
		qu.add(new Node(x,y,size));
		for(int i =0; i<n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[x][y] = 0;
		while(!qu.isEmpty()) {
			Node curr = qu.poll();
			for(int i=0; i<4; i++) {
				int nx = curr.x+dx[i];
				int ny = curr.y+dy[i];
				
				if(nx>=0 && nx<n && ny>=0 && ny<n && dist[nx][ny] > dist[curr.x][curr.y] + 1) {
					if(MAP[nx][ny] <= BabyShark.size) {
						dist[nx][ny] = dist[curr.x][curr.y]+1;
						qu.add(new Node(nx,ny,curr.size));
					}
				}
			}
		}
		return dist;
	}
}
class Node implements Comparable<Node>{
	int x,y,size,time;
	
	public Node(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		time = 0;
	}
	 public int compareTo(Node o) {
	      if (this.x > o.x)
	         return 1;
	      else if (this.x < o.x)
	         return -1;
	      else {
	         if (this.y > o.y)
	            return 1;
	         else if (this.y < o.y)
	            return -1;
	         else
	            return 0;
	      }
	   }

}

