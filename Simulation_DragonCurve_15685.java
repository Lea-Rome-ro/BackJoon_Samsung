import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int top, N, g, dir, x, y,cnt=0 ;
	static int[] stack;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()); y = Integer.parseInt(st.nextToken()); dir = Integer.parseInt(st.nextToken()); g = Integer.parseInt(st.nextToken());
			stack = new int[(int) Math.pow(2, g)];
			top = 0;
			stack[top] = dir;	
			while(top<Math.pow(2, g)-1) {
				for(int j=top; j>=0; j--) {
//					System.out.println("J : "+j);
					top++;
//					System.out.println("stack["+top+"] = stack["+j+"] + 1");
					stack[top] = stack[j]+1;
					if(stack[top]==4) stack[top]=0;
					
				}
			}
//			System.out.println("ÇöÀç top : "+top);
//			for(int j=0; j<=top; j++)
//				System.out.println("stack["+j+"] = "+stack[j]);
			makeCurve();
/*			for(int k=0; k<10; k++) {
				for(int j=0; j<10; j++) {
					System.out.print(map[k][j]+" ");
				}
				System.out.println();
			}
			*/
		}
		for(int i=0; i<100; i++){
			for(int j=0; j<100; j++) {
				if(map[i][j]==true) {
					if(map[i+1][j]==true && map[i][j+1]==true && map[i+1][j+1]==true)
						cnt++;
				}
			}		
		}
		bw.append(String.valueOf(cnt));
		bw.flush();
	}
	private static void makeCurve() {
		map[y][x]=true;
		for(int i=0; i<=top; i++) {
			switch(stack[i]) {
			case 0 : 
				x++;break;
			case 1: 
				y--;break;
			case 2: 
				x--;break;
			case 3: 
				y++;break;
			default : break;
			}
//			System.out.println("y : "+y+", x : "+x);
			if(x>=0&&x<101&&y>=0&&y<101)
				map[y][x] = true;
			else return;
		}
	}
}