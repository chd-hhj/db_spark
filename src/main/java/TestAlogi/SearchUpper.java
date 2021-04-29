package TestAlogi;

import org.junit.Test;

/**
 * @author hhj
 * @create 2021-04-29 9:34
 */
public class SearchUpper {
    public static int SearchUpper(int[] arr, float tar){
        return searchUpper2(arr,tar,0,arr.length);



    }

    public static int SearchUpper1(int[] arr, float tar,int l,int r){
        if(l==r){
            if(l>=arr.length)
                return -1;
            return arr[l];
        }
        int mid=(l+r)/2;
        if(arr[mid]<=tar)
            return SearchUpper1(arr,tar,mid+1,r);
        else
            return SearchUpper1(arr,tar,l,mid);
    }
    //非递归
    public static int searchUpper2(int[] arr, float tar,int l,int r){
        int i=l;
        int j=r;
        while (i<j){
            int mid=(i+j)/2;
            if(arr[mid]>tar)
                j=mid;
            else
                i=mid+1;
        }
        if(i>=arr.length)
            return -1;
        else
            return i;

    }
    //lower递归
    public static int searchLower(int[] arr, float tar,int l,int r){
        if(l==r){
            if(l<0)
                return -1;
            else
                return l;
        }
        int mid=(l+r+1)/2;
        if(arr[mid]<tar){
            return searchLower(arr,tar,mid,r);
        }
        else
            return searchLower(arr,tar,l,mid-1);
    }

    @Test
    public void test(){
        int[] a={1,2,3,3,3,3,5,7};
        //System.out.println(SearchUpper(a, 2.5F));
        System.out.println(searchLower(a,2.5F,0,7));
    }

}
