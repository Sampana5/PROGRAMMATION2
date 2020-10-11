

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
        //occurence(arrondissement);
        TrieAlphabet();
        EcrireFichier();
       
        // LireFichierFin(); 
       
       
        
       
        
        
    
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


        // Déclarations des variables
        PrintWriter fluxPrintWriter;

        try {
            // Création d'un flux connecteur
            fluxPrintWriter = new PrintWriter(NOM_FICHIER2);

            // Écriture des lignes dansle fichier.
            for (int i = 0; i < tableauFichier2050.length && tableauFichier2050[i] != null; i++) {
                // Écrit les lignes les unes après les autres.
               
                    if(tableauFichier2050[i].getArrondissement() != tableauFichier2050[i-1].getArrondissement() && i >0){
                    fluxPrintWriter.println(tableauFichier2050[i].getArrondissement()+","+ occurernce(tableauFichier2050[i].getArrondissement()));
                        
                    }else if(i == 0){
                        fluxPrintWriter.println(tableauFichier2050[i].getArrondissement()+","+ occurernce(tableauFichier2050[i].getArrondissement()));
                    }
                }   
            
            fluxPrintWriter.close();

        } catch (IOException e) {
            System.out.println("Erreur d'entrée / sortie");
        }


        
    }
    
    
    /*public static void ecrireDansLeFichier(String date , String heure , String parc , String arrondissement, String description) {
        
        LireFichier();
        for(int i = 0; i < tableauFichier2050.length ; i++){
           
            EcrireFichier();
        }
    }
 
    **/


  
   
   
   
   
    public static int occurence ( String arrondissement ){
        int i = 0;
        int nb = 0;
        
        LireFichier();
        
        for (i = 0; i < tableauFichier2050.length ; i++){

            if(tableauFichier2050[i].getArrondissement().equals(arrondissement)){

                nb++;

            }
        }
          return nb;

        

        }
       

    public static void TrieAlphabet(){

        LireFichier();
        int i = 0;
        char a = tableauFichier2050[i].getArrondissement().charAt(0);
        char b = tableauFichier2050[i+1].getArrondissement().charAt(0);
        for ( i = 0; i <= tableauFichier2050.length - 1 && tableauFichier2050[i] != null; i++){

            if( a > b){

                System.out.println(tableauFichier2050[i].getArrondissement());

            }
        }

        

    }


    //





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
    
                    int nbIntervention = Integer.parseInt(lesAttributsParcsFin[i].trim());
                    i = i + 1;
                   
    
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


    public static void EcrireFichierFin(){


        // Déclarations des variables
        PrintWriter fluxPrintWriter;

        try {
            // Création d'un flux connecteur
            fluxPrintWriter = new PrintWriter(NOM_FICHIER2);

            // Écriture des lignes dansle fichier.
            for (int i = 0; i < tableauFichierFin.length && tableauFichierFin[i] != null; i++) {
                // Écrit les lignes les unes après les autres.
                fluxPrintWriter.println(tableauFichierFin[i].formaterApres());
            }

            // Fermeture du flux d'écriture
            fluxPrintWriter.close();

        } catch (IOException e) {
            System.out.println("Erreur d'entrée / sortie");
        }


        
    }

    

}





    


  



    

