package Variante;

import Awale.Awale;
import Joueur.Joueur;

public class Owale extends Awale {

    public Owale(int nb){
        super(12,48,nb);
        for(int i=0;i<getLongueurGrille();++i)
            setCaseGrille(i,getNbGraine() / getLongueurGrille());
    }

    @Override
    public int jouerUnCoup(int cellule, Joueur joueur){
        assert (joueur.getTurn());
        assert (cellule > 0 && cellule < 13);
        //assert (joueur.isMyCamp(cellule));    desactivation a cause de la recusion dans cette variante


        if(!(getCaseGrille(cellule-1) > 0))
            return -1;

        //je décrémente cellule pour correspondre à mon index du tableau
        cellule--;
        // temp est l'analogie de ma "main" avant de semer, je récupère les graines présente dans mon trou
        int temp = getCaseGrille(cellule);

        setCaseGrille(cellule,0);

        int i;
        int nb = 0; // pour savoir si gain de graine
        for (i = cellule + 1; temp > 0; ++i) {
            // ce if me permet de revenir à l'indice 0 de ma grille une fois arrivé à l'index 11 (bout de mon tableau)
            if (i % 12 == 0)
                i = 0;
            // ce if me permet de "sauter" ma case si j'avais plus de 12 graines et que je dois faire un tour complet
            if(i != cellule){
                setCaseGrilleAjout(i);
                temp--;
                // Difference avec classique : si ce n'est pas la derniere case ET que y'a 4 graines alors le joueur du terrain doit cash out !
                if(temp > 0 && getCaseGrille(i) == 4){
                    System.out.println("Trace : je passe dans ma boucle des joueurs");
                    // je parcours mes joueurs pour tester lequel appartient le terrain
                    for(int j = 0; j<getNbJoueur();++j){
                        if(getJoueur(j).isMyCamp(i)){    // i+1 peut être ?
                            getJoueur(j).setScore(getCaseGrille(i));
                            setCaseGrille(i,0);
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
        if (getCaseGrille(i) == 4) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            nb += getCaseGrille(i);
            joueur.setScore(getCaseGrille(i));
            setCaseGrille(i,0);
        }
        else if(getCaseGrille(i) == 1){
            //return 2;
        }
        // Sinon je continue de semer
        else{
            System.out.println("Trace : recursion jouer ");
            jouerUnCoup(i+1,joueur);
        }
        if(nb > 0)
            return 0;
        return 1;

    }

    @Override
    public String toString(){
        String chaine = new String();
        if(getNbJoueur() == 2){
            chaine += "                     J2            \n";
            chaine += "        12   11   10    9    8    7";
            chaine += "\n";
            chaine += "      ";
            for(int i=11;i>5;--i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "      ";
            for(int i=0;i<6;++i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "         1    2    3    4    5    6";
            chaine += "\n                     J1             ";
            return chaine;

        }
        else if(getNbJoueur() == 3){
            chaine += "                     J3            \n";
            chaine += "        12   11   10    9    8    7";
            chaine += "\n";
            chaine += "      ";
            for(int i=11;i>5;--i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "    J2 \n";
            chaine += "      ";
            for(int i=0;i<6;++i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "         1    2    3    4    5    6";
            chaine += "\n                     J1             ";
            return chaine;

        }
        else if(getNbJoueur() == 4){
            chaine += "              J4               J3 \n";
            chaine += "        12   11   10    9    8    7";
            chaine += "\n";
            chaine += "      ";
            for(int i=11;i>5;--i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "      ";
            for(int i=0;i<6;++i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "         1    2    3    4    5    6";
            chaine += "\n               J1             J2    ";
            return chaine;

        }
        else if(getNbJoueur() == 6){
            chaine += "           J6          J5         J4 \n";
            chaine += "        12   11   10    9    8    7";
            chaine += "\n";
            chaine += "      ";
            for(int i=11;i>5;--i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "      ";
            for(int i=0;i<6;++i){
                chaine += " [ "+getCaseGrille(i) +"]";
            }
            chaine += "\n";
            chaine += "         1    2    3    4    5    6";
            chaine += "\n          J1         J2         J3  ";
            return chaine;

        }
        return "bug";
    }

}
