import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boarwidth = 360;
    int boardhieght = 540;

    Color customLightGray = new Color(212,212,210);
    Color customDarkGray = new Color(80,80,80);
    Color customBlack =new Color(28,28,28);
    Color customOrange = new Color(255,149,0);

String[] buttonValues = {
    "AC", "+/-", "%", "÷",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "0", ".", "√", "="
};

String[] rightSymbols = {"÷", "×", "-", "+", "="};
String[] topSymbols = {"AC", "+/-", "%"};
JFrame frame = new JFrame("Calculator");

JLabel displayLabel1= new JLabel();
JPanel displayPanel= new JPanel();
JPanel buttonsPanel=new JPanel();

String A="0";
String operator=null;
String B=null;

Calculator(){

frame.setSize(boarwidth,boardhieght);
frame.setLocationRelativeTo(null);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());

displayLabel1.setBackground(customBlack);
displayLabel1.setForeground(Color.white);
displayLabel1.setFont(new Font("Arial",Font.PLAIN,80));
displayLabel1.setHorizontalAlignment(JLabel.RIGHT);
displayLabel1.setText("0");
displayLabel1.setOpaque(true);

displayPanel.setLayout(new BorderLayout());
displayPanel.add(displayLabel1);
frame.add(displayPanel,BorderLayout.NORTH);

buttonsPanel.setLayout(new GridLayout(5,4));
buttonsPanel.setBackground(customBlack);
frame.add(buttonsPanel);

for(int i=0;i<buttonValues.length;i++){
    JButton button=new JButton(buttonValues[i]);
    String buttonValue=buttonValues[i];

    button.setFont(new Font("Arial",Font.PLAIN,30));
    button.setFocusable(false);
    button.setBorder(new LineBorder(customBlack));

    if(Arrays.asList(topSymbols).contains(buttonValue)){
        button.setBackground(customLightGray);
        button.setForeground(customBlack);
    } else if(Arrays.asList(rightSymbols).contains(buttonValue)){
        button.setBackground(customOrange);
        button.setForeground(Color.white);
    } else {
        button.setBackground(customDarkGray);
        button.setForeground(Color.white);
    }

    buttonsPanel.add(button);

    button.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

            String val=button.getText();

            if(Arrays.asList(rightSymbols).contains(val)){

                if(val.equals("=")){
                    if(operator==null) return;

                    B=displayLabel1.getText();
                    double a=Double.parseDouble(A);
                    double b=Double.parseDouble(B);
                    double r=0;

                    if(operator.equals("+")) r=a+b;
                    else if(operator.equals("-")) r=a-b;
                    else if(operator.equals("×")) r=a*b;
                    else if(operator.equals("÷")){
                        if(b==0){ displayLabel1.setText("Error"); return; }
                        r=a/b;
                    }

                    displayLabel1.setText(removeZeroDecimal(r));
                    A=displayLabel1.getText();
                    operator=null;
                    B=null;
                }
                else{
                    A=displayLabel1.getText();
                    operator=val;
                    displayLabel1.setText("0");
                }
            }

            else if(Arrays.asList(topSymbols).contains(val)){
                if(val.equals("AC")){
                    clearAll();
                    displayLabel1.setText("0");
                }
                else if(val.equals("+/-")){
                    double x=Double.parseDouble(displayLabel1.getText());
                    displayLabel1.setText(removeZeroDecimal(x*-1));
                }
                else if(val.equals("%")){
                    double x=Double.parseDouble(displayLabel1.getText());
                    displayLabel1.setText(removeZeroDecimal(x/100));
                }
            }

            else{
                if(val.equals(".")){
                    if(!displayLabel1.getText().contains(".")){
                        displayLabel1.setText(displayLabel1.getText()+".");
                    }
                }
                else{
                    if(displayLabel1.getText().equals("0"))
                        displayLabel1.setText(val);
                    else
                        displayLabel1.setText(displayLabel1.getText()+val);
                }
            }
        }
    });
}

frame.setVisible(true);
}

void clearAll(){
A="0";
operator=null;
B=null;
}

String removeZeroDecimal(double numDisplay){
if(numDisplay%1==0) return Integer.toString((int)numDisplay);
return Double.toString(numDisplay);
}

public static void main(String[] args){
new Calculator();
}
}