import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Utilisateur {
    //Attribut de classe
    private static int seqId = 1;

    //Attributs d'instance 
    public int id;
    public String pseudo;
    public String motPasse;
    public String courriel;
    public ArrayList<Integer> evaluations;


    /**
     * Constructeur - Utilisateur
     * 
     * @param pseudo
     * @param motPasse
     * @param courriel
     */
    public Utilisateur(String pseudo, String motPasse, String courriel) {

        this.pseudo = pseudo;
        this.motPasse = motPasse;
        this.courriel = courriel;
        this.evaluations = new ArrayList<>(0);
        this.id = seqId;
        seqId++;
    }

    /**
     * 
     * @param utilisateur
     */
    public Utilisateur(Utilisateur utilisateur){
        this.pseudo = utilisateur.pseudo;
        this.motPasse = utilisateur.motPasse;
        this.courriel = utilisateur.courriel;
        this.evaluations = utilisateur.evaluations;
        
    }


    //Methodes d'instances publiques abstraites

    /**
     * 
     * @return
     */
    public abstract ArrayList compilerProfil();
    
    /**
     * 
     * @param utilisateur
     * @param eval
     */
    public abstract void evaluer(Utilisateur utilisateur, int eval);


    //Les getteurs
    /**
     * 
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * 
     * @return
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * 
     * @return
     */
    public String getMotPasse() {
        return this.motPasse;
    }

    /**
     * 
     * @return
     */
    public String getCourriel() {
        return this.courriel;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Integer> getEvaluations() {
        return this.evaluations;
    }
    


    //Les setters

    /**
     * 
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * 
     * @param motPasse
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    /**
     * 
     * @param courriel
     */
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    

}
