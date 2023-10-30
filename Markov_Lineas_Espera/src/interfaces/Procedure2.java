package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Procedure2 extends JFrame implements ActionListener {
    private double[][] matriz;
    private double[][] matrizValores;
    private JMenuBar menubar;
    private JMenu menuOpciones;
    private JMenuItem menuItemRetroceder;
    private JButton buttoncalcular,buttonlimpiar;
    private JTextField textfielite;
    private JTextField[][] textfielcuadrito;
    private JLabel labelitera,labelvsol;
    private JTextArea textAreaMatrizValores;
    private JScrollPane scrollPaneMatrizValores;
    protected Markov markov;
    private int n2, veces;

    public Procedure2(Markov markov, double[][] matriz, int n) {
        this.markov = markov;
        this.matriz = matriz;
        n2 = n;
        this.setBounds(0, 0, 800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setLayout(null);
        setTitle("Calculo de evolucion de los usuarios en el sistema");
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
        Interfazinicial();
    }

    private void Interfazinicial() {
        labelitera = new JLabel("Digite las iteraciones a realizar");
        labelitera.setBounds(20, 80, 275, 30);
        labelitera.setFont(new Font("Serif", 0, 14));
        labelitera.setForeground(new Color(255, 255, 255));
        add(labelitera);

        labelvsol = new JLabel("Digite el vector sol.");
        labelvsol.setBounds(400, 80, 275, 30);
        labelvsol.setFont(new Font("Serif", 0, 14));
        labelvsol.setForeground(new Color(255, 255, 255));
        add(labelvsol);

        textfielite = new JTextField();
        textfielite.setBounds(200, 80, 80, 30);
        add(textfielite);

        buttoncalcular = new JButton("Calcular");
        buttoncalcular.setBounds(50, 130, 100, 30);
        add(buttoncalcular);
        buttoncalcular.addActionListener(this);

        CrearMatriz(n2);

        buttonlimpiar = new JButton("Limpiar");
        buttonlimpiar.setBounds(160, 130, 100, 30);
        add(buttonlimpiar);
        buttonlimpiar.addActionListener(this);

        textAreaMatrizValores = new JTextArea();
        textAreaMatrizValores.setEditable(false);
        textAreaMatrizValores.setFont(new Font("Serif", 1, 16));
        scrollPaneMatrizValores = new JScrollPane(textAreaMatrizValores);
        scrollPaneMatrizValores.setBounds(50, 200, 700, 200);
        add(scrollPaneMatrizValores);
        
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttoncalcular){
            if (matrizJTextFieldVacia(textfielcuadrito)) {
                JOptionPane.showMessageDialog(null, "Digite valores en el vector solución...");
            } else {
                guardarValores();
                veces = Integer.parseInt(textfielite.getText());
                resolver(matriz, matrizValores);
            }
        }
        if(e.getSource() == buttonlimpiar) {
            textAreaMatrizValores.setText("");
        }
        if(e.getSource() == menuItemRetroceder){
            this.setVisible(false);
            (new Markov()).setVisible(true);
        }
    }

    public void CrearMatriz(int n2) {
        int x = 565;
        int y = 80;
        int textFieldWidth = 50;
        int textFieldHeight = 20;

        textfielcuadrito = new JTextField[n2][1];

        for (int i = 0; i < n2; i++) {
            textfielcuadrito[i][0] = new JTextField();
            textfielcuadrito[i][0].setBounds(x, y, textFieldWidth, textFieldHeight);
            add(textfielcuadrito[i][0]);

            y += textFieldHeight + 10; // Espacio vertical entre JTextField
        }

        revalidate();
        repaint();
    }

    private void guardarValores() {
        double[][] valores = new double[n2][1];
        for (int i = 0; i < n2; i++) {
            try {
                valores[i][0] = Double.parseDouble(textfielcuadrito[i][0].getText());
            } catch (NumberFormatException e) {
                valores[i][0] = 0.0; // Valor por defecto o manejo personalizado del error
            }
        }

        matrizValores = valores;
    }

    private boolean matrizJTextFieldVacia(JTextField[][] textfielcuadrito) {
        for (int i = 0; i < n2; i++) {
            if (!this.textfielcuadrito[i][0].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void resolver(double[][] matriz, double[][] matrizValores) {
        StringBuilder sb = new StringBuilder();

        while (veces > 0) {
            int rows = matriz.length;
            int cols = matrizValores[0].length;
            double[][] tempMatrizValores = new double[rows][cols];

            for (int j = 0; j < cols; j++) {
                for (int i = 0; i < rows; i++) {
                    double resultado = 0;

                    for (int k = 0; k < matriz[i].length; k++) {
                        resultado += matriz[k][i] * matrizValores[k][j];
                    }

                    tempMatrizValores[i][j] = resultado;
                }
            }

            matrizValores = tempMatrizValores;

            // Agregar la matrizValores al StringBuilder
            for (int i = 0; i < matrizValores.length; i++) {
                for (int j = 0; j < matrizValores[i].length; j++) {
                    sb.append(matrizValores[i][j]).append(" ");
                }
                sb.append("\n");
            }
            sb.append("\n");
            veces--;
        }

        textAreaMatrizValores.setText(sb.toString());
    }
}