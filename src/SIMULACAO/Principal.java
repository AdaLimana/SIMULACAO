
package SIMULACAO;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        
        
        TabAtendimentoCliente tab = new TabAtendimentoCliente();/*cria um objeto do tipo TabAtendimentoCliente*/
        
        ArrayList< Cliente > clientes = tab.getClientes();  /*recebe a referencia do objeto do tipo ArrayList<Cliente>*/
                                                            /*que eh um atributo do objeto referenciado por tab */
        
        TabAtendimentoAtendente tabP = new TabAtendimentoAtendente(clientes);   /*cria um objeto do tipo TabAtendimentoAtendente*/
                                                                                /*passando a referencia do objeto do tipo*/
                                                                                /*ArrayList<Cliente>, que eh um atributo do objeto*/
                                                                                /*referenciado por tab, como parametro*/
        tab.mostraTabela();
      
        tabP.mostraTabela();
        
    }  
}
