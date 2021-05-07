package TestAlogi;

/**
 * @author hhj
 * @create 2021-05-07 10:09
 * 并查集接口
 */
public interface UF {
    int getSize();
    public void union(int i,int j);
    public boolean isConnected(int i,int j);
}
