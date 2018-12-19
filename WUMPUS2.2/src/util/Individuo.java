package util;

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
        boolean processAmbiente = true;
        boolean japegouOuro = false;

        int sizeGene = genes.length();

        int movimentoX = 0;
        int moviemntoY = 0;

        for (int i = 0; i < sizeGene; i++) {

            char teste = genes.charAt(i);

            switch (teste) {
                case 'N':

                    movimentoX++;

                    if (verify(movimentoX, moviemntoY, iMax, xMax)) {
                        aptidao = aptidao + 10;
                    } else {
                        aptidao = aptidao - 20;
                    }

                    break;
                case 'S':

                    movimentoX--;

                    if (verify(movimentoX, moviemntoY, iMax, xMax)) {
                        aptidao = aptidao + 10;
                    } else {
                        aptidao = aptidao - 20;
                    }
                    break;
                case 'L':

                    moviemntoY++;

                    if (verify(movimentoX, moviemntoY, iMax, xMax)) {
                        aptidao = aptidao + 10;
                    } else {
                        aptidao = aptidao - 20;
                    }

                    break;
                case 'O':

                    moviemntoY--;

                    if (verify(movimentoX, moviemntoY, iMax, xMax)) {
                        aptidao = aptidao + 10;
                    } else {
                        aptidao = aptidao - 20;
                    }

                    break;
            }

            int percepcao = ambiente.getPercepcao(movimentoX, moviemntoY);

            if (processAmbiente) {

                switch (percepcao) {

                    //nada
                    case 0:

                        if (estadoAtual.equals("A")) {
                            aptidao = aptidao + 10;
                        } else if (estadoAtual.equals("B")) {
                            aptidao = aptidao + 20;
                        } else if (estadoAtual.equals("C")) {
                            aptidao = aptidao + 20;
                        } else if (estadoAtual.equals("F")) {
                            aptidao = aptidao + 50;
                        }

                        estadoAtual = "A";

                        break;

                    //fedor    
                    case 2:

                        if (estadoAtual.equals("A")) {
                            aptidao = aptidao + 10;
                        } else if (estadoAtual.equals("B")) {
                            aptidao = aptidao + 5;
                        } else if (estadoAtual.equals("C")) {
                            aptidao = aptidao + 5;
                        } else if (estadoAtual.equals("F")) {
                            aptidao = aptidao + 10;
                        }

                        estadoAtual = "B";

                        break;

                    //brisa
                    case 4:

                        if (estadoAtual.equals("A")) {
                            aptidao = aptidao + 10;
                        } else if (estadoAtual.equals("B")) {
                            aptidao = aptidao + 5;
                        } else if (estadoAtual.equals("C")) {
                            aptidao = aptidao + 5;
                        } else if (estadoAtual.equals("F")) {
                            aptidao = aptidao + 10;
                        }

                        estadoAtual = "C";

                        break;

                    //ouro    
                    case 6:

                        if (!japegouOuro) {
                            if (estadoAtual.equals("A")) {
                                aptidao = aptidao + 1000;

                            } else if (estadoAtual.equals("B")) {
                                aptidao = aptidao + 1000;
                            } else if (estadoAtual.equals("C")) {
                                aptidao = aptidao + 1000;
                            }
                        }

                        japegouOuro = true;
                        estadoAtual = "F";

                        break;

                    //foco    
                    case 3:

                        if (estadoAtual.equals("C")) {
                            aptidao = aptidao - 2000;
                        }

                        estadoAtual = "D";
                        processAmbiente = false;

                        break;

                    //wumpus
                    case 1:

                        if (estadoAtual.equals("B")) {
                            aptidao = aptidao - 2000;

                            estadoAtual = "E";
                            processAmbiente = false;

                            break;
                        }

                }

                //VERIFICAR SE O ULTLIMO MOVIMENTO FOI X =0 E Y =0
                //caso tenha pegado o ouro
                if (japegouOuro) {

                    if (moviemntoY == 0 && movimentoX == 0) {
                        aptidao = aptidao + 5000;
                        processAmbiente = false;
                    }

                }
            }

            //cada volta no laço desconto 10 pontos.
            aptidao = aptidao - 10;

        }
    }

    public boolean verify(int movX, int movY, int iMax, int xMax) {
        boolean result = false;

        //verifica a posicao x e y da movimentacao n s l o incremeta  edecrementa cada movimento
        //e tbm verifica se o movimento não é negativo
        if (movX <= iMax && movX >= 0 && movY <= xMax && movY >= 0) {
            result = true;
        }
        return result;
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
        return "Individuo{" + "genes=" + genes + ", sizeGenes=" + sizeGenes + ", aptidao=" + aptidao + '}';
    }

}
