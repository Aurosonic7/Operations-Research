package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.apache.commons.math4.legacy.linear.LUDecomposition;
import org.apache.commons.math4.legacy.linear.MatrixUtils;
import org.apache.commons.math4.legacy.linear.RealMatrix;
import org.apache.commons.math4.legacy.linear.SingularOperatorException;

public class Procedure3 extends JFrame implements ActionListener {
    private double[][] matriz;
    private JMenuBar menubar;
    private JMenu menuOpciones;
    private JMenuItem menuItemRetroceder;
    private JButton buttonCalcular;
    private JTextArea textArea;
    protected Markov markov;

    public Procedure3(Markov markov, double[][] matriz, int n) {
        this.markov = markov;
        this.matriz = matriz;
        this.setBounds(0, 0, 250, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setLayout(null);
        setTitle("Calculo de estado estable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 0, 0));
        setLayout(null);
        menubar=new JMenuBar();
        setJMenuBar(menubar);
        menuOpciones=new JMenu("Opciones");
        menuOpciones.setFont(new Font("Serif",0,10));
        menubar.add(menuOpciones);
        menuItemRetroceder=new JMenuItem("Retroceder");
        menuItemRetroceder.setFont(new Font("Serif",0,10));
        menuOpciones.add(menuItemRetroceder);
        menuItemRetroceder.addActionListener(this);
        InterfazInicial();
    }

    private void InterfazInicial(){
        buttonCalcular = new JButton("Calcular");
        buttonCalcular.setBounds(75, 240, 100, 30);
        add(buttonCalcular);
        buttonCalcular.addActionListener(this);

        textArea = new JTextArea();
        textArea.setBounds(20, 20, 200, 200);
        textArea.setEditable(false);
        textArea.setBackground(new Color(0,0,0));
        textArea.setFont(new Font("Serif",1,14));
        textArea.setForeground(new Color(255,255,255));
        add(textArea);
    }

    private double[][] transponerYRestarUno(double[][] matriz) {
        int n = matriz.length;
        double[][] transpuesta = new double[n][n];

        // Transponer la matriz original
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transpuesta[i][j] = matriz[j][i];
            }
        }

        // Restar uno a los elementos en la diagonal de la matriz transpuesta
        for (int i = 0; i < n; i++) {
            transpuesta[i][i] -= 1.0;
        }

        return transpuesta;
    }

    private double[][] agregarFilaColumna(double[][] matriz) {
        int n = matriz.length;
        double[][] nuevaMatriz = new double[n + 1][n + 1];

        // Copiar los valores de la matriz original a la nueva matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nuevaMatriz[i][j] = matriz[i][j];
            }
        }

        // Agregar una columna con valores 1 a excepción del último elemento
        for (int i = 0; i < n; i++) {
            nuevaMatriz[i][n] = 1.0;
        }
        nuevaMatriz[n][n] = 0.0;

        // Agregar una fila con valores 1
        for (int j = 0; j < n; j++) {
            nuevaMatriz[n][j] = 1.0;
        }

        return nuevaMatriz;
    }

    private String formatValue(double value) {
        return String.format("%.4f", value);
    }

    private void imprimirMatrizEnTextArea(double[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(formatValue(matriz[i][j])).append("   "); // Aumentamos el espacio entre valores
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }

    private double[][] calcularInversa(double[][] matriz){
        // Crear una instancia de RealMatrix a partir de la matriz dada
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matriz);

        try {
            // Calcular la matriz inversa utilizando el método de la pseudoinversa
            RealMatrix inversa = new LUDecomposition(realMatrix).getSolver().getInverse();

            // Convertir la matriz inversa de RealMatrix a double[][]
            return inversa.getData();
        } catch (SingularOperatorException e) {
            // La matriz es singular (no tiene inversa)
            //System.out.println("La matriz no tiene inversa.");
            JOptionPane.showMessageDialog(null,"La matriz no tiene inversa...");
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCalcular) {
            // Transponer la matriz original y restar uno a los elementos en la diagonal
            double[][] matrizTranspuesta = transponerYRestarUno(matriz);

            // Agregar una fila y una columna a la matriz transpuesta
            double[][] nuevaMatriz = agregarFilaColumna(matrizTranspuesta);

            // Imprimir la nueva matriz en el JTextArea
            imprimirMatrizEnTextArea(nuevaMatriz);

            // Calcular la matriz inversa
            double[][] matrizInversa = calcularInversa(nuevaMatriz);

            if (matrizInversa != null) {
                // Imprimir la matriz inversa en el JTextArea
                imprimirMatrizEnTextArea(matrizInversa);
            }
        }
        if(e.getSource() == menuItemRetroceder){
            this.setVisible(false);
            (new Markov()).setVisible(true);
        }
    }
}
