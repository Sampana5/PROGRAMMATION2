import java.util.Iterator;

/**
 * La classe FilePrioChainee est une classe concrète qui implementera
 * toute les methodes de l'interface IFilePrio En utilisant
 * une liste de maillons chainés comme structure de données sous-jacente.
 *
 * @author Songa Yannick(SONS67100100)
 * @version Automne 2020
 */
public class FilePrioChainee<T extends ITachePrio> implements IFilePrio<T> {


    //-----------------------------------
    // ATTRIBUTS D'INSTANCE
    // -----------------------------------

    private Maillon<T> elements; // Le premier maillon de la liste chainée représentant la tête de la file de
    //priorité. Si elements est null, c’est que la file de priorité est vide.

    private int taille; // Le nombre d’éléments dans cette file de priorité. Il doit être mis à jour à
    //chaque ajout ou retrait d’éléments dans la liste chainée représentant la
    // file de priorité. Lorsque taille contient la valeur 0, c’est que la file de
    // priorité est vide.


    //-------------------
    // CONSTRUCTEURS
    //-------------------


    // Constructeur d’initialisation

    /**
     * Ce constructeur sans argument crée une file de priorité vide
     * (qui ne contient aucun élément).
     */
    public FilePrioChainee() {
        this.elements = null;

    }

    /**
     * Enfile l'element (non null) dans cette file de priorite.
     * <p>
     * Exemples :
     * Soit la file de priorite suivante :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de enfiler(e7(5)) donne la file :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e7(5), e6(3) ] fin
     * <p>
     * - l'appel de enfiler(e8(7)) donne la file :
     * tete [ e1(9), e2(9), e8(7), e3(5), e4(5), e5(5), e6(3) ] fin
     *
     * @param element l'element a enfiler dans cette file de priorite.
     * @throws NullPointerException si l'element donne en parametre est null.
     */////////MARCHE////////
    @Override
    public void enfiler(T element) {
        //maillom représentant le début
        Maillon<T> debut = elements;
        // le nouveau maillon à insérer
        Maillon<T> nouveau = new Maillon<T>(element);
        //maillom courant
        Maillon<T> tmp = null;

        if (element == null) {
            throw new NullPointerException();
        }
        tmp = debut;
        if (elements == null){
            elements = nouveau;
        }else if (nouveau.getInfo().getPriorite() > tmp.getInfo().getPriorite()) {
            nouveau.setSuivant(debut);
            elements = nouveau;
        } else {

            while (tmp.getSuivant() != null &&
                    tmp.getSuivant().getInfo().getPriorite() > nouveau.getInfo().getPriorite()) {
                tmp = tmp.getSuivant();

            }
            Maillon<T> save = tmp.getSuivant();
           tmp.setSuivant(nouveau);
           nouveau.setSuivant(save);
            //nouveau.setSuivant(debut.getSuivant());
           // debut.setSuivant(nouveau);
            //elements = nouveau;



        }


    }

    /**
     * Defile l'element le plus prioritaire (premier arrivee de la plus grande
     * priorite) de cette file de priorite.
     * <p>
     * Exemple :
     * Soit la file de priorite suivante :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de defiler() retourne e1(9), et la file resultante est :
     * tete [ e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     *
     * @return l'element defile.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *                           de cette methode.
     */////////MARCHE////////
    @Override
    public T defiler() throws FileVideException {
        //maillom représentant le début
        Maillon<T> debut = elements;
        //L'element défile qu'on doit retourné
        T ElementDefile = null;

        if (elements == null){
            throw new FileVideException();
        }else{
            ElementDefile = elements.getInfo();
            elements = debut.getSuivant();
        }

        return (T) ElementDefile;
    }

    /**
     * Defile l'element le plus prioritaire de la priorite donnee en parametre.
     * Si aucun element de la priorite donnee n'existe dans cette file de priorite,
     * la methode retourne null et cette file de priorite n'est pas modifiee.
     * <p>
     * Exemples :
     * Soit la file de priorite suivante :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de defiler(5) retourne e3(5), et la file resultante est :
     * tete [ e1(9), e2(9), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de defiler(9) retourne e1(9), et la file resultante est :
     * tete [ e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de defiler(4) retourne null, et la file reste inchangee.
     *
     * @param priorite la priorite de l'element a defiler.
     * @return l'element defile ou null si aucun element de la priorite donnee
     * en parametre n'existe dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant qu'on
     *                           ne tente de defiler l'element.
     */ ////////MARCHE////////
    @Override
    public T defiler(int priorite) throws FileVideException {
        //maillom représentant le début
        Maillon<T> debut = elements;
        //maillom courant
        Maillon<T> precedent = debut;
        // Element défilé selon sa priorité
        T ElementDefileSelonPriorite = null;

        if (elements == null){
            throw new FileVideException();
        }else {
            while (precedent.getSuivant() != null &&
                    (precedent.getInfo().getPriorite() != priorite )){
                 precedent = precedent.getSuivant();
            }
            ElementDefileSelonPriorite = precedent.getInfo();
           precedent.setSuivant(precedent.getSuivant().getSuivant());
        }

        return ElementDefileSelonPriorite;
    }

    /**
     * Defile tous les elements de la priorite donnee. Si aucun element de cette
     * priorite n'existe dans cette file de priorite, celle-ci n'est pas modifiee.
     * La methode retourne une file de priorite contenant tous les elements
     * defiles, dans le meme ordre que lorsqu'ils se trouvaient dans cette file
     * de priorite. Si aucun element n'est defile, la file retournee est vide.
     * <p>
     * Exemples :
     * Soit la file de priorite suivante :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de defilerTous(5) retourne la file suivante :
     * tete [ e3(5), e4(5), e5(5) ] fin
     * <p>
     * et la file resultante modifiee est :
     * tete [ e1(9), e2(9), e6(3) ] fin
     * <p>
     * - l'appel de defilerTous(4) retourne une file vide et la file resultante
     * reste inchangee.
     *
     * @param priorite la priorite des elements a defiler de cette file de
     *                 priorite.
     * @return Une file de priorite contenant tous les elements defiles, dans
     * le meme ordre.
     * @throws FileVideException si cette file de priorite est vide avant
     *                           l'appel de cette methode.
     */
    @Override
    public IFilePrio<T> defilerTous(int priorite) throws FileVideException {

       // IFilePrio<T>

        return null;
    }

    /**
     * Verifie si cette file de priorite contient au moins un element ayant la
     * priorite donnee en parametre.
     *
     * @param priorite la priorite dont on veut verifier l'existence dans cette
     *                 file de priorite.
     * @return true si au moins un element ayant la priorite donnee en parametre
     * existe dans cette file de priorite, false sinon.
     */ ///////////MARCHE///////////////
    @Override
    public boolean prioriteExiste(int priorite) {

        boolean contient = false;
        //maillom représentant le début
        Maillon<T> debut = elements;
        // le nouveau maillon à insérer
        //maillom courant
        Maillon<T> tmp = debut;

        while (tmp != null){

            tmp = tmp.getSuivant();

            if(tmp != null) {
                if (tmp.getInfo().getPriorite() == priorite) {
                    contient = true;

                }
            }
        }
        return contient;
    }

    /**
     * Verifie si cette file de priorite contient des elements ou non.
     *
     * @return true si cette file de priorite ne contient aucun element, false
     * sinon.
     */ //////MARCHE////////
    @Override
    public boolean estVide() {
        return taille == 0;
    }

    /**
     * Permet d'obtenir le nombre d'elements contenus dans cette file de priorite.
     *
     * @return le nombre d'elements dans cette file de priorite.
     */
    @Override ////////MARCHE////////
    public int taille() {

        Maillon<T> courant = elements;
        int size = 0;
        while (courant != null) {
            courant = courant.getSuivant();
            size ++;
        }
        return size;
    }

    /**
     * Permet d'obtenir le nombre d'elements ayant la priorite donnee en parametre
     * qui sont contenus dans cette file de priorite.
     *
     * @param priorite la priorite des elements dont on veut le nombre.
     * @return le nombre d'elements ayant la priorite donnee en parametre qui sont
     * contenus dans cette file de priorite.
     */ /////////MARCHE//////////
    @Override
    public int taille(int priorite) {
        int sizePriorite = 0;
        //maillom représentant le début
        Maillon<T> courant = elements;

        while ( courant != null ){

            if (courant.getInfo().getPriorite() == priorite) {
                sizePriorite++;
            }
            courant = courant.getSuivant();
        }
        return sizePriorite;
    }

    /**
     * Permet de consulter l'element en tete de cette file de priorite, sans
     * modifier celle-ci. L'element en tete de file est toujours l'element
     * le plus ancien parmis ceux ayant la priorite la plus forte.
     *
     * @return l'element en tete de cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *                           de cette methode.
     */ ////////////Marche//////////
    @Override
    public T premier() throws FileVideException {
        //maillom représentant le début
        Maillon<T> debut = elements;
        // L'element se trouvant en tête de file
        T firstElement = null;

        if (debut == null){
            throw new FileVideException();
        }else {
            firstElement = debut.getInfo();
        }

        return firstElement;
    }

