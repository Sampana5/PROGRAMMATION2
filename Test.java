import heritagePoly_1.*;
public class Test {
    public static void main(String[] args) throws ErrConstDate {
        Date date = new Date();
        

        
        Employe employe1 = new Employe("Abd", date);
        EmployeSalarie employeSalarie1 = new EmployeSalarie("abd",date,2000);
        EmployeHoraire employeHoraire1 = new EmployeHoraire("abd",date,20,45);
        Administrateur administrateur1 = new Administrateur();

        System.out.println(employe1.toString());
        System.out.println(employeSalarie1.toString());
        System.out.println(employeHoraire1.toString());
        System.out.println(administrateur1.toString());


    }
}
