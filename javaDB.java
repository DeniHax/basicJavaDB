//Establish a connection to a Java DB database using JDBC
import java.sql.* ; // for standard JDBC programs:
import java.math.* ; // for BigDecimal and BigInteger support
public class javaDB
{
    // JDBC driver name and database URL
    static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static String URL = "jdbc:derby:UNIVERSITYDB";
    // Database credentials
    static String USER = "user";
    static String PASS = "pass";
 public static void main (String[] args)
 {
        Connection conn = null;
        Statement stmt = null;
        try{
            // Step 1: "Load" the JDBC driver
            Class.forName( DRIVER );
            // Step 2: Establish the connection to the database
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(URL, USER, PASS);
            //STEP 3: Create a Statement object
            System.out.println("Creating statement...");
            stmt = conn.createStatement(
            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //STEP 4: Execute a query
            String sql;
            sql = "SELECT id, age, name, dept, salary FROM faculty";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from the result set
            while(rs.next( ))
            {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                String department = rs.getString("dept");
                double salary = rs.getDouble( "salary" );
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + name);
                System.out.print(", DEPARTMENT: " + department);
                System.out.println(", SALARY " + salary);
            }
            //STEP 6: Clean-up environment
            rs.close( );
            stmt.close( );
            conn.close( );
        }
        catch(SQLException se)
        {
        //Handle errors for JDBC
        se.printStackTrace( );
        }
        catch(Exception e)
        {
        //Handle errors for Class.forName
        e.printStackTrace( );
        }
        System.out.println("Goodbye!");
        }//end main
}