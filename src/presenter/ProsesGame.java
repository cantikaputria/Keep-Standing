/*
 * Filename     : ProsesGame.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : Proses Logic Hitung Skor dan Standing dalam game
*/

//AKSES LIBRARY DAN PACKAGE
package presenter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Skor;
import model.TabelSkor;

public class ProsesGame implements KontrakProsesGame{
    
    //DEKLARASI VARIABEL
    private String error;                           //deklarasi error handing
    private TabelSkor tabelSkor;                    //kelas untuk mengakses query tabel skor
    private ArrayList<Skor> data;                   //list penyimpan hasil skor
    
    //PROSEDUR PROSES GAKE
    public ProsesGame(){
        try{
            tabelSkor = new TabelSkor();            //instansiasi tabel skor
            data = new ArrayList<Skor>();           //instansiasi data user
        }
        //ERROR HANDLING
        catch(Exception e){
            error = e.toString();
        }
    }
    
    @Override
    //MENGAMBIL DAN MEMPROSES DATA TABEL
    public void prosesDataSkor() {
        try{
            tabelSkor.getSkor();                    //mengambil data dari tabel skor
            
            //selama data tabelnya ada isinya
            while(tabelSkor.getResult().next()){
                //mengambil hasil query
                Skor skor = new Skor();             //instansiasi objek skor untuk setiap data skor
                
                //mengambil data di tabel untuk dimasukkan
                skor.setId(tabelSkor.getResult().getString(1));
                skor.setUsername(tabelSkor.getResult().getString(2));
                skor.setScore(tabelSkor.getResult().getString(3));
                skor.setStanding(tabelSkor.getResult().getString(4));
                
                //menambahkan data ke list
                data.add(skor);
            }            
        }
        
        //ERROR HANDLING
        catch(Exception e){
            System.err.println("GAGAL!!!!" +e.getMessage());
        }
    }
   
    //MEMBACA DATA YANG ADA TABEL
    public DefaultTableModel readTable(){
        
        DefaultTableModel dataTabel = null;
        try{
            //mengambil data di tabel pengguna
            Object[] column = {"Username", "Score", "Standing"};
            dataTabel = new DefaultTableModel(null, column);
            
            data.clear();                                   //mengosongkan araay list
            prosesDataSkor();                               //memanggil prosedur prosesDataSkor
            
            //memasukkan data
            for(int i=0; i<data.size(); i++){
                Object[] hasil = new Object[3];
                hasil[0] = getUsername(i);
                hasil[1] = getScore(i);
                hasil[2] = getStanding(i);
                
                dataTabel.addRow(hasil);
            }
        }
        
        //ERROR HANDLING
        catch(Exception e){
            System.err.println("Read gagal " +e.getMessage());
        }
        
        //mengembalikan nilai data
        return dataTabel;
    }
    
    //mengembalikan id pengguna dengan indeks ke i
    public String getId(int i){
        return data.get(i).getId();
    }
     
    //mengembalikan username pengguna dengan indeks ke i
    public String getUsername(int i){
        return data.get(i).getUsername();
    }
    
    //mengembalikan skor Score dengan indeks ke i
    public String getScore(int i){
        return data.get(i).getScore();
    }
    
    //mengembalikan skor standing dengan indeks ke i
    public String getStanding(int i){
        return data.get(i).getStanding();
    }
    
    //mengembalikan banyaknya isi list
    public int getSize(){
        return data.size();
    }
    
    //MENERIMA DATA
    public void terimaData(String update, String username, int Score, int standing){

        Skor skor= new Skor();
        skor.setUsername(username);
        skor.setScore(Integer.toString(Score));
        skor.setStanding(Integer.toString(standing));
        
        //melakukan insert jika nilai updatenya -1
        if("-1".equals(update)){
            
            //memanggil model
            tabelSkor.insertSkor(skor.getUsername(), skor.getScore(), skor.getStanding());
        }else{
            
            //memanggil model
            tabelSkor.updateSkor(update, skor.getScore(), skor.getStanding());
        }
    }   

    @Override
    public String geStanding(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   
}