    /**
     * Permet de consulter l'element le plus prioritaire de la priorite donnee
     * en parametre, sans modifier cette file de priorite. Si aucun element
     * de la priorite donnee existe dans cette file de priorite, la methode
     * retourne null.
     *
     * @param priorite la priorite de l'element le plus prioritaire que l'on veut
     *                 consulter.
     * @return l'element le plus prioritaire de la priorite donnee en parametre.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *                           de cette methode.
     */ ///////Pas Sûr//////////////
    @Override
    public T premier(int priorite) throws FileVideException {
        //maillom représentant le début
        Maillon<T> debut = elements;
        // L'element se trouvant en tête de file selon la priorité
        T firstElementPrio = null;

        if (debut == null){
            throw new FileVideException();
        }else {
            while ( debut != null &&
                    (debut.getInfo().getPriorite() != priorite)){
                debut = debut.getSuivant();
            }
            firstElementPrio = debut.getInfo();
        }

        return firstElementPrio;
    }

    /**
     * Retire tous les elements de cette file de priorite. Apres l'appel de cette
     * methode, l'appel de la methode estVide() retourne true.
     */ /////////MARCHE///////////
    @Override
    public void vider() {
        elements = null;
    }

    /**
     * Retourne une file de priorite contenant tous les elements ayant la priorite
     * donnee en parametre se trouvant dans cette file de priorite. Les elements
     * dans la file de priorite a retourner doivent conserver l'ordre dans lequel
     * ils apparaissent dans cette file de priorite. Apres l'appel de cette methode,
     * cette file de priorite ne doit pas avoir ete modifiee. De plus, si aucun
     * element ayant la priorite donnee ne se trouve dans cette file de priorite,
     * la methode retourne une file de priorite vide.
     * <p>
     * Exemples :
     * Soit la file de priorite suivante :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(5), e6(3) ] fin
     * <p>
     * - l'appel de sousFilePrio(5) retourne la file suivante :
     * tete [ e3(5), e4(5), e5(5) ] fin
     * <p>
     * - l'appel de sousFilePrio(4) retourne une file vide
     *
     * @param priorite la priorite des elements de la file de priorite a retourner.
     * @return une file de priorite contenant tous les elements ayant la priorite
     * donnee en parametre se trouvant dans cette file de priorite.
     */
    @Override
    public IFilePrio<T> sousFilePrio(int priorite) {
        return null;
    }


    
    /**
     * Teste si cette file de priorite contient au moins un element identique a
     * celui donne en parametre. Un element e1 est identique a un element e2
     * si e1.equals(e2) retourne true.
     *
     * @param elem l'element dont on teste l'existence.
     * @return true s'il existe au moins un element dans cette file de priorite
     * qui est identique a celui donne en parametre, false sinon.
     *//////////////Marche/////////////
    @Override
    public boolean contient(T elem) {
        boolean contient = false;
        //maillom représentant le début
        Maillon<T> debut = elements;
        // le nouveau maillon à insérer
        Maillon<T> nouveau = new Maillon<T>(elem);
        //maillom courant
        Maillon<T> tmp = debut;

        while (tmp != null){

            tmp = tmp.getSuivant();
            if(tmp != null) {
                if (tmp.getInfo().equals(nouveau.getInfo())) {
                    contient = true;

                }
            }
        }

        return contient;
    }

    /**
     * Normalise les priorites des elements de cette file de priorite
     * en modifiant celles-ci pour que la plus petite priorite devienne 1, que
     * la deuxieme plus petite priorite devienne 2, et ainsi de suite, jusqu'a la
     * plus grande priorite (qui correspondra au nombre de priorites differentes
     * dans cette file de priorite).
     * <p>
     * Par exemple, soit la file de priorite suivante (qui contient 4 priorites
     * differentes) :
     * tete [ e1(9), e2(9), e3(5), e4(5), e5(3), e6(-4) ]
     * <p>
     * Apres normalisation, la file de priorite devient :
     * tete [ e1(4), e2(4), e3(3), e4(3), e5(2), e6(1) ] fin
     * <p>
     * Si la file de priorite etait deja sous sa forme "normale", elle demeure
     * inchangee.
     */
    @Override
    public void normaliser() {

    }

