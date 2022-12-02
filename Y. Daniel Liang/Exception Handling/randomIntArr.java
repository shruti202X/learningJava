import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class randomIntArr {
    public static void main(String[] args) {
        int[] arr = getRandIntArr();

        JFrame jf = new JFrame("Exercise 18_3: Show Bounds Error");
        jf.setLayout(new BorderLayout());

        JPanel jp = new JPanel(new GridLayout(2, 2, 0, 0));
        JLabel indLbl = new JLabel("Array Index");
        JTextField indTF = new JTextField();
        JLabel eleLbl = new JLabel("Array Element");
        JTextField eleTF = new JTextField();
        eleTF.setEnabled(false);
        jp.add(indLbl);
        jp.add(indTF);
        jp.add(eleLbl);
        jp.add(eleTF);
        eleTF.setDisabledTextColor(Color.BLACK);

        JButton jb = new JButton("Show Element");
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String _ind = indTF.getText();
                String display = new String();
                try{
                    int ind = Integer.parseInt(_ind);
                    int val = arr[ind];
                    display = String.valueOf(val);
                }catch(NumberFormatException nfe){
                    display = "Number Format Exception";
                }catch(ArrayIndexOutOfBoundsException aiobe){
                    display = "Out of Bound";
                }
                eleTF.setText(display);
            }
        });

        jf.add(jp, BorderLayout.CENTER);
        jf.add(jb, BorderLayout.SOUTH);

        jf.setSize(380, 110);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    public static int[] getRandIntArr(){
        Random rand = new Random();
        int[] arr = new int[100];
        for(int i=0; i<100; i++){
            arr[i] = rand.nextInt();
        }
        return arr;
    }
}
