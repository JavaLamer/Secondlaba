package laba2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import static java.lang.Math.*;


@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
//        private Ilness beta ;
//        public MainFrame(Ilness beta) {
//            this.beta = beta;
//        }


    Ilness localInstance;

    public MainFrame(Ilness _beta)
    {

        localInstance = _beta;
        Ilness locale = localInstance;
    }


    // Размеры окна приложения в виде констант
    private static final int WIDTH = 600;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;

    private JTextField textFieldZ;

    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return (pow(Math.log(pow(1+z,2))+Math.cos(Math.PI*pow(y,3)),1.0/4))/(pow(Math.cos(exp(x))+pow(1.0/x,1.0/2)+exp(pow(x,2)),Math.sin(x))) ;
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return pow(Math.sin(pow(z,y)),2)/pow(1+pow(x,3),1.0/2);
    }
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton  (String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();

        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldY);
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");


        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());



//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
//
        final Double[] sum ={0.0};
// Создать область для кнопок

        JButton M = new JButton("M+");
        M.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                Double result;
                if (formulaId == 1)
                    result = calculate1(x, y, z);
                else
                    result = calculate2(x, y, z);
                sum[0] += result;
                textFieldResult.setText(sum[0].toString());
            }

        });
        JButton MC = new JButton("MC");

        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                sum[0] =0.0;

            }

        });

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {

                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(M);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(MC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());

// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    private Double actionPerformedSum(int formulaId) {
        Double x = Double.parseDouble(textFieldX.getText());
        Double y = Double.parseDouble(textFieldY.getText());
        Double z = Double.parseDouble(textFieldZ.getText());
        Double sum = 0.0;
        if (formulaId==1)
            sum +=calculate1(x,y,z) ;
        else
            sum +=calculate2(x,y,z);
        return sum;
    }

    public class Ilness{
        Double FieldText = 0.0;

        public double getResult(){
            FieldText += actionPerformedSum(formulaId);
            return FieldText;
        }

        public double getFieldText(){
            return FieldText;
        }

        public double CanValue(){
            FieldText = 0.0;
            return FieldText;
        }
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}