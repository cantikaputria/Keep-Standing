
/*
 * Filename     : sKOR.java
 * Programmer   : Cantika Putri Arbiliansyah
 * Deskripsi    : package model untuk menampung setiap data pengguna
*/

//AKES PACKAGE
package model;

//CLASS KOR
public class Skor {
    //VARIABEL
    private String id;
    private String username;
    private String score;
    private String standing;
    
    public Skor() {
        //konstruktor
    }
    
    //GETTER DAN SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }
 
}
