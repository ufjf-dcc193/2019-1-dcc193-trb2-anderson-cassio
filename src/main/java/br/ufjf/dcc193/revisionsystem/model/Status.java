
package br.ufjf.dcc193.revisionsystem.model;

/**
 * Status
 */
public enum Status {    
    A_FAZER(0), AVALIADO(1), IMPEDIDO(2), VALIDADO(3), INVALIDADO(4);
     
    private final int valor;
    Status(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}