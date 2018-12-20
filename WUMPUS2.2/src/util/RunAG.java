package util;

public class RunAG {

    public static String caracteres = "NSLO";
    public static int iMax = 5;
    public static int xMax = 5;
    Populacao populacao = null;
 
    // Ambiente ambiente = new Ambiente();
    int rangeNumGenes = 100;
    int tamPop = 3;
    int tamGer = 100;

    public void run() {

        //população incial
        populacao = new Populacao(rangeNumGenes, tamPop, iMax, xMax);
        tamPop = populacao.getTamPopulacao();

        System.out.println("\nPopulação inicial: " + populacao);
        for (int i = 0; i <= tamGer; i++) {

    

            Geracao g = new Geracao(populacao, rangeNumGenes, iMax, xMax);
            g.run();

            System.out.println("\nGeracção: " + i);
              
            tamPop = g.getPopulacao().getTamPopulacao();
            populacao = g.getPopulacao();

            Individuo in0 = populacao.getIndividuos().get(0);
            Individuo in1 = populacao.getIndividuos().get(1);
            Individuo in2 = populacao.getIndividuos().get(2);
            Individuo in3 = populacao.getIndividuos().get(3);

            String e0 = in0.getGenes();
            String e1 = in1.getGenes();
            String e2 = in2.getGenes();
            String e3 = in3.getGenes();

//            System.out.println("\n\ne0 : " + e0 + "  aptd: " + in0.getAptidaoMov());
//            System.out.println("\t e1 : " + e1+ "  aptd: " + in1.getAptidaoMov());
//            System.out.println("\t \t e2 : " + e2+ "  aptd: " + in2.getAptidaoMov());
//            System.out.println("\t \t \t e3 : " + e3+ "  aptd: " + in3.getAptidaoMov());

            System.out.println(populacao);

            if (e0.equals(e1) && e0.equals(e2) && e0.equals(e3)) {
                System.out.println("\ntodos iguais\n ");
            }

          

        }

        String solucao = populacao.getIndividuos().get(0).getGenes();

    }

}
