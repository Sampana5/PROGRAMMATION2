public class test {
    public static void main(String[] args) throws FileVideException {
        FilePrioChainee f1 = new FilePrioChainee();
        TachePrio t1 = new TachePrio(1);
        TachePrio t2 = new TachePrio(2);
        TachePrio t3 = new TachePrio(2);
        TachePrio t4 = new TachePrio(-2);
        TachePrio t5 = new TachePrio(5);
        TachePrio t6 = new TachePrio(1);
        TachePrio t7 = new TachePrio(7);
        TachePrio tFalse = new TachePrio(25);



        System.out.println(f1);
        f1.enfiler(t7);
        System.out.println(f1.defiler());
        System.out.println(f1);
        f1.enfiler(t7);
        f1.enfiler(t2);
        f1.enfiler(t2);
        System.out.println(f1.defiler(2));
        System.out.println(f1);
        f1.enfiler(t4);
        f1.enfiler(t4);
        f1.enfiler(t5);
        f1.enfiler(t6);
        f1.enfiler(t2);
        f1.enfiler(t2);
        f1.enfiler(t5);
        f1.enfiler(t5);
        f1.enfiler(t7);
        f1.enfiler(t7);
        f1.enfiler(t7);
        f1.enfiler(t7);
        f1.enfiler(t7);
        f1.enfiler(t5);
        System.out.println(f1);
        System.out.println(f1.taille(5)); //4
        System.out.println(f1.taille(2)); //3
        System.out.println(f1.taille(7)); //2
        System.out.println(f1.taille(-2)); //2
        // System.out.println(f1.prioriteMin()); //7
        //System.out.println(f1.prioriteExiste(1));
       f1.eliminerDoublons();
        System.out.println(f1);

    }


}


