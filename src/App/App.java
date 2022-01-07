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

            Jeu.jouerUnePartie();
        }
    }

}
