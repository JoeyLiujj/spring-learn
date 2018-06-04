package cn.joey.util;

public class SortAlgorithm {
	/**
	 * 插入排序
	 * @param a
	 */
	public static void insertSort(int[] a) {
		int i, j, temp;
		for (i = 1; i < a.length; i++) {
			temp = a[i];
			for (j = i - 1; j >= 0 && a[j] > temp; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = temp;
		}
	}
	
	/**
	 * 选择排序
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		int i, j, k;
		for (i = 0; i < arr.length-1; i++) {
			k = i;
			for (j = i + 1; j < arr.length; j++) {
				if (arr[k] > arr[j]) {
					k = j;
				}
			}
			if (k != i) {
				int temp = arr[i];
				arr[i] = arr[k];
				arr[k] = temp;
			}
		}
	}

	public static void selectSort_Two(int[] a){
		int i,j,min,max,temp;
		for(i=1;i<=a.length/2;i++){
			min=i;max=i;
			for(j=i+1;j<=a.length-i;j++){
				if(a[j]>a[max]){
					max=j;continue;
				}
				if(a[j]<a[min])
					min=j;
			}
			temp=a[i-1];a[i-1]=a[min];a[min]=temp;
			temp=a[a.length-i];a[a.length-i]=a[max];a[max]=temp;
		}
	}
	
	public static void shellInsertSort(int[] a,int dk){
		for (int i = dk; i < a.length; i++) {
			if (a[i] < a[i - dk]) {
				int j = i - dk;
				int x = a[i];
				a[i] = a[i - dk];
				while (j >= 0 && x < a[j]) {
					a[j + dk] = a[j];
					j -= dk;
				}
				a[j + dk] = x;
			}
		}
//		int i, j, temp;
//		for (i = dk; i < a.length; i++) {
//			temp = a[i];
//			for (j = i - 1; j >= 0 && a[j] > temp; j--) {
//				a[j + 1] = a[j];
//			}
//			a[j + 1] = temp;
//		}
	}
	
	/**
	 * 希尔排序
	 * @param a
	 */
	public static void shellsSort(int[] a){
		int dk = a.length/2;
		while(dk>=1){
			shellInsertSort(a, dk);
			dk = dk/2;
		}
	}
	
	/**
	 * 快速排序
	 * 通过一趟排序将待排序的记录分割成两部分，其中一部分的关键字比另一部分的关键字小，即按照一个特定的标识符将记录分成两部分
	 * 并通过两个指针量low和high进行记录
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	public static int partition(int[] a, int low, int high) {
		int privo = a[low];
		while (low < high) {
			while (low < high && a[high] >= privo)
				--high;
			a[low] = a[high];
			while (low < high && a[low] <= privo)
				++low;
			a[high] = a[low];
		}
		a[low] = privo;
		return low;
	}

	public static void qSort(int[] a, int low, int high) {
		if (low < high) {
			int privo = partition(a, low, high);
			qSort(a, low, privo - 1);
			qSort(a, privo + 1, high);
		}
	}

	public static void quickSort(int[] a) {
		qSort(a, 0, a.length - 1);
	}
	
	/**
	 * 堆排序
	 * @param a
	 * @param s
	 * @param length
	 */
	public static void heapAdjust(int[] a, int s, int length) {
		int rc = a[s];
		int child = 2 * s + 1;
		while (child < length) {
			if (child + 1 < length && a[child] < a[child + 1])
				++child;
			if (a[s] < a[child]) {
				a[s] = a[child];
				s = child;
				child = 2 * s + 1;
			} else {
				break;
			}
			a[s] = rc;
		}
	}
	
	public static void buildingHeap(int[] a) {
		for (int i = (a.length - 1) / 2; i >= 0; i--) {
			heapAdjust(a, i, a.length);
		}
	}
	
	public static void heapSort(int[] a) {
		buildingHeap(a);
		for (int i = a.length - 1; i > 0; --i) {
			int temp = a[i];
			a[i] = a[0];
			a[0] = temp;
			heapAdjust(a, 0, i);
		}
	}
	
	/**
	 * 冒泡排序
	 * @param a
	 */
	public static void bubbleSort(int[] a){
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
	
	/**
	 * 归并排序
	 * @param a
	 */
	public static void merge(int[] a,int[] b,int i,int m,int n){
		int j,k;
		for(j=m+1,k=i;j<=n&&i<=m;++k){
			if(a[i]<=a[j]){
				b[k]=a[i++];
			}else{
				b[k]=a[j++];
			}
		}
		while(i<=m) b[k++] = a[i++];
		while(j<=n) b[k++] = a[j++];
	}
	
	public static void mergingSort(int[] a,int[] b){
		int length = a.length;
		int len = 1;
		int[] q=a;
		int[] temp;
		while (len < length) {
			int s = len;
			len = 2 * s;
			int i = 0;
			while (i + len < length) {
				merge(q, b, i, i + s - 1, i + len - 1);
				i = i + len;
			}
			if (i + s < length){
				merge(q, b, i, i + s - 1, length - 1);
			}
			temp = q;
			q = b;
			b = temp;// 交换q,b，以保证下一趟归并时，仍从q 归并到b
		}
	}
	
	/**
	 * 基数排序
	 * @param args
	 */
	public static void radixSort(int[] a){
		
	}
	
	public static void main(String[] args) {
		int a[]={49,38,65,97,55,4,76,13,27,49};
		int b[]=new int[a.length];
		mergingSort(a,b);
		System.out.println("最终结果");
		for(int i=0;i<b.length;i++){
			if(i==b.length-1){
				System.out.print(b[i]);
			}else{
				System.out.print(b[i]+"->");
			}
		}
	}
}
