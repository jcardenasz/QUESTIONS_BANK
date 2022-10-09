class Mazo {
    public SortedLinkedList<Tarjeta> lista = new SortedLinkedList<Tarjeta>();

    public Mazo() {

    }

    public Mazo(Tarjeta[] tarjetas) {
        for (int i = 0; i < tarjetas.length; i++) {
            this.añadirTarjeta(tarjetas[i]);
        }
    }

    public void añadirTarjeta(Tarjeta tarjeta) {
        lista.insert(tarjeta);
    }

    public int tarjetasDisponiblesParaPracticar() {
        int resultado = 0;
        Node<Tarjeta> nodoTarjetaActual = lista.head;
        while (nodoTarjetaActual != null
                && (nodoTarjetaActual.data.getSiguienteFechaDeRepaso().getTime() <= System.currentTimeMillis())) {
            resultado++;
            nodoTarjetaActual = nodoTarjetaActual.next;
        }
        return resultado;
    }

    public boolean hayTarjetasParaPracticar() {
        return (lista.head != null)
                && lista.head.data.getSiguienteFechaDeRepaso().getTime() <= System.currentTimeMillis();
    }

    public Tarjeta sacarTarjeta() {
        return lista.pop();
    }
}