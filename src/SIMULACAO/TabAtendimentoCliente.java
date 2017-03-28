
package SIMULACAO;

import java.util.ArrayList;
import java.util.Random;

public class TabAtendimentoCliente {

    private ArrayList<Cliente> clientes;
    private int totalTEspera;
    private int totalTAtendimento;
    
    
    /*Construtor padrao da classe
    TabAtendimentoCliente*/
    TabAtendimentoCliente(){
    
        totalTEspera = 0;
        totalTAtendimento = 0;
        inicializaClientes();
        setaTempoChegadaSistema();
        atendimento();
        geraEstatistica();
    }
    
    /*Metodo para permitir acesso
    a lista de clientes*/
    public ArrayList<Cliente> getClientes(){return clientes;}
    
    /*Metodo responsavel por
    inicializar os clientes
    da lista clientes, determinando,
    aleatoriamente, de 10 a 100, a
    quantidade de clientes*/
    private void inicializaClientes(){
        
        clientes = new ArrayList<>();
        
        Random aleatorio = new Random();
        
        int qntClientes = 10 + aleatorio.nextInt(90);/*determina uma quantidade de clientes de 10 a 90*/
        
        for(int i = 0; i < qntClientes; i++){
        
            clientes.add(new Cliente());
        }
        
    }
    
    /*Metodo responsavel por setar
    o tempo de chegada ao sistema
    de todos o clientes, esse tempo
    eh acumulativo*/
    private void setaTempoChegadaSistema(){
        
        Cliente clienteAtual;
        Cliente clienteAnterior;
        
        clienteAtual = clientes.get(0);/*pega o primeiro Cliente do sistema*/
        clienteAtual.setTChegadaSis(clienteAtual.getTCriacao());/*O tempo de chegada ao sistema, do primeiro Cliente, é o seu tempo de Criação*/
        
        clienteAnterior = clienteAtual; /*O tempo de chegada ao sistema, do próximo Cliente, é o seu tempo de criação mais o tempo de chegada ao sistema, do Cliente anterior*/
    
        for(int i = 1; i < clientes.size(); i++){
        
            clienteAtual = clientes.get(i);
            
            /*O tempo de chegada ao sistema, do próximo Cliente, é o seu tempo de criação mais o tempo de chegada ao sistema, do Cliente anterior*/
            clienteAtual.setTChegadaSis(clienteAtual.getTCriacao() + clienteAnterior.getTChegadaSis());
            
            clienteAnterior = clienteAtual;
        }
    }
    
    /*Metodo responsavel por setar
    os demais atributos restantes
    dos clientes*/
    private void atendimento(){
    
        Cliente clienteAtual;
        Cliente clienteAnterior;
        
        clienteAtual = clientes.get(0);/*O primeiro é tratado diferente dos outros clientes.*/
        clienteAtual.setTEspera(0);/*tempo de espera, do primeiro cliente, é 0, pois sendo o primeiro, o mesmo não precisou esperar*/
        clienteAtual.setTInAtendimento(clienteAtual.getTChegadaSis());/*Assim que ele entra no sistema já é atendido, por isso o tempo de chegada e entrada são os mesmos*/
        clienteAtual.setTSaida(clienteAtual.getTInAtendimento() + clienteAtual.getTAtendimento());/*O temo de saida é o tempo de entrada no atendimento mais o tempo de atendimento*/
    
        clienteAnterior = clienteAtual;
        
        for(int i = 1; i < clientes.size(); i++){
        
            clienteAtual = clientes.get(i);
            
            if(clienteAtual.getTChegadaSis() >= clienteAnterior.getTSaida()){/*se o tempo de chegada ao sistema, do cliente atual, for >= ao tempo de saida do anterior, o atual não espera para ser atendido*/
                
                clienteAtual.setTInAtendimento(clienteAtual.getTChegadaSis());
                clienteAtual.setTEspera(0);                
            }
            else{
            
                clienteAtual.setTInAtendimento(clienteAnterior.getTSaida());/*O tempo de entrada é o tempo de saida do cliente anterior*/
                clienteAtual.setTEspera(clienteAtual.getTInAtendimento() - clienteAtual.getTChegadaSis());/*O tempo inicio atendimento menos o tempo de chegada ao sistema */
            }
            clienteAtual.setTSaida(clienteAtual.getTInAtendimento() + clienteAtual.getTAtendimento());/*O temo de saida é o tempo de entrada no atendimento mais o tempo de atendimento*/
            clienteAnterior = clienteAtual;
            
        }
    }
    
    
    private void geraEstatistica(){
    
        totalTEspera = 0;
        totalTAtendimento = 0;
        int i = 0;
        Cliente c;
        while(i < clientes.size()){
        
            c = clientes.get(i);
            totalTEspera = totalTEspera + c.getTEspera();
            totalTAtendimento = totalTAtendimento + c.getTAtendimento();
            i++;
        
        }
    
    }
    
    /*Metodo responsavel por imprimir
    a tabela*/
    public void mostraTabela(){
        
        System.out.printf("\n###################################################################################\n"
                        +   "#                          TABELA PONTO DE VISTA CLIENTE                          #\n"
                        +   "###################################################################################\n"
                        +   "#ID   = Numero identificador                                                      #\n"
                        +   "#TCR  = Tempo criacao (cliente)                                                   #\n"
                        +   "#TCH  = Tempo de chegada ao sistema                                               #\n"
                        +   "#TINA = Tempo de entrada em atendimento                                           #\n"
                        +   "#TA   = Tempo de atendimento                                                      #\n"
                        +   "#TS   = Tempo de saida do sistema                                                 #\n"
                        +   "#TES  = Tempo de espera no sistema                                                #\n"
                        +   "###################################################################################");
    
        Cliente c;
        
        for(int i = 0; i < clientes.size(); i++){
        
            c = clientes.get(i);
            
            System.out.printf("\n# ID = %3d # TCR = %3d # TCH = %3d # TINA = %3d # TA = %3d # TS = %3d # TES = %3d #", c.getId(), c.getTCriacao(),c.getTChegadaSis(),c.getTInAtendimento(), c.getTAtendimento(), c.getTSaida(), c.getTEspera());
        }
        System.out.printf("\n###################################################################################\n");
    
        System.out.printf("Tempo total de espera em fila = %d\n"
                        + "Tempo medio de espera em fila = %f\n"
                        + "Tempo total de atendimento    = %d\n"
                        + "Tempo medio de atendimento    = %f\n\n", totalTEspera, (float)totalTEspera / clientes.size(), totalTAtendimento, (float)totalTAtendimento / clientes.size() );
    }
    
    
}