    /**
     * Elimine les doublons de cette file de priorite. Si une sousFile de priorite
     * contient, par exemple, 3 elements identiques, la methode elimine les deux
     * moins prioritaire (en terme du moment d'entree dans la file). Par exemple :
     * <p>
     * Soit le file de priorite suivante qui contient 2 elements e1(9),
     * 4 elements e3(5), et 2 elements e4(5) :
     * tete [ e1(9), e2(9), e1(9), e3(5), e3(5), e4(5), e3(5), e4(5), e8(5), e3(5) ]
     * <p>
     * Apres l'elimination des doublons, la file de priorite devient :
     * tete [ e1(9), e2(9), e3(5), e4(5), e8(5) ]
     * <p>
     * Note : un element e1 est identique a un element e2 si e1.equals(e2)
     * retourne true.
     * <p>
     * Si la file de priorite ne contenait aucun doublon(s), elle demeure
     * inchangee.
     *///////////////MARCHE PAS TOTALEMENT/////////////
    @Override
    public void eliminerDoublons() {

        //maillom représentant le début
        Maillon<T> debut = elements;
        //maillom courant
        Maillon<T> tmp = debut;
        Maillon<T> suivant = debut.getSuivant();


        while (tmp != null && suivant != null) {

                if (tmp != null && suivant != null) {

                    if (tmp.getInfo().getPriorite() == tmp.getSuivant().getInfo().getPriorite()
                            && suivant.getInfo().getPriorite() == suivant.getSuivant().getInfo().getPriorite()
                    ) {
                        tmp.setSuivant(tmp.getSuivant().getSuivant());
                        suivant.setSuivant(suivant.getSuivant());
                    }
                    tmp = tmp.getSuivant();
                    suivant = suivant.getSuivant();

                }



        }
    }

    /**
     * Permet d'obtenir la priorite la plus grande parmi les priorites de tous
     * les elements de cette file de priorite.
     *
     * @return la priorite maximum dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *                           de cette methode.
     */ //////MARCHE/////////////
    @Override
    public int prioriteMax() throws FileVideException {

        // La priorité la plus grande
        int prioMax = 0;
        //maillom représentant le début
        Maillon<T> debut = elements;


        if (debut == null){
            throw new FileVideException();
        }else {
            prioMax = debut.getInfo().getPriorite();
        }

        return prioMax;
    }

    /**
     * Permet d'obtenir la priorite la plus petite parmi les priorites de tous
     * les elements de cette file de priorite.
     *
     * @return la priorite minimum dans cette file de priorite.
     * @throws FileVideException si cette file de priorite est vide avant l'appel
     *                           de cette methode.
     */ /////////MARCHE////////////
    @Override
    public int prioriteMin() throws FileVideException {
        // La priorité la plus grande
        int prioMin = 0;
        //maillom représentant le début
        Maillon<T> debut = elements;
        //avant-dernier maillon
        Maillon<T> precedent = debut;
        if (debut == null){
            throw new FileVideException();
        }
        if (debut.getSuivant() == null) {
        prioMin = debut.getInfo().getPriorite();
        }else {
            while (precedent.getSuivant() !=  null) {
                precedent = precedent.getSuivant();
            }
            prioMin = precedent.getInfo().getPriorite();
        }

        return prioMin;
    }

    /**
     * Retourne une copie de cette file de priorite.
     * <p>
     * NOTE POUR LES ETUDIANTS : La copie retournee ne doit pas pointer vers le
     * meme espace memoire que la file a copier. De plus, chacun des maillons
     * de la file copiee ne doit pas pointer sur aucun des maillons de la file a
     * copier. Par contre, l'info dans chaque maillon de la file copiee peut
     * pointer sur la meme info que celle se trouvant dans les maillons de la
     * file a copier.
     *
     * @return une copie de cette file de priorite.
     */ /////////Ne Marche Pas////////////////
    @Override
    public IFilePrio<T> copie() {
        Maillon <T> fileCopie= null;
        Maillon<T> debut = elements;

        while (debut != null) {
            // placer un nouveau maillon au debut de reponse
            fileCopie = new Maillon(debut.getInfo(), fileCopie);
            debut = debut.getSuivant();
        }



        return (IFilePrio<T>) fileCopie;
    }

    /**
     * Construit une representation sous forme de chaine de caracteres de cette
     * file de priorite.
     *
     * @return une representation sous forme de chaine de caracteres de cette
     * file de priorite.
     */
    @Override
    public String toString() {
        String s = "tete [ ";
        Maillon<T> tmp = elements;
        if (tmp == null) {
            s = s + " ] fin";
        } else {
            while (tmp != null) {
                s = s + tmp.getInfo() + ", ";
                tmp = tmp.getSuivant();
            }
            s = s.substring(0, s.length() - 2) + " ] fin";
        }
        return s;
    }



}
