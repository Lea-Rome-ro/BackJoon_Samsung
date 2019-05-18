import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, Max=0,Pay=0;
	static int[][] table;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		table = new int[2][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			table[0][i] = Integer.parseInt(st.nextToken());
			table[1][i] = Integer.parseInt(st.nextToken());
		}
		Counsel(0);
		bw.append(String.valueOf(Max));
		bw.flush();

	}
	private static void Counsel(int day) {
//		System.out.println(day+1+"일 함수 시작");
		if(Pay>Max) {
				Max = Pay;
//				System.out.println("Max값 변경 : "+Max);

		}
		for(int i=day; i<N; i++){
			Pay += table[1][i];
//			System.out.println("Pay = "+Pay);
			if(i+table[0][i]<=N)
				Counsel(i+table[0][i]);
			Pay -= table[1][i];
//			System.out.println("Pay값 돌아옴 : "+Pay);
		}		
	}
}