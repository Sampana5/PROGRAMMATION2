import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CollectionMusicale {
    
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
        Path chemin = Paths.get(ficAlbums);

        try {
            // Créer un stream dont les éléments sont des lignes 
            //du fichier Albums.txt
            Stream<Album> albFile = Files.lines(chemin);
            Album[] tabAlbums = albFile.toArray(i -> new Album[i]);
            albums = new ArrayList<>(tabAlbums);
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

    }

}
