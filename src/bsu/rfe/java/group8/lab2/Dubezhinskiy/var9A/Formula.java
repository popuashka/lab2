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
}