package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Geracao {

    public Populacao populacao;
    public List<Individuo> individuosSelecionados;
    public List<Individuo> novosIndividuos;
    public Individuo mutado;
    public int iMax;
    public int xMax;
    public int selecionado;

    
    public Geracao(Populacao populacao,int rangeNumGenes, int iMax, int xMax) {
        this.populacao = populacao;
        this.iMax = iMax;
        this.xMax = xMax;
    }
     
    public void run(){
          
       
        selecionarIindividuos();
     
        cruzarIndividuos();
        
        mutarIndividuo();
        
       
        rank();
        
      
       
    }
    
    public void rank(){
 
    int size = this.populacao.getIndividuos().size();
       
     Collections.sort(this.populacao.getIndividuos(), (Individuo o1, Individuo o2) -> {
         //TODO testar nulos
         if (o1.getAptidaoMov() < o2.getAptidaoMov()){
             return 1;
         }
         
         if (o1.getAptidaoMov() > o2.getAptidaoMov()){
             return -1;
         }
         
         return 0;
    });
    
     this.populacao.removeUtlimoIndividuo(size-1);
     this.populacao.removeUtlimoIndividuo(size-2);
     this.populacao.removeUtlimoIndividuo(size-3);
     this.populacao.removeUtlimoIndividuo(size-4);
     
     
      //  System.out.println(this.populacao.getIndividuos());
     
    
    }

    public void selecionarIindividuos() {
        this.individuosSelecionados = Util.selecionarIndividuos(this.populacao.getIndividuos());
   }

    public void cruzarIndividuos() {
        this.novosIndividuos = Util.cruzarIndividuos(individuosSelecionados, this.iMax, this.xMax); 
    }

    public void mutarIndividuo() {
        this.selecionado = new Random().nextInt(this.individuosSelecionados.size());
        this.mutado = Util.mutarIndividuo(this.novosIndividuos, this.iMax, this.xMax, this.selecionado);

        //atualizar individuo mutado na populaçao
        this.individuosSelecionados.remove(selecionado);
        this.individuosSelecionados.add(mutado);
        
   
        //adicono os novos individuos na população 
        for (Individuo in : this.individuosSelecionados) {
            this.populacao.addIndividuos(in);
        }
  
    }

    public Populacao getPopulacao() {
        return populacao;
    }
     
    
    
    
    
}
