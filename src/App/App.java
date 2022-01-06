package App;

import java.util.Scanner;

import Variante.Classique;
import Joueur.Joueur;
import Variante.Owale;

public class App {

    public static void main(String[] args) {

        System.out.println("Bonjour, Quelle variante de l'AWALE voulez vous jouer ?");
        System.out.println("1. Classique");
        System.out.println("2. OWARE");
        System.out.println("3. v3");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        while(choix < 1 || choix > 2){
            System.out.println("Erreur lors de la selection du jeu, veuillez taper un nombre entre 1 et 3");
            choix = scanner.nextInt();
        }
        System.out.println("Vous avez donc choisi : " + choix);

        /********************************** AWALE ****************************************/

        if(choix == 1){

            Classique Jeu = new Classique();

            Jeu.jouerUnePartie();
        }

        /*********************************************  OWALE     ***************************/


        if(choix == 2){
            System.out.println("combien de joueurs êtes vous ? (2 à 4 ou 6)");
            choix = scanner.nextInt();
            while((choix < 2 || choix > 4) && choix != 6){
                System.out.println("Il ne peut y avoir que de 2 à 4 ou 6 joueurs !");
                choix = scanner.nextInt();
            }
            System.out.println("Il y aura donc " + choix + " Joueurs");

            Owale Jeu = new Owale(choix);

            String choixNom = new String();

            Jeu.initJoueur(choix);

            int j =0; // indice de mes joueurs
            while(!Jeu.getIsOver()){
                if(Jeu.getJoueur(j).getTurn()){
                    String plateau = Jeu.toString();
                    System.out.println(plateau);
                    System.out.println("\n");

                    for(int i = 0;i<Jeu.getNbJoueur();++i)
                        System.out.println(Jeu.getJoueur(i).toString());

                    System.out.println("Ou voulez vous jouer ? " +Jeu.getJoueur(j).getNom());
                    choix = scanner.nextInt();

                    while(!(Jeu.getJoueur(j).isMyCamp(choix))){
                        System.out.println("Erreur lors de la selection du coup, veuillez taper une cellule vous appartenant");
                        choix = scanner.nextInt();
                    }

                    // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                    int [] temp = new int[Jeu.getNbJoueur()];
                    for(int z=0;z<Jeu.getNbJoueur();++z)
                        temp[z] = Jeu.getJoueur(j).getScore();

                    int retourJouer = Jeu.jouerUnCoup(choix,Jeu.getJoueur(j));

                    while(retourJouer == -1){
                        System.out.println("Vous ne pouvez pas jouer une case vide !");
                        choix = scanner.nextInt();
                        retourJouer = Jeu.jouerUnCoup(choix,Jeu.getJoueur(j));
                    }
                    if(retourJouer == 0){
                        System.out.println("\n ***************************************");
                        for(int z =0;z<Jeu.getNbJoueur();z++)
                            if(Jeu.getJoueur(z).getScore() != temp[z])
                                System.out.println("Le joueur " + Jeu.getJoueur(z).getNom() + " a collecté " + (Jeu.getJoueur(z).getScore()-temp[z]) + " graine(s)");
                        System.out.println("***************************************");
                    }


                    Jeu.getJoueur(j).setTurn(false);
                    if(j == Jeu.getNbJoueur()-1)
                        j = 0;
                    else
                        j += 1;
                    Jeu.getJoueur(j).setTurn(true);
                }
            }

        }

    }
}
