import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, min;//diff;
	static int[][] ability;
	static int[] STeam, LTeam;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		ability = new int[N][N];
		STeam = new int[N/2];
		LTeam = new int[N/2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		STeamMake(0, 0);
		bw.append(String.valueOf(min));
		bw.flush();
	}
	private static void STeamMake(int num, int peoplecnt ) {
		if(peoplecnt==N/2) {
			for(int i=0; i<N/2; i++)
//			System.out.println(STeam[i]);
			LTeamMake();
			return;
		}
		for(int i=num; i<N; i++) {
			STeam[peoplecnt]=i;
			STeamMake(i+1, peoplecnt+1);
		}
	}
	private static void LTeamMake() {
		int Scnt=0,Lcnt=0;
		for(int i=0; i<N; i++) {
			if(Scnt<N/2&&i==STeam[Scnt]) {//범위지정 다시 해야할듯
				Scnt++;
			}
			else {
//				System.out.println("현재 Lcnt : "+Lcnt+", i : "+i);
				LTeam[Lcnt] = i;
				Lcnt++;
			}
		}
		
		CalScore();	
	}
	private static void CalScore() {
		int diff= Integer.MAX_VALUE;
		int Sscore=0, Lscore=0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++ ) {
				Sscore += ability[STeam[i]][STeam[j]];
				Lscore += ability[LTeam[i]][LTeam[j]];
			}
		}
		diff = Math.abs(Sscore-Lscore);
		if(diff<min) min = diff;
	}

}