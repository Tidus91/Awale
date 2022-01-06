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

    private void ajouterJoueur(int indice,Joueur j){
        joueurs[indice] = j;
    }
    public Joueur getJoueur(int indice){
        return joueurs[indice];
    }
    public Joueur [] getAllJoueurs(){
        return this.joueurs;
    }
    public int getNbJoueur(){
        return joueurs.length;
    }

    public void initJoueur(int nbJ){
        int nb = 0;

        Scanner scanner = new Scanner(System.in);
        String choixNom = new String();
        int [] tab = new int [grille.length/nbJ];

        while(nb < nbJ){
            for(int i =0; i < tab.length;++i){
                tab[i] = nb * tab.length + (i+1);
            }
            System.out.println("Le joueur1 commencera toujours en premier");
            System.out.println("Quel est le nom du Joueur " + (nb+1) );

            choixNom = scanner.next();

            Joueur joueurf = new Joueur(choixNom,tab);
            if(nb == 0)
                joueurf.setTurn(true);
            ajouterJoueur(nb,joueurf);
            nb++;
        }
    }

    public abstract int jouerUnCoup(int cellule,Joueur j);
    // en vérité je pourrais faire une méthode jouerUnCoup non abstract qui contiendrais des fonctions abstract étant donné
    // que certain passages sont similaire entre cette methode classique et celle de l'owalé


    public String toString(){
        String chaine = new String();
        chaine += "                     J2            \n";
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
        chaine += "\n                     J1             ";
        return chaine;
    }

    public boolean getIsOver(){
        return isOver;
    }

    public int getNbGraine(){
        return nbGraine;
    }
}
