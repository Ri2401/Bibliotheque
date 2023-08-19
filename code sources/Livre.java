package riri.sg.com.bibliotheque;

/**
 * CLASS LIVRE POUR STOCKER L'INFORMATION PERSONNEL DE CHAQUE LIVRE
 */
public class Livre {
    protected String titre;
    protected String auteur;
    protected String genre;

    /**
     * CONSTRUCTEUR DE LA CLASS LIVRE
     * @param titre
     * @param auteur
     * @param genre
     */
    public Livre(String titre,String auteur,String genre)
    {
        this.titre=titre.trim();
        this.auteur=auteur.trim();
        this.genre=genre.trim();
    }

    /**
     * METHODE GETTER DE CHAQUE PARAMETTRE DU LIVRE
     * @return
     */
    public String getAuteur() {
        return auteur;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitre() {
        return titre;
    }
}
