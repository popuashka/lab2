package bsu.rfe.java.group8.lab2.Dubezhinskiy.var9A;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Formula extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    //поля для считывания х, у, z и результата
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    public double sum = 0;
    public JTextField Temp4;
    //радио кнопки
    private ButtonGroup radioButtons = new ButtonGroup();
    //Контейнер для отображения радио кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    //идентификатор выбранной формулы
    private int formulaId = 1;

    //Формула 1 для расчета
    public Double Formula_1(double x, double y, double z) {
        return Math.sin(Math.sin(y) + Math.pow(2.718, Math.cos(y)) + z * z) * Math.pow(Math.sin(3.14159 * y * y) + Math.log(x * x), 1 / 4);
    }

    //Формула 2 для расчета
    public Double Formula_2(double x, double y, double z) {
        return (Math.atan(Math.pow(z, x))) / (y * y + z * Math.sin(Math.log(x)));
    }

    //метод для добавления радио кнопок
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                Formula.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public Formula(){
        //заголовок
        super("Калькулятор формулы");
        //установка размера и положения окна
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,(kit.getScreenSize().height - HEIGHT) / 2);
        //Добавление радио кнопок
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(),true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        //Добавление текстовых полей для переменных
        JLabel labelX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelZ = new JLabel("z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        //Добавление области для вывода рузультата
        JLabel labelResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0",15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        textFieldResult.setEditable(false);
        //создание кнопок
        JButton buttonVichislit = new JButton("Вычислить");
        buttonVichislit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = Formula_1(x, y, z);
                    else
                        result = Formula_2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(Formula.this,"Ошибка в формате записи числа с " +
                            "плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonErase = new JButton("Очистить поля");
        buttonErase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        //создание МС кнопки
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                Temp4.setText("0");
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        //создание М+ кнопки
        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                double temp2 = 0;
                try{
                    String temp = textFieldResult.getText();
                    if(temp.isEmpty()){
                        throw new IOException("Incorrect format");
                    }
                    temp2 = Double.valueOf(temp);
                }
                catch (IOException e){
                    System.out.println("Please enter numbers");
                }
                sum += temp2;
                String temp3 = Double.toString(sum);
                Temp4.setText(temp3);
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonVichislit);
        hboxButtons.add(Box.createHorizontalStrut(40));
        hboxButtons.add(buttonErase);
        hboxButtons.add(Box.createHorizontalStrut(40));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(40));
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalGlue());
        //создание вывода Sum
        JLabel labelsum = new JLabel("Sum:");
        Temp4 = new JTextField("0",15);
        Temp4.setMaximumSize(Temp4.getPreferredSize());
        Box hboxTemp = Box.createHorizontalBox();
        hboxTemp.add(Box.createHorizontalGlue());
        hboxTemp.add(labelsum);
        hboxTemp.add(Box.createHorizontalStrut(10));
        hboxTemp.add(Temp4);
        hboxTemp.add(Box.createHorizontalGlue());
        Temp4.setEditable(false);
        //Сборка панелей окна
        Box vboxOkna = Box.createVerticalBox();
        vboxOkna.add(Box.createVerticalGlue());
        vboxOkna.add(hboxFormulaType);
        vboxOkna.add(hboxVariables);
        vboxOkna.add(Box.createVerticalGlue());
        vboxOkna.add(hboxResult);
        vboxOkna.add(Box.createVerticalGlue());
        vboxOkna.add(hboxButtons);
        vboxOkna.add(Box.createVerticalGlue());
        vboxOkna.add(hboxTemp);
        vboxOkna.add(Box.createVerticalGlue());
        getContentPane().add(vboxOkna, BorderLayout.CENTER);
    }
}

