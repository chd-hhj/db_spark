package reflect;

import org.junit.Test;

/**
 * @author hhj
 * @create 2021-04-22 15:27
 */
class person{

}

public class reflect1 {
    @Test
    public void test1(){
        Class<person> personClass = person.class;
        person person1 = new person();
        System.out.println(person1.getClass());
    }
    @Test
    public void test2() throws ClassNotFoundException {
        System.out.println(Class.forName("java.lang.String")==Class.forName("java.lang.String"));
    }
    @Test
    public void test3(){


    }
}
