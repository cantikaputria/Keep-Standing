
/*
 * Filename     : TabelSkor.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : package model untuk mengambil data dari DB
*/

//AKES LIBRARY DAN PACJAFE
package model;
import java.sql.SQLException;


public class TabelSkor extends DB{

    public TabelSkor() throws  Exception, SQLException{
        //kontruktor
        super();
    }

    public void getSkor(){
        //mengeksekusi query untuk mengambil semua data pada tabel 
        try{
            String query = "SELECT * FROM tscore";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void insertSkor(String username, String score, String standing){
        //mengeksekusi query untuk insert data tabel
        try{
            String query = "INSERT INTO tscore (username, score, standing) VALUES"
                    + "('"+username+"', '"+score+"', '"+standing+"');";

            createUpdate(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void updateSkor(String id, String score, String standing){
        //mengeksekusi query untuk update data tabel
        try{
            String query = "UPDATE tscore setScore = " + score + ", standing = " + standing
                    + " where id = "+id;

            System.out.println(query);
            createUpdate(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

}