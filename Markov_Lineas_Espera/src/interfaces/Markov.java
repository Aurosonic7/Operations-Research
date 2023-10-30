package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Markov extends JFrame implements ActionListener{
    private JMenuBar menubar;
    private JMenu menuOpciones;
    private JMenuItem menuItemRetroceder;
    public double[][] matrizValores;

    private JLabel labelTitulo, labelnxn, LabelParabt1, Label2Parabt1,labeParabt2,Label2Parabt2,LabelParabt3,Label3Parabt3;
    private JTextField textfieldMatrix;
    private JButton buttonGenerar, buttonPeriodos, buttonEstestable, buttonProb, buttonGuardar, buttonVaciar;
    private JTextField[][] textfielcuadrito;
    private int n;
    public Markov(){
        this.setBounds(0, 0, 1000, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Cadenas de Markov");
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
        labelTitulo=new JLabel("Cadenas de Markov");
        labelTitulo.setBounds(10, 1, 200, 80);
        labelTitulo.setFont(new Font("Serif", 1, 16));
        labelTitulo.setForeground(new Color(255, 255, 255));
        add(labelTitulo);

        labelnxn = new JLabel("Tamaño de la matriz (T)");
        labelnxn.setBounds(350, 20, 500, 30);
        labelnxn.setFont(new Font("Serif", 0, 14));
        labelnxn.setForeground(new Color(255, 255, 255));
        add(labelnxn);

        textfieldMatrix = new JTextField();
        textfieldMatrix.setBounds(510, 20, 80, 30);
        add(textfieldMatrix);

        buttonGenerar=new JButton("Generar");
        buttonGenerar.setBounds(600, 20, 100, 30);
        add(buttonGenerar);
        buttonGenerar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == buttonGenerar){
            if (textfieldMatrix.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Digite en el campo...");
            }else{
                borrar();
                n = Integer.parseInt(textfieldMatrix.getText());
                CrearMatriz(n);
            }
        }
        if(e.getSource() == buttonGuardar){
            if(matrizTextFieldVacia(textfielcuadrito)){
                JOptionPane.showMessageDialog(null, "Digite todos los campos...");
            }else{
                guardarValores();
                calculo1();
                calculo2();
                calculo3();
            }
        }
        if(e.getSource() == buttonProb){
            double[][] matriz = getMatrizValores(); // la esa cosa que obtine la matriz la clase Claseinicio
            Procedure1 metodo1 = new Procedure1(this, matriz);
            metodo1.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == buttonPeriodos){
            double[][] matriz = getMatrizValores(); // la esa cosa que obtine la matriz la clase Claseinicio
            Procedure2 metodo2 = new Procedure2(this, matriz,n);
            metodo2.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == buttonEstestable){
            double[][] matriz = getMatrizValores(); // la esa cosa que obtine la matriz la clase Claseinicio
            Procedure3 metodo3 = new Procedure3(this, matriz,n);
            metodo3.setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == menuItemRetroceder){
            this.setVisible(false);
            (new Ventana()).setVisible(true);
        }
    }

    public void CrearMatriz(int n){
        if(textfielcuadrito != null){
            for(int i = 0; i < textfielcuadrito.length; i++){
                for(int j = 0; j < textfielcuadrito[i].length; j++){
                    remove(textfielcuadrito[i][j]);
                }
            }
        }
        textfielcuadrito = new JTextField[n][n];
        int startX = 18;
        int startY = 65;
        int fieldSize = 35;
        int spacing = 10;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                textfielcuadrito[i][j] = new JTextField();
                textfielcuadrito[i][j].setBounds(startX + (j * (fieldSize + spacing)), startY + (i * (fieldSize + spacing)), fieldSize, fieldSize);
                add(textfielcuadrito[i][j]);
            }
        }
        if(buttonGuardar == null){
            buttonGuardar = new JButton("Guardar");
            buttonGuardar.setFont(new Font("Serif", 0, 12));
            add(buttonGuardar);
            buttonGuardar.addActionListener(this);
        }
        if(buttonVaciar == null){
            buttonVaciar = new JButton("Limpiar");
            buttonVaciar.setFont(new Font("Serif", 0, 12));
            add(buttonVaciar);
            buttonVaciar.addActionListener(this);
        }
        matrizValores = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                String valorTexto = textfielcuadrito[i][j].getText();
                if(!valorTexto.isEmpty()){
                    double valorNumerico = Double.parseDouble(valorTexto);
                    matrizValores[i][j] = valorNumerico;
                }else{
                    matrizValores[i][j] = 0.0;
                }
            }
        }
        //Por consola
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(matrizValores[i][j] + " ");
            }
            System.out.println();
        }
        buttonVaciar.setBounds(startX + (n * (fieldSize + spacing)) - 20 + spacing, startY + (n * (fieldSize + spacing)) + spacing, 80, 30);
        buttonGuardar.setBounds(startX + (n * (fieldSize + spacing)) - 110 + spacing, startY + (n * (fieldSize + spacing)) + spacing, 80, 30);
        revalidate();
        repaint();
    }


    public void calculo1(){
        LabelParabt1 = new JLabel("Calculo de las probabilidades de");
        LabelParabt1.setBounds(10 + (n * (35 + 10)) - 100 + 10, 65 + (n * (35 + 10)) + 10 + 70, 200, 30);
        LabelParabt1.setHorizontalAlignment(SwingConstants.CENTER);
        LabelParabt1.setFont(new Font("Serif", Font.PLAIN, 14));
        LabelParabt1.setForeground(Color.WHITE);
        add(LabelParabt1);

        Label2Parabt1 = new JLabel("cambio en el sistema");
        Label2Parabt1.setBounds(10 + (n * (35 + 10)) - 130 + 10, 65 + (n * (35 + 10)) + 10 + 100, 200, 30);
        Label2Parabt1.setHorizontalAlignment(SwingConstants.CENTER);
        Label2Parabt1.setFont(new Font("Serif", Font.PLAIN, 14));
        Label2Parabt1.setForeground(Color.WHITE);
        add(Label2Parabt1);

        buttonProb = new JButton("Opcion 1");
        buttonProb.setBounds(10 + (n * (35 + 10)) - 90 + 10, 65 + (n * (35 + 10)) + 10 + 140, 100, 30);
        add(buttonProb);
        buttonProb.addActionListener(this);

        revalidate();
        repaint();
    }
    public void calculo2(){
        labeParabt2 = new JLabel("Calculo de evolucion de");
        labeParabt2.setBounds(270 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 70, 200, 30);
        labeParabt2.setHorizontalAlignment(SwingConstants.CENTER);
        labeParabt2.setFont(new Font("Serif", Font.PLAIN, 14));
        labeParabt2.setForeground(Color.WHITE);
        add(labeParabt2);


        Label2Parabt2 = new JLabel("los usuarios en el sistema");
        Label2Parabt2.setBounds(276 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 100, 200, 30);
        Label2Parabt2.setHorizontalAlignment(SwingConstants.CENTER);
        Label2Parabt2.setFont(new Font("Serif", Font.PLAIN, 14));
        Label2Parabt2.setForeground(Color.WHITE);
        add(Label2Parabt2);

        buttonPeriodos = new JButton("Opcion 2");
        buttonPeriodos.setBounds(305 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 140, 100, 30);
        add(buttonPeriodos);
        buttonPeriodos.addActionListener(this);


        revalidate();
        repaint();
    }
    public void calculo3(){
        LabelParabt3 = new JLabel("Calculo del estado");
        LabelParabt3.setBounds(600 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 70, 200, 30);
        LabelParabt3.setHorizontalAlignment(SwingConstants.CENTER);
        LabelParabt3.setFont(new Font("Serif", Font.PLAIN, 14));
        LabelParabt3.setForeground(Color.WHITE);
        add(LabelParabt3);


        Label3Parabt3 = new JLabel("Estable");
        Label3Parabt3.setBounds(570 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 100, 200, 30);
        Label3Parabt3.setHorizontalAlignment(SwingConstants.CENTER);
        Label3Parabt3.setFont(new Font("Serif", Font.PLAIN, 14));
        Label3Parabt3.setForeground(Color.WHITE);
        add( Label3Parabt3);

        buttonEstestable = new JButton("Opcion 3");
        buttonEstestable.setBounds(650 + (n * (35 + 10))  , 65 + (n * (35 + 10)) + 10 + 140, 100, 30);
        add(buttonEstestable);
        buttonEstestable.addActionListener(this);

        revalidate();
        repaint();
    }
    public void mostrarClaseInicio(){
        this.setVisible(true);
    }

    public void borrar(){
        if(buttonProb != null){
            remove(buttonProb); // Eliminación del botón del contenedor
            revalidate();
            repaint();
        }
        if(LabelParabt1 != null){
            remove(LabelParabt1); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
        if(Label2Parabt1 != null){
            remove(Label2Parabt1); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
        if( buttonPeriodos != null){
            remove( buttonPeriodos); // Eliminación del botón del contenedor
            revalidate();
            repaint();
        }
        if(labeParabt2  != null){
            remove(labeParabt2 ); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
        if(Label2Parabt2 != null){
            remove(Label2Parabt2); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
        if(buttonEstestable != null){
            remove(buttonEstestable); // Eliminación del botón del contenedor
            revalidate();
            repaint();
        }
        if(LabelParabt3 != null){
            remove(LabelParabt3 ); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
        if(Label3Parabt3!= null){
            remove(Label3Parabt3); // Eliminación del JLabel del contenedor
            revalidate();
            repaint();
        }
    }
    private void guardarValores(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                String valorTexto = textfielcuadrito[i][j].getText();
                if (!valorTexto.isEmpty()){
                    double valorNumerico = Double.parseDouble(valorTexto);
                    matrizValores[i][j] = valorNumerico;
                }else{
                    matrizValores[i][j] = 0.0; // Valor que pondre  para valores vacíos
                }
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(matrizValores[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean matrizTextFieldVacia(JTextField[][] matriz){
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
                String texto = matriz[i][j].getText().trim();
                if(texto.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public double[][] getMatrizValores(){
        return matrizValores;
    }
}