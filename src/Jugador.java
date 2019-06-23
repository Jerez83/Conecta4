public class Jugador {

    private String alias;                         // NO NULO, NO MODIFICABLE, NO VACIO
    private Pieza pieza;                          // NO NULO

    public Jugador(String alias, Pieza pieza) {
        setAlias(alias) ;
        setPieza(pieza);
    }

    private void setAlias(String alias) {
        assert alias != null:"Error: el alias recibido no puede ser nulo";
        assert !alias.isEmpty() : "Error el alias no puede estar vacío";
        this.alias = alias;
    }

    public void setPieza(Pieza pieza) {
        assert pieza != null : "Error: la pieza recibida no puede se nula";
        this.pieza = pieza;
    }

    public String getAlias() {
        return alias;
    }

    public Pieza getPieza() {
        return pieza;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "alias='" + alias + '\'' +
                ", ficha=" + pieza +
                '}';
    }
}
