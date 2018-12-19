package util;

import static util.RunAG.iMax;
import static util.RunAG.xMax;

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

    public Ambiente() {

        //limha //coluna
        matriz[3][2] = wumpus;
        matriz[3][3] = fedor;
        matriz[3][1] = fedor;
        matriz[4][2] = fedor;
        matriz[2][2] = fedor;

        matriz[6][5] = foco;
        matriz[7][5] = brisa;
        matriz[5][5] = brisa;
        matriz[6][6] = brisa;
        matriz[6][4] = brisa;

        matriz[0][9] = foco;
        matriz[1][9] = brisa;
        matriz[0][8] = brisa;

        matriz[9][0] = ouro;
           

        //System.out.println(Util.formataSaidaDaMatriz2(matriz));

    }

    public static int[][] getMatriz() {
        return matriz;
    }

    public static int getPercepcao(int movX, int movY) {
       
        try{
            return  matriz[movX][movY];
        }catch(Exception e){
            return  50;//numero não entra na logica
        }
       
    }


}
