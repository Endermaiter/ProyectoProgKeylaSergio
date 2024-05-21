package com.interfaces.cuestionarios.sangrado;

import com.calendario.SelectorFecha;
import com.cuestionario.Duracion;
import com.interfaces.cuestionarios.uso.UsoProg;
import com.interfaces.interfazinicio.Database;
import com.interfaces.interfazinicio.gui.Register;
import com.periodo.Menstruacion;
import com.usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuestionarioFInal {
    public JPanel panelPrincipal;
    private JLabel lblCiclo;
    private JLabel lblSangrado;
    private JButton continuarButton;
    private JSpinner numerCiclo;
    private JSpinner numerSangrado;
    int duracionCiclo;
    int duracionSangrado;
    Duracion duracion = new Duracion();
    private Usuario usuario;  // Almacena el usuario registrado
    private Menstruacion menstruacion; // Instancia de Menstruacion

    // Constructor que acepta un objeto Usuario
    public CuestionarioFInal(Usuario usuario) {
        this.usuario = usuario;
        this.menstruacion = new Menstruacion(); // Inicializa la instancia de Menstruacion
        menstruacion.setUsuario(usuario.getUsuario()); // Usa el usuario pasado al constructor
        getJpanel();
    }

    public void getJpanel() {
        numerCiclo.setModel(new SpinnerNumberModel(28, 1, Integer.MAX_VALUE, 1));
        numerSangrado.setModel(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    duracionCiclo = (Integer) numerCiclo.getValue();
                    duracionSangrado = (Integer) numerSangrado.getValue();
                    duracion.setDuracionCiclo(duracionCiclo);
                    duracion.setDuracionSangrado(duracionSangrado);
                    System.out.println("Duración del ciclo: " + duracion.getDuracionCiclo());
                    System.out.println("Duración del sangrado: " + duracion.getDuracionSangrado());
                    if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
                        throw new IllegalArgumentException("Por favor, introduce valores válidos.");
                    }
                    menstruacion.setMediaCiclo(duracionCiclo);
                    menstruacion.setMediaSangrado(duracionSangrado);
                    new SelectorFecha(menstruacion); // Pasa la instancia de Menstruacion a SelectorFecha
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
}
