package TestAlogi;

import org.glassfish.jersey.servlet.internal.PersistenceUnitBinder;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

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


    public static void pre(Node cur ){
        if(cur==null)
            return;
        pre(cur.left);
        System.out.println(cur.val);
        pre(cur.right);
    }

    public static void ceng(Node cur){
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(cur);
        while (!nodes.isEmpty()){
            Node temp=nodes.poll();
            System.out.println(temp.val);
            if(temp.left!=null)
                nodes.offer(temp.left);
            else if (temp.right!=null)
                nodes.offer(temp.right);
        }
    }

    public static Node delMin(Node cur){
        if(cur==null)
            return null;
        if (cur.left==null)
            return cur.right;
        else{
            cur.left=delMin(cur.left);
            return cur;
        }
    }

    public static Node delMax(Node cur) {
        if (cur == null)
            return null;
        if (cur.right == null)
            return cur.left;
        else {
            cur.right = delMax(cur.right);
            return cur;
        }
    }
    @Test
    public void test(){
        int[] a={1,2,3,4,5};
        Node tree=new Node(a[0]);
        for(int i=1;i<a.length;i++){
            tree=add(tree,a[i]);
        }
        System.out.println(contain(tree,3).val);
        System.out.println("pre:");
        pre(tree);
        System.out.println("ceng***************");
        ceng(tree);

        System.out.println("delmin max");
        tree=delMin(tree);
        delMax(tree);
        pre(tree);
    }


}
