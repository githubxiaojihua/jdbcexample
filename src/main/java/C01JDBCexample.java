import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 知识点：
 * jdbc链接mysql数据库
 */
public class C01JDBCexample {
    public static void main(String[] args) throws Exception{
        //1、加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2、创建Connection链接
        String url = "jdbc:mysql://localhost:3306/mysqltest1";
        Connection conn = DriverManager.getConnection(url,"root","root");
        //3、创建执行语句
        Statement statement = conn.createStatement();
        //4、执行语句并返回结果集
        ResultSet rs = statement.executeQuery("select * from category");
        //5、输出结果集
        while(rs.next()){
            Object cid = rs.getObject("cid");
            Object cname = rs.getObject("cname");
            System.out.println(cid + "\t" + cname);
        }
        //关闭资源
        rs.close();
        statement.close();
        conn.close();

    }
}
