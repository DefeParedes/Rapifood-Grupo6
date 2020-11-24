package rapifood.modelo;

/**
 *
 * @author Fedep
 */
public class Mesa {
    private int id;
    private int max_comensales;
    private boolean estado;

    public Mesa(int id, int max_comensales, boolean estado) {
        this.id = id;
        this.max_comensales = max_comensales;
        this.estado = estado;
    }
    
    public Mesa() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setMax_comensales(int max_comensales) {
        this.max_comensales = max_comensales;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getMax_comensales() {
        return max_comensales;
    }

    public boolean isEstado() {
        return estado;
    }
}
