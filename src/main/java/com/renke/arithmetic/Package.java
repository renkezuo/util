package com.renke.arithmetic;

public class Package {
	public static int max(int x, int y) {
		if (x > y)
			return x;
		return y;
	}
	
	public static int getMax(int[] array){
		int max = -1;
		for (int a : array) {
			if (a > max) {
				max = a;
			}
		}
		return max;
	}
	
	/**
	 * 主要逻辑，判断第一个物品是否需要放入背包，依次类推
	 * 		检查如果不放入和放入的最大值
	 * f(Vmax,V,P) = max(f(Vmax, V1, P1) , f(Vmax - V[0], V1, P1) + P[0]);
	 * arrayCopy(V , 1 , V1 , 0, V.length - 1);
	 * arrayCopy(P , 1 , P1 , 0, P.length - 1);
	 * @param Vmax
	 * @param Vcell
	 * @param Pcell
	 * @return
	 * @author Z.R.K
	 */
	public static int getMaxScore(int Vmax ,int[] Vcell, int[] Pcell){
		int base = 0;
		int other = 0;
		for(int i=0;i<Vcell.length;i++){
			if(Vcell[i] <= Vmax){
				int[] x = new int[Vcell.length - (i+1)];
				int[] y = new int[Pcell.length - (i+1)];
				System.arraycopy(Vcell, i + 1, x, 0, x.length);
				System.arraycopy(Pcell, i + 1, y, 0, y.length);
				base = getMaxScore(Vmax, x, y);
				other = getMaxScore(Vmax - Vcell[0], x, y) + Pcell[0];
				return max(base, other);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int V[] = { 3,4,5,6,7 };
		int P[] = { 6,8,10,12,15 };
		System.out.println(getMaxScore(10, V, P));
	}
}
