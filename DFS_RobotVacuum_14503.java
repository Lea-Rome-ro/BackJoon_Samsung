import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] dxy = {{0,-1,0,1},{-1,0,1,0}};
	static int[][] room;
	static boolean[][] chk;
	static int N,M,r,c,dir,cleanArea=0;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		chk = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Clean(r, c);
		bw.append(String.valueOf(cleanArea));
		bw.flush();
	}
	public static void Clean(int r2, int c2) {
		int row=r2, col = c2,cnt=0;
//		System.out.println("Clean함수 row : "+row + ", col : "+col);
//		System.out.println("현재 Dir : "+dir);

		boolean done=false;
		if(chk[row][col]==false) {
			chk[row][col] = true;
			cleanArea++;
		}
		while(!done && cnt<4) {
			cnt++;
			int tmpr = row+dxy[0][dir], tmpc = col+dxy[1][dir];
			if(tmpr>=0&& tmpr<N && tmpc>=0 && tmpc<M) {
				switch(dir) {
				case 0 :
					dir = 3; 
					if(room[tmpr][tmpc]==0 && chk[tmpr][tmpc]==false) {
						done=true;
						Clean(tmpr,tmpc);
					}
					break;
				case 1:
					dir = 0; 
					if(room[tmpr][tmpc]==0 && chk[tmpr][tmpc]==false) {
						done=true;
						Clean(tmpr,tmpc);
					}
					break;
				case 2:
					dir = 1; 
					if(room[tmpr][tmpc]==0 && chk[tmpr][tmpc]==false) {
						done=true;
						Clean(tmpr,tmpc);
					}
					break;
				case 3:
					dir = 2; 
					if(room[tmpr][tmpc]==0 && chk[tmpr][tmpc]==false) {
						done=true;
						Clean(tmpr,tmpc);
					}
					break;
				default: break;
				}
				
			}
		}
		if(cnt==4&&done==false) {
//			System.out.println("후진 필요!, Dir : "+dir);
			int tmpr=0,tmpc=0;
			switch(dir) {
			case 0 :
				tmpr = row+dxy[0][3]; tmpc = col+dxy[1][3];
				break;
			case 1:
				tmpr = row+dxy[0][0]; tmpc = col+dxy[1][0];
				break;
			case 2:
				tmpr = row+dxy[0][1]; tmpc = col+dxy[1][1];
				break;
			case 3:
				tmpr = row+dxy[0][2]; tmpc = col+dxy[1][2];
				break;
			default: break;
			}
//			System.out.println("tmpr : "+tmpr + ", tmpc : "+tmpc);
			if(tmpr>=0&& tmpr<N && tmpc>=0 && tmpc<M && room[tmpr][tmpc]==0)
				Clean(tmpr,tmpc);
		}
	}
}
