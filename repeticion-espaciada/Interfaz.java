import java.util.*;

public class Interfaz {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarMenu(Mazo mazo) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        String banner = "                         __  _                     __                __  \n" +
                "  ____ ___  _____  _____/ /_(_)___  ____  _____   / /_  ____ _____  / /__\n" +
                " / __ `/ / / / _ \\/ ___/ __/ / __ \\/ __ \\/ ___/  / __ \\/ __ `/ __ \\/ //_/\n" +
                "/ /_/ / /_/ /  __(__  ) /_/ / /_/ / / / (__  )  / /_/ / /_/ / / / / ,<   \n" +
                "\\__, /\\__,_/\\___/____/\\__/_/\\____/_/ /_/____/  /_.___/\\__,_/_/ /_/_/|_|  \n" +
                "  /_/                                                                    \n";
        String bienvenida = "Bienvenido!\n" +
                "question bank te permite practicar preguntas usando repetición espaciada";
        System.out.print(banner + "\n\n" + bienvenida + "\n\n");
        while (true) {
            int tarjetasPaPracticar = mazo.tarjetasDisponiblesParaPracticar();
            if (tarjetasPaPracticar == 0) {
                System.out.println("No tiene tarjetas para practicar\nPresione enter para volver a revisar ");
                scanner.nextLine();
            } else {
                System.out.print("Tiene " + tarjetasPaPracticar
                        + " tarjetas para practicar\nPresione enter para empezar a practicar ");
                scanner.nextLine();

                practicarMazo(mazo);
                clearScreen();
            }
        }

    }

    public static void practicarMazo(Mazo mazo) {
        System.out.println("\n\n");
        Scanner scanner = new Scanner(System.in);
        String instrucciones = "Intente recordar las respuestas de las preguntas e ingrese un número dependiendo de la dificultad de la pregunta\n"
                +
                "Ingrese 1 (otra vez) si no recuerda la respuesta.\n" +
                "Ingrese 2 (normal) si logra recordar la respuesta.\n" +
                "Ingrese 3 (fácil) si fue muy fácil recordar la respuesta.\n";
        while (mazo.hayTarjetasParaPracticar()) {
            clearScreen();
            Tarjeta tarjetaPaPracticar = mazo.sacarTarjeta();
            System.out.print(instrucciones + "\n" + tarjetaPaPracticar.pregunta + "\n\n\n");
            System.out.print("Presione enter para mostrar la respuesta" + "\n\n\n");
            scanner.nextLine();
            clearScreen();
            System.out.print(instrucciones + "\n" + tarjetaPaPracticar.pregunta + "\n\n\n");
            System.out.print(tarjetaPaPracticar.respuesta + "\n\n");
            System.out.print("Ingrese el número de dificultad de la pregunta: ");
            boolean resepuestaValida = false;
            while (!resepuestaValida) {
                String respuesta = scanner.nextLine();
                switch (respuesta) {
                    case "1": {
                        tarjetaPaPracticar.otraVez();
                        mazo.añadirTarjeta(tarjetaPaPracticar);
                        resepuestaValida = true;
                        break;
                    }
                    case "2": {
                        tarjetaPaPracticar.normal();
                        mazo.añadirTarjeta(tarjetaPaPracticar);
                        resepuestaValida = true;
                        break;
                    }
                    case "3": {
                        tarjetaPaPracticar.facil();
                        mazo.añadirTarjeta(tarjetaPaPracticar);
                        resepuestaValida = true;
                        break;
                    }
                    default: {
                        System.out.println(respuesta
                                + " no es una dificultad válida\nIngrese nuevamente la dificultad de la pregunta: ");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // cargar mazo del json

        // practicar mazo
    }
}