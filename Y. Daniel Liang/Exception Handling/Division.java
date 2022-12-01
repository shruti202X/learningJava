import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Division {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Exercise18_2");
        jf.setLayout(new BorderLayout(2, 4));//hgap, vgap
        
        JPanel jp = new JPanel();//flowlayout -> default layout
        jp.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 4));//align, hgap, vgap
        //jp.setLayout(new GridLayout());//Creates a grid layout with a default of one column per component, in a single row.
        //jp.setLayout(new GridLayout(1, 6));//rows, cols
        //jp.setLayout(new GridLayout(1, 6, 2, 5));//rows, cols, hgap, vgap
        JLabel jlbl_n1 = new JLabel("Number 1");
        JLabel jlbl_n2 = new JLabel("Number 2");
        JLabel jlbl_res = new JLabel("Result");
        JTextField n1 = new JTextField(6);//columns
        JTextField n2 = new JTextField(6);
        JTextField res = new JTextField(6);
        res.setEnabled(false);
        jp.add(jlbl_n1);
        jp.add(n1);
        jp.add(jlbl_n2);
        jp.add(n2);
        jp.add(jlbl_res);
        jp.add(res);

        JButton jb = new JButton("Divide");
        
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String n1_str = n1.getText();
                String n2_str = n2.getText();
                try{
                    int n1_int = Integer.parseInt(n1_str);
                    int n2_int = Integer.parseInt(n2_str);
                    int res_int = n1_int / n2_int;
                    res.setText(String.valueOf(res_int));
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(jf, "Number format errors", "Warning", JOptionPane.WARNING_MESSAGE);
                    //Occurs if input String cant be converted to integer values
                    //Examples: "", "wewe", "32ds".
                }catch(ArithmeticException ex){
                    JOptionPane.showMessageDialog(jf, "Arithmetic errors (division by 0) ", "Warning", JOptionPane.WARNING_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(jf, ex.toString(), "Title", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        jf.add(jp, BorderLayout.NORTH);
        jf.add(jb, BorderLayout.SOUTH);//Component comp, Object constraints

        jf.pack();//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
