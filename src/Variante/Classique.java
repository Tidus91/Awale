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
    public void jouerUnePartie(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quelle est le nom du Joueur 1 ? (qui commencera en premier)");
        String choixNom = new String();
        choixNom = scanner.next();
        int[] tab = new int[]{12, 11, 10, 9, 8, 7};
        Joueur joueur1 = new Joueur(choixNom,tab);
        joueur1.setTurn(true);
        System.out.println("Quelle est le nom du Joueur 2 ?");
        choixNom = scanner.next();
        int[] tab2 = new int[]{1, 2, 3, 4, 5, 6};
        Joueur joueur2 = new Joueur(choixNom,tab2);

        System.out.println("prenom 1 : "+joueur1.getNom());
        System.out.println("prenom 2 : "+joueur2.getNom());

        while(!super.getIsOver()){
            int choix;
            if(joueur1.getTurn()){
                String plateau = this.toString();//Jeu.toString();
                System.out.println(plateau);
                System.out.println("\n");
                System.out.println("NORD : "+joueur1.getScore() + " Bille(s)");
                System.out.println("SUD : "+joueur2.getScore() + " Bille(s)");

                System.out.println("Ou voulez vous jouer ? " +joueur1.getNom());
                choix = scanner.nextInt();
                while((choix < 7 || choix > 12) && choix != 0){
                    System.out.println("Erreur lors de la selection du coup, veuillez taper un nombre entre 7 et 12 // ou 0 pour abandonner");
                    choix = scanner.nextInt();
                }
                if(choix == 0){
                    if(joueur1.propositionAbandon(joueur2)){
                        int grainesRestante = 0;
                        for(int i=0;i<getLongueurGrille();++i){
                            grainesRestante += getCaseGrille(i);
                        }
                        joueur1.setScore(grainesRestante/2);
                        joueur2.setScore(grainesRestante/2);
                        break;
                    }

                }

                // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                int temp = joueur1.getScore();

                int retourJouer = this.jouerUnCoup(choix,joueur1);

                while(retourJouer == -1){
                    System.out.println("Vous ne pouvez pas jouer une case vide !");
                    choix = scanner.nextInt();
                    retourJouer = this.jouerUnCoup(choix,joueur1);
                }
                if(retourJouer == 0){
                    System.out.println("\n ***************************************");
                    System.out.println("Le joueur " + joueur1.getNom() + " a collecté " + (joueur1.getScore()-temp) + " graine(s)");
                    System.out.println("***************************************");
                }

                joueur1.setTurn(false);
                joueur2.setTurn(true);
            }
            if(joueur2.getTurn()){
                String test = this.toString();
                System.out.println(test);
                System.out.println("\n");
                System.out.println("NORD : "+joueur1.getScore() + " Bille(s)");
                System.out.println("SUD : "+joueur2.getScore() + " Bille(s)");

                System.out.println("Ou voulez vous jouer ? " +joueur2.getNom());
                choix = scanner.nextInt();
                while(choix < 0 || choix > 6  ){
                    System.out.println("Erreur lors de la selection du coup, veuillez taper un nombre entre 1 et 6 // ou 0 pour abandonner");
                    choix = scanner.nextInt();
                }
                if(choix == 0){
                    if(joueur2.propositionAbandon(joueur1)){
                        int grainesRestante = 0;
                        for(int i=0;i<getLongueurGrille();++i){
                            grainesRestante += getCaseGrille(i);
                        }
                        joueur1.setScore(grainesRestante/2);
                        joueur2.setScore(grainesRestante/2);
                        break;
                    }

                }

                // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                int temp = joueur2.getScore();
                int retourJouer = this.jouerUnCoup(choix,joueur2);

                while(retourJouer == -1){
                    System.out.println("Vous ne pouvez pas jouer une case vide !");
                    choix = scanner.nextInt();
                    retourJouer = this.jouerUnCoup(choix,joueur2);
                }

                if(retourJouer == 0){
                    System.out.println("\n ***************************************");
                    System.out.println("Le joueur " + joueur2.getNom() + " a collecté " + (joueur2.getScore()-temp) + " graine(s)");
                    System.out.println("***************************************");
                }

                joueur1.setTurn(true);
                joueur2.setTurn(false);
            }
        }
        System.out.println("Le joueur " +joueur1.getNom() + " termine le jeu avec " + joueur1.getScore() + " graines !");
        System.out.println("Le joueur " +joueur2.getNom() + " termine le jeu avec " + joueur2.getScore() + " graines !");
        if(joueur1.getScore() > joueur2.getScore()){
            System.out.println("\n" + joueur1.getNom() + " a gagné !!");
        }
        else if(joueur1.getScore() < joueur2.getScore())
            System.out.println("\n" + joueur2.getNom() + " a gagné !!");
        else
            System.out.println("\n égalité entre les joueurs !");
    }
}
