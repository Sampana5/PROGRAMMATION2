
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

/**
 * INF2050 ( Outils et pratiques de développement logiciel)
 *
 * travail Pratique 1:  Logiciel qui effectura des Statistiques sur des interventions policière
 * dans les parcs de la ville de Montréal
 *   
 * @author Songa Sampana Yannick(SONS67100100) 
 * @version 
 *
 */





    public class LectureFichier2050 {
    
    static ParcMontrealInventaire [] tableauFichier2050 = new ParcMontrealInventaire[7];
    static ParcMontrealInventaire intervention;
    static ParcMontrealFichierSorti interventionFin;
    static ParcMontrealFichierSorti [] tableauFichierFin = new ParcMontrealFichierSorti[4];

    public static final String NOM_FICHIER = "./SONS67100100/FichiersTest/DemoText.csv";
    public static final String NOM_FICHIER2 = "./SONS67100100/FichiersTest/DemoTextFin.csv";
    
    public static String arrondissement;
    
   

    public static void main(String[] arg){
        LireFichier();
        DateFormatT(tableauFichier2050);
        //HeureFormat(tableauFichier2050);
        EcrireFichier();
        LireFichierFin();
    }

    public static void LireFichier() {

        // Déclarations des variables
        FileReader fluxConnecteur;
        BufferedReader fluxTampon;
        String uneLigne;
        String[] lesAttributsParcsMontreal;
        int nb = 0;

        try {
            // Création d'un flux connecteur
            // Assurez-vous que ce fichier existe dans le même
            // dossier que votre classe
            fluxConnecteur = new FileReader(NOM_FICHIER);

            // Création d'un flux tampon
            fluxTampon = new BufferedReader(fluxConnecteur);

            // lecture ligne par ligne
            while (fluxTampon.ready()) {

                uneLigne = fluxTampon.readLine(); // Lecture d'une ligne

                // Découper la ligne en tabeau de chaines de caractères avec , comme séparateur
                lesAttributsParcsMontreal = uneLigne.split("\\,");

                for (int i = 0; i < lesAttributsParcsMontreal.length; i++) {

                    String date = lesAttributsParcsMontreal[i].trim();

                    i = i + 1;
                    String Heure = lesAttributsParcsMontreal[i].trim();

                    i = i + 1;

                    String parc = lesAttributsParcsMontreal[i].trim();

                    i = i + 1;

                    String arrondissement = lesAttributsParcsMontreal[i].trim();

                    i = i + 1;

                    String Description = lesAttributsParcsMontreal[i].trim();

                    i = i + 1;

                  intervention = new ParcMontrealInventaire(date, Heure, parc, arrondissement, Description);
                  tableauFichier2050[nb++] = intervention;
                    
                 // System.out.println(intervention.toString());
               
                }
               
                
         }
         // Fermeture d'un fichier
         fluxTampon.close();
         fluxConnecteur.close();

      } catch (FileNotFoundException e) {
         System.out.println("Erreur : Fichier non trouvé ");
      } catch (IOException e) {
         System.out.println("Erreur d'entrée / sortie");
      } catch (NumberFormatException e) {
         System.out.println("Donnée invalide !");
      }
   }


   
   

   public static void EcrireFichier(){

    ArrayList <String> liste = new ArrayList<>();
    ArrayList <String> liste2 = new ArrayList<>();
    ArrayList <String> listeI = new ArrayList<>();
    ArrayList <String> listeF = new ArrayList<>();
    // Déclarations des variables
    PrintWriter fluxPrintWriter;

    try {
        // Création d'un flux connecteur
        fluxPrintWriter = new PrintWriter(NOM_FICHIER2);

        // Écriture des lignes dansle fichier.
        for (int i = 0; i < tableauFichier2050.length && tableauFichier2050[i] != null; i++) {
           
                
               /* if(!liste.contains(tableauFichier2050[i].getArrondissement())){
                    liste.add(tableauFichier2050[i].getArrondissement());
                }
                **/
               
                if(i != 0){
                    liste.add(tableauFichier2050[i].getArrondissement()+","+ occurence(tableauFichier2050[i].getArrondissement()));

                    }else{
                        liste.add(tableauFichier2050[0].getArrondissement()+","+ "Occurence");
                    }
               
                //fluxPrintWriter.println(tableauFichier2050[i].getArrondissement()+","+ occurernce(tableauFichier2050[i].getArrondissement()));
                    
                        listeI.add(tableauFichier2050[i].getDate());
                    
                
            } 
        
            for (int i = 0; i < liste.size()  && liste.get(i) != null; i++) {
            // Écrit les lignes les unes après les autres.
                if (!liste2.contains(liste.get(i))) {
                    liste2.add(liste.get(i));
                    
                }
               
                for (int j = 0; j < listeI.size()  && listeI.get(j) != null; j++) {
                    // Écrit les lignes les unes après les autres.
                        if (!listeF.contains(listeI.get(j))) {
                            listeF.add(listeI.get(j));
                            
                        }
                    }       
                //fluxPrintWriter.println(liste.get(i)+","+ occurence(tableauFichier2050[i].getArrondissement()));
                    

                
            } 
            TrieAlphabet(liste2);
           // DateFormatA(listeF);
            for(int j =0; j < liste2.size() && liste2.get(j) != null; j++){

                fluxPrintWriter.println(liste2.get(j));
            }

            


           
        fluxPrintWriter.close();

    } catch (IOException e) {
        System.out.println("Erreur d'entrée / sortie");
    }


    
}
    
    


  
   
   
   
   
    public static int occurence ( String arrondissement ){
        int i = 0;
        int nb = 0;
        
        LireFichier();
        
        for (i = 1; i < tableauFichier2050.length ; i++){

            if(tableauFichier2050[i].getArrondissement().equals(arrondissement)){

                nb++;

            }
        }
          return nb;

        

        }
       

   


   
        public static void TrieAlphabet(ArrayList<String> liste2)
        {
             for (int i = 0; i < liste2.size() - 1; i++)  
             {
                  int index = i;  
                  for (int j = i + 1; j < liste2.size(); j++)
                  {
                       if ( liste2.get(j).charAt(0) < liste2.get(index).charAt(0)) {
                            index = j;
                       }
                  }
    
                  String min = liste2.get(index);
                  liste2.set(index, liste2.get(i)) ; 
                  liste2.set(i, min);
             }
        
            }




    public static void LireFichierFin() {

        // Déclarations des variables
        FileReader fluxConnecteur;
        BufferedReader fluxTampon;
        String uneLigne;
        String[] lesAttributsParcsFin;
        int nb = 0;
        
        try {
            // Création d'un flux connecteur
            // Assurez-vous que ce fichier existe dans le même
            // dossier que votre classe
            fluxConnecteur = new FileReader(NOM_FICHIER2);
    
            // Création d'un flux tampon
            fluxTampon = new BufferedReader(fluxConnecteur);
    
            // lecture ligne par ligne
            while (fluxTampon.ready()) {
    
                uneLigne = fluxTampon.readLine(); // Lecture d'une ligne
    
                // Découper la ligne en tabeau de chaines de caractères avec , comme séparateur
                lesAttributsParcsFin = uneLigne.split("\\,");
    
                for (int i = 0; i < lesAttributsParcsFin.length; i++) {
    
                    String Arrondissement = lesAttributsParcsFin[i].trim();
    
                    i = i + 1;
                    
                    String  nbIntervention= lesAttributsParcsFin[i].trim();
    
                    i = i + 1;
                   
                   // int nbIntervention = Integer.parseInt(lesAttributsParcsFin[i].trim());
                    //i = i + 1;
                   
    
                  interventionFin = new ParcMontrealFichierSorti(Arrondissement, nbIntervention);
                  tableauFichierFin[nb++] = interventionFin;
                    
                  System.out.println(interventionFin.toString());
               
                }
               
             
         }
         // Fermeture d'un fichier
         fluxTampon.close();
         fluxConnecteur.close();
    
      } catch (FileNotFoundException e) {
         System.out.println("Erreur : Fichier non trouvé ");
      } catch (IOException e) {
         System.out.println("Erreur d'entrée / sortie");
      } catch (NumberFormatException e) {
         System.out.println("Donnée invalide !");
      }
    
    
    
    
    }

    
public static void DateFormatA (ArrayList<String> listeF){
    //LireFichier();
    
    boolean verification = false;
  
        
    for (int i = 0; i < listeF.size(); i++) {
       
       
            
        for (int j = 0; j < listeF.size(); j++) {
            
                if (listeF.size() != 10) {
                verification = false;
                }else{

                verification = true;

                for( j = 0; j <= 3 ; j++){
                    if (j == 0 || j == 1 || j == 2 ||  j == 3 ){
                    if(Character.isDigit(listeF.get(i).charAt(j)) && verification){

                        verification = true;
                    }else{

                        verification = false;
                     }
                    }
                }

                for( j=5; j <= 6 ; j++){
                    if(j == 5 || j == 6){
                        if(Character.isDigit(listeF.get(i).charAt(j)) && verification){

                            verification = true;
                        }else{

                            verification = false;
                        }
                    }
                }

                for( j=8; j <= 9 ; j++){
                    if(j == 8 || j ==9){
                        if(Character.isDigit(listeF.get(i).charAt(j)) && verification ){
                            verification = true;

                        }else{
                            verification = false;
                        }
                    }
                }
            
                if(verification == true){
                    if(listeF.get(i).charAt(4) != '-' ){

                         verification = false;
                    }

                    if(listeF.get(i).charAt(7) != '-' ){
                        verification = false;
                    }
                
                    
                }

              
    
            }
        
           
        }
    
   
   if (verification == false) {
        
    
    System.out.println("Format date invalide !");
    System.exit(0);
}
}
        
           
    

}






    //2020-09-01 

    public static void DateFormatT (ParcMontrealInventaire [] tableauFichier2050){
        //LireFichier();
        
        boolean verification = false;
      // try{
            
        for (int i = tableauFichier2050.length - 1; i >=0; i--) {
           
           // if (tableauFichier2050[0].getDate().equals("Date")) {
               
                //verification = true;
               // }
                
            for (int j = 0; j < tableauFichier2050[i].getDate().length(); j++) {
                
                
                verification = true;
                    if (tableauFichier2050[i].getDate().length() != 10) {
                    verification = false;
                    }else{

                    verification = true;

                    for( j = 0; j <= 3 ; j++){
                        if (j == 0 || j == 1 || j == 2 ||  j == 3 ){
                        if(Character.isDigit(tableauFichier2050[i].getDate().charAt(j)) && verification){

                            verification = true;
                        }else{

                            verification = false;
                         }
                        }
                    }

                    for( j=5; j <= 6 ; j++){
                        if(j == 5 || j == 6){
                            if(Character.isDigit(tableauFichier2050[i].getDate().charAt(i)) && verification){

                                verification = true;
                            }else{

                                verification = false;
                            }
                        }
                    }

                    for( j=8; j <= 9 ; j++){
                        if(j == 8 || j ==9){
                            if(Character.isDigit(tableauFichier2050[i].getDate().charAt(j)) && verification ){
                                verification = true;

                            }else{
                                verification = false;
                            }
                        }
                    }
                
                   
                    
                       
                    }

                  
                   
                
                    }
             
                
                    if(verification == true){
                        if(tableauFichier2050[i].getDate().charAt(4) != '-' ){

                             verification = false;
                        }

                        if(tableauFichier2050[i].getDate().charAt(7) != '-' ){
                            verification = false;
                        }    
       
              
            }
        
    
           
           
            
       
        }       
            
        if (verification == false) {
            
        
            System.out.println("Format date invalide !");
            //System.exit(0);
        }
       
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //20:41
    public static void HeureFormat (ParcMontrealInventaire [] tableauFichier2050){
        boolean verification = false;
       
        for (int i = 0; i < tableauFichier2050.length; i++) {
           
           
           
           
            
            
             for (int j = 0; j < tableauFichier2050[i].getHeure().trim().length(); j++) {
                
            


                if(tableauFichier2050[i].getHeure().trim().length() != 5 ){

                }else{

                    verification = true;

                for( j = 0; j <= 1 ; j++){
                    if (j == 0 || j == 1 ){
                        if(Character.isDigit(tableauFichier2050[i].getHeure().trim().charAt(j)) && verification){

                            verification = true;
                        }else{

                            verification = false;
                        }
                    }
                }

                for( j=3; j <= 4 ; j++){
                    if(j == 3 || j == 4){
                        if(Character.isDigit(tableauFichier2050[i].getHeure().trim().charAt(j)) && verification){

                            verification = true;
                        }else{

                            verification = false;
                        }
                    }
                }

                if(verification == true){
                    if(tableauFichier2050[j].getHeure().trim().charAt(2) != ':' ){

                        verification = false;
                    }
                }

            }
        }
            

    
      
        if(verification == false){
            System.out.println("le Format de L'heure est invalide!");
           
            }
          

    } 
    
   
    
    
}



}





    


  



    

