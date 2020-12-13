import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;

public class CollectionMusicale {
    
    //----------------------
    // ATTRIBUT D'INSTANCE
    //----------------------

     List<Album> albums;//La liste des albumsappartenant à cette collection.

    //----------------------
    // CONSTRUCTEUR
    //----------------------

    /**
     * Ce constructeur lit chacun des albumscontenus dans le fichier 
     * texte ficAlbums, et les insère dans la liste albums
     * (dans l’ordre où ils seprésentent)
     * @param ficAlbums //Le chemin du fichier qui contient la liste 
     *                  //des albums de cette collection.
     */
    public CollectionMusicale(String ficAlbums){
        Path p = Paths.get(ficAlbums);
        albums = new ArrayList<>();

        try {
            // Créer un stream dont les éléments sont des lignes 
            //du fichier Albums.txt
            Stream<String> albFile = Files.lines(p);
            albFile
            .filter(line -> !line.startsWith("#"))
            .filter(line -> line.contains("|"))
            .forEach(lin -> {
                if(lin != null ){
                    String tab[] = lin.split("\\|");
                    Album al = new Album(Integer.parseInt(tab[0].trim()), tab[2], tab[3], Integer.parseInt(tab[1].trim()),Integer.parseInt(tab[4].trim()));
                    al.ajouterGenre(tab[5]);
                    //if (tab[6] != null) {
                       // al.ajouterSousGenre(tab[6]);
                //}
                
                albums.add(al);}
                        });
            albFile.close();
        } catch (IOException e) {
            System.out.println("Erreur d'entree / sortie");
        }

    }



    //-------------------------------
    // METHODES D'INSTANCES PUBLIQUES
    //-------------------------------

    /**
     * Cette méthode retourne le nombre total 
     * d’albums distincts(sans doublons) dans cettecollection.
     * @return le nombre total d’albums distincts
     * (sans doublons) dans cettecollection
     */
    public int getNombreAlbumsDistincts(){
        return (int)albums.stream().distinct().count();
    }

    /**
     * Cette méthode retourne un tableau contenant tous les albums
     * de cette collection ayant été faits par l’artiste
     * donné en paramètre
     * NOTE: Cette recherche n’est pas une recherche exacte
     *       et pour l’effectuer on teste plutôt si la valeur
     *       du paramètre est contenue dans le champs artiste 
     *       des albums testés(et NON est égale au champs artiste). 
     * Exemple:
     *          Album[] tab = rechercherParAriste("STON");
     * pourrait retourner les albums suivants:
     *  109. Aftermath -The Rolling Stones (1966) *** [Rock][Blues Rock, Pop Rock]
     *  186. Fresh -Sly & The Family Stone (1973) *** [Funk / Soul][Rhythm & Blues, Funk]
     *  257. Whitney Houston -Whitney Houston(1985) *** [Funk / Soul, Pop][Rhythm & Blues]
     *  ... 
     * @param artiste //L’artistedes albums recherchés.
     * @return un tableau contenant tous les albums de cette
     *          collection ayant été faits par l’artiste
     *          donné en paramètre
     */
    public Album[] rechercherParArtiste(String artiste){

        Album[] retour = albums.stream().filter(e -> artiste.contains(e.getArtiste()))
        .distinct().toArray(i -> new Album[i]);
        return retour;
    }

    /**
     * 
     * @param titre
     * @return
     */
    public Album[] rechercherParTitre(String titre){
        return albums.stream().filter(e -> titre.contains(e.getTitre()))
        .distinct().sorted().toArray(i -> new Album[i]);
    }


    public Album[] rechercherParPeriode(int anneeMin, int anneeMax){

        Album[] tab = null;
        Predicate<Album> estSup = e -> e.getAnnee() >= anneeMin;
        Predicate<Album> estInf = e -> e.getAnnee() <= anneeMax;
        
     
        if (anneeMin == anneeMax) {
            tab = rechercherParAnnee(anneeMax);

        } else if (anneeMin > anneeMax){
            tab = null;
        } else {
            tab = albums.stream().filter(estSup.and(estInf)).sorted().toArray(i -> new Album[i]);
        }
        return tab;
    }

    public Album[] rechercherParAnnee(int annee){

        return albums.stream().filter(e -> e.getAnnee() == annee)
                .sorted().toArray(i -> new Album[i]);
    }

    public Album[] rechercherParevaluation(int eval){
        Comparator<Album> trie = (e1, e2) -> {
            int res = 0;
            if (e1.getTitre().compareToIgnoreCase(e2.getTitre()) == -1) 
                res = -1;
             else if (e1.getTitre().compareToIgnoreCase(e2.getTitre()) == 1)
                res = 1;
            return res;
        };
        return albums.stream().filter(e -> e.getEvaluation() == eval)
                .distinct().sorted(trie).toArray(i -> new Album[i]);
    }

    public Album[] rechercherParGenres(String[] genres){
        
        Album [] tab = null;
        //String genre = "";
        //Iterator<String> iterGenre = 
       /** Predicate<Album> estContainsGenre = e -> {
            Iterator<String> iter = e.iterateurGenres();
            iter.forEachRemaining((String gen) -> gen.contains(genre));   
        };
       // Predicate<Album> estContainsSousGenre = e -> System.out.println(e); //e.iterateurSousGenres().forEachRemaining(a -> a.contains(genre));;

        if (genres.length > 0 && genres != null){
            for (int i = 0; i < genres.length; i++) {
                genre = genres[i];
                tab = albums.stream().filter(estContainsGenre.or(estContainsSousGenre)).distinct().sorted(trie).toArray(i -> new Album[i]);
            }
        }*/
        return tab;
    }

    public double getMoyenneEvaluations(String artiste){
        return 0;
    }


    public static void main(String[] args) {
        CollectionMusicale ab = new CollectionMusicale("albums.txt");
        
            System.out.println(ab.getNombreAlbumsDistincts());

            System.out.println(ab.albums.get(0).toString());
        
    }

    

}
