package riri.sg.com.bibliotheque;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * CLASS ARBRE ELLE REPRESENTE L'ARBRE BINAIRE
 */
public class Arbre {
    private LivreNoeud racine;

    /**
     * CONSTRUCTEUR ELLE INITIALISE LE NOEUD RACINE A NULL
     */
    public Arbre()
    {
        racine=null;
    }

    /**
     * METHODE RECURSIVE D'AJOUT AFIN D'AJOUTER LE LIVRE AU BON ENDROIT
     * @param noeud
     * @param livre
     * @return
     */
    private LivreNoeud ajouterLivreRecursivement(LivreNoeud noeud,Livre livre)
    {
        if(noeud==null)
        {
            return new LivreNoeud(livre);
        }
        //Comparaison des titres de chaque noeud afin de connaitre si la livre a ajouter est de poids faible(gauche) ou fort(droite)
        if(livre.titre.compareTo(noeud.livre.titre)<0)
        {
            noeud.gauche=this.ajouterLivreRecursivement(noeud.gauche, livre);
        }
        else
        {
            noeud.droite=this.ajouterLivreRecursivement(noeud.droite, livre);
        }
        return noeud;
    }

    /**
     * METHODE RECURSIVE DE RECHERCHE
     * @param noeud
     * @param titre
     * @return
     */
    private LivreNoeud rechercheLivreRecursivement(LivreNoeud noeud,String titre)
    {
        titre=titre.trim();
        if(noeud==null || noeud.livre.titre.equals(titre))
        {
            return noeud;
        }
        if(titre.compareTo(noeud.livre.titre)<0)
        {
            return this.rechercheLivreRecursivement(noeud.gauche, titre);
        }
        else
        {
            return this.rechercheLivreRecursivement(noeud.droite, titre);
        }
    }

    /**
     * METHODE RECURSIVE POUR CONNAIRE LE NOEUD PREDECESSEUR EN CAS DE SUPPRESSION
     * @param noeud
     * @return
     */
    private LivreNoeud rechercheLivreNoeudPredecesseur(LivreNoeud noeud)
    {
        LivreNoeud courant=noeud;
        while (courant!=null && courant.gauche!=null)
        {
            courant=courant.gauche;
        }
        return courant;
    }

    /**
     * METHODE RECURSIVE DE SUPPRESSION POUR TROUVER LE LIVRE A SUPPRIMER
     * @param noeud
     * @param titre
     * @return
     */
    private LivreNoeud supprimerLivreRecursivement(LivreNoeud noeud,String titre)
    {
        //Suppression des espaces pourque le resultat de la recherche soit exacte
        titre=titre.trim();
        if(noeud==null)
        {
            return null;
        }
        //comparaison de chaque titre de chaque noeud au valeur de la titre rechercher
        //si le poids est faible on continu la recherche a gauche et a droite dans le cas contraire
        if(titre.compareTo(noeud.livre.titre)<0)
        {
            noeud.gauche=this.supprimerLivreRecursivement(noeud.gauche, titre);
        }
        else
        {
            if(titre.compareTo(noeud.livre.titre)>0)
            {
                noeud.droite=this.supprimerLivreRecursivement(noeud.droite, titre);
            }
            else
            {
                //Cas 1 le noeud a supprimer est une feuille ou a un seul enfant
                if(noeud.gauche==null)
                {
                    LivreNoeud temp=noeud.droite;
                    noeud=null;
                    return temp;
                }
                else if(noeud.droite==null){
                    LivreNoeud temp=noeud.gauche;
                    noeud=null;
                    return temp;
                }

                //Cas 2 le noeud a supprimer a 2 enfant
                LivreNoeud successeur=rechercheLivreNoeudPredecesseur(noeud.droite);
                noeud.livre=successeur.livre;
                noeud.droite=supprimerLivreRecursivement(noeud.droite,successeur.livre.titre);
            }
        }
        return noeud;
    }

    /**
     * METHODE RECURSIVE POUR AFFICHER L'ARBRE
     * ET LES DONNES STOCKES DANS CHAQUE NOEUD ET LIVRE
     * @param noeud
     * @param niveau
     * @param tableView
     * @param textArea
     */
    private void afficherRecursivementArbre(LivreNoeud noeud, int niveau, TableView tableView, TextArea textArea)
    {
        if(noeud==null)
        {
            return;
        }
        this.afficherRecursivementArbre(noeud.droite, niveau+1,tableView,textArea);
        noeud.niveau=niveau;
        String tab="";
        for(int i=0; i<niveau; ++i)
        {
            tab+="\t";
        }
        textArea.appendText("\n"+tab+"|-- Titre: "+noeud.livre.titre +" Niveau: "+noeud.niveau);
        tableView.getItems().add(noeud.livre);
        this.afficherRecursivementArbre(noeud.gauche,niveau+1,tableView,textArea);
    }
    //public

    /**
     * METHODE PERMET D'AJOUTER UN LIVRE
     * ET APPEL LA METHODE RECURSIVE AJOUTER VU PLUS HAUT
     * @param livre
     */
    public void ajouterLivre(Livre livre)
    {
        racine=this.ajouterLivreRecursivement(racine, livre);
    }

    /**
     * METHODE PERMET DE RECHERCHER UN LIVRE
     * ET APPEL LA METHODE RECURSIVE RECHERCHE VU PLUS HAUT
     * @param titre
     * @return
     */
    public Livre rechercherLivre(String titre) {
        LivreNoeud resultat=this.rechercheLivreRecursivement(racine, titre.trim());
        return (resultat!=null)?resultat.livre:null;
    }

    /**
     * METHODE PERMET DE SUPPRIMER UN LIVRE
     * ET APPEL LA METHODE RECURSIVE SUPPRIMER VU PLUS HAUT
     * @param titre
     * @return
     */
    public boolean supprimerLivre(String titre)
    {
        if(rechercherLivre(titre)==null)
        {
            return false;
        }
        this.supprimerLivreRecursivement(racine, titre);
        return true;
    }

    /**
     * METHODE QUI PERMET DE METTRE A JOUR LES DETAILS DE CHAQUE LIVRE
     * @param titre
     * @param auteur
     * @param genre
     */
    public void modifierLivre(String titre,String auteur,String genre)
    {
        LivreNoeud livreAModifier=this.rechercheLivreRecursivement(racine, titre);
        if(livreAModifier!=null)
        {
            livreAModifier.livre.auteur=auteur;
            livreAModifier.livre.genre=genre;
        }
    }

    /**
     * METHODE QUI PERMET D'AFFICHER L'ABRE ET MET
     * A JOUR LES VALEUR DU TABLEAU DE VISUALISATION
     * ET DU TEXT AREA
     * @param tableView
     * @param textArea
     */
    public void afficherArbre(TableView tableView,TextArea textArea)
    {
        this.afficherRecursivementArbre(racine, 0,tableView,textArea);
    }
}
