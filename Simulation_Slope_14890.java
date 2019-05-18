import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int loadcnt,n,l;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		loadcnt=n*2;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
//			System.out.println("�Լ� ����!");
			SlopeRight(i,0);
			SlopeDown(0,i);
		}
		bw.append(String.valueOf(loadcnt));
		bw.flush();
		
	}
	private static void SlopeDown(int row, int col) {
//		System.out.println(col+"���� ���� SlopeDown ����!");
		int cnt = 1,cnt2=0;
		for(int i=1; i<n; i++) {
			int prev = map[row+i-1][col];
			if(prev == map[row+i][col]) {
				cnt++;
			}
			else if(map[row+i][col]-prev==1) {
				if(cnt>=l||cnt2>=l*2) cnt = 1;
				else{
					loadcnt--;
//					System.out.println("loadcnt ���� : "+ loadcnt);
					return;
				}
			}
			else if(prev-map[row+i][col]==1) {
				cnt2=1;
				for(int j=row+i+1; j<n; j++) {
					if(map[j][col]==map[j-1][col]) {
						cnt2++;
					}
					else break;
				}
//				System.out.println(col+"�� �˻� cnt2 : "+cnt2);
				if(cnt2>=l) {
					i += cnt2-1;
					cnt=0;
				}
				else {
					loadcnt--;
//					System.out.println("loadcnt ���� : "+ loadcnt);
					return;
				}
			}
			else if(Math.abs(prev-map[row+i][col])>1) {
				loadcnt--;
//				System.out.println("loadcnt ���� : "+ loadcnt);
				return;
			}
		}
		return;
	}
	private static void SlopeRight(int row, int col) {
//		System.out.println(row+"�࿡ ���� SlopeDown ����!");
		int cnt = 1,cnt2=1;		
		for(int i=1; i<n; i++) {
			int prev = map[row][col+i-1];
			if(prev == map[row][col+i]) {
				cnt++;
			}
			else if(map[row][col+i]-prev==1) {
				if(cnt>=l || cnt2>=l*2) cnt=1;
				else {
					loadcnt--;
//					System.out.println("loadcnt ���� : "+ loadcnt);
					return;
				}
			}
			else if(prev-map[row][col+i]==1) {
				cnt2 = 1;
//				System.out.println("���� �ں��� 1ū ���");
				for(int j=col+i+1; j<n; j++) {
//					System.out.println("���� �ں��� 1ū ��� for��");
					if(map[row][j]==map[row][j-1]) {
//						System.out.println("("+row+","+j+")�� ("+row+","+(j+1)+") ��");
						cnt2++;
					}
					else break;
				}
//				System.out.println(row+"�� �˻� cnt2 : "+cnt2);
				if(cnt2>=l) {
					i +=cnt2-1;
					cnt=0;
				}
				else {
					loadcnt--;
//					System.out.println("loadcnt ���� : "+ loadcnt);
					return;
				}
			}
			else if(Math.abs(prev-map[row][col+i])>1) {
				loadcnt--;
//				System.out.println("loadcnt ���� : "+ loadcnt);
				return;
			}
		}
		return;
	}
}