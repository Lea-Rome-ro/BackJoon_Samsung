import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, tmp, dir, tmpdir, startwheel, score;
	static boolean nextDoR = true, nextDoL=true;
	static int[][] TW = new int[4][8];
	public static void main(String[] args) throws Exception{
		for(int i=0; i<4; i++) {
			String string = br.readLine();
			for(int j=0; j<8; j++) {
				TW[i][j] = string.charAt(j)-48;
			}			
		}
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
//			System.out.println((i+1)+"번째 회전 시작");
			st = new StringTokenizer(br.readLine());
			startwheel = Integer.parseInt(st.nextToken())-1;
			dir = Integer.parseInt(st.nextToken());
			nextDoR = nextDoL = true;
			if(startwheel!=0&&TW[startwheel][6]==TW[startwheel-1][2]) nextDoL = false;
//			System.out.println("TW["+startwheel+"][2] ="+TW[startwheel][2]+", TW["+(startwheel+1)+"][6] = "+TW[startwheel+1][6]);
			if(startwheel!=3&&TW[startwheel][2]==TW[startwheel+1][6]) nextDoR = false;
			switch(dir) {
			case -1 : 
				tmp = TW[startwheel][0];
				for(int k=0; k<7; k++) {
					TW[startwheel][k] = TW[startwheel][k+1];
				}
				TW[startwheel][7] = tmp;
				break;
			case 1:
				tmp = TW[startwheel][7];
				for(int k=7; k>0; k--) {
					TW[startwheel][k] = TW[startwheel][k-1];
				}
				TW[startwheel][0] = tmp;
				break;
			default : break;
			}
			tmpdir = dir;		
			for(int j = startwheel; j>0; j--) {
				if(nextDoL==false) break;
				if(j>1&&TW[j-1][6]==TW[j-2][2]) nextDoL = false;
				switch(tmpdir) {
				case 1 : 
					tmp = TW[j-1][0];
					for(int k=0; k<7; k++)
						TW[j-1][k] = TW[j-1][k+1];
					TW[j-1][7] = tmp;
					tmpdir = -1;
					break;
				case -1:
					tmp = TW[j-1][7];
					for(int k=7; k>0; k--)
						TW[j-1][k] = TW[j-1][k-1];
					TW[j-1][0] = tmp;
					tmpdir = 1;
					break;
				default : break;
				}
			}
			tmpdir = dir;
			for(int j=startwheel; j<3; j++) {
				if(nextDoR==false) break;
				if(j<2&&TW[j+1][2]==TW[j+2][6]) nextDoR = false;
				switch(tmpdir) {
				case 1 : 
					tmp = TW[j+1][0];
					for(int k=0; k<7; k++)
						TW[j+1][k] = TW[j+1][k+1];
					TW[j+1][7] = tmp;
					tmpdir = -1;
					break;
				case -1:
					tmp = TW[j+1][7];
					for(int k=7; k>0; k--)
						TW[j+1][k] = TW[j+1][k-1];
					TW[j+1][0] = tmp;
					tmpdir = 1;
					break;
				default : break;
				}
				/*
				for(int m=0; m<4; m++) {
					for(int n=0; n<8; n++)
						System.out.print(TW[m][n]);
					System.out.println();
				}
				System.out.println();
				*/
			}
		}
		score = 0;
		for(int i=0; i<4; i++) {
			score += TW[i][0]*Math.pow(2, i);
		}
		bw.append(String.valueOf(score));
		bw.flush();
	}
	
}