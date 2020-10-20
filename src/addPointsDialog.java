
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
public class addPointsDialog extends JDialog {

    JTextArea input;
    int currentPoints, max;

    public addPointsDialog(JFrame frame, int currentPoints, int max) {

        super(frame, true);

        this.currentPoints = currentPoints;
        this.max = max;

        this.setTitle("Add/Remove Points");
        this.setSize(200, 200);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        input = new JTextArea();
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        add.addActionListener(new buttonIsPressed());
        remove.addActionListener(new buttonIsPressed());

        JPanel subPanel = new JPanel(new GridLayout(1, 2));

        subPanel.add(add);
        subPanel.add(remove);

        mainPanel.add(input);
        mainPanel.add(subPanel);

        this.add(mainPanel);

    }

    public int getPoints() {

        return currentPoints;

    }

    class buttonIsPressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("Add")) {

                try {

                    if (currentPoints + Integer.parseInt(input.getText()) <= max) {

                        currentPoints += Integer.parseInt(input.getText());

                    } else {

                        System.out.println(max);
                        currentPoints = max;

                        JOptionPane.showMessageDialog(null, "Number of points was higher than your max. Adjusted to fit");
                    }

                    dispose();

                } catch (Exception f) {

                    JOptionPane.showMessageDialog(null, "This isn't a number you silly goose");

                }

            } else {

                try {

                    if (currentPoints - Integer.parseInt(input.getText()) >= 0) {

                        currentPoints -= Integer.parseInt(input.getText());

                    } else {

                        currentPoints = 0;

                        if (JOptionPane.showConfirmDialog(null, "This will put your points below 0\nThis will be adgusted to 0?\nAre you sure", "WARNING",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            
                            currentPoints = 0;
                            
                        } 
                    }

                    dispose();

                } catch (Exception f) {

                    JOptionPane.showMessageDialog(null, "This isn't a number you silly goose");

                }

            }

        }

    }

}
