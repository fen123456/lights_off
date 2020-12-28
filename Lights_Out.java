
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* The aim of lights out is to turn all of the lights (buttons) off (set to 0)
 * Each press of any button switches the button and its neighbours
 * Your job is to set up the buttons and action listeners
 */

/* IF YOU HAVEN'T WORKED WITH JBUTTONS AND ACTION LISTNERS BEFORE:
 * 
 */
public class Lights_Out extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final int W = 800, H = 400;
    private int solvedLights = 0;
    private static int COLS = 2, ROWS = 2;
    private JButton[][] lights = new JButton[COLS][ROWS];

    public Lights_Out() {
        super("Lights Out"); 
        setSize(W,H);
        setLayout(new GridLayout(COLS,ROWS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[][] input = generateStart(COLS, ROWS);
        for(int i = 0; i < COLS; i++) {
            for(int j = 0; j < ROWS; j++) {

                lights[i][j] = new JButton();
                lights[i][j].addActionListener(this); 

                lights[i][j].setText(input[i][j]);
                add(lights[i][j]);

            }
        }
    }

   public static void main(String[] args){
       Lights_Out game1 = new Lights_Out();
       game1.setVisible(true);
    }
    public static String[][] generateStart(int cols, int rows) {
        int current;
        String[][] start = new String[cols][rows];
        for (int i=0; i < cols; i++) {
            for (int j=0; j < rows; j++) {
                current = (int)(Math.random()+0.5);
                start[i][j] = Integer.toString(current);
            }
        }
        return start;
    }
    public void actionPerformed(ActionEvent e) {
        solvedLights = 0;
        Object source = e.getSource();
        for(int i = 0; i < COLS; i++) {
            for(int j = 0; j < ROWS; j++) {
                if (source.equals(lights[i][j])) {
                  handle(i, j);
                }
            }
        }
        for(int i = 0; i < COLS; i++) {
            for(int j = 0; j < ROWS; j++) {
                if (lights[i][j].getText() == "0") {
                    solvedLights++;
                }
            }
        }
        System.out.println(solvedLights);
        if (solvedLights == COLS*ROWS) {
            System.out.println("You've Won!");
        }
    }    
    
    private void toggle(int i, int j) {
        if (0 <= i && i < COLS && 0 <= j && j < ROWS) {
            JButton toggling = lights[i][j];
            String oldText = toggling.getText();
            String newText = "1".equals(oldText) ? "0" : "1";
            toggling.setText(newText);
        }
    }
    private void handle(int i, int j) {
        toggle(i, j);
        toggle(i+1, j);
        toggle(i-1, j);
        toggle(i, j+1);
        toggle(i, j-1);
    }
}