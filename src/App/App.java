package App;

import java.util.Scanner;
import Awale.Awale;
import Awale.Classique;
import Joueur.Joueur;
import Awale.Owale;
import org.junit.runner.OrderWith;

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

        if(choix == 1){

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

            Classique Jeu = new Classique();

            while(!Jeu.isOver){
                if(joueur1.getTurn()){
                    String plateau = Jeu.toString();
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
                            for(int i=0;i<Jeu.grille.length;++i){
                                grainesRestante += Jeu.grille[i];
                            }
                            joueur1.setScore(grainesRestante/2);
                            joueur2.setScore(grainesRestante/2);
                            break;
                        }

                    }


                    // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                    int temp = joueur1.getScore();

                    int retourJouer = Jeu.jouer(choix,joueur1);

                    while(retourJouer == -1){
                        System.out.println("Vous ne pouvez pas jouer une case vide !");
                        choix = scanner.nextInt();
                        retourJouer = Jeu.jouer(choix,joueur1);
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
                    String test = Jeu.toString();
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
                            for(int i=0;i<Jeu.grille.length;++i){
                                grainesRestante += Jeu.grille[i];
                            }
                            joueur1.setScore(grainesRestante/2);
                            joueur2.setScore(grainesRestante/2);
                            break;
                        }

                    }

                    // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                    int temp = joueur2.getScore();
                    int retourJouer = Jeu.jouer(choix,joueur2);

                    while(retourJouer == -1){
                        System.out.println("Vous ne pouvez pas jouer une case vide !");
                        choix = scanner.nextInt();
                        retourJouer = Jeu.jouer(choix,joueur2);
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

            if(choix == 2){
                System.out.println("Quel est le nom du joueur 1 ?");
                choixNom = scanner.next();
                int[] tab = new int[]{12, 11, 10, 9, 8, 7};
                Joueur joueur1 = new Joueur(choixNom,tab);
                Jeu.joueurs[0] = joueur1;
                joueur1.setTurn(true);
                System.out.println("Quel est le nom du joueur 2 ?");
                choixNom = scanner.next();
                int[] tab2 = new int[]{1, 2, 3, 4, 5, 6};
                Joueur joueur2 = new Joueur(choixNom,tab2);
                Jeu.joueurs[1] = joueur2;

                System.out.println("prenom 1 : "+joueur1.getNom());
                System.out.println("prenom 2 : "+joueur2.getNom());

            }
            else if(choix == 3){
                System.out.println("Quel est le nom du joueur 1 ?");
                choixNom = scanner.next();
                int[] tab = new int[]{12, 11, 10, 9};
                Joueur joueur1 = new Joueur(choixNom,tab);
                Jeu.joueurs[0] = joueur1;
                joueur1.setTurn(true);
                System.out.println("Quel est le nom du joueur 2 ?");
                choixNom = scanner.next();
                int[] tab2 = new int[]{1, 2, 3, 4};
                Joueur joueur2 = new Joueur(choixNom,tab2);
                Jeu.joueurs[2] = joueur2;
                System.out.println("Quel est le nom du joueur 3 ?");
                choixNom = scanner.next();
                int[] tab3 = new int[]{5, 6, 7, 8};
                Joueur joueur3 = new Joueur(choixNom,tab3);
                Jeu.joueurs[2] = joueur3;

            }
            else if(choix == 4){
                System.out.println("Quel est le nom du joueur 1 ?");
                choixNom = scanner.next();
                int[] tab = new int[]{12, 11, 10};
                Joueur joueur1 = new Joueur(choixNom,tab);
                Jeu.joueurs[0] = joueur1;
                joueur1.setTurn(true);
                System.out.println("Quel est le nom du joueur 2 ?");
                choixNom = scanner.next();
                int[] tab2 = new int[]{1, 2, 3};
                Joueur joueur2 = new Joueur(choixNom,tab2);
                Jeu.joueurs[1] = joueur2;
                System.out.println("Quel est le nom du joueur 3 ?");
                choixNom = scanner.next();
                int[] tab3 = new int[]{4, 5, 6};
                Joueur joueur3 = new Joueur(choixNom,tab3);
                Jeu.joueurs[2] = joueur3;
                System.out.println("Quel est le nom du joueur 4 ?");
                choixNom = scanner.next();
                int[] tab4 = new int[]{7, 8, 9};
                Joueur joueur4 = new Joueur(choixNom,tab4);
                Jeu.joueurs[3] = joueur4;

            }
            else if(choix == 6){
                System.out.println("Quel est le nom du joueur 1 ?");
                choixNom = scanner.next();
                int[] tab = new int[]{12, 11};
                Joueur joueur1 = new Joueur(choixNom,tab);
                Jeu.joueurs[0] = joueur1;
                joueur1.setTurn(true);
                System.out.println("Quel est le nom du joueur 2 ?");
                choixNom = scanner.next();
                int[] tab2 = new int[]{1, 2,};
                Joueur joueur2 = new Joueur(choixNom,tab2);
                Jeu.joueurs[1] = joueur2;
                System.out.println("Quel est le nom du joueur 3 ?");
                choixNom = scanner.next();
                int[] tab3 = new int[]{3, 4};
                Joueur joueur3 = new Joueur(choixNom,tab3);
                Jeu.joueurs[2] = joueur3;
                System.out.println("Quel est le nom du joueur 4 ?");
                choixNom = scanner.next();
                int[] tab4 = new int[]{5, 6};
                Joueur joueur4 = new Joueur(choixNom,tab4);
                Jeu.joueurs[3] = joueur4;
                System.out.println("Quel est le nom du joueur 5 ?");
                choixNom = scanner.next();
                int[] tab5 = new int[]{7,8};
                Joueur joueur5 = new Joueur(choixNom,tab5);
                Jeu.joueurs[4] = joueur5;
                System.out.println("Quel est le nom du joueur 6 ?");
                choixNom = scanner.next();
                int[] tab6 = new int[]{9, 10};
                Joueur joueur6 = new Joueur(choixNom,tab6);
                Jeu.joueurs[5] = joueur6;

            }

            int j =0; // indice de mes joueurs
            while(!Jeu.isOver){
                if(Jeu.joueurs[j].getTurn()){
                    String plateau = Jeu.toString();
                    System.out.println(plateau);
                    System.out.println("\n");

                    for(int i = 0;i<Jeu.joueurs.length;++i)
                        System.out.println(Jeu.joueurs[i].toString());

                    System.out.println("Ou voulez vous jouer ? " +Jeu.joueurs[j].getNom());
                    choix = scanner.nextInt();

                    while(!(Jeu.joueurs[j].isMyCamp(choix)) && choix != 0){
                        System.out.println("Erreur lors de la selection du coup, veuillez taper une cellule vous appartenant // ou 0 pour abandonner");
                        choix = scanner.nextInt();
                    }
                    if(choix == 0){
                        if(Jeu.joueurs[0].propositionAbandon(Jeu.joueurs[1])){
                            int grainesRestante = 0;
                            for(int i=0;i<Jeu.grille.length;++i){
                                grainesRestante += Jeu.grille[i];
                            }
                            Jeu.joueurs[0].setScore(grainesRestante/2);
                            Jeu.joueurs[0].setScore(grainesRestante/2);
                            break;
                        }

                    }

                    // "temp" pas vraiment utile mais je l'utilise pour calculer le nombre de graines gagner lors du coup
                    int [] temp = new int[Jeu.joueurs.length];
                    for(int z=0;z<Jeu.joueurs.length;++z)
                        temp[z] = Jeu.joueurs[j].getScore();

                    int retourJouer = Jeu.jouer(choix,Jeu.joueurs[j]);

                    while(retourJouer == -1){
                        System.out.println("Vous ne pouvez pas jouer une case vide !");
                        choix = scanner.nextInt();
                        retourJouer = Jeu.jouer(choix,Jeu.joueurs[j]);
                    }
                    if(retourJouer == 0){
                        System.out.println("\n ***************************************");
                        for(int z =0;z<Jeu.joueurs.length;z++)
                            System.out.println("Le joueur " + Jeu.joueurs[z].getNom() + " a collecté " + (Jeu.joueurs[z].getScore()-temp[z]) + " graine(s)");
                        System.out.println("***************************************");
                    }

                    Jeu.joueurs[j].setTurn(false);
                    if(j == Jeu.joueurs.length-1)
                        j = 0;
                    else
                        j += 1;
                    Jeu.joueurs[j].setTurn(true);
                }
            }

        }

    }
}
