package Sort;

import java.util.Arrays;

public class TenSort {

    public static void bubbleSort(int[] array)
    {
        if(array.length==1)
        {
            System.out.println(array[0]);
            return;
        }
        for (int i = 0; i < array.length; i++){
            for (int j = 0 ; j < array.length-i-1; j++)
            {
                if(array[j]>array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void selectionSort(int[] array)
    {
        if(array.length==1)
        {
            System.out.println(array[0]);
            return;
        }
        for (int i = 0; i < array.length; i++)
        {
            for (int j = array.length - 1; j > i; j--)
            {
                if(array[i]>array[j])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[] array)
    {
        if(array.length==1)
        {
            System.out.println(array[0]);
            return;
        }
        for (int i = 1; i < array.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if(array[j]<array[j-1])
                {
                    int temp = array[j];
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }
                else
                {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void QuickSort(int[] array, int low, int high)
    {
        if(low >= high){
            return;
        }
        int first = low;
        int last = high;
        int key = array[first];

        while (first < last)
        {
            while (first<last&&array[last] >= key)
                last--;
            if(first<last)
                array[first++]=array[last];

            while (first < last&&array[first]<=key)
                first++;
            if(first<last)
                array[last--]=array[first];
        }
        array[first] = key;
        QuickSort(array,low,first-1);
        QuickSort(array,first+1,high);
    }

    private static void maxHeap(int[] array, int start,int end){
        int dad = start;
        int son = 2*dad+1;
        while (son <= end){
            if(son+1<=end&&array[son]<array[son+1])
                son++;
            if(array[dad]>array[son])
                return;
            else {
                int temp = array[dad];
                array[dad]=array[son];
                array[son]=temp;
                dad = son;
                son = dad * 2 + 1;
            }
        }
    }

    public static void heapSort(int[] array, int len){
        for (int i = len / 2 - 1; i >= 0; i--)
        {
            maxHeap(array,i,len-1);
        }
        for (int i = len - 1; i > 0; i--) {
            int temp = array[0];
            array[0]=array[i];
            array[i]=temp;
            maxHeap(array, 0, i - 1);
        }
        System.out.println(Arrays.toString(array));
    }

    public static void mergesort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        mergesort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    private static void mergesort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            mergesort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            mergesort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    public static void main(String[] args){
        int[] array = {768112,7691,1220457, 4083, 636452, 4580, 1161139, 6963, 318397, 5093, 131501, 3417, 711250, 5815, 986741, 4178, 248016, 2547, 1074088, 5935, 437592, 5135, 951398, 2141, 590286, 4980, 668449, 4651, 148668, 7517, 875788, 5224, 1174101, 6012, 893262, 1892, 655166};
        int[] array1 = {2,3,4,5,1,7,0};
//        bubbleSort(array1);
//        selectionSort(array1);
//        insertSort(array);
//        QuickSort(array,0,array.length-1);
//        System.out.println(Arrays.toString(array));
        mergesort(array1);

    }
}
