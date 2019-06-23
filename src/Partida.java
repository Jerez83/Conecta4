import java.util.Scanner;

public class Partida {
    public static final String FICHA = "\u25CF";

    private int turno;                     // Turno 1 ó 2
    private Tablero tablero;
    private Jugador jugador1;              // NO NULO, NO MODIFICABLE
    private Jugador jugador2;              // NO NULO, NO MODIFICABLE

    public Partida(String jugador1, String jugador2) {
        this.jugador1 = new Jugador(jugador1, new Pieza("X"));
        this.jugador2 = new Jugador(jugador2, new Pieza("O"));
        tablero = new Tablero(6, 7);
        setTurno(1);
    }

    private void setTurno(int turno) {
        assert turno == 1 || turno == 2 : String.format("Error: el turno (%d) debe ser 1 o 2", turno);
        this.turno = turno;
    }

    private void cambiarTurno() {
        setTurno(turno == 1 ? 2 : 1);
    }

    public void jugar() {
        System.out.println(jugador1.toString());
        System.out.println(jugador2.toString());
        do {

            int c;
            boolean correcto;

            System.out.println(tablero.toString());
            System.out.println();
            do {
                System.out.println("Es el turno de " + jugadorActual().getAlias() + " " + jugadorActual().getPieza());
                c = leerColumna();
                correcto = tablero.esColumna(c);
                if (!correcto)
                    System.out.printf("COLUMNA %s INCORRECTA\n", c);
                else
                tablero.agregarPieza(jugadorActual().getPieza(), c);
            } while (!correcto);
            cambiarTurno();

        } while (!esFinjuego());
        System.out.println(tablero.toString());
        cambiarTurno();
        if (esEmpate()) {
            System.out.println("LA PARTIDA HA FINALIZADO EN EMPATE");
        }
        System.out.println("HAS GANADO " + jugadorActual().getAlias() + " " + jugadorActual().getPieza());

    }

    private boolean esEmpate() {
        if (tablero.hayTablas())
            return true;
        return false;
    }

    private boolean esFinjuego() {
        boolean fin = false;

        if (tablero.hayLineaHorizontal() || tablero.hayLineaVertical() || tablero.hayDiagonalDerchaColumna() || tablero.hayDiagonalDerchaFila()
                || tablero.hayDiagonalIzquierdaColumna() || tablero.hayDiagonalIzquierdaFila())
            return true;

        return fin;
    }

    private int leerColumna() {

        System.out.printf("Introduzca la columna donde insertar la ficha (0-%d): ", tablero.getColumna() - 1);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }


    private Jugador jugadorActual() {
        return turno == 1 ? jugador1 : jugador2;
    }


}
