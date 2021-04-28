package reflect;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author hhj
 * @create 2021-04-22 15:27
 */
class person{
    public person(){
        System.out.println("person()");
    }
    public person(String a){
        System.out.println("person()"+a);
    }
}

class Stu extends person{
    public Stu(){
        System.out.println("stu()");
    }
}

public class reflect1 {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<person> personClass = person.class;
        personClass.getDeclaredConstructor(String.class).newInstance("hhj");
//        person person1 = new person();
//        System.out.println(person1.getClass());
    }
    @Test
    public void test2() throws ClassNotFoundException {
        System.out.println(Class.forName("java.lang.String")==Class.forName("java.lang.String"));
    }
    @Test
    public void test3() throws ClassNotFoundException {
        ClassLoader classLoader = person.class.getClassLoader();
        System.out.println(classLoader.getParent());

    }
    @Test
    public void test4() throws IOException {
        Properties pro = new Properties();
        //FileInputStream fs = new FileInputStream("jdbc.properties");

        ClassLoader classLoader = reflect1.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
        pro.load(resourceAsStream);
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        System.out.println("user="+user+"\n"+"password"+password);
    }
    @Test
    public void test5(){
        new Stu();

    }
}


