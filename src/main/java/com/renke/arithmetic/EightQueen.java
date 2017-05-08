package com.renke.arithmetic;

public class EightQueen {
	public static int cnt = 0;
	public static int queens = 8;

	// 记录历史
	public static void way(int row, int[] oldboard) {
		int chessboard[] = new int[queens];
		System.arraycopy(oldboard, 0, chessboard, 0, queens);
		for (int k = 1; k <= queens; k++) {
			boolean bl = true;
			for (int i = 1; i < row; i++) {
				//不同列
				if(chessboard[row - 1 - i] == k){
					bl = false;
					break;
				}
				//右上斜无障碍
				if (chessboard[row - 1 - i] == k - i) {
					bl = false;
					break;
				}
				//右上斜无障碍
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

	// 获取一个单元格，判断单元格和纵横斜的单元格是否冲突
	// 不冲突，保存为一个结果，

	// 数组，行列，斜行，无阻碍
	public static void main(String[] args) {
		way(1, new int[queens]);
		System.out.println(cnt);
	}
}
