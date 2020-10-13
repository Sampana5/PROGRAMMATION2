
/**
 * Classe d'Exception levee lorsqu'on tente de modifier un produit avec une
 * information invalide.
 * @author melanie lord
 * @version Automne 2020
 */
public class ExceptionInfoProduitInvalide extends RuntimeException {
   public ExceptionInfoProduitInvalide (String msgErr) {
      super(msgErr);
   }
}
