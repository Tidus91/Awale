package Awale;
import Joueur.Joueur;

import java.util.Scanner;

public abstract class Awale implements IAwale{
    private int [] grille;
    private int nbGraine;
    private Joueur [] joueurs;
    private boolean isOver;

    public Awale(int taille,int nbG,int nbJ){
        this.grille = new int[taille];
        this.nbGraine = nbG;
        this.joueurs = new Joueur[nbJ];
        isOver = false;
    }

    public void setCaseGrille(int indice,int valeur){
        grille[indice] = valeur;
    }
    public void setCaseGrilleAjout(int indice){
        grille[indice] += 1;
    }
    public int getCaseGrille(int indice){
        return grille[indice];
    }
    public int getLongueurGrille(){
        return grille.length;
    }

    public void ajouterJoueur(int indice,Joueur j){
        joueurs[indice] = j;
    }
    public Joueur getJoueur(int indice){
        return joueurs[indice];
    }
    public int getNbJoueur(){
        return joueurs.length;
    }

    public abstract int jouerUnCoup(int cellule,Joueur j);
    // en vérité je pourrais faire une méthode jouerUnCoup non abstract qui contiendrais des fonctions abstract étant donné
    // que certain passages sont similaire entre cette methode classique et celle de l'owalé

    public String toString(){
        String chaine = new String();
        chaine += "NORD    12   11   10    9    8    7";
        chaine += "\n";
        chaine += "      ";
        for(int i=11;i>5;--i){
            chaine += " [ "+grille[i] +"]";
        }
        chaine += "\n";
        chaine += "      ";
        for(int i=0;i<6;++i){
            chaine += " [ "+grille[i] +"]";
        }
        chaine += "\n";
        chaine += "SUD      1    2    3    4    5    6";
        return chaine;
    }

    public boolean getIsOver(){
        return isOver;
    }

    public int getNbGraine(){
        return nbGraine;
    }
}
