package riri.sg.com.bibliotheque;

/**
 * CLASS LIVRENOEUD ELLE REPRESENTE CHAQUE NOEUD DE L'ARBRE BINAIRE
 */
public class LivreNoeud {
    protected Livre livre;
    protected Integer niveau; //Noeud de gauche
    protected LivreNoeud gauche;
    protected LivreNoeud droite; //Noeud de droite

    /**
     * CONSTRUCTEUR DU NOEUD LIVRE
     * @param livre
     */
    public LivreNoeud(Livre livre)
    {
        this.livre=livre;
        gauche=null;
        droite=null;
    }

    /**
     * METHODES GETTER
     * @return
     */
    public Livre getLivre() {
        return livre;
    }

    public LivreNoeud getGauche() {
        return gauche;
    }

    public LivreNoeud getDroite() {
        return droite;
    }
}
