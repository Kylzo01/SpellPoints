
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    private static JLabel spellPointsCount = new JLabel("");
    private static JButton concentrating = new JButton("Concentrating");
    private static JButton[] spellButton = new JButton[9];
    private static int max;
    private static int level = 0;
    private static int charisma = 0;
    private static int curInsp = 0;
    private static int curPoints = 0;
    private static JButton[] bardicInsp;
    private static ArrayList<String> spellsUsed = new ArrayList<>();
    private static JButton[]isntrumentButton;
    private static Integer[] pointsToLevel = {4,6,14,17,27,32,38,44,57,64,73,73,83,83,94,94,107,114,123,133};
    
    public static void main(String[] args) {
        //Do frame shit        
        
        frame.setSize(400,1000);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveToTXT();
                System.exit(0);
            }
        });
        
        
        
        
        getStats();
        
        if(level == 0){
            
            writeToTextFileDialog wtf = new writeToTextFileDialog(frame);
            
            wtf.setVisible(true);
            
            Integer[] in = wtf.returnThings();
            
            level = in[0];
            charisma = in[1];
            
            curPoints = pointsToLevel[level-1];            
            
        }

        max = pointsToLevel[level-1];
        
        JMenuBar menuBar = new JMenuBar();
        JMenu rests = new JMenu("Resting");
        JMenu extra = new JMenu("Extras");
        
        JMenuItem longRest = new JMenuItem("Long Rest");
        JMenuItem shortRest = new JMenuItem("Short Rest");
        
        menuBar.add(rests);
        rests.add(longRest);
        rests.add(shortRest);
        
        JMenuItem levelUp = new JMenuItem("Level Up");
        
        menuBar.add(rests);
        menuBar.add(extra);
        extra.add(levelUp);
        
        
        frame.setJMenuBar(menuBar);
        
        
        
        longRest.addActionListener(new buttonPress());
        shortRest.addActionListener(new buttonPress());
        levelUp.addActionListener(new buttonPress());
        
        JPanel splitUp = new JPanel(new GridLayout(3,1));
        
        
        
        
        splitUp.add(setUpPoints());
        splitUp.add(setUpInspiration());
        splitUp.add(setUpInstrument());
        splitUp.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        frame.add(splitUp);
        
        
    }
    
    
    
    public static void getStats(){
        
        
        try{
            
            File file = new File("statTracker.txt");
            Scanner sc = new Scanner(file);
            String[] inp = new String[2];
            if (sc.hasNext()) {
                inp = sc.nextLine().split(",");
            
            
            level = Integer.parseInt(inp[0]);

            charisma = Integer.parseInt(inp[1]);
            
            }
            if (sc.hasNext()) {
                inp = sc.nextLine().split(",");
            
            curPoints = Integer.parseInt(inp[0]);
            curInsp = Integer.parseInt(inp[1]);
            }
            String[] spInp = new String[8];
            
            if (sc.hasNext()) {
                spInp = sc.nextLine().split(",");
            
            
            for (int i = 0; i < spInp.length; i++){
                
                spellsUsed.add(spInp[i]);
                
            }
            
            }
        } catch (IOException e) {

        }

        
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
        
        spellPointsCount.setText(curPoints + "");
        
        
        concentrating.addActionListener(new buttonPress());
        
        spellPointsCount.setHorizontalAlignment(JLabel.CENTER);
        
        spellPoints.add(spellAddRemove);
        spellPoints.add(spellPointsCount);
        spellPoints.add(concentrating);
        
        return spellPoints;
        
    }
    
    
    private static JPanel setUpInspiration(){
        
        JPanel mainPanel;
        
        JPanel subPanel;
        JPanel subPanelBottom = new JPanel(new GridLayout(1,3));
        
        
        if(charisma <= 3){
            
            mainPanel = new JPanel(new GridLayout(1,1));
            subPanel = new JPanel(new GridLayout(1,charisma));
            
        }else{
            
            mainPanel = new JPanel(new GridLayout(2,1));
            subPanel = new JPanel(new GridLayout(1,3));
            
            
        }
         

        bardicInsp = new JButton[charisma];
        
        
        for (int i = 0; i < charisma; i++) {
            
            bardicInsp[i] = new JButton("<html>Bardic<br/>Inspiration</html>");
            bardicInsp[i].addActionListener(new buttonPress());
            
            
            if(charisma <= 3){
                subPanel.add(bardicInsp[i]);
            }
        }
        
        
        for (int i = 0; i < curInsp; i++) {
            
            bardicInsp[i].setEnabled(false);
                
            
        }
        
        if(charisma > 3){
            
            subPanel.add(bardicInsp[0]);
            subPanel.add(bardicInsp[1]);
            subPanel.add(bardicInsp[2]);
            subPanelBottom.add(bardicInsp[3]);
            subPanelBottom.add(bardicInsp[4]);
            mainPanel.add(subPanel);
            mainPanel.add(subPanelBottom);
            
        }else{
        
            mainPanel.add(subPanel);
        
        }
        
        mainPanel.setBorder(new EmptyBorder(30, 10, 30, 10));
        
        
        return mainPanel;
        
    }
    
    private static JPanel setUpInstrument(){
        
        String[] baseSpells = {"Invisibility","Fly","Levitate","<html><center>Protect from<br/>evil and good</center></html>","<html><center>Protect from<br/>fire</center></html>","<html><center>Animal<br/>Friendship</center></html>", "<html><center>Protect from<br>Poison</center></html>", "<html><center>They Love Me<br/>They Really Love Me</center></html>"};
     
        double siz = Math.ceil((baseSpells.length)/2);
        int size = (int) siz;
        
        JPanel mainPanel = new JPanel(new GridLayout(size,1));
        
        isntrumentButton = new JButton[baseSpells.length];
        
        for (int i = 0; i < baseSpells.length; i++) {
            
            isntrumentButton[i] = new JButton(baseSpells[i]);
            isntrumentButton[i].addActionListener(new buttonPress());
            
            
            isntrumentButton[i].setMargin(new Insets(0,-30, 0,-30));
            mainPanel.add(isntrumentButton[i]);
            
            if(spellsUsed.contains(baseSpells[i])){
                
                isntrumentButton[i].setEnabled(false);
                
            }
                        
        }
        
        return mainPanel;
        
    }
    
    
    
    
    
    
    public static void saveToTXT(){
        
        try{
            
            File file = new File("statTracker.txt");

            FileWriter writer = new FileWriter(file);

            writer.write(level + "," + charisma);
            writer.write("\n" + spellPointsCount.getText() + "," + curInsp);
            
            
            String output = "\n";
            
            for(int i = 0; i < spellsUsed.size(); i++){
                
                if(i != spellsUsed.size()-1){
                    output += spellsUsed.get(i) + ",";
                }else{
                    output += spellsUsed.get(i);
                }
                
            }
            writer.write(output);
            writer.close();
        
        
        }catch(Exception e){System.out.println(e);}
        
    }
    
    
    
    
    
    
    
    
    
    public static class buttonPress implements ActionListener{

        final Color VERY_LIGHT_RED = new Color(255,102,102);
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
             String input = e.getActionCommand();
             
             
             if (input.equals("Level Up")){
                 
                 String m = JOptionPane.showInputDialog("What is your new level?");
                 
                 boolean letMeOut = false;
                 
                 while(!letMeOut){
                     
                    try{

                       Integer.parseInt(m);
                       letMeOut = true;
                       
                    }catch(Exception f){

                         m = JOptionPane.showInputDialog("That wasn't a number\nNow, What is your new level?");
                         letMeOut = false;
                    }
                 
                 }
                 
                 level = Integer.parseInt(m);
                 curPoints = pointsToLevel[level-1];
                 spellPointsCount.setText(curPoints + "");
                 max = curPoints;
                 
                 
             }else if(input.contains("Level")){
                                 
                 
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
                 
             }else if(input.equals("Long Rest")){
                 
                 spellPointsCount.setText(max + "");
                                  
                 for(int i = 0; i < charisma; i++){
                     
                     bardicInsp[i].setEnabled(true);
                     
                 }
                 
                  for(int i = 0; i < isntrumentButton.length; i++){
                      
                    isntrumentButton[i].setEnabled(true);
                    
                  }
                  
                  spellsUsed.removeAll(spellsUsed);
                  
                  
                  curPoints = max;
                  curInsp = 0;
                  
             }else if(input.equals("Short Rest")){
                 
                 for(int i = 0; i < charisma; i++){
                     
                     bardicInsp[i].setEnabled(true);
                     
                 }
                 curInsp = 0;
                 
             }else if(input.contains("Inspiration")){
                 
                Object source = e.getSource();
                 
                if (source instanceof JButton) {
                    JButton button = (JButton) source;
                    button.setEnabled(false);
                    spellsUsed.add(button.getText());
                                        
                }
                
                
                curInsp++;
                if(curInsp>5){
                    curInsp = 5;
                }
                     
                     
             }else{
                 
                 Object source = e.getSource();
                 
                if (source instanceof JButton) {
                    JButton button = (JButton) source;
                    button.setEnabled(false);
                    spellsUsed.add(button.getText());
                    
                    
                }
                 
             }
            
            
        }
        
        
        
    }
    
}
