import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del jugador 1: ");
        String j1 = sc.next();

        System.out.println("Introduzca el nombre del jugador 2: ");
        String j2 = sc.next();

        Partida p1 = new Partida(j1, j2);
        p1.jugar();

    }



}
