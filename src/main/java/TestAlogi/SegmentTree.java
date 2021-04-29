package TestAlogi;


import java.awt.dnd.InvalidDnDOperationException;

/**
 * @author hhj
 * @create 2021-04-29 19:30
 * 线段树
 */

interface Merge<E>{
    E merge(E a,E b);
}

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merge merger;
    public SegmentTree(E[] arr,Merge merge1) {
        data= (E[]) new Object[arr.length];
        for(int i=0;i<arr.length;i++){
            data[i]=arr[i];
        }
        tree= (E[]) new Object[4*arr.length];
        merger=merge1;
        buildSegmentTree(0,0,data.length-1);
    }

    private void buildSegmentTree(int treeindex,int l,int r) {
        if(l==r){
            tree[treeindex]=data[l];
            return;
        }
        int mid=l+(r-l)/2;
        buildSegmentTree(left(treeindex),l,mid);
        buildSegmentTree(right(treeindex),mid+1,r);
        tree[treeindex]= (E) merger.merge(tree[left(treeindex)],tree[right(treeindex)]);
    }

    public E query(int l, int r){
        return query(0,0,data.length-1,l,r);
    }

    private E query(int index, int i, int j, int l, int r){
        if(i==l&&j==r)
            return tree[index];
        int mid=i+(j-i)/2;
        if(l>mid){
            return query(right(index),mid+1,j,l,r);
        }
        else if(r<=mid){
            return query(left(index),i,mid,l,r);
        }
        else
            return (E) merger.merge(query(right(index),mid+1,j,mid+1,r),query(left(index),i,mid,l,mid));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(int i=0;i<tree.length;i++){
            if(tree[i]!=null)
                stringBuilder.append(tree[i]);
            else
                stringBuilder.append("null");
            if(i!=tree.length-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public E get(int index){
        if(index<0||index>=data.length)
            throw new IllegalArgumentException("index out of range");
        return data[index];
    }

    private int left(int index){
        return index*2+1;
    }
    private int right(int index){
        return index*2+2;
    }

    public static void main(String[] args) {
        Integer[] arr={-2,0,3,-5,2,-1};
        SegmentTree<Integer> integerSegmentTree = new SegmentTree<>(arr, new Merge<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a+b;
            }
        });
        System.out.println(integerSegmentTree);
        System.out.println(integerSegmentTree.query(0,2));
        System.out.println(integerSegmentTree.query(2,5));
        System.out.println(integerSegmentTree.query(0,5));
    }


}
