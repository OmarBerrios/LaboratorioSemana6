/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acosta_christian_6;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author omarr
 */
public class Acosta_Christian_6 extends JPanel {

    private ArrayList<String> Palabras = new ArrayList<>(
        Arrays.asList(
            "HONDURAS", "GORILA", "CANTANTE", "ANGELES", "PALOMA", 
            "LIBERTAD", "LABORATORIO", "TACO", "REINADO", "GORRA"
        )
    );

    String palabraActual;
    StringBuilder progreso;
    int oportunidades;
    boolean Juego;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Acosta_Christian_6::new);
    }

    public Acosta_Christian_6() {
        JFrame frame = new JFrame("Adivina la Palabra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("Bienvenido al juego", SwingConstants.CENTER);
        JLabel progresoLabel = new JLabel("", SwingConstants.CENTER);
        JLabel oportunidadesLabel = new JLabel("", SwingConstants.CENTER);
        JTextField entrada = new JTextField(1); 
        JButton enviar = new JButton("Enviar");
        JButton reiniciar = new JButton("Reiniciar el juego");
        JButton cambiarPalabras = new JButton("Cambiar palabras");
        JButton verListado = new JButton("Ver listado de palabras");

        panel.add(mensaje, BorderLayout.NORTH);
        panel.add(progresoLabel, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Ingresa un carácter:"));
        panelInferior.add(entrada);
        panelInferior.add(enviar);
        panelInferior.add(reiniciar);
        panelInferior.add(cambiarPalabras);
        panelInferior.add(verListado);
        panel.add(panelInferior, BorderLayout.SOUTH);
        panel.add(oportunidadesLabel, BorderLayout.WEST);

        frame.add(panel);
        frame.setVisible(true);

        iniciarJuego(mensaje, progresoLabel, oportunidadesLabel);

        enviar.addActionListener(e -> {
            if (!Juego) {
                JOptionPane.showMessageDialog(frame, "El juego ha terminado. Presiona Reiniciar para jugar de nuevo.");
                return;
            }

            String entradaTexto = entrada.getText().toUpperCase();
            entrada.setText("");

            if (entradaTexto.length() != 1) {
                mensaje.setText("Por favor ingresa solo un carácter.");
                return;
            }

            char letra = entradaTexto.charAt(0);
            boolean acierto = false;

            for (int i = 0; i < palabraActual.length(); i++) {
                if (palabraActual.charAt(i) == letra) {
                    progreso.setCharAt(i * 2, letra);
                    acierto = true;
                }
            }

            if (acierto) {
                mensaje.setText("¡Le pegaste a un carácter!");
            } else {
                oportunidades--;
                mensaje.setText("La palabra no contiene esa letra.");
            }

            progresoLabel.setText(progreso.toString());
            oportunidadesLabel.setText("Oportunidades restantes: " + oportunidades);

            if (progreso.indexOf("_") == -1) {
                mensaje.setText("¡Felicidades, adivinaste la palabra!");
                Juego = false;
            } else if (oportunidades <= 0) {
                mensaje.setText("Lo siento, te quedaste sin oportunidades. La palabra era: " + palabraActual);
                Juego = false;
            }
        });

        reiniciar.addActionListener(e -> iniciarJuego(mensaje, progresoLabel, oportunidadesLabel));

        cambiarPalabras.addActionListener(e -> cambiarPalabras());

        verListado.addActionListener(e -> verListadoPalabras());
    }

    private void iniciarJuego(JLabel Mensaje, JLabel Progreso, JLabel Oportunidades) {
        palabraActual = Palabras.get(new Random().nextInt(Palabras.size()));
        progreso = new StringBuilder();

        for (int i = 0; i < palabraActual.length(); i++) {
            progreso.append("_ ");
        }

        oportunidades = 5;
        Juego = true;

        Mensaje.setText("¡Comienza el juego! Adivina la palabra.");
        Progreso.setText(progreso.toString());
        Oportunidades.setText("Oportunidades restantes: " + oportunidades);
    }

    private void cambiarPalabras() {
        String nuevaPalabra = JOptionPane.showInputDialog("Ingrese una nueva palabra:");

        if (nuevaPalabra != null && !nuevaPalabra.trim().isEmpty()) {
            Palabras.add(nuevaPalabra.toUpperCase());
            JOptionPane.showMessageDialog(null, "Palabra añadida exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se ingresó ninguna palabra.");
        }
    }

    private void verListadoPalabras() {
        String listado = "Listado de palabras:\n";
        for (String palabra : Palabras) {
            listado += palabra + "\n";
        }
        JOptionPane.showMessageDialog(null, listado);
    }
}