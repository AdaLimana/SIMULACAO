
package SIMULACAO;

import java.util.ArrayList;

/**
 *
 * @author imortal77
 */
public class TabAtendimentoAtendente {
    
    private ArrayList<Evento> eventos;
    private char estado;
    private int tOcupacao;
    private int qntNoSistema;
    
    /*Construtor padra da classe
    TabAtendimentoAtendente*/
    TabAtendimentoAtendente(ArrayList<Cliente> clientes){
        
        eventos = new ArrayList<>();
        estado = 'L';
        tOcupacao = 0;
        qntNoSistema = 0;
        
        eventos.add(new Evento(0, 'I', estado, tOcupacao, qntNoSistema));
        criaEvento(clientes);
    }
    
    /*Metodo para permitir o acesso
    ao atributo estado*/
    public char getEstado(){return estado;}
    
    /*Metodo para permitir o acesso
    ao atributo tOcupacao (tempo de
    ocupacao do sistema)*/
    public int getTOcupacao(){return tOcupacao;}
    
    /*Metodo para permitir o acesso
    ao atributo qntNoSistema
    (quantidade de clientes no sistema)*/
    public int getQntNoSistema(){return qntNoSistema;}
    
    /*Metoto para permitir o acesso
    a lista de eventos*/
    public ArrayList<Evento> getEventos(){return eventos;}
    
    
    /*Metodo responsavel por criar
    os eventos*/
    private void criaEvento(ArrayList<Cliente> clientes){
    
        Cliente clienteAtual;
        Cliente clienteEmAtendimento;
        ArrayList<Cliente> clienteEmEspera = new ArrayList();
        Evento ev;
        
        int i = 0;
        
        clienteAtual = clientes.get(i);
        i++;
        qntNoSistema++;
        ev = eventoChegaEAtendido(clienteAtual);
        eventos.add(ev);
        clienteEmAtendimento = clienteAtual;
        
        while((i < clientes.size())||estado == 'O'){
          
            if(estado == 'L'){/*Se está livre*/
            
                        clienteAtual = clientes.get(i);
                        i++;
                        qntNoSistema++;
                        ev = eventoChegaEAtendido(clienteAtual);
                        eventos.add(ev);
                        clienteEmAtendimento = clienteAtual;
                   
            }
            
            else if(i < clientes.size()){/*caso esteja ocupado e possua clientes que nao chegaram ao sistema*/
            
                clienteAtual = clientes.get(i);/*pega o proximo cliente da lista clientes*/
                i++;
                /*compara qual evento acontece primeiro, se a saida do cliente que está sendo atendido*/
                /*ou a chegada do novo cliente, para esse cálculo é levado em consideração o tempo de */
                /*chegada ao sistema do novo cliente, e o tempo de saida do processo que está executando*/
                
                if(clienteAtual.getTChegadaSis() < clienteEmAtendimento.getTSaida()){
                
                    clienteEmEspera.add(clienteAtual);/*coloca o cliente na lista de espera*/
                    qntNoSistema++; /*Aumenta em um quantidade de clientes em espera, justamente por ele mesmo entrar em espera*/
                    ev = eventoChegaEEspera(clienteAtual);
                    eventos.add(ev);
                
                }
                else{
                    
                    if(clienteEmEspera.isEmpty()){/*caso nao tenha ninguem na lista de espera*/
                        estado = 'L';/*o atendente fica livre*/
                        qntNoSistema--;/*pois um saira do sistema*/
                        ev = eventoSaiAtendimento(clienteEmAtendimento);/*tira o cliente do atendimento*/
                        eventos.add(ev);
                      
                        
                    }
                    else{
                        qntNoSistema--; /*pois um saira do sistema*/
                        ev = eventoSaiAtendimento(clienteEmAtendimento);/*tira o cliente do atendimento*/
                        eventos.add(ev);
                        clienteEmAtendimento = clienteEmEspera.remove(0); 
                        tOcupacao = tOcupacao + clienteEmAtendimento.getTAtendimento();/*o tempo de ocupação recebe o o tempo já acumulado mais o tempo de atendimento do cliente*/
                    }
                    
                    i--; /*pois o cliente que estava em espera, e entrou em atendimento agora, pode ter*/
                         /* o tempo de saida menor que o tempo de entrada do clienteAtual*/
                    
                }
            }
            else{
                qntNoSistema--;
                if(clienteEmEspera.isEmpty()){
                    estado = 'L';
                    ev = eventoSaiAtendimento(clienteEmAtendimento);/*tira o cliente do atendimento*/
                    eventos.add(ev);
                }
                else{
                    ev = eventoSaiAtendimento(clienteEmAtendimento);/*tira o cliente do atendimento*/
                    eventos.add(ev);
                    clienteEmAtendimento = clienteEmEspera.remove(0);/*remove o primeiro da lista e armazena em clienteAtual*/
                    tOcupacao = tOcupacao + clienteEmAtendimento.getTAtendimento();/*o tempo de ocupação recebe o o tempo já acumulado mais o tempo de atendimento do cliente*/
                }
                
            }
           
        }
    }
    
