//STEP 1. Import required packages
package connection;

import java.sql.*;

public class ConnectionFactory {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "org.connection.jdbc.Driver";
    static final String DB_URL = "jdbc:connection://localhost/academia";

    //  Database credentials
    static final String USER = "academia";
    static final String PASS = "academia";

    public static java.sql.Connection getConnection(){

        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão", e);
        }
    }

    public static void closeConnection(java.sql.Connection con){

        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error: "+e);
            }
        }
    }

    public static void closeConnection(java.sql.Connection con, PreparedStatement stmt){

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error: "+e);
            }
        }

        closeConnection(con);
    }

    public static void closeConnection(java.sql.Connection con, PreparedStatement stmt, ResultSet rs){

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error: "+e);
            }
        }

        closeConnection(con, stmt);
    }

    /*

    public void main() {
        ConnectionFactory conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION "
                    + "(id INTEGER not NULL, "
                    + " first VARCHAR(255), "
                    + " last VARCHAR(255), "
                    + " age INTEGER, "
                    + " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    */

}//end JDBCExample
