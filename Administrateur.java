package heritagePoly_1;

public class Administrateur extends EmployeSalarie {


    private String titre;
    private String domaine;
    private String superviseur;

    public Administrateur(){
        this.titre = "sans titre";
        this.domaine = "sans domaine";
        this.superviseur = "sans superviseur";
    }

    public Administrateur(String  leNom, Date laDate, Double leSalaire, String titre, String domaine, String superviseur){
        super(leNom, laDate, leSalaire);
        this.titre = titre;
        this.domaine = domaine; 
        this.superviseur = superviseur;
    }


    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine() {
        return this.domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getSuperviseur() {
        return this.superviseur;
    }

    public void setSuperviseur(String superviseur) {
        this.superviseur = superviseur;
    }

    //Redefinition de la methode toString de la clase EmployeSalarie

    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+" \n" + "Titre: "+ titre + "\nDomaine: "+ domaine+ "\nSuperviseur: "+ this.superviseur+"\n" ;
    }









}