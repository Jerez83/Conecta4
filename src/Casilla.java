import java.util.Objects;

public class Casilla {
    private Pieza pieza;


    public Casilla() {
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casilla casilla = (Casilla) o;
        if (this.estaVacia() && casilla.estaVacia()) return false;
        if (this.estaVacia() && !casilla.estaVacia()) return false;
        if (!this.estaVacia() && casilla.estaVacia()) return false;

        return pieza.equals(casilla.pieza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieza);
    }

    @Override
    public String toString() {
        return pieza==null ? "| |":"|"+pieza+"|";
    }

    public boolean estaVacia() {
        return pieza==null;
    }
}
