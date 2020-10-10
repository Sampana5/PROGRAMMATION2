

public class ParcMontrealFichierSorti {
    private String Arrondissement;
    private int nbIntervention;

 public ParcMontrealFichierSorti(String Arrondissement , int nbIntervention) {

    this.Arrondissement = Arrondissement;
    this.nbIntervention = nbIntervention;

}


    /**
     * @return String return the Arrondissement
     */
    public String getArrondissement() {
        return Arrondissement;
    }

    /**
     * @param Arrondissement the Arrondissement to set
     */
    public void setArrondissement(String Arrondissement) {
        this.Arrondissement = Arrondissement;
    }

    /**
     * @return int return the nbIntervention
     */
    public int getNbIntervention() {
        return nbIntervention;
    }

    /**
     * @param nbIntervention the nbIntervention to set
     */
    public void setNbIntervention(int nbIntervention) {
        this.nbIntervention = nbIntervention;
    }


    
   @Override
   public String toString() {
       // TODO Auto-generated method stub
       return getArrondissement() +  " ," + getNbIntervention(); 
   }


/**
    * formate les attributs de la classe sous la forme d'une chaîne de caractères en 
    * mettant la virgule , comme séparateur entre ces attrbuts.
    * @Return les atributs sous la forme d'une chaîne de caractères
    */
    public String formaterApres() {
      
        String inventaire = getArrondissement() +  " ," + getNbIntervention(); 
         
        return inventaire;
        
     }



   













}
