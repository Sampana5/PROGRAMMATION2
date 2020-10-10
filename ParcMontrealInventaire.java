

public class ParcMontrealInventaire {
    
    private String date;
    private String heure;
    private String parc;
    private String arrondissement;
    private String description;

    
	
    
    
    
    public ParcMontrealInventaire( String date , String heure , String parc , String arrondissement, String description){

        this.date = date ;
        this.heure = heure;
        this.parc = parc;
        this.arrondissement = arrondissement;
        this.description = description;
    }

    /**
     * @return String return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return String return the heure
     */
    public String getHeure() {
        return heure;
    }

    /**
     * @param heure the heure to set
     */
    public void setHeure(String heure) {
        this.heure = heure;
    }

    /**
     * @return String return the parc
     */
    public String getParc() {
        return parc;
    }

    /**
     * @param parc the parc to set
     */
    public void setParc(String parc) {
        this.parc = parc;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }




    /**
     * @return String return the arrondissement
     */
    public String getArrondissement() {
        return arrondissement;
    }

    /**
     * @param arrondissement the arrondissement to set
     */
    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }






   @Override
   public String toString() {
       // TODO Auto-generated method stub
       return getHeure() +  " ," + getDate() + " ," + getParc() +" ,"+ getArrondissement() + " ," + getDescription(); 
   }

/**
    * formate les attributs de la classe sous la forme d'une chaîne de caractères en 
    * mettant la virgule , comme séparateur entre ces attrbuts.
    * @Return les atributs sous la forme d'une chaîne de caractères
    */
    public String formaterAvant() {
      
        String inventaire = getHeure() +  " ," + getDate() + " ," + getParc() +" ,"+ getArrondissement() + " ," + getDescription();  
         
        return inventaire;
        
     }
  
  






}
