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
        assert (joueur.isMyCamp(cellule));


        if(!(grille[cellule -1] > 0))
            return -1;

        cellule--;
        int temp = grille[cellule];

        grille[cellule] = 0;

        int i;
        for (i = cellule + 1; temp > 0; ++i) {
            // ce if me permet de revenir à l'indice 0 de ma grille une fois arrivé à l'index 11 (bout de mon tableau)
            if (i % 12 == 0)
                i = 0;
            // ce if me permet de "sauter" ma case si j'avais plus de 12 graines et que je dois faire un tour complet
            if(i != cellule){
                grille[i] += 1;
                temp--;
            }

        }
        // Semage terminé, je verifie donc ma case d'arrivé
        // i-- pour bien retomber sur mon index correspondant
        i--;
        int nb = 0;
        if (joueur.getCamp().equals("NORD")) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            if (i > 5)
                return 2;
            while(i > -1 && grille[i] < 4 && grille[i] > 1){
                nb += grille[i];
                joueur.setScore(grille[i]);
                grille[i] = 0;
                i--;
            }
        }
        // Si le joueur est SUD
        else {
            //System.out.println("i : et grille[i] debuggage : "+ i +"    :s "  + grille[i]);
            if (i < 6)
                return 2;
            while(i > 5 && grille[i] < 4 && grille[i] > 1){
                nb += grille[i];
                joueur.setScore(grille[i]);
                grille[i] = 0;
                i--;
            }
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
