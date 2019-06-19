
package br.ufjf.dcc193.revisionsystem.model;

/**
 * Status
 */
public enum Status {
    A_FAZER(0), AVALIADO(1), IMPEDIDO(2), VALIDADO(3), INVALIDADO(4);

    private final int valor;

    Status(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }

    public static Status getStatusByValue(int i){
        switch (i){
            case 0: 
                return A_FAZER;
            case 1: 
                return AVALIADO;
            case 2: 
                return IMPEDIDO;
            case 3: 
                return VALIDADO;
            case 4: 
                return INVALIDADO;
        }
        return A_FAZER;
    }

}