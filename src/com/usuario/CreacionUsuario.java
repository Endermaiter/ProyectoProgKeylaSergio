/**
 * La clase CreacionUsuario se encarga de la creación de usuarios en el sistema.
 * Contiene una referencia a un objeto Usuario.
 */
package com.usuario;

public class CreacionUsuario {
    private Usuario usuario; // Referencia al usuario que se va a crear

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
