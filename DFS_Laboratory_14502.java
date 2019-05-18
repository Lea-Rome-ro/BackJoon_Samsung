import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Main {
	static int n, m, max=0, wallcnt, safeplace;
	static int[][] map,tmp;
	static int[][] dxy = {{0,0,1,-1},{1,-1,0,0}}; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<Node> arrlist;
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		wallcnt=0;
		arrlist = new ArrayList<>();
		map = new int[n][m];
		tmp = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) 
					arrlist.add(new Node(i,j));
			}
		}
	//	System.out.println("wall함수 시작!");
		wall(0);
	//	System.out.println("wall함수 종료!");
		bw.append(String.valueOf(max));
		bw.flush();
		
	}
	private static void wall(int a) {
		if(a==3) {
			for(int i=0; i<n; i++)
				for(int j=0; j<m; j++)
					tmp[i][j]=map[i][j];
			Spread(tmp);
			return;
		}
		for(int i=0;i<n;i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					wall(a+1);
					map[i][j]=0;
				}
			}
		}		
	}
	private static void Spread(int[][] tmp2) {
		Queue<Node> q = new LinkedList<>();
		for(int i=0; i<arrlist.size(); i++) {
			q.add(arrlist.get(i));
		}
		while(!q.isEmpty()) {
		/*	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			int a = q.peek().i;
			int b = q.poll().j;
		//	System.out.println("a : "+a+", b : "+b);
			for(int i=0; i<4; i++) {
		//		System.out.println((a+dxy[0][i])+"행, "+(b+dxy[1][i])+"열");
				if(a+dxy[0][i]>=0 && a+dxy[0][i]<n && b+dxy[1][i]>=0 && b+dxy[1][i]<m) {
					if(tmp2[a+dxy[0][i]][b+dxy[1][i]]==0) {
						tmp2[a+dxy[0][i]][b+dxy[1][i]]=2;
						q.add(new Node(a+dxy[0][i],b+dxy[1][i]));
					}
				}
			}
		}
//		System.out.println("while문 종료!");
		int cnt=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tmp2[i][j]==0) cnt++;
			}
		}
		if(cnt>max) {
			max = cnt;
		}
	}

}
class Node {
	int i, j;
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
}