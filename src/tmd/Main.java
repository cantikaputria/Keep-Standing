/*
 * Filename     : Main.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : kelas Main untuk mengakses package view
 * Janji        : Saya Cantika Putri Arbiliansyah dengan NIM 2103727 mengerjakan Tugas
 *                Masa Depan dalam mata kuliah DPBO untuk keberkahan-Nya maka  
 *                tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
*/

// AKSES LIBRARY DAN PACKAGE
package tmd;
import View.Menu;

/**
 *
 * @author cacan
 */

//kelas main program
public class Main {
    public static void main(String[] args) {
        //menjalankan kelas menu
        Menu menuu = new Menu();
        //memunculkan window menu
        menuu.show();
    }
    
}
