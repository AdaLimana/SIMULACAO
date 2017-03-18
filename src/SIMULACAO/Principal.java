
package SIMULACAO;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        
        
        TabAtendimentoCliente tab;
        tab = new TabAtendimentoCliente();
        
        ArrayList< Cliente > clientes;
        Cliente c;
        
        TabAtendimentoProfessor tabP;
        ArrayList< Evento > eventos;
        Evento e;
        
        clientes = tab.getClientes();
        
        tabP = new TabAtendimentoProfessor(clientes);
        
        tab.mostraTabela();
      
        tabP.mostraTabela();
        
    }  
}
