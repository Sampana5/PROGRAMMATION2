public class Item{


    //Declaration des variables
    private static int  numero;
    private String description;
    private double prix;
    private boolean tpsApplicable;
    private boolean tvqApplicable;


    public Item() {
        description = "";
        prix = 0;
        tpsApplicable = false;
        tvqApplicable = false;
        numero++;
    }

    public Item(String description, double prix){
        this();
        this.description = description;
        this.prix = prix;
    }

    public Item(String description, double prix, boolean tpsApplicable, boolean tvqApplicable){
        this(description, prix);
        this.tpsApplicable = tpsApplicable;
        this.tvqApplicable = tvqApplicable;
    }

    public Item(Item unItem){
        description = unItem.description;
        prix = unItem.prix;
        tpsApplicable = unItem.tpsApplicable;
        tvqApplicable = unItem.tvqApplicable;
        numero = unItem.numero;        
    }

    
    public String toString() {
        
        return "Description: "+ description+"\nPrix: "+ prix+ "\nTPS Applicable: "+ tpsApplicable+ "\nTVQ Applicable: "+tvqApplicable;
    }

    
    public boolean equals(Item autreItem) {
        return autreItem == this 
                || ( autreItem != null 
                &&  this.getClass().equals(autreItem.getClass()) 
                && this.description.equalsIgnoreCase(autreItem.description) 
                && this.prix == autreItem.prix 
                && this.tpsApplicable == autreItem.tpsApplicable
                && this.tvqApplicable == autreItem.tvqApplicable );
        
    }




}