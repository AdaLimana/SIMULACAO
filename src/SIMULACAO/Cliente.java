
package SIMULACAO;

import java.util.Random;

/*Implementacao da classe cliente*/

public class Cliente {
    
    private static int qntClientes = 0;/*Variável estática para contar o número de objetos instanciados */
    private int id;
    private int tCriacao;
    private int tChegadaSis;
    private int tEspera;
    private int tInAtendimento;
    private int tAtendimento;
    private int tSaida;
    
    
    /*Construtor padrao da classe cliente*/
    Cliente (){
        
        id = qntClientes;/*define o id cliente conforme a quantidade de objetos já instanciados*/
        qntClientes++;
        
        geraTCriacao();
        geraTAtendimento();
        
    }
    
    /*Metodo para permitir o acesso 
    do valor referente ao atributo id
    (identificador do cliente)*/
    public int getId(){return id;}
    
    /*Metodo que define um valor aleatorio
    de 1 a 10 para o atributo tCriacao
    (o tempo de criacao do cliente)*/
    public final void geraTCriacao(){
        
        Random aleatorio = new Random();/*objeto para gerar numeros aleatorios*/
        
        tCriacao =  1 + aleatorio.nextInt(10) ;/*numero de aleatórios  */
    }
    
    /*Metodo para permitir o acesso 
    ao valor do atributo tCriacao
    (o tempo de criacao do cliente)*/
    public int getTCriacao(){return tCriacao;}
    
    /*Metodo para setar um valor ao
    atributo tChegadaSis (tempo de
    chegada ao sistema)*/
    public void setTChegadaSis(int TCS){
        tChegadaSis = TCS;
    }
    
    /*Metodo para permitir o acesso
    ao atributo tChegadaSis (tempo de
    chegada ao sistema)*/
    public int getTChegadaSis(){return tChegadaSis;}
    
    /*Metodo para setar um valor ao
    atributo tEspera (tempo de espera)*/
    public void setTEspera(int TE){
     tEspera = TE;
    }
    
    /*Metodo para permitir acesso ao
    atributo tEspera (tempo de espera)*/
    public int getTEspera(){return tEspera;}
    
    /*Metodo para setar um valor ao
    atributo tInAtendimento (tempo
    de entrada em atendimento)*/
    public void setTInAtendimento(int TIA){
        tInAtendimento = TIA;
    }
    
    /*Metodo para permitir acesso ao
    atributo tInAtendimento (tempo
    de entrada em atendimento)*/
    public int getTInAtendimento(){return tInAtendimento;}
    
    /*Metodo que define um valor
    aleatorio de 5 15 para o
    atributo tAtendimento
    (tempo de atendimento)*/
    public final void geraTAtendimento(){
        
        Random aleatorio = new Random();
        
        tAtendimento = 5 + aleatorio.nextInt(11);/*numero aleatório de 5 a 15*/
    
    }
    
    /*Metodo para permitir acesso
    ao atributo tAtendimento
    (tempo de atendimento)*/
    public int getTAtendimento(){return tAtendimento;}
    
    /*Metodo para setar um valor
    ao atributo tSaida (tempo de
    saida do sistema)*/
    public void setTSaida(int TS){
        tSaida = TS;
    }
    
    /*Metodo para permitir acesso
    ao atributo tSaida (tempo de
    saida do sistema)*/
    public int getTSaida(){return tSaida;}
    
}