    /*Metodo responsavel por criar
    os eventos que quando o cliente
    chega jah eh atendido*/
    private Evento eventoChegaEAtendido(Cliente c){

        Evento ev;
        int tempo;
        char tipoEv;
        
        tempo = c.getTChegadaSis();/*para envento chegada, o tempo é o mesmo tempo da entrada do cliente no sistema*/
        tipoEv = 'C';/*tipo de evento chegada (C)*/
        estado = 'O'; /*Coloca como ocupado*/
        
        /*tempo de Ocupacao (0), pois passa o tempo de ocupação só quando o atendente fica em estado livre*/
        /*no evento (chega e atendido), ninguem está esperando na fila, entao passa (0) para qntEmEspera*/
        ev = new Evento(c, tempo, tipoEv, estado, 0 , qntNoSistema);
        
        tOcupacao = tOcupacao + c.getTAtendimento();/*o tempo de ocupação recebe o o tempo já acumulado mais o tempo de atendimento do cliente*/
        return ev;
    }
    
    /*Metodo responsavel por criar
    os eventos que quando o cliente 
    chega vai para a fila de espera*/
    private Evento eventoChegaEEspera(Cliente c){
        
        Evento ev;
        
        int tempo;
        char tipoEv;
        
        tempo = c.getTChegadaSis();/*para envento chegada, o tempo é o mesmo tempo da entrada do cliente no sistema*/
        tipoEv = 'C';/*tipo de evento chegada (C)*/
        
        ev = new Evento(c, tempo, tipoEv, estado, 0 , qntNoSistema);
        
        return ev;
    }
    
    /*Metodo responsavel por criar
    os eventos dos clientes que saem
    do atendimento e consequentemente
    do sistema*/
    private Evento eventoSaiAtendimento(Cliente c){
    
        Evento ev;
        int tempo;
        char tipoEv;
        
        tempo = c.getTSaida();/*O tempo do evento é o tempo de saida do cliente*/ 
        tipoEv = 'S'; /*tipo de evento saida (S)*/
        
        if(estado == 'L'){/*se o atendente ficar livre o (tempo de ocupacao) entao eh mostrado no evento*/
                          /*e o mesmo eh zerado para ser acumulado novamente*/
            ev = new Evento(c, tempo, tipoEv, estado, tOcupacao , qntNoSistema);
            tOcupacao = 0;
        }
        else{/*se o atendente nao ficar livre, o tempo de ocupacao nao e mostrado no evento, e o mesmo*/
             /* nao eh zerado*/
            ev = new Evento(c, tempo, tipoEv, estado, 0 , qntNoSistema);
        }
        
        return ev;
   }
    
    /*Metodo responsavel por imprimir
    a tabela*/
    public void mostraTabela(){
        
        Evento e;
        Cliente c;
        System.out.printf("\n################################################################\n"
                        +   "#               TABELA PONTO DE VISTA ATENDENTE                #\n"
                        +   "################################################################\n"
                        +   "#TE  = Tempo de criacao do evento                              #\n"
                        +   "#ID  = Numero identificador                                    #\n"
                        +   "#TIE = Tipo de evento: Inicio(I), Chegada(C), Saida(S)         #\n"
                        +   "#EA  = Estado atendente: Livre(L), Ocupado(O)                  #\n"
                        +   "#TO  = Tempo de Ocupacao                                       #\n"
                        +   "#QS  = Quantidade de clientes no sistema                       #\n"
                        +   "################################################################");

        
        /*O primeiro evento nao possui cliente*/
        e = eventos.get(0);
        System.out.printf("\n# TE = %3d # ID = %3c # TIE = %c # EA = %c # TO = %3d # QS = %3d #",e.getTEvento(),'-', e.getTipoEvento(), e.getEstadoAtendente(), e.getTOcupacao(), e.getQntNoSistema());
        
        for(int i = 1 ; i < eventos.size(); i++){
            
            e = eventos.get(i);
            c = e.getCliente();
            System.out.printf("\n# TE = %3d # ID = %3d # TIE = %c # EA = %c # TO = %3d # QS = %3d #",e.getTEvento(), c.getId(), e.getTipoEvento(), e.getEstadoAtendente(), e.getTOcupacao(), e.getQntNoSistema());
        }
        
        System.out.printf("\n################################################################\n");
    } 

}
