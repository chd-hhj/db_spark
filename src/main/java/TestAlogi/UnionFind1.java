package TestAlogi;

/**
 * @author hhj
 * @create 2021-05-07 10:12
 */
public class UnionFind1 implements UF{
    private int[] arr;

    public UnionFind1(int size) {
        arr=new int[size];
        for(int i=0;i<size;i++){
            arr[i]=i;
        }
    }

    private int find(int p){
        if(p<0||p>=arr.length)
            throw new IllegalArgumentException("p is out of bound");
        else
            return arr[p];
    }

    @Override
    public int getSize() {
        return arr.length;
    }

    @Override
    public void union(int p, int q) {
        int pid=find(p);
        int qid=find(q);
        if(pid==qid)
            return;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==pid)
                arr[i]=qid;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }
}
