package Awale;
import Joueur.Joueur;

import java.util.Scanner;

public abstract class Awale {
    public int [] grille;
    public boolean isOver;

    public Awale(int taille){
        this.grille = new int[taille];
        isOver = false;
    }

    public void jouerUnePartie(){

    }

    public abstract void jouerUnCoup(int cellule,Joueur j);
    // recopier içi ce qui est en commun avec classique et owale, puis mettre dans une fonction les differences
}
