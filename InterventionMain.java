

public class InterventionMain {
    
    static ParcMontrealInventaire [] tableauFichier2050 = new ParcMontrealInventaire[7];
    static ParcMontrealFichierSorti [] tableauFichierFin = new ParcMontrealFichierSorti[4];
    LectureFichier2050 LectureFichier2050F = new LectureFichier2050() ;
    
    
   
   
    public static void main(String[] args) {
        String arrondissement = " ";
        String fichierEntree = args[0];
        String fichierSortie = args[1];
        
        LectureFichier2050.LireFichier(); 
        LectureFichier2050.occurence(arrondissement);
        //LectureFichier2050.TrieAlphabet();
        LectureFichier2050.EcrireFichier();
        LectureFichier2050.LireFichierFin(); 


    
    
    
    
    }
}
