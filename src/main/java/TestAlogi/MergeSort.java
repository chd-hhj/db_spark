package TestAlogi;

import org.apache.spark.sql.execution.columnar.INT;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author hhj
 * @create 2021-04-28 16:41
 */



public class MergeSort {

    public static <E extends Comparable<E>> void sort(E[] arr){

        sort1(arr,0,arr.length-1);
    }

    public static <E extends Comparable<E>> void sort1(E[] arr,int i,int j){
        if(i==j){
            return;
        }
        int mid=(i+j)/2;
        sort1(arr,i,mid);
        sort1(arr,mid+1,j);
        merge(arr,i,mid,j);
    }

    public static <E extends Comparable<E>> void merge(E[] arr,int l,int mid,int r){
        E[] clone = Arrays.copyOfRange(arr,l,r+1);
        int i=l,j=mid+1;
        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=clone[j-l];
                j++;
            }
            else if(j>r){
                arr[k]=clone[i-l];
                i++;
            }
            else if(clone[i-l].compareTo(clone[j-l])<=0){
                arr[k]=clone[i-l];
                i++;
            }
            else{
                arr[k]=clone[j-l];
                j++;

            }


        }

    }
    @Test
    public void test(){
 ;
        int[] b={6,3,8,9};
        //先将int数组转换为数值流
        IntStream stream = Arrays.stream(b);
        //流中的元素全部装箱，转换为流 ---->int转为Integer
        Stream<Integer> integerStream = stream.boxed();
        //将流转换为数组
        Integer[] integers = integerStream.toArray(Integer[]::new);
        sort(integers);
        for(Integer x: integers){
            System.out.println(x);
        }
    }

}
