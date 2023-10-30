package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Procedure1 extends JFrame implements ActionListener{
    private double[][] matriz;
    int n;
    private JMenuBar menubar;
    private JMenu menuOpciones;
    private JMenuItem menuItemRetroceder;
    private JButton buttoncalcular, buttonLimpiar;
    private JTextField textfieldNumero;
    private JTextArea textArea;
    private JLabel labelitera;
    protected Markov markov;
    public Procedure1(Markov markov, double[][] matriz) {
        this.markov=markov;
        this.matriz = matriz;
        this.setBounds(0, 0, 800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setLayout(null);
        setTitle("Calculo de las probabilidades de cambio en el sistema");
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

    private void Interfazinicial(){
        labelitera = new JLabel("Digite las iteraciones a realizar");
        labelitera.setBounds(0, 40, 275, 30);
        labelitera.setHorizontalAlignment(SwingConstants.CENTER);
        labelitera.setFont(new Font("Serif", 0, 14));
        labelitera.setForeground(new Color(255, 255, 255));
        add(labelitera);

        textfieldNumero = new JTextField();
        textfieldNumero.setBounds(250, 40, 50, 30);
        add(textfieldNumero);

        buttoncalcular = new JButton("Calcular");
        buttoncalcular.setBounds(50, 80, 100, 30);
        add(buttoncalcular);
        buttoncalcular.addActionListener(this);

        buttonLimpiar = new JButton("Limpiar");
        buttonLimpiar.setBounds(160, 80, 100, 30);
        add( buttonLimpiar);
        buttonLimpiar.addActionListener(this);

        textArea = new JTextArea();
        textArea.setFont(new Font("Serif", 1, 16));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 180, 700, 200);
        add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttoncalcular) {
            n = Integer.parseInt(textfieldNumero.getText());
            resolver();
        }
        if ( e.getSource() == buttonLimpiar) {
            textArea.setText("");
            textfieldNumero.setText("");
        }
        if(e.getSource() == menuItemRetroceder){
            this.setVisible(false);
            (new Markov()).setVisible(true);
        }
    }

    public void resolver() {
        int a = 2;
        double[][] matrizAuxiliar = new double[matriz.length][matriz[0].length];

        // Copiar los valores de la matriz original a la matriz auxiliar
        for (int i = 0; i < matriz.length; i++) {
            System.arraycopy(matriz[i], 0, matrizAuxiliar[i], 0, matriz[i].length);
        }

        DecimalFormat df = new DecimalFormat("0.0000");

        while (n > 1) {
            double[][] resultado = new double[matriz.length][matriz[0].length];

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    double suma = 0.0;
                    for (int k = 0; k < matriz[i].length; k++) {
                        suma += matrizAuxiliar[i][k] * matriz[k][j];
                    }
                    resultado[i][j] = suma;
                }
            }

            // Actualizar la matriz original con los nuevos valores
            for (int i = 0; i < matriz.length; i++) {
                System.arraycopy(resultado[i], 0, matriz[i], 0, matriz[i].length);
            }

            // Agregar los resultados al JTextArea con cuatro decimales y más espacio
            textArea.append("Estado " + a + "\n");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    textArea.append(String.format("%10s", df.format(matriz[i][j])) + " ");
                }
                textArea.append("\n");
            }
            textArea.append("\n");

            a = a + 1;
            n = n - 1;
        }
    }
}
