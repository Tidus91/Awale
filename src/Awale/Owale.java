package Awale;

import Joueur.Joueur;

public class Owale extends Awale {

    private int nbGraine;
    private int nbJoueurs;
    public Joueur [] joueurs;

    public Owale(int nb){
        super(12);
        nbGraine = 48;
        for(int i=0;i<grille.length;++i)
            grille[i] = nbGraine / grille.length;
        nbJoueurs = nb;
        joueurs = new Joueur [nb];
    }

    public int jouer(int cellule, Joueur joueur){
        assert (joueur.getTurn());
        assert (cellule > 0 && cellule < 13);
        //assert (joueur.isMyCamp(cellule));    desactivation a cause de la recusion dans cette variante


        if(!(grille[cellule -1] > 0))
            return -1;

        //je décrémente cellule pour correspondre à mon index du tableau
        cellule--;
        // temp est l'analogie de ma "main" avant de semer, je récupère les graines présente dans mon trou
        int temp = grille[cellule];

        grille[cellule] = 0;

        int i;
        int nb = 0; // pour savoir si gain de graine
        for (i = cellule + 1; temp > 0; ++i) {
            // ce if me permet de revenir à l'indice 0 de ma grille une fois arrivé à l'index 11 (bout de mon tableau)
            if (i % 12 == 0)
                i = 0;
            // ce if me permet de "sauter" ma case si j'avais plus de 12 graines et que je dois faire un tour complet
            if(i != cellule){
                grille[i] += 1;
                temp--;
                // Difference avec classique : si ce n'est pas la derniere case ET que y'a 4 graines alors le joueur du terrain doit cash out !
                if(temp > 0 && grille[i] == 4){
                    System.out.println("debuggage : je passe dans ma boucle des joueurs");
                    // je parcours mes joueurs pour tester lequel appartient le terrain
                    for(int j = 0; j<joueurs.length;++j){
                        if(joueurs[j].isMyCamp(i)){    // i+1 peut être ?
                            joueurs[j].setScore(grille[i]);
                            grille[i] = 0;
                            nb++;
                        }
                    }
                }
            }

        }
        // Semage terminé, je verifie donc ma case d'arrivé
        // i-- pour bien retomber sur mon index correspondant
        i--;

        // Difference avec Awale maintenant ! :
        if (grille[i] == 4) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            nb += grille[i];
            joueur.setScore(grille[i]);
            grille[i] = 0;
        }
        else if(grille[i] == 1){
            //return 2;
        }
        // Sinon je continue de semer
        else{
            System.out.println("Trace : recursion jouer ");
            jouer(i+1,joueur);
        }
        if(nb > 0)
            return 0;
        return 1;

    }

    public String toString(){
        String chaine = new String();
        if(nbJoueurs == 2){
            chaine += "                     A            \n";
            chaine += "        12   11   10    9    8    7";
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
            chaine += "         1    2    3    4    5    6";
            chaine += "\n                     B             ";
            return chaine;

        }
        else if(nbJoueurs == 3){
            chaine += "                     A            \n";
            chaine += "        12   11   10    9    8    7";
            chaine += "\n";
            chaine += "      ";
            for(int i=11;i>5;--i){
                chaine += " [ "+grille[i] +"]";
            }
            chaine += "    C \n";
            chaine += "      ";
            for(int i=0;i<6;++i){
                chaine += " [ "+grille[i] +"]";
            }
            chaine += "\n";
            chaine += "         1    2    3    4    5    6";
            chaine += "\n                     B             ";
            return chaine;

        }
        else if(nbJoueurs == 4){
            chaine += "              A               B \n";
            chaine += "        12   11   10    9    8    7";
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
            chaine += "         1    2    3    4    5    6";
            chaine += "\n               C             D    ";
            return chaine;

        }
        else if(nbJoueurs == 6){
            chaine += "           A          B         C \n";
            chaine += "        12   11   10    9    8    7";
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
            chaine += "         1    2    3    4    5    6";
            chaine += "\n          D         E         F  ";
            return chaine;

        }
        return "bug";
    }

    @Override
    public void jouerUnCoup(int cellule, Joueur j) {

    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }
}
