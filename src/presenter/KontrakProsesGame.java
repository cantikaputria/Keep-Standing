/*
 * Filename     : KontrakProsesGame.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : Kontrak Proses Game
*/

//AKSES LIBRARY
package presenter;

public interface KontrakProsesGame {
    public void prosesDataSkor();       //mengambil data tabel
    public String getId(int i);         //mengambil data id
    public String getUsername(int i);   //mengambil data username
    public String getScore(int i);       //mengambil data score
    public String geStanding(int i);    //mengambil data standing
    public int getSize();               //mengambil ukuran list
    public void terimaData(String update, String username, int score, int standing); //menerima data
}
