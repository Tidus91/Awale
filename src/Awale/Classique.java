package Awale;
import Joueur.Joueur;

public class Classique extends Awale {

    private int nbGraine;
    private int nbJoueurs;

    // Ceci est la variante 1 : je ne récupère que les graines des trous précédents
    public Classique(){
        super(12);
        nbGraine = 48;
        for(int i=0;i<grille.length;++i)
            grille[i] = nbGraine / grille.length;
        nbJoueurs = 2;
    }

    public int jouer(int cellule, Joueur joueur) {
        assert (joueur.getTurn());
        assert (cellule > 0 && cellule < 13);
        assert(joueur.isMyCamp(cellule));
        /*
        if (cellule > 6) {
            assert (joueur.getCamp().);
        } else {
            assert (joueur.getCamp().equals("16"));
        }*/

        if(!(grille[cellule -1] > 0))
            return -1;
        //je décrémente cellule pour correspondre à mon index du tableau
        cellule--;
        // temp est l'analogie de ma "main" avant de semer, je récupère les graines présente dans mon trou
        int temp = grille[cellule];
        // mon trou est donc vide
        grille[cellule] = 0;
        // Semage de mes graines
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
        if (joueur.isMyCamp(i+1)) {
            //System.out.println("i : et grille[i] debuggage : "+ i +"   :n  " + grille[i]);
            return 2;
        }
        else{
            while(grille[i] < 4 && grille[i] > 1){
                // protection
                if(joueur.isMyCamp(i+1)){
                    break;
                }
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
        return chaine;
    }

    @Override
    public void jouerUnePartie(){
        System.out.println("coucou");
    }



    @Override
    public void jouerUnCoup(int cellule,Joueur j) {

    }
}
