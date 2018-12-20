package util;

import static util.RunAG.iMax;
import static util.RunAG.xMax;
import static wumpus2.pkg2.WUMPUS22.ambiente;

public class Ambiente {

    public static int nda = 0;
    public static int wumpus = 1;
    public static int fedor = 2;

    public static int foco = 3;
    public static int brisa = 4;

    public static int ouro = 6;

    public static int ag = 7;

    public static int matriz[][] = new int[xMax][iMax];
    int matrizMov[][] = new int[xMax][iMax];
    public static int percepcao = 0;

    int aptidao = 0;

    public Ambiente() {

        //limha //coluna
        matriz[3][2] = wumpus;
        matriz[3][3] = fedor;
        matriz[3][1] = fedor;
        matriz[4][2] = fedor;
        matriz[2][2] = fedor;
//
        matriz[0][4] = foco;
        matriz[1][4] = brisa;
        matriz[0][3] = brisa;
//        matriz[6][6] = brisa;
//        matriz[6][4] = brisa;
//
//
//
        matriz[4][0] = ouro;

        System.out.println(Util.formataSaidaDaMatriz2(matriz));

    }

    public static int[][] getMatriz() {
        return matriz;
    }

    public static int getPercepcao(int movX, int movY) {

        try {
            return matriz[movX][movY];
        } catch (Exception e) {
            return 50;//numero não entra na logica
        }

    }

    public void runSolucao(String solucao) {
        avaliarPerformance(iMax, xMax, solucao);
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

            //printa o movimento na matriz aqui....
            //printa as percepções
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

            System.out.println("aptidão: " + aptidao);
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

}
