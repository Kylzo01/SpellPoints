
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kyle Taylor
 */
public class writeToTextFileDialog extends JDialog {

    JTextArea level,charisma;

    public writeToTextFileDialog(JFrame frame) {

        super(frame, true);

        this.setTitle("Save Information");
        this.setSize(200, 200);

        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton confirm = new JButton("Confirm");

        JLabel levelTxt = new JLabel("Level");
        JLabel charismaTxt = new JLabel("Charisma Modifier +...");
        
        level = new JTextArea();
        charisma = new JTextArea();
        
        confirm.addActionListener(new buttonIsPressed());


        mainPanel.add(levelTxt);
        mainPanel.add(level);
        mainPanel.add(charismaTxt);
        mainPanel.add(charisma);
        
        mainPanel.add(confirm);

        this.add(mainPanel);

    }
    
    public Integer[] returnThings(){
        
        Integer[] array = {Integer.parseInt(level.getText()), Integer.parseInt(charisma.getText())};
        
        return array;
        
    }

    class buttonIsPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("Confirm")){

                dispose();

            }

        }

    }

}
