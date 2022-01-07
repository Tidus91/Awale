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

    private void initJoueur(int nbJ){
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

    @Override
    public int jouerUnePartie() {
        Scanner scanner = new Scanner(System.in);

        initJoueur(getNbJoueur());
        int j=0;
        while(!isOver) {
            int choix = 0;
            if (getJoueur(j).getTurn()) {
                String plateau = this.toString();//Jeu.toString();
                System.out.println(plateau);
                System.out.println("\n");

                for (int i = 0; i < getNbJoueur(); ++i)
                    System.out.println(getJoueur(i).toString());


                System.out.println("Ou voulez vous jouer ? " + getJoueur(j).getNom());
                choix = scanner.nextInt();

                while ((!(getJoueur(j).isMyCamp(choix)) && choix != 0)) {
                    System.out.println("Erreur lors de la selection du coup, veuillez taper une cellule vous appartenant");
                    choix = scanner.nextInt();
                }

                if (choix == 0) {
                    if (getJoueur(j).propositionAbandon(getAllJoueurs())) {
                        int grainesRestante = 0;
                        for (int i = 0; i < getLongueurGrille(); ++i) {
                            grainesRestante += getCaseGrille(i);
                        }
                        for (int z = 0; z < getNbJoueur(); ++z) {
                            getJoueur(z).setScore(grainesRestante / getNbJoueur());
                        }
                        return 5;
                    } else {
                        System.out.println("Refus d'abandon d'un joueur !");
                        continue; // frère du break, je reviens au début de ma boucle, sans sortir
                    }
                }

                // temp utile pour savoir les graines gagner pendant le tour de jeu
                int[] temp = new int[getNbJoueur()];
                for (int z = 0; z < getNbJoueur(); ++z)
                    temp[z] = getJoueur(j).getScore();

                int retourJouer = this.jouerUnCoup(choix, getJoueur(j));

                while (retourJouer == -1) {
                    System.out.println("Vous ne pouvez pas jouer une case vide !");
                    choix = scanner.nextInt();
                    retourJouer = this.jouerUnCoup(choix, getJoueur(j));
                }
                if (retourJouer == 0) {
                    System.out.println("\n ***************************************");
                    for (int z = 0; z < getNbJoueur(); z++)
                        if (getJoueur(z).getScore() != temp[z])
                            System.out.println("Le joueur " + getJoueur(z).getNom() + " a collecté " + (getJoueur(z).getScore() - temp[z]) + " graine(s)");
                    System.out.println("***************************************");
                }

                getJoueur(j).setTurn(false);
                if (j == getNbJoueur() - 1)
                    j = 0;
                else
                    j += 1;
                getJoueur(j).setTurn(true);
            }
        }
        Joueur gagnant = getJoueur(0);
        for(int i=0;i<getNbJoueur();++i){
            System.out.println("Le joueur " +i + " termine le jeu avec " + getJoueur(i).getScore() + " graines !");
            if(getJoueur(i).getScore() > gagnant.getScore())
                gagnant = getJoueur(i);

        }
        System.out.println("\n" + gagnant.getNom() + " a gagné !! avec : " +gagnant.getScore() +" graines !");
        return 0;
    }

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
