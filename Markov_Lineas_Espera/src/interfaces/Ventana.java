package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Ventana extends JFrame implements ActionListener{
    private JLabel labelFondo;
    private JButton buttonLineasEspera,buttonCadenasDeMarkov;
    public Ventana(){
        this.setBounds(0,0,300,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        getContentPane().setBackground(new Color(0,0,0));

        interfazInicial();

    }
    private void interfazInicial(){
        buttonLineasEspera=new JButton("Lineas de espera");
        buttonLineasEspera.setBounds(50,50,200,30);
        add(buttonLineasEspera);
        buttonLineasEspera.addActionListener(this);

        buttonCadenasDeMarkov=new JButton("Cadenas de Markov");
        buttonCadenasDeMarkov.setBounds(50,100,200,30);
        add(buttonCadenasDeMarkov);
        buttonCadenasDeMarkov.addActionListener(this);

        ImageIcon fondo=new ImageIcon("img/fondo.gif");
        labelFondo=new JLabel(fondo);
        labelFondo.setBounds(0,0,300,200);
        add(labelFondo);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == buttonLineasEspera){
            this.setVisible(false);
            (new LineasEspera()).setVisible(true);
        }
        if(e.getSource() == buttonCadenasDeMarkov){
            this.setVisible(false);
            (new Markov()).setVisible(true);
        }
    }
}