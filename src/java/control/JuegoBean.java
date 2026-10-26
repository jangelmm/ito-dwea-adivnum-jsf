/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
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

    public JuegoBean() {}

    // Getters y Setters obligatorios para JSF
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

    public String iniciarJuego() {
        if (valorInicial >= valorFinal || intentos <= 0) {
            this.mensaje = "Rango o intentos inválidos.";
            return null; // Se queda en la misma página mostrando el error
        }
        
        Random rand = new Random();
        // Genera el número aleatorio dentro del rango proporcionado
        this.numeroSecreto = rand.nextInt((valorFinal - valorInicial) + 1) + valorInicial;
        this.mensaje = "¡Juego iniciado! Adivina el número.";
        this.terminado = false;
        this.intentoActual = 0; // Limpia la caja de texto
        
        return "juego"; // Navega a juego.xhtml
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
        
        return null; // Recarga la misma página para seguir jugando
    }

    public String reiniciar() {
        return "index"; // Regresa a la configuración
    }
}