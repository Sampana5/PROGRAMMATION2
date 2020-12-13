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
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CollectionMusicale {
    //CONSTANTES
    private Comparator<Album> TRIE_PAR_TITRE = (e1, e2) -> {
        int res = 0;
        if (e1.getTitre().compareToIgnoreCase(e2.getTitre()) == -1) 
            res = -1;
         else if (e1.getTitre().compareToIgnoreCase(e2.getTitre()) == 1)
            res = 1;
        return res;
    };

    private Comparator<Album> TRIE_PAR_ANNEE_SORTIE = (e1, e2) -> {
        int res = 0;
        if (e1.getAnnee() < e2.getAnnee()) 
            res = -1;
         else if (e1.getAnnee() > e2.getAnnee())
            res = 1;
        return res;
    };
    
    //----------------------
    // ATTRIBUT D'INSTANCE
    //----------------------

    private List<Album> albums;//La liste des albumsappartenant à cette collection.

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

                    System.out.println("Taille du tableau = "+ tab.length);
                    Album al = new Album(Integer.parseInt(tab[0].trim()), tab[2], tab[3], Integer.parseInt(tab[1].trim()),Integer.parseInt(tab[4].trim()));
                    String genres[] = tab[5].split(",") ;
                    for (String genre : genres) {
                        al.ajouterGenre(genre.trim());
                    }
                    final int taille = tab.length;
                    
                    if (taille == 7) {
                            String sousGenres[] = tab[taille - 1].split(",");
                            for (String sousGenre : sousGenres) {
                                al.ajouterSousGenre(sousGenre.trim());
                            }
                         
                    }
                
                
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
    public Album [] rechercherParArtiste(String artiste){
        return albums.stream().filter(e -> e.getArtiste().toLowerCase().contains(artiste.toLowerCase())).distinct().sorted(TRIE_PAR_TITRE).toArray(i -> new Album[i]);
       
    }

    /**
     * 
     * @param titre
     * @return
     */
    public Album[] rechercherParTitre(String titre){
        return albums.stream().filter(e -> e.getTitre().toLowerCase().contains(titre.toLowerCase())).distinct().sorted(TRIE_PAR_TITRE).toArray(i -> new Album[i]);
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
            tab = albums.stream().filter(estSup.and(estInf)).sorted(TRIE_PAR_ANNEE_SORTIE).toArray(i -> new Album[i]);
        }
        return tab;
    }

    public Album[] rechercherParAnnee(int annee){
        return albums.stream().filter(e -> e.getAnnee() == annee)
                .sorted(TRIE_PAR_ANNEE_SORTIE).toArray(i -> new Album[i]);
    }

    public Album[] rechercherParEvaluation(int eval){
        Predicate<Album> estEgale = (e) -> e.getEvaluation() == eval;
        return albums.stream().filter(estEgale).distinct().sorted(TRIE_PAR_ANNEE_SORTIE).toArray(i -> new Album[i]);
    }

    public Album[] rechercherParGenres(String[] genres){
        
        Album [] tab = null;
       
        
        

        if (genres.length > 0 && genres != null){
            ArrayList<String> list = new ArrayList<>();
            Iterator<String> iterator;
            for (int i = 0; i < genres.length; i++) {
                //genre = genres[i];
                list.add(genres[i]);
                
            }
            iterator  = list.iterator();
                Predicate<Album> estContainsGenre = e -> {
                    boolean ret = false;
                    while (e.iterateurGenres().hasNext() && !ret) {
                        ret = e.iterateurGenres().next().contains("qq");
                    }
                    return ret;
                };
                Predicate<Album> estContainsSousGenre = e -> {
                    boolean ret = false;
                    while (e.iterateurSousGenres().hasNext() && !ret) {
                        ret = e.iterateurSousGenres().next().contains("aa");
                    }
                    return ret;
                };
                tab = albums.stream().filter(estContainsGenre.or(estContainsSousGenre)).distinct().sorted(TRIE_PAR_TITRE).toArray(n -> new Album[n]);
            
        }
        return tab;
    }

    public double getMoyenneEvaluations(String artiste){
        return 0;
    }


    public static void main(String[] args) {
        CollectionMusicale ab = new CollectionMusicale("albums.txt");
            System.out.println("NBR D'OEURVRE:");
            System.out.println(ab.getNombreAlbumsDistincts());
            System.out.println();
            System.out.println("1ere LIGNE : ");
            System.out.println(ab.albums.get(0).toString());

            Album [] tAlbums = ab.rechercherParAnnee(2000);

            System.out.println("RECHERCHE PAR ANNEE (2000)");
            for (int i = 0; i < tAlbums.length; i++) {
                System.out.println(tAlbums[i].toString());
            }
            System.out.println();

            Album [] tAlbum = ab.rechercherParPeriode(2000, 2010);
            System.out.println("RECHERCHE PAR PERIODE (2000 - 2010)");
            for (int i = 0; i < tAlbum.length; i++) {
                System.out.println(tAlbum[i].toString());
            }
            System.out.println();

            Album [] tAlbu = ab.rechercherParEvaluation(3);
            System.out.println("RECHERCHE PAR Evaluation (5)");
            for (int i = 0; i < tAlbu.length; i++) {
                System.out.println(tAlbu[i].toString());
            }
            System.out.println();

            Album [] tAlb = ab.rechercherParTitre("The");
            System.out.println("RECHERCHE PAR TITRE (The)");
            for (int i = 0; i < tAlb.length; i++) {
                System.out.println(tAlb[i].toString());
            }
            System.out.println();

            Album [] tAl = ab.rechercherParArtiste("emine");
            System.out.println("RECHERCHE PAR ARTISTE (emine)");
            for (int i = 0; i < tAl.length; i++) {
                System.out.println(tAl[i].toString());
            }
            System.out.println();

            System.out.println("Genres ::  " );
            Iterator<String> ite = ab.albums.get(495).iterateurGenres();
            ite.forEachRemaining((ge) -> System.out.println(ge)) ;

            System.out.println();
            String [] str = {"pop","Folk"};
           // Album [] tA = ab.rechercherParGenres(str);
            System.out.println("RECHERCHE PAR GENRES (pop, Folk)");
           // for (int i = 0; i < tA.length; i++) {
               // System.out.println(tA[i].toString());
            //}
            System.out.println();
    }

    

}
