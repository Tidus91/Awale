package Variante;
import Awale.Awale;
import Joueur.Joueur;

import java.util.Scanner;

public class Classique extends Awale {


    // Ceci est la variante 1 : je ne récupère que les graines des trous précédents
    public Classique(){
        super(12,48,2);
        for(int i=0;i<getLongueurGrille();++i)
            setCaseGrille(i,getNbGraine() / getLongueurGrille());
    }

    public int jouerUnCoup(int cellule, Joueur joueur) {
        assert (joueur.getTurn());
        assert (cellule > 0 && cellule < 13);
        assert(joueur.isMyCamp(cellule));

        if(!(getCaseGrille(cellule-1) > 0))
            return -1;
        //je décrémente cellule pour correspondre à mon index du tableau
        cellule--;
        // temp est l'analogie de ma "main" avant de semer, je récupère les graines présente dans mon trou
        int temp = getCaseGrille(cellule);
        // mon trou est donc vide
        setCaseGrille(cellule,0);
        // Semage de mes graines
        int i;
        for (i = cellule + 1; temp > 0; ++i) {
            // ce if me permet de revenir à l'indice 0 de ma grille une fois arrivé à l'index 11 (bout de mon tableau)
            if (i % 12 == 0)
                i = 0;
            // ce if me permet de "sauter" ma case si j'avais plus de 12 graines et que je dois faire un tour complet
            if(i != cellule){
                setCaseGrilleAjout(i);
                temp--;
            }

        }
        // Semage terminé, je verifie donc ma case d'arrivé
        // i-- pour bien retomber sur mon index correspondant
        i--;
        int nb = 0;
        if (joueur.isMyCamp(i+1)) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            return 2;
        }
        else{
            while(getCaseGrille(i) < 4 && getCaseGrille(i) > 1){
                // protection
                if(joueur.isMyCamp(i+1)){
                    break;
                }
                nb += getCaseGrille(i);
                joueur.setScore(getCaseGrille(i));
                setCaseGrille(i,0);
                i--;
            }
        }
        if(nb > 0)
            return 0;
        return 1;
    }

    @Override
    public int jouerUnePartie(){

        Scanner scanner = new Scanner(System.in);

        initJoueur(getNbJoueur());
        int j=0;
        while(!super.getIsOver()) {
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
                        continue;
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
}