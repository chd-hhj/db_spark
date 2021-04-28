package TestAlogi;

import org.junit.Test;

import java.security.cert.X509Certificate;

/**
 * @author hhj
 * @create 2021-04-27 16:20
 */
public class MaxHeap {

    public static <E extends Comparable<E>> void heapify(E[] arr){
        int cur=parent(arr.length-1);
        for(int i=arr.length-1;i>=0;i--){
            shiftUp(arr,i);
        }
    }

    public static  <E extends Comparable<E>> void heapSort(E[] arr){
        heapify(arr);
        int i=0;
        int j=arr.length-1;
        while (i<j){
            swap(arr,i,j);
            j--;
            shiftDown(arr,i,j);

        }
    }

    public static <E extends Comparable<E>> void shiftDown(E[] arr,int i){
        while (true){
            int curLeft=leftChild(i);
            int curRight=rightChild(i);
            if(curLeft<arr.length&&arr[curLeft].compareTo(arr[i])>0){
                if(curRight<arr.length&&arr[curRight].compareTo(arr[curLeft])>0){
                    swap(arr,i,curRight);
                    i=curRight;
                }
                else{
                    swap(arr,i,curLeft);
                    i=curLeft;
                }
            }
            else if(curRight<arr.length&&arr[curRight].compareTo(arr[i])>0){
                swap(arr,i,curRight);
                i=curRight;
            }
            else
                break;
        }
    }
    public static <E extends Comparable<E>> void shiftDown(E[] arr,int i,int j){
        while (true){
            int curLeft=leftChild(i);
            int curRight=rightChild(i);
            if(curLeft<j+1&&arr[curLeft].compareTo(arr[i])>0){
                if(curRight<j+1&&arr[curRight].compareTo(arr[curLeft])>0){
                    swap(arr,i,curRight);
                    i=curRight;
                }
                else{
                    swap(arr,i,curLeft);
                    i=curLeft;
                }
            }
            else if(curRight<j+1&&arr[curRight].compareTo(arr[i])>0){
                swap(arr,i,curRight);
                i=curRight;
            }
            else
                break;
        }
    }

    public static <E extends Comparable<E>> void shiftUp(E[] arr,int i){
        int cur=i;
        while (cur>0&&arr[parent(cur)].compareTo(arr[cur])<0){
            swap(arr,cur,parent(cur));
            cur=parent(cur);
        }
    }

    public static int parent(int i){
        return (i-1)/2;
    }
    public static int leftChild(int i){
        return 2*i+1;
    }
    public static int rightChild(int i){
        return 2*i+2;
    }
    public static <E extends Comparable<E>> void swap(E[] arr,int i,int j){
        E temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    @Test
    public void test1(){
        Integer[] arr={3,2,3,1,2,4,5,5,6};

        heapSort(arr);
        for(int x:arr){
            System.out.println(x);
        }
    }

}
