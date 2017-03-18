
package SIMULACAO;

/**
 *
 * @author imortal77
 */
public class Evento {
    
    private int tEvento;
    private Cliente cliente;
    private char tipoEvento;
    private char estadoAtendente;
    private int tOcupacao;
    private int qntNoSistema;
    
    /*Construtor da classe Evento
    utilizado quando o evento
    criado nao possui um cliente
    exe: o evento inicial*/
    Evento (int tempo, char tipo, char eAtendente, int teOcupacao, int qntSistema ){
        tEvento = tempo;
        tipoEvento = tipo;
        estadoAtendente  = eAtendente;
        tOcupacao = teOcupacao;
        qntNoSistema = qntSistema;
        
    }
    
    /*Contrutor da classe Evento
    utilizado quando o evento
    criado possui um cliente*/
    Evento (Cliente cliente,int tempo, char tipo, char eAtendente, int teOcupacao, int qntSistema  ){
        this.cliente = cliente;
        tEvento = tempo;
        tipoEvento = tipo;
        estadoAtendente  = eAtendente;
        tOcupacao = teOcupacao;
        qntNoSistema = qntSistema;
    }
    
    /*Metodo para permitir acesso
    ao atributo tEvento (tempo que
    o evento foi criado)*/
    public int getTEvento(){return tEvento;}
    
    /*Metodo para permitir acesso
    ao atributo cliente*/
    public Cliente getCliente(){return cliente;}
    
    /*Metodo para permitir acesso
    ao atributo tipoEvento (o tipo
    do evento)*/
    public char getTipoEvento(){return tipoEvento;}
    
    /*Metodo para permitir acesso
    ao atributo estadoAtendente 
    (o estado que atendente estava
    na criacao do evento)*/
    public char getEstadoAtendente(){return estadoAtendente;}
    
    /*Metodo para permitir acesso
    ao atributo tOcupacao (tempo
    de ocupacao do sistema)*/
    public int getTOcupacao(){return tOcupacao;}
    
    /*Metodo para permitir acesso
    ao atributo qntNoSistema
    (quantidade de clientes no
    sistema)*/
    public int getQntNoSistema(){return qntNoSistema;}
    
}
