package HDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * @author hhj
 * @create 2021-05-07 16:09
 */
public class HDFSTest {
    private FileSystem fs;
    @Before
    public void before() throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        fs = FileSystem.get(URI.create("hdfs://master:9870"), conf,"root");

    }
    @After
    public void after() throws IOException {
        fs.close();

    }

    @Test
    public void test1() throws IOException {
        fs.mkdirs(new Path("/test"));

    }
}
