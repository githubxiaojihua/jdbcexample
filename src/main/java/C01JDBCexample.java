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
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysqltest1";
        Connection conn = DriverManager.getConnection(url,"root","root");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from category");
        while(rs.next()){
            Object cid = rs.getObject("cid");
            Object cname = rs.getObject("cname");
            System.out.println(cid + "\t" + cname);
        }

    }
}
