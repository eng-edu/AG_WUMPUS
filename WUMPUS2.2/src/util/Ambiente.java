package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.AG.iMax;
import static util.AG.xMax;
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

        matriz[4][4] = ouro;

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

    public boolean runSolucao(String genes) {
        boolean result = false;
        boolean japegouOuro = false;
        boolean movimentoFora = false;

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
                    moviemntoY++;
                    break;
                case 'O':
                    moviemntoY--;
                    break;
            }

            if (getPercepcao(movimentoX, moviemntoY) == 6 && japegouOuro == false) {
                japegouOuro = true;
            }

            if (getPercepcao(movimentoX, moviemntoY) == 50) {
                movimentoFora = true;
            }
        }

        if (movimentoX == 0 && moviemntoY == 0 && japegouOuro == true && movimentoFora == false) {
            result = true;
        }

        return result;
    }

    public void runAag(String genes) throws InterruptedException {

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
                    moviemntoY++;
                    break;
                case 'O':
                    moviemntoY--;
                    break;
            }

            int percpcao = getPercepcao(movimentoX, moviemntoY);

            System.out.println(Util.formataSaidaDaMatriz2(matriz));

            System.out.println(Util.formataSaidaDaMatriz3(movimentoX, moviemntoY));

            if (percpcao == nda) {

                System.out.println("nada");
            } else if (percpcao == brisa) {
                System.out.println("brisa");
            } else if (percpcao == foco) {
                System.out.println("foco");
            } else if (percpcao == fedor) {
                System.out.println("fedor");
            } else if (percpcao == wumpus) {
                System.out.println("wumpus");
            } else if (percpcao == ouro) {
                System.out.println("ouro");
            }

            try {

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

                Thread.sleep(1000);

            } catch (Exception ex) {
                Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
