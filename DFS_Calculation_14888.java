import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, Min, Max, result=0;
	static int[] Num,Operator = new int[4] ;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		Num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			Num[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			Operator[i] = Integer.parseInt(st.nextToken());
		Min = Integer.MAX_VALUE; Max = Integer.MIN_VALUE;
		result = Num[0];
		Cal(0);
		bw.append(String.valueOf(Max)+"\n"+String.valueOf(Min));
		bw.flush();
	}
	private static void Cal(int num) {
		if(Operator[0]==0&&Operator[1]==0&&Operator[2]==0&&Operator[3]==0) {
			if(result<Min)Min = result;
			if(result>Max)Max = result;
			return;
		}
		for(int j=0; j<4; j++) {
			if(Operator[j]!=0) {
				int tmp = result;
				switch(j) {
				case 0:
					result += Num[num+1];break;
				case 1 : 
					result -= Num[num+1];break;
				case 2 : 
					result *= Num[num+1];break;
				case 3 : 
					result /= Num[num+1];break;
				default : break;
				}
				Operator[j]--;
				Cal(num+1);
				Operator[j]++;
				result = tmp;
			}
		}
		/*
		for(int i=num; i<N; i++) {
			for(int j=0; j<4; j++) {
				if(Operator[j]!=0) {
					int tmp = result;
					switch(j) {
					case 0:
						result += Num[i+1];break;
					case 1 : 
						result -= Num[i+1];break;
					case 2 : 
						result *= Num[i+1];break;
					case 3 : 
						result /= Num[i+1];break;
					default : break;
					}
					Operator[j]--;
					Cal(i+1);
					Operator[j]++;
					result = tmp;
				}
			}
		}
		*/
			
	}

}
