
/*
 * Filename     : DB.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : package model untuk mengakses database
*/

//AKSES LIBRARY DAN PACKAGE
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private String url ="jdbc:mysql://localhost/db_tmd";
    private String user="root";
    private String pass="";
    
    private Statement stmt = null;  //koneksi query
    private ResultSet rs = null;    //hasil query
    private Connection conn = null; //koneksi MySql dan basis data
    
    public DB() throws Exception, SQLException{
        
        try{
            //membuat driver MySQL
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection(url,user,pass);
        }
        catch(SQLException es){
            //mengeluarkan pesan error jika koneksi gagal
            throw es;
        }
    }
    
    public void createQuery(String Query)throws Exception, SQLException {
        /*
         * method createQuery
         * mengeksekusi query tanpa mengubah isi data
         * menerima masukan berupa string query
         */
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)){
                rs = stmt.getResultSet();
            }
        }
        catch(SQLException es){
            throw es;
        }
    }
    
    public void createUpdate(String Query) throws Exception, SQLException {
        /*
         * method createUpdate
         * mengeksekusi query update, insert, delete
         * menerima masukan berupa string query
         */
        
        try{
            stmt = conn.createStatement();
            int hasil = stmt.executeUpdate(Query);
        }
        catch(SQLException es){
            throw es;
        }
    }
    
    public ResultSet getResult() throws Exception{
        /*
         * method getResult
         * memberikan hasil query
         */
        ResultSet Temp = null;
        try{
            return rs;
        }
        catch(Exception ex){
            //eksepsi jika hasil tidak dapat dikembalikan
            return Temp;
        }
    }
    
    public void closeResult() throws Exception, SQLException {
        /*
         * method closeResult
         * menutup hubungan dari eksekusi query
         */
        
        if(rs != null){
            try{
                rs.close();
            }
            catch(SQLException ex){
                rs = null;
                throw ex;
            }
        }
        
        if(stmt != null){
            try{
                stmt.close();
            }
            catch(SQLException ex){
                stmt = null;
                throw ex;
            }
        }
    }
    
    public void closeConnection() throws Exception, SQLException {
        /*
         * method closeConnection
         * menutup hubungan dengan MySQL dan basis data
         */
        
        if(conn != null){
            try{
                conn.close();
            }
            catch(SQLException ex){
                conn = null;
                throw ex;
            }
        }
    }
}
