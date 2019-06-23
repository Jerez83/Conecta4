import java.util.Arrays;
import java.util.function.ToDoubleBiFunction;

public class Tablero {
    private Casilla[][] casillas;
    private int fila;
    private int columna;


    public Tablero(int filas, int columnas) {

        casillas = new Casilla[filas][columnas];
        crearCasillas();
        fila = filas;
        columna = columnas;

    }

    private void crearCasillas() {

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }


    public void agregarPieza(Pieza p, int colum) {
        assert p != null : "Error: la pieza no puede ser null";
        assert colum <= this.columna - 1
                : String.format("Error: La columna %d está fuera de rango, rango valido(0-%d)", colum, columna - 1);
        assert casillas[0][colum].estaVacia()
                : String.format("Error: No se pueden añadir más fichas en la columna %d", colum);

        for (int i = fila - 1; i >= 0; i--) {
            if (casillas[i][colum].estaVacia()) {
                casillas[i][colum].setPieza(p);
                break;
            }
        }
    }

    public boolean esColumna(int n) {
        return n >= 0 && n <= 6;
    }

    public int getColumna() {
        return columna;
    }

    /**
     * Comprueba si hay 4 fichas iguales correlativas en la fila pasada por parametro
     *
     * @param fila
     * @return true si hay 4 iguales
     */
    private boolean lineaFila(int fila) {


        for (int i = 0; i < 4; i++) {
            int contador = 0;
            for (int j = 1; j < 4; j++) {

                if (casillas[fila][i].equals(casillas[fila][i + j]))
                    contador++;

                if (contador == 3)
                    return true;
            }

        }
        return false;
    }

    /**
     * Comprueba si hay 4 fichas iguales correlativas en la columna pasada por parametro
     *
     * @param columna
     * @return true si hay 4 iguales
     */
    private boolean lineaColumna(int columna) {


        for (int i = 0; i < 3; i++) {
            int contador = 0;
            for (int j = 1; j < 4; j++) {

                if (casillas[i][columna].equals(casillas[i + j][columna]))
                    contador++;

                if (contador == 3)
                    return true;
            }

        }
        return false;
    }

    public boolean hayLineaHorizontal() {
        for (int i = fila - 1; i >= 0; i--) {
            if (lineaFila(i))
                return true;
        }
        return false;
    }

    public boolean hayLineaVertical() {
        for (int i = columna - 1; i >= 0; i--) {
            if (lineaColumna(i))
                return true;
        }
        return false;
    }

    public boolean hayDiagonalDerchaColumna() {
        for (int i = 0; i < 4; i++) {
            if (diagonalesColumnasDerecha(i))
                return true;

        }
        return false;
    }

    public boolean hayDiagonalDerchaFila() {
        for (int i = 4; i >= 3; i--) {
            if (diagonalesFilasDerecha(i))
                return true;

        }
        return false;
    }

    public boolean hayDiagonalIzquierdaColumna() {
        for (int i = 6; i >= 3; i--) {
            if (diagonalesColumnaIzquierda(i))
                return true;
        }
        return false;
    }

    public boolean hayDiagonalIzquierdaFila() {
        for (int i = 3; i < 5; i++) {
            if (diagonalesFilasIzquierda(i))
                return true;
        }
        return false;
    }

    public boolean hayTablas() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].estaVacia())
                    return false;
            }
        }
        return true;
    }


    /**
     * Metodo que comprueba las diagonales hacia la derecha segun la columna pasada por parametro
     *
     * @param columna será de [0-3] ya que a partir de la 3 no caben 4 fichas en diagonal
     * @return true si hay 4 fichas iguales en diagonal correlativas
     */

    private boolean diagonalesColumnasDerecha(int columna) {

        for (int i = 0; i < 3; i++) {
            int contador = 0;
            for (int j = 5, k = columna; j >= 3; j--, k++) {

                if (i + k + 1 > 6)
                    break;
                if (casillas[j - i][i + k].equals(casillas[j - i - 1][i + k + 1]))
                    contador++;
                if (contador == 3)
                    return true;

            }

        }
        return false;
    }

    /**
     * Metodo que comprueba las digonales hacia la derecha segun la fila
     *
     * @param fila irá de [3-5] pero para optimizar un poco más de [3-4] ya que la fila 5 ya se está comprobando en diagonalesColumnasDerecha()
     * @return true si hay 4 fichas iguales y correlativas en diagonal
     */
    private boolean diagonalesFilasDerecha(int fila) {

        for (int i = 0; i < 3; i++) {
            int contador = 0;
            for (int j = fila, k = 0; j >= 3; j--, k++) {

                if (i + k + 1 > 6)
                    break;
                if (casillas[j - i][i + k].equals(casillas[j - i - 1][i + k + 1]))
                    contador++;
                if (contador == 3)
                    return true;
            }
        }
        return false;
    }

    /**
     * Metodo que comprueba las diagonales de izquierda a derecha empezando por la columna pasada por parametro
     *
     * @param columna es la columna a comprobar, en este tablero se comprobará [3-6]
     * @return true si encuentra cuatro fichas iguales de forma correlativa
     */

    private boolean diagonalesColumnaIzquierda(int columna) {
        int aux = 3;

        if (columna == 4)
            aux = 2;
        else if (columna == 3)
            aux = 1;

        for (int i = 0; i < aux; i++) {
            int contador = 0;
            for (int j = 5, k = columna; j >= 3; j--, k--) {

                if (casillas[j - i][k - i].equals(casillas[j - i - 1][k - i - 1]))
                    contador++;
                if (contador == 3)
                    return true;
            }
        }
        return false;
    }

    /**
     * Metodo que comprueba las diagonales de izquierda a derecha empezando por la fila pasada por parametro
     *
     * @param fila es la fila a comprobar, en este tablero se comprobará [3-4]
     * @return true si encuentra cuatro fichas iguales de forma correlativa
     */

    private boolean diagonalesFilasIzquierda(int fila) {

        for (int i = 0; i < 2; i++) {
            int contador = 0;
            for (int j = fila, k = 6; j >= 2; j--, k--) {
                if (j - i - 1 < 0)
                    break;
                if (casillas[j - i][k - i].equals(casillas[j - i - 1][k - i - 1]))
                    contador++;
                if (contador == 3)
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        String resultado = "";
        for (int i = 0; i < casillas.length; i++) {
            resultado += "\n";
            for (int j = 0; j < casillas[i].length; j++) {
                resultado += casillas[i][j];
            }

        }

        return resultado;
    }
}
