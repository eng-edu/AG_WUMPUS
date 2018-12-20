package util;

import com.sun.xml.internal.ws.encoding.soap.SOAP12Constants;
import java.util.Random;
import static util.RunAG.caracteres;
import static wumpus2.pkg2.WUMPUS22.ambiente;

public final class Individuo {

    private String genes = "";
    private int sizeGenes = 0;
    private int aptidao = 0;

    public Individuo(String genes, int iMax, int xMax) {
        this.genes = genes;
        avaliarPerformance(iMax, iMax, genes);

    }

    //gera um indivíduo aleatório 
    public Individuo(int rangeNumGenes, int iMax, int xMax) {

        Random r = new Random();

        for (int i = 0; i < r.nextInt(rangeNumGenes); i++) {
            //Ssorteia uma posicao aleatoria dos caracteres definidos
            this.genes += caracteres.charAt(r.nextInt(caracteres.length()));
        }

        avaliarPerformance(iMax, iMax, genes);
    }

    public void avaliarPerformance(int iMax, int xMax, String genes) {

        String estadoAtual = "A";
        boolean japegouOuro = false;
        boolean jachegou = false;

        String estadoPercepcao = "";

        int sizeGene = genes.length();

        int movimentoX = 0;
        int moviemntoY = 0;

        for (int i = 0; i < sizeGene; i++) {

            char gene = genes.charAt(i);

            switch (gene) {
                case 'N':
                    movimentoX++;
                    break;
                case 'S':
                    movimentoX--;
                    break;
                case 'L':
                    moviemntoY--;
                    break;
                case 'O':
                    moviemntoY++;
                    break;
            }

            int percepcao = ambiente.getPercepcao(movimentoX, moviemntoY);

            switch (percepcao) {

                //nada
                case 0:
                    if (japegouOuro) {
                        aptidao = aptidao + 1;
                    } else {
                        aptidao = aptidao - 10;
                    }
                    break;

                //fedor    
                case 2:
                    if (japegouOuro) {
                        aptidao = aptidao + 1;
                    } else {
                        aptidao = aptidao - 10;
                    }

                    break;

                //brisa
                case 4:
                    if (japegouOuro) {
                       aptidao = aptidao + 1;
                    } else {
                        aptidao = aptidao - 10;
                    }

                    break;

                //ouro    
                case 6:

                    if (japegouOuro == false) {
                        aptidao = aptidao + 10000;
                        japegouOuro = true;
                    } else {
                        aptidao = aptidao - 100;
                    }

                    break;

                //foco    
                case 3:

                    if (japegouOuro) {
                        aptidao = aptidao - 1000;
                    } else {
                        aptidao = aptidao - 100;
                    }

                    break;

                //wumpus
                case 1:

                    if (japegouOuro) {
                        aptidao = aptidao - 1000;
                    } else {
                        aptidao = aptidao - 100;
                    }

                    break;
                case 50:

                    if (japegouOuro) {
                        aptidao = aptidao - 1000;
                    } else {
                        aptidao = aptidao - 500;
                    }

                    break;

            }

            //VERIFICAR SE O ULTLIMO MOVIMENTO FOI X =0 E Y =0
            //caso tenha pegado o ouro
            if (japegouOuro) {
                if (moviemntoY == 0 && movimentoX == 0) {
                    
                    if(jachegou){   
                         aptidao = aptidao - 10000; 
                    }else{
                        aptidao = aptidao + 100000; 
                       jachegou = true; 
                    }
                    
                   
                }
                
                
            }

        }

    }

    public String getGenes() {
        return genes;
    }

    public void setGenes(String genes) {
        this.genes = genes;
    }

    public int getAptidaoMov() {
        return aptidao;
    }

    @Override
    public String toString() {
        return "Individuo{" + "genes=" + genes + ", sizeGenes=" + genes.length() + ", aptidao=" + aptidao + '}';
    }

}
