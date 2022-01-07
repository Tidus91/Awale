package Variante;
import Awale.Awale;
import Joueur.Joueur;

import java.util.Scanner;

public class Classique extends Awale {


    // Ceci est la variante 1 : je ne récupère que les graines des trous précédents
    public Classique() {
        super(12, 48, 2);
        for (int i = 0; i < getLongueurGrille(); ++i)
            setCaseGrille(i, getNbGraine() / getLongueurGrille());
    }

    public int jouerUnCoup(int cellule, Joueur joueur) {
        assert (joueur.getTurn());
        assert (cellule > 0 && cellule < 13);
        assert (joueur.isMyCamp(cellule));

        if (!(getCaseGrille(cellule - 1) > 0))
            return -1;
        //je décrémente cellule pour correspondre à mon index du tableau
        cellule--;
        // temp est l'analogie de ma "main" avant de semer, je récupère les graines présente dans mon trou
        int temp = getCaseGrille(cellule);
        // mon trou est donc vide
        setCaseGrille(cellule, 0);
        // Semage de mes graines
        int i;
        for (i = cellule + 1; temp > 0; ++i) {
            // ce if me permet de revenir à l'indice 0 de ma grille une fois arrivé à l'index 11 (bout de mon tableau)
            if (i % 12 == 0)
                i = 0;
            // ce if me permet de "sauter" ma case si j'avais plus de 12 graines et que je dois faire un tour complet
            if (i != cellule) {
                setCaseGrilleAjout(i);
                temp--;
            }

        }
        // Semage terminé, je verifie donc ma case d'arrivé
        // i-- pour bien retomber sur mon index correspondant
        i--;
        int nb = 0;
        if (joueur.isMyCamp(i + 1)) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            return 2;
        } else {
            while (getCaseGrille(i) < 4 && getCaseGrille(i) > 1) {
                // protection
                if (joueur.isMyCamp(i + 1)) {
                    break;
                }
                nb += getCaseGrille(i);
                joueur.setScore(getCaseGrille(i));
                setCaseGrille(i, 0);
                i--;
            }
        }
        if (nb > 0)
            return 0;
        return 1;
    }
}