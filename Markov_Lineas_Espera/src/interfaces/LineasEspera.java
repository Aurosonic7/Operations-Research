package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LineasEspera extends JFrame implements ActionListener{
    private JMenuBar menubar;
    private JMenu menuFunciones,menuOpciones;
    private JMenuItem menuItemRho,menuItemCeroClientes,menuItemPromedioClientesFilaEspera,menuItemLs,menuItemWq,menuItemWs,menuItemPn,menuItemPw,menuItemRetroceder;
    private JLabel labelTitulo,labelL,labelS,labelM1,labelM2,labeln;
    private JTextField textfieldL,textfieldS,textfieldM,textfieldn;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    private JButton buttonRho,buttonP0,buttonLq,buttonLs,buttonWq,buttonWs,buttonPn,buttonPw;
    private Double p0=0.0,lQ=0.0,wQ=0.0;
    public LineasEspera(){
        this.setBounds(0,0,400,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0,0,0));
        //*Interfaz del menu en barra */
        menubar=new JMenuBar();
        setJMenuBar(menubar);

        menuFunciones=new JMenu("Funciones");
        menuFunciones.setFont(new Font("Serif",0,10));
        menubar.add(menuFunciones);

        menuItemRho=new JMenuItem("Utilizacion promedio del sistema (Rho)");
        menuItemRho.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemRho);
        menuItemRho.addActionListener(this);

        menuItemCeroClientes=new JMenuItem("Probabilidad de que cero clientes esten en el sistema (P0)");
        menuItemCeroClientes.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemCeroClientes);
        menuItemCeroClientes.addActionListener(this);

        menuItemPromedioClientesFilaEspera=new JMenuItem("Promedio de clientes en la fila de espera (Lq)");
        menuItemPromedioClientesFilaEspera.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemPromedioClientesFilaEspera);
        menuItemPromedioClientesFilaEspera.addActionListener(this);

        menuItemLs=new JMenuItem("Promedio de clientes en el sistema (Ls)");
        menuItemLs.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemLs);
        menuItemLs.addActionListener(this);

        menuItemWq=new JMenuItem("Tiempo promedio de espera en la fila (Wq)");
        menuItemWq.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemWq);
        menuItemWq.addActionListener(this);

        menuItemWs=new JMenuItem("Tiempo promedio de permanencia en el sistema (Ws)");
        menuItemWs.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemWs);
        menuItemWs.addActionListener(this);

        menuItemPn=new JMenuItem("Probabilidad de que haya N clientes en el sistema (Pn)");
        menuItemPn.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemPn);
        menuItemPn.addActionListener(this);

        menuItemPw=new JMenuItem("Probabilidad de que un cliente tenga que esperar (Pw)");
        menuItemPw.setFont(new Font("Serif",0,10));
        menuFunciones.add(menuItemPw);
        menuItemPw.addActionListener(this);
        
        menuOpciones=new JMenu("Opciones");
        menuOpciones.setFont(new Font("Serif",0,10));
        menubar.add(menuOpciones);

        menuItemRetroceder=new JMenuItem("Retroceder");
        menuItemRetroceder.setFont(new Font("Serif",0,10));
        menuOpciones.add(menuItemRetroceder);
        menuItemRetroceder.addActionListener(this);

        //*Interfaces de las etiquetas y las cajas de texto */

        labelTitulo=new JLabel("::Lineas de espera::");
        labelTitulo.setBounds(125,10,250,30);
        labelTitulo.setFont(new Font("Serif",1,16));
        labelTitulo.setForeground(new Color(230,240,255));
        add(labelTitulo);

        labelL=new JLabel("Tasa media de llegadas en unidad (L) por hora");
        labelL.setBounds(10, 40, 200, 30);
        labelL.setFont(new Font("Serif",0,10));
        labelL.setForeground(new Color(255,255,255));
        add(labelL);
        //labelL.setVisible(false);

        textfieldL=new JTextField("");
        textfieldL.setBounds(75,60,50,30);
        textfieldL.setFont(new Font("Serif",0,10));
        textfieldL.setForeground(new Color(255,255,255));
        textfieldL.setBackground(new Color(50,50,50));
        add(textfieldL);
        //textfieldL.setVisible(false);

        labelS=new JLabel("Numero de servidores (S)");
        labelS.setBounds(250, 40, 150, 30);
        labelS.setFont(new Font("Serif",0,10));
        labelS.setForeground(new Color(255,255,255));
        add(labelS);
        //labelS.setVisible(false);

        textfieldS=new JTextField("");
        textfieldS.setBounds(280,60,50,30);
        textfieldS.setFont(new Font("Serif",0,10));
        textfieldS.setForeground(new Color(255,255,255));
        textfieldS.setBackground(new Color(50,50,50));
        add(textfieldS);
        //textfieldS.setVisible(false);

        labelM1=new JLabel("Tasa media de servicio por canal de servicio");
        labelM1.setBounds(10, 90, 200, 30);
        labelM1.setFont(new Font("Serif",0,10));
        labelM1.setForeground(new Color(255,255,255));
        add(labelM1);

        labelM2=new JLabel("en unidad de tiempo (M) por hora");
        labelM2.setBounds(25, 100, 200, 30);
        labelM2.setFont(new Font("Serif",0,10));
        labelM2.setForeground(new Color(255,255,255));
        add(labelM2);
        //labelM.setVisible(false);

        textfieldM=new JTextField("");
        textfieldM.setBounds(75,120,50,30);
        textfieldM.setFont(new Font("Serif",0,10));
        textfieldM.setForeground(new Color(255,255,255));
        textfieldM.setBackground(new Color(50,50,50));
        add(textfieldM);
        //textfieldM.setVisible(false);

        labeln=new JLabel("Numero de clientes (N)");
        labeln.setBounds(255, 90, 150, 30);
        labeln.setFont(new Font("Serif",0,10));
        labeln.setForeground(new Color(255,255,255));
        add(labeln);
        labeln.setVisible(false);

        textfieldn=new JTextField("");
        textfieldn.setBounds(280,120,50,30);
        textfieldn.setFont(new Font("Serif",0,10));
        textfieldn.setForeground(new Color(255,255,255));
        textfieldn.setBackground(new Color(50,50,50));
        add(textfieldn);
        textfieldn.setVisible(false);

        //*Interfaz de JArea y el scroll */
        textarea=new JTextArea();
        textarea.setEditable(false);
        textarea.setFont(new Font("Serif",0,10));
        textarea.setForeground(new Color(255,255,255));
        textarea.setBackground(new Color(10,10,10));
        //textarea.setText("Christian");
        scrollpane=new JScrollPane(textarea);
        scrollpane.setBounds(10,170,375,125);
        add(scrollpane);

        //*Button de Rho */
        buttonRho=new JButton("Calcular Rho");
        buttonRho.setBounds(125,305,125,30);
        add(buttonRho);
        buttonRho.setVisible(false);
        buttonRho.addActionListener(this);
        
        //*Button de P0 */
        buttonP0=new JButton("Calcular P0");
        buttonP0.setBounds(125,305,125,30);
        add(buttonP0);
        buttonP0.setVisible(false);
        buttonP0.addActionListener(this);

        //*Button de Lq */
        buttonLq=new JButton("Calcular Lq");
        buttonLq.setBounds(125,305,125,30);
        add(buttonLq);
        buttonLq.setVisible(false);
        buttonLq.addActionListener(this);

        //*Button de Ls */
        buttonLs=new JButton("Calcular Ls");
        buttonLs.setBounds(125,305,125,30);
        add(buttonLs);
        buttonLs.setVisible(false);
        buttonLs.addActionListener(this);

        //*Button de Wq */
        buttonWq=new JButton("Calcular Wq");
        buttonWq.setBounds(125,305,125,30);
        add(buttonWq);
        buttonWq.setVisible(false);
        buttonWq.addActionListener(this);

        //*Button de Ws */
        buttonWs=new JButton("Calcular Ws");
        buttonWs.setBounds(125,305,125,30);
        add(buttonWs);
        buttonWs.setVisible(false);
        buttonWs.addActionListener(this);

        //*Button de Pn */
        buttonPn=new JButton("Calcular Pn");
        buttonPn.setBounds(125,305,125,30);
        add(buttonPn);
        buttonPn.setVisible(false);
        buttonPn.addActionListener(this);

        //*Button de Pw */
        buttonPw=new JButton("Calcular Pw");
        buttonPw.setBounds(125,305,125,30);
        add(buttonPw);
        buttonPw.setVisible(false);
        buttonPw.addActionListener(this);

    }
    private Integer Factorial(Double n){
        Integer factorial=1;
        for(Integer i=1;i<=n;i++){
            factorial*=i;
        }
        return factorial;
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == menuItemRho){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(true);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Porcentaje de Ocupacion del Sistema#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemCeroClientes){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(true);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Probabilidad de sistema vacio#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemPromedioClientesFilaEspera){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(true);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Cantidad promedio de clientes en la cola#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemLs){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(true);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Cantidad promedio de clientes en el sistema#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemWq){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(true);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Tiempo promedio de espera en la fila#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemWs){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(true);
            buttonPn.setVisible(false);
            buttonPw.setVisible(false);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Tiempo promedio de permanencia en el sistema#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemPn){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(true);
            buttonPw.setVisible(false);
            labeln.setVisible(true);
            textfieldn.setVisible(true);
            textarea.setText("\t      #####Probabilidad de que haya N clientes en el sistema#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemPw){
            labelL.setVisible(true);
            textfieldL.setVisible(true);
            labelS.setVisible(true);
            textfieldS.setVisible(true);
            labelM1.setVisible(true);
            labelM2.setVisible(true);
            textfieldM.setVisible(true);
            buttonRho.setVisible(false);
            buttonP0.setVisible(false);
            buttonLq.setVisible(false);
            buttonLs.setVisible(false);
            buttonWq.setVisible(false);
            buttonWs.setVisible(false);
            buttonPn.setVisible(false);
            buttonPw.setVisible(true);
            labeln.setVisible(false);
            textfieldn.setVisible(false);
            textarea.setText("\t      #####Probabilidad de que un cliente tenga que esperar#####\n");
            textfieldL.setText("");
            textfieldS.setText("");
            textfieldM.setText("");
        }
        if(e.getSource()==menuItemRetroceder){
            this.setVisible(false);
            (new Ventana()).setVisible(true);
        }
        if(e.getSource()==buttonRho){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                textfieldL.setText("");
                textfieldS.setText("");
                textfieldM.setText("");
                double resultado=0.0;
                if(S*M>L){ 
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+(L/(S*M)));
                    resultado= (L/(S*M)) * 100;
                    resultado=Math.round(resultado*1000)/1000d; //*3 decimales redondeados */
                    //textarea.append("#####Porcentaje de Ocupacion del Sistema#####\n");
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("El porcentaje de ocupacion del sistema es: "+resultado+"%\n");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema inestable...");
                }
            }catch(Exception error){
                //error.printStackTrace();
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonP0){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double acumulador=0.0,resultado=0.0;
                if(S*M>L){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    resultado=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    p0=resultado;
                    p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Probabilidad de sistema vacio: "+p0+"%\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+resultado);
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonLq){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double acumulador=0.0;
                if(S*M>L){
                    //if(p0!=0.0){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    //p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    lQ=(Math.pow((L/M),S)*L*M*p0)/(Factorial(S-1)*Math.pow((S*M-L),2));
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Cantidad promedio de clientes en la cola: "+lQ+" clientes\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+lQ);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a P0...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonLs){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double Ls=0.0,acumulador=0.0;
                if(S*M>L){
                    //if(lQ!=0.0){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    //p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    lQ=(Math.pow((L/M),S)*L*M*p0)/(Factorial(S-1)*Math.pow((S*M-L),2));
                    Ls=lQ+L/M;
                    Ls=Math.round(Ls*100)/100d;
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Cantidad promedio de clientes en el sistema: "+Ls+" clientes\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+resultado);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a lQ...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonWq){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double acumulador=0.0;
                if(S*M>L){
                    //if(lQ!=0.0){
                        for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                        p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                        //p0*=100;
                        p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                        lQ=(Math.pow((L/M),S)*L*M*p0)/(Factorial(S-1)*Math.pow((S*M-L),2));
                        wQ=lQ/L;
                        textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                        textarea.append("Numero de servidores (S): "+S+" servidores\n");
                        textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                        textarea.append("Tiempo promedio de espera en la fila: "+wQ+" minutos\n");
                        //JOptionPane.showMessageDialog(null,"El resultado es: "+wQ);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a lQ...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonWs){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double wS=0.0,acumulador=0.0;
                if(S*M>L){
                    //if(wQ!=0.0){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    //p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    lQ=(Math.pow((L/M),S)*L*M*p0)/(Factorial(S-1)*Math.pow((S*M-L),2));
                    wQ=lQ/L;
                    wS=wQ+(1/M);
                    wS=Math.round(wS*1000)/1000d;//*3 decimales redondeado */
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Tiempo promedio de espera en la fila: "+wS+" minutos\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+wS);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a wQ...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonPn){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double n=Double.parseDouble(textfieldn.getText());
                Double resultado=0.0,acumulador=0.0;
                if(S*M>L){
                    //if(p0!=0.0){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    //p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    if(n<=S) resultado=(Math.pow((L / M),n))*p0 / Factorial(n);
                    else resultado=(Math.pow((L / M),n)) * p0 / (Factorial(S) * Math.pow(S,(n-S)));
                    resultado*=100;
                    resultado=Math.round(resultado*1000)/1000d;//*3 decimales redondeado */
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Probabilidad que haya N clientes en el sistema: "+resultado+"%\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+resultado);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a P0...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
        if(e.getSource()==buttonPw){
            try{
                Double L=Double.parseDouble(textfieldL.getText());
                Double S=Double.parseDouble(textfieldS.getText());
                Double M=Double.parseDouble(textfieldM.getText());
                Double resultado=0.0,acumulador=0.0;
                if(S*M>L){
                    //if(p0!=0.0){
                    for(Double i=0.0;i<=S-1;i++) acumulador+=Math.pow(L/M,i)/Factorial(i);
                    p0=1/(acumulador + (Math.pow((L/M),S) / Factorial(S)) * (1 / (1 - (L/(S*M)))));
                    //p0*=100;
                    p0=Math.round(p0*1000)/1000d;//*3 decimales redondeado */
                    resultado=(1 / Factorial(S)) * Math.pow((L / M),S) * ((S*M) / (S * M - L)) * p0;
                    resultado*=100;
                    resultado=Math.round(resultado*1000)/1000d;//*3 decimales redondeado */
                    textarea.append("Tasa Media de Llegadas en Unidad (L) "+L+" por hora\n");
                    textarea.append("Numero de servidores (S): "+S+" servidores\n");
                    textarea.append("Tasa media de servicio por canal de servicio en unidad de tiempo (M) "+M+" por hora\n");
                    textarea.append("Probabilidad de que un cliente tenga que esperar: "+resultado+"%\n");
                    //JOptionPane.showMessageDialog(null,"El resultado es: "+resultado);
                    //}else JOptionPane.showMessageDialog(null,"Ingresa un valor a P0...");
                }else{
                    textarea.append("Sistema inestable...\n");
                    //JOptionPane.showMessageDialog(null,"Sistema insetable...");
                }
            }catch(Exception error){
                JOptionPane.showMessageDialog(null,"Digite numeros...");
            }
        }
    }
}