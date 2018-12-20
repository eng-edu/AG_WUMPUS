package util;

public class RunAG {

    public static String caracteres = "NSLO";
    public static int iMax = 5;
    public static int xMax = 5;
    Populacao populacao = null;
    // Ambiente ambiente = new Ambiente();
    int rangeNumGenes = 100;
    int tamPop = 3;
    int tamGer = 1000;

    public void run() {

        //população incial
        populacao = new Populacao(rangeNumGenes, tamPop, iMax, xMax);
        tamPop = populacao.getTamPopulacao();

        System.out.println("\nPopulação inicial: " + populacao.toString());

        for (int i = 0; i <= tamGer; i++) {

            Geracao g = new Geracao(populacao, rangeNumGenes, iMax, xMax);
            g.run();

            tamPop = g.getPopulacao().getTamPopulacao();
            populacao = g.getPopulacao();

            System.out.println("\nGeracção: " + i + " " + populacao.toString());

        }
        
        String solucao = populacao.getIndividuos().get(0).getGenes();
        
        
        
        
    }

}
