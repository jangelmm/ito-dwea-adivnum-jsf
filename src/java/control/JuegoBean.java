package control;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;

@Named("juego")
@SessionScoped
public class JuegoBean implements Serializable {

    private int valorInicial;
    private int valorFinal;
    private int intentos;
    private int numeroSecreto;
    private int intentoActual;
    private String mensaje;
    private boolean terminado;
    private boolean juegoIniciado; // Nueva variable de control de vista

    public JuegoBean() {}

    // Getters y Setters
    public int getValorInicial() { return valorInicial; }
    public void setValorInicial(int valorInicial) { this.valorInicial = valorInicial; }
    public int getValorFinal() { return valorFinal; }
    public void setValorFinal(int valorFinal) { this.valorFinal = valorFinal; }
    public int getIntentos() { return intentos; }
    public void setIntentos(int intentos) { this.intentos = intentos; }
    public int getIntentoActual() { return intentoActual; }
    public void setIntentoActual(int intentoActual) { this.intentoActual = intentoActual; }
    public String getMensaje() { return mensaje; }
    public boolean isTerminado() { return terminado; }
    public boolean isJuegoIniciado() { return juegoIniciado; }

    public String iniciarJuego() {
        if (valorInicial >= valorFinal || intentos <= 0) {
            this.mensaje = "Error: Rango o intentos inválidos.";
            this.juegoIniciado = false;
            return null; 
        }
        
        Random rand = new Random();
        this.numeroSecreto = rand.nextInt((valorFinal - valorInicial) + 1) + valorInicial;
        this.mensaje = "¡Juego iniciado! Adivina el número.";
        this.terminado = false;
        this.intentoActual = 0;
        this.juegoIniciado = true; // Cambia la vista al modo de juego
        
        return null; // Se queda en la misma página
    }

    public String evaluarIntento() {
        if (terminado) return null;

        this.intentos--;

        if (intentoActual == numeroSecreto) {
            this.mensaje = "¡Ganaste! El número era " + numeroSecreto + ".";
            this.terminado = true;
        } else if (this.intentos == 0) {
            this.mensaje = "Perdiste. El número secreto era " + numeroSecreto + ".";
            this.terminado = true;
        } else if (intentoActual < numeroSecreto) {
            this.mensaje = "El valor que diste es más pequeño. Te quedan " + intentos + " intentos.";
        } else {
            this.mensaje = "El valor es más grande. Te quedan " + intentos + " intentos.";
        }
        
        return null; 
    }

    public String reiniciar() {
        this.juegoIniciado = false; // Devuelve la vista al modo configuración
        this.mensaje = null;
        this.valorInicial = 0;
        this.valorFinal = 0;
        this.intentos = 0;
        return null; 
    }
}