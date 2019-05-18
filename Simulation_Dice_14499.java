import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, x, y, K;
	static boolean doDice = true;
	static int[][] dice = new int[4][3], map;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		//bw.append(String.valueOf(dice[1][1])+"\n");
		for(int i=0; i<K; i++) {
			//System.out.println("i : "+i);
			int a = Integer.parseInt(st.nextToken()),tmp;
			doDice = true;
			switch(a) {
			case 1 : 
				y++; 
				if(y>=M) { 
					doDice = false;
					y--;
				}
				break;
			case 2 : 
				y--;
				if(y<0) { 
					doDice = false;
					y++;
				}
				break;
			case 3 : 
				x--; 
				if(x<0) {
					doDice = false;
					x++;
				}
				break;
			case 4 : 
				x++; 
				if(x>=N) {
					doDice = false;
					x--;
				}
				break;
			default : break;
			}
			if(doDice==false) continue;
			switch(a) {
			case 1 : 
				tmp = dice[1][0];
				dice[1][0] = dice[3][1];
				dice[3][1] = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = tmp;
				break;
			case 2 : 
				tmp = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = dice[3][1];
				dice[3][1] = tmp;
				break;
			case 3 : 
				tmp = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = dice[3][1];
				dice[3][1] = tmp;
				break;
			case 4 : 
				tmp = dice[0][1];
				dice[0][1] = dice[3][1];
				dice[3][1] = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = tmp;				 
				break;
			default : break;
			}
			/*
			for(int m=0; m<4; m++) {
				for(int n=0; n<3; n++){
					System.out.print(dice[m][n]+" ");
				}
				System.out.println();
			}
			System.out.println();
			*/
			//System.out.println("x : "+x+", y : "+y);
			
			if(map[x][y]==0) {
				//System.out.println("map["+x+"]["+y+"] = 0인경우");
				map[x][y] = dice[3][1];
			}
			else {
				//System.out.println("map["+x+"]["+y+"] = 0이 아닌경우");
				dice[3][1] = map[x][y];
				map[x][y]=0;
			}
			bw.append(String.valueOf(dice[1][1])+"\n");
		}
		bw.flush();
	}
}
