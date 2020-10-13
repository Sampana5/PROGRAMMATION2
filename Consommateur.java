import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Consommateur extends Utilisateur {
    public String description;
    public String categorie;
  Produit produit = new Produit(description, categorie);
  
   
     //Attribut de classe
    private static int seqId = 1;

     //Attributs d'instance 

     public ArrayList<Produit> achats;
    
    
    
    public Consommateur(String pseudo, String motPasse, String courriel) {
        super(pseudo, motPasse, courriel);
        super.evaluations = new ArrayList<>(0);
        this.achats = new ArrayList<>(0);
        
        seqId++;
        // TODO Auto-generated constructor stub
        }

    
    
     /**
     * 
     * @param Consommateur
     */
        public Consommateur(Consommateur consommateur){
       // super.pseudo = consommateur.pseudo;
        //super.motPasse = consommateur.motPasse;
        //super.courriel = consommateur.courriel;
        //super.evaluations = consommateur.evaluations;
        
       // this.achats = consommateur.achats;
        //this.id = consommateur.id;
        
    }
    
    
    
    
    
    
    
    
    
    @Override
    public ArrayList<String> compilerProfil() {
        // TODO Auto-generated method stub
     

        
         achats = 
        
       
       
       
        return ;
    }

    @Override
    public void evaluer(Utilisateur utilisateur, int eval) {
        // TODO Auto-generated method stub

    }
    
}
