package TestAlogi;

import org.junit.Test;

/**
 * @author hhj
 * @create 2021-04-29 10:49
 */

class Node{
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }
}
public class BST {
    public static Node add(Node cur,int a){
        if(cur==null)
            return new Node(a);
        if(cur.val==a)
            return cur;
        else if(cur.val<a){
            cur.right=add(cur.right,a);
            return cur;
        }

        else{
            cur.left=add(cur.left,a);
            return cur;
        }

    }

    public static Node contain(Node cur,int a){
        if(cur==null)
            return null;
        if(cur.val==a)
            return cur;
        if(cur.val<a)
            return contain(cur.right,a);
        return contain(cur.left,a);
    }

    @Test
    public void test(){
        int[] a={1,2,3,4,5};
        Node tree=new Node(1);
        for(int i=1;i<a.length;i++){
            tree=add(tree,a[i]);
        }
        System.out.println(contain(tree,3).val);
    }


}
