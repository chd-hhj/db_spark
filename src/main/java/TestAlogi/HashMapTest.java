package TestAlogi;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hhj
 * @create 2021-04-28 9:59
 */

class num{

    private int n;
    public int getN() {
        return n;
    }



    num(int n) {
        this.n = n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public int hashCode() {
        return n/10;
    }

    @Override
    public boolean equals(Object obj) {
        num obj1=(num) obj;
        if(n == obj1.getN()){
            return true;
        }
        else
            return false;
    }
}

public class HashMapTest {
    @Test
    public void test(){
        int[] arr={3,2,3,1,2,4,5,5,6};
        Arrays.sort(arr);
        }
    }

