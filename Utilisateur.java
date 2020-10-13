import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

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
        this.id = utilisateur.id;
        
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

    
     /**
     * 
     * @return 
     
        public double evaluationMoyenne(){
        double moyenne;
        
        for (int i = 0; i < evaluations.size() && evaluations.get(i) != null; i++) {
          
            
            
           
            moyenne = (double) Math.round(getEvaluations().get(i) * 100) / 100;

      
        
        
           
        }
         
        return moyenne;
       

        }

     */
     
    
     /**
     * 
     * @return 
     */public void ajouterEvaluation (int eval) {   
        boolean estValide = true;
        
       
            
            try{
                Scanner scan = new Scanner(System.in);
                eval = scan.nextInt();
            
                if(eval < 1 || eval > 5){
                System.out.println("Erreur, l'evaluation doit etre un nombre entier entre 1 et 5 inclusivement.");
                estValide = false;
                }
                
            }catch(NumberFormatException e){
                System.out.println("Erreur, l'evaluation doit etre un nombre entier entre 1 et 5 inclusivement.");
                estValide = false;
            } 
    
                if (estValide == true) {
            
                    evaluations.add(eval);
                }
        
            
    
        }   




    public boolean equals(Object utilisateur) {
            return utilisateur == this 
                    || ( utilisateur != null 
                    &&  utilisateur.getClass() == this.getClass()
                    && pseudo.equals(((Utilisateur) utilisateur).pseudo) 
                    && motPasse.equals(((Utilisateur) utilisateur).motPasse) 
                    && courriel.equals(((Utilisateur) utilisateur).courriel)
                    && evaluations.equals(((Utilisateur) utilisateur).evaluations));
                    
            
    }


    /**
* Retourne une representation de cet utilisateur sous forme d'une chaine de
* caracteres (son id, son pseudonyme, son mot de passe, son courriel et le
* nombre de ses evaluations reçues).
*
* @return une representation de cet utilisateur sous forme d'une chaine de
* caracteres.
*/
public String toString() {
    return id + " : " + pseudo + " - " + motPasse + " - " + courriel
    + " - " + evaluations.size();
    }















}
