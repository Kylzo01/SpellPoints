
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class main extends JFrame{

    /**
     * @param args the command line arguments
     */
    private static JFrame frame = new JFrame();
    private static JLabel spellPointsCount = new JLabel("27");
    private static JButton concentrating = new JButton("Concentrating");
    private static JButton[] spellButton = new JButton[9];
    private static int max = 27;
    
    public static void main(String[] args) {
     
        //Do frame shit        
        
        frame.setSize(400,1000);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveToTXT();
            }
        });
        
        JPanel splitUp = new JPanel(new GridLayout(3,1));
        splitUp.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        
        splitUp.add(setUpPoints());
        
        frame.add(splitUp);
        
        
    }
    
    public static JPanel setUpPoints(){
        
         
        JPanel spellPoints = new JPanel(new GridLayout(4,3));
        JButton spellAddRemove = new JButton("Add or Remove");
        spellAddRemove.addActionListener(new buttonPress());
        Integer[] pointsToLevel = {2,3,5,6,7,9,10,11,13};
        
        
        for(int i = 0; i < 9; i++){
        
            spellButton[i] = new JButton("Level " + (i+1) + " (" + pointsToLevel[i] + ")");
            spellButton[i].addActionListener(new buttonPress());
            spellPoints.add(spellButton[i]);
            
            
        }
        
        concentrating.addActionListener(new buttonPress());
        
        spellPointsCount.setHorizontalAlignment(JLabel.CENTER);
        
        spellPoints.add(spellAddRemove);
        spellPoints.add(spellPointsCount);
        spellPoints.add(concentrating);
        
        return spellPoints;
        
    }
    
    private JPanel setUpInspiration(){
        
        return new JPanel();
        
    }
    
    private JPanel setUpInstrument(){
        
        return new JPanel();
        
    }
    
    
    
    public static void saveToTXT(){
        System.out.println("Poggers");
        System.exit(0);
    }
    
    public static class buttonPress implements ActionListener{

        final Color VERY_LIGHT_RED = new Color(255,102,102);
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
             String input = e.getActionCommand();
             
             if(input.contains("Level")){
                                 
                 
                 int i = Integer.parseInt(input.charAt(input.length()-2) + "");;
                 
                 
                 if(input.charAt(input.length()-3) == '1'){
                     
                     i+= 10;
                     
                 }
                 
                 int cur = Integer.parseInt(spellPointsCount.getText());
                 
                 int newPoints = (cur - i);
                 
                 if(newPoints >=0){
                 
                    spellPointsCount.setText(newPoints + "");
                 
                 }else{
                     
                     JOptionPane.showMessageDialog(null,"Not enough spell points");  
                     
                 }
             
             }else if (input.equals("Concentrating")){
                 
                 //if its already red then reset it
                 if(concentrating.getBackground().equals(VERY_LIGHT_RED)){
                     
                     concentrating.setBackground(spellButton[1].getBackground());
                    
                    
                 }else{
                     
                     concentrating.setBackground(VERY_LIGHT_RED);
                     
                     
                 }
                 
             }else if(input.equals("Add or Remove")){
                 addPointsDialog dialog = new addPointsDialog(frame, Integer.parseInt(spellPointsCount.getText()), max);
                 dialog.setVisible(true);
                 spellPointsCount.setText(dialog.getPoints() + "");
                 
             }
            
            
        }
        
        
        
    }
    
}
