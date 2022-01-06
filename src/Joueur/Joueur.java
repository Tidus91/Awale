package Joueur;

import java.util.Scanner;

public class Joueur {
    private String nom;
    private int score;
    private boolean turn;
    private int [] camp;

    public Joueur(String nom,int [] c){
        this.nom = nom;
        this.camp = new int [c.length];
        this.camp = c.clone();
        this.score = 0;
        this.turn = false;
    }

    public String getNom(){
        return nom;
    }

    public int getScore(){
        return score;
    }
    public void setScore(int v){
        this.score += v;
    }

    public void setTurn(boolean value){
        this.turn = value;
    }

    public boolean getTurn(){
        return turn;
    }

    public int[] getCamp(){return camp;}

    public boolean isMyCamp(int cellule){
        for(int i=0;i<camp.length;++i){
            if(cellule == camp[i])
                return true;
        }
        return false;
    }

    public boolean propositionAbandon(Joueur [] joueursAdverse){
        Scanner scanner = new Scanner(System.in);
        for(int j=0;j< joueursAdverse.length;++j){
            System.out.println("Proposition d'abandon, êtes vous d'accord pour abandonner " + joueursAdverse[j].getNom() + " ? \t tapez 0 pour abandonner ");
            int choix = scanner.nextInt();
            if(choix != 0)
                return false;
        }
        return true;
    }

    public String toString(){
        return (nom +" :  " +String.valueOf(score) + " Bille(s)");
    }
}
