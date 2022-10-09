import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Tarjeta implements Comparable<Tarjeta> {

    /* La clase tarjeta permite almacenar una pregunta y una respuesta a ser
       memorizada. */
    String pregunta;
    String respuesta;
    // El momento del repaso se almacenada en la variable siguienteFechaDeRepaso.
    Date siguienteFechaDeRepaso;
    /* Dependiendo de la retroalmentación del usuario (otra vez, normal o fácil) la
       variable fechaDeRepaso es modificada */
    private long otraVezIntervalo, normalIntervalo, intervaloGraduado;
    private double factorDeEspaciadoNormal, factorDeEspaciadoFacil, factorDeEspaciadoMinimo;
    private LinkedList<String> retroalimentacionDelUsuario;
    private boolean estaGraduada;

    /**
     * Al crear una nueva tarjeta, solo se requiere
     * 
     * @param pregunta  - el String que va a ser la pregunta de la tarjeta
     * @param respuesta - el String que va a ser la respuesta de la tarjeta
     */
    public Tarjeta(String pregunta, String respuesta) {
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        // Se asigna el tiempo actual como siguiente fecha de repaso
        this.siguienteFechaDeRepaso = new Date(System.currentTimeMillis());

        // Valores por defecto de intervalos y factores de espaciados
        this.otraVezIntervalo = 60000; // 1 minuto == 60000ms
        this.normalIntervalo = 600000; // 10 minutos == 60000ms
        this.intervaloGraduado = 86470000; // 1 día == 8.64e+7ms
        this.factorDeEspaciadoNormal = 2.5;
        this.factorDeEspaciadoFacil = 3;
        this.factorDeEspaciadoMinimo = 1.3;
        this.estaGraduada = false;
        this.retroalimentacionDelUsuario = new LinkedList<String>();
    }

    /**
     * Defnimos el orden entre tarjetas: una tarjeta A que una tarjeta B cuando
     * la siguiente fecha de repaso de la tarjeta A es cronológicamente anterior
     * a la siguiente fecha de repaso de la tarjeta B
     */
    @Override
    public int compareTo(Tarjeta otraTarjeta) {
        long thisFechaDeRepasoEnMilisegundos = this.getSiguienteFechaDeRepaso().getTime();
        long otraTarjetaFechaDeRepasoEnMilisegundos = otraTarjeta.getSiguienteFechaDeRepaso()
                .getTime();
        if (thisFechaDeRepasoEnMilisegundos > otraTarjetaFechaDeRepasoEnMilisegundos) {
            return 1;
        } else if (thisFechaDeRepasoEnMilisegundos < otraTarjetaFechaDeRepasoEnMilisegundos) {
            return -1;
        } else {
            return 0;
        }
    }

    public void setSiguienteFechaDeRepaso(Date nuevaFechaDeRepaso) {
        this.siguienteFechaDeRepaso = nuevaFechaDeRepaso;
    }

    public Date getSiguienteFechaDeRepaso() {
        return this.siguienteFechaDeRepaso;
    }

    public boolean equals(Tarjeta otraTarjeta) {
        return this.pregunta == otraTarjeta.pregunta &&
                this.respuesta == otraTarjeta.respuesta;
    }

    public void otraVez() {
        long siguienteFechaEnMs = this.siguienteFechaDeRepaso.getTime();
        if (this.estaGraduada) {
            this.estaGraduada = false;
            this.factorDeEspaciadoNormal = Math.max(this.factorDeEspaciadoMinimo, this.factorDeEspaciadoNormal - 0.2);
        }
        this.siguienteFechaDeRepaso = new Date(siguienteFechaEnMs + this.otraVezIntervalo);
        this.retroalimentacionDelUsuario.push("otraVez");
    }

    public void normal() {
        long siguienteFechaEnMs = this.siguienteFechaDeRepaso.getTime();
        if (this.estaGraduada) {
            long tiempoMultiplicado = siguienteFechaEnMs
                    + Math.round(this.normalIntervalo * this.factorDeEspaciadoNormal);
            this.siguienteFechaDeRepaso = new Date(tiempoMultiplicado);
        } else {
            if (this.retroalimentacionDelUsuario.head != null
                    && (this.retroalimentacionDelUsuario.head.data.equals("normal")
                            || this.retroalimentacionDelUsuario.head.data.equals("facil"))) {
                this.estaGraduada = true;
                this.siguienteFechaDeRepaso = new Date(siguienteFechaEnMs + this.intervaloGraduado);
            } else {
                this.siguienteFechaDeRepaso = new Date(siguienteFechaEnMs + this.normalIntervalo);
            }
        }
        this.retroalimentacionDelUsuario.push("normal");
    }

    public void facil() {
        long siguienteFechaEnMs = this.siguienteFechaDeRepaso.getTime();
        if (this.estaGraduada) {
            long tiempoMultiplicado = siguienteFechaEnMs
                    + Math.round(this.normalIntervalo * this.factorDeEspaciadoFacil);
            this.siguienteFechaDeRepaso = new Date(tiempoMultiplicado);
        } else {
            if (this.retroalimentacionDelUsuario.head != null
                    && (this.retroalimentacionDelUsuario.head.data.equals("normal")
                            || this.retroalimentacionDelUsuario.head.data.equals("facil"))) {
                this.estaGraduada = true;
                this.siguienteFechaDeRepaso = new Date(siguienteFechaEnMs + this.intervaloGraduado);
            } else {
                this.siguienteFechaDeRepaso = new Date(siguienteFechaEnMs + this.normalIntervalo);
            }
        }
        this.retroalimentacionDelUsuario.push("facil");
    }

    public String toString() {
        return this.pregunta;
    }

    public static Tarjeta[] tarjetasDePrueba() {
        Tarjeta[] array = {
                new Tarjeta("¿Cuántos litros de sangre tiene una persona adulta?", "Tiene entre 4 y 6 litros."),
                new Tarjeta("¿Quién es el autor de la frase -Pienso, luego existo-?", "Descartes"),
                new Tarjeta("¿Cuál es el país más grande y el más pequeño del mundo?", "Rusia y Vaticano"),
                new Tarjeta("¿Cuál es el libro más vendido en el mundo después de la Biblia?",
                        "Don Quijote de la Mancha"),
                new Tarjeta("¿Cuáles son los representantes más destacados de la literatura renacentista?",
                        "Miguel de Cervantes, William Shakespeare, Luis de Camões")
        };
        return array;
    }
}
