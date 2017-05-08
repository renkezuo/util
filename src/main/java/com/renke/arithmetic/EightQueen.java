package com.renke.arithmetic;

public class EightQueen {
	public static int cnt = 0;
	public static int queens = 8;

	// ��¼��ʷ
	public static void way(int row, int[] oldboard) {
		int chessboard[] = new int[queens];
		System.arraycopy(oldboard, 0, chessboard, 0, queens);
		for (int k = 1; k <= queens; k++) {
			boolean bl = true;
			for (int i = 1; i < row; i++) {
				//��ͬ��
				if(chessboard[row - 1 - i] == k){
					bl = false;
					break;
				}
				//����б���ϰ�
				if (chessboard[row - 1 - i] == k - i) {
					bl = false;
					break;
				}
				//����б���ϰ�
				if (chessboard[row - 1 - i] == k + i) {
					bl = false;
					break;
				}
			}
			if (bl) {
				chessboard[row - 1] = k;
				if (row == queens) {
					for (int i = 0; i < queens; i++) {
						System.out.println("row:" + (i + 1) + ",col:" + chessboard[i]);
					}
					cnt++;
					break;
				}else{
					way(row + 1, chessboard);
					chessboard = oldboard;
				}
			}
		}
	}

	// ��ȡһ����Ԫ���жϵ�Ԫ����ݺ�б�ĵ�Ԫ���Ƿ��ͻ
	// ����ͻ������Ϊһ�������

	// ���飬���У�б�У����谭
	public static void main(String[] args) {
		way(1, new int[queens]);
		System.out.println(cnt);
	}
}
