import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


public class Spot extends JPanel{
    public final int WIDTH = 800;
    public final int HEIGHT = 800;
    Grid grid;

    public Spot(){
        int test = 78780;
        System.out.println(Integer.toBinaryString(test));
        grid = new Grid();

        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        im.put(KeyStroke.getKeyStroke("RIGHT"), "shiftRight");
        getActionMap().put("shiftRight", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                grid.shiftRight();
                repaint();
            }
        });
        im.put(KeyStroke.getKeyStroke("LEFT"), "shiftLEFT");
        getActionMap().put("shiftLEFT", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                grid.shiftLeft();
                repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke("UP"), "shiftUP");
        getActionMap().put("shiftUP", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                grid.shiftUp();
                repaint();
            }
        });

        im.put(KeyStroke.getKeyStroke("DOWN"), "shiftDOWN");
        getActionMap().put("shiftDOWN", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                grid.shiftDown();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.drawLines(g);
        grid.drawCords(g);
    }

    @Override 
    public Dimension getPreferredSize(){
        return new Dimension(800, 800);
    }

    public class Grid {
        private final int BUFFER = 100;
        private static final int SHIFT_INC = 30;
        private final int ZMAX = WIDTH;

        //x line
        private int p1x = 102;
        private int p1y = 216;
        private int p2x = 697;
        private int p2y = 583;
        //y top to bottom
        private int p3x = WIDTH / 2;
        private int p3y = BUFFER;
        private int p4x = WIDTH / 2;
        private int p4y = HEIGHT - BUFFER;
        //z line
        private int p5x = 102;
        private int p5y = 583;
        private int p6x = 697;
        private int p6y = 216;


        public Grid(){
            System.out.println("made grid");
        }

        public void drawLines(Graphics g){
            g.drawLine(p1x, p1y, p2x, p2y);
            g.drawLine(p3x, p3y, p4x, p4y);
            g.drawLine(p5x, p5y, p6x, p6y);

        }

        public void drawCords(Graphics g){
            String xLineString = "XLine on:" +  Integer.toString(p1x) + "," + Integer.toString(p1y) + " - " + Integer.toString(p2x) + "," + Integer.toString(p2y);
            g.drawString(xLineString, 50, 750);
            String yLineString = "YLine on:" +  Integer.toString(p3x) + "," + Integer.toString(p3y) + " - " + Integer.toString(p4x) + "," + Integer.toString(p4y);
            g.drawString(yLineString, 300, 750);
            String zLineString = "zLine on:" +  Integer.toString(p5x) + "," + Integer.toString(p5y) + " - " + Integer.toString(p6x) + "," + Integer.toString(p6y);
            g.drawString(zLineString, 600, 750);
        }

        public void shiftLeft(){
            if(p1x < WIDTH / 2){
                p1x += SHIFT_INC;
            } else {
                p1x -= SHIFT_INC;
            }
            if(p2x > WIDTH / 2){
                p2x -= SHIFT_INC;
            } else {
                p2x += SHIFT_INC;
            }

            if(p5x < BUFFER){
                p5x += SHIFT_INC;
            } else {
                p5x -= SHIFT_INC;
            }
            if(p6x > WIDTH - BUFFER){
                p6x -= SHIFT_INC;
            } else {
                p6x += SHIFT_INC;
            }
        }

        public void shiftRight(){
            //rotate x1
            int quad = getQuad(p1x, p1y);
            switch(quad){
                case 1:
                    p1x += SHIFT_INC;
                    p1y -= SHIFT_INC;
                    break;
                case 2:
                    p1x += SHIFT_INC;
                    p1y += SHIFT_INC;
                    break;
                case 3: 
                    p1x -= SHIFT_INC;
                    p1y += SHIFT_INC;
                    break;
                case 4:
                    p1x -= SHIFT_INC;
                    p1y -= SHIFT_INC;
                default:
                    throw new NullPointerException("Error");
            }

            quad = getQuad(p2x, p2y);
            switch(quad){
                case 1:
                    p2x += SHIFT_INC;
                    p2y -= SHIFT_INC;
                    break;
                case 2:
                    p2x += SHIFT_INC;
                    p2y += SHIFT_INC;
                    break;
                case 3: 
                    p2x -= SHIFT_INC;
                    p2y += SHIFT_INC;
                    break;
                case 4:
                    p2x -= SHIFT_INC;
                    p2y -= SHIFT_INC;
                default:
                    throw new NullPointerException("Error");
            }
            quad = getQuad(p5x, p5y);
            switch(quad){
                case 1:
                    p5x += SHIFT_INC;
                    p5y -= SHIFT_INC;
                    break;
                case 2:
                    p5x += SHIFT_INC;
                    p5y += SHIFT_INC;
                    break;
                case 3: 
                    p5x -= SHIFT_INC;
                    p5y += SHIFT_INC;
                    break;
                case 4:
                    p5x -= SHIFT_INC;
                    p5y -= SHIFT_INC;
                default:
                    throw new NullPointerException("Error");
            }
            quad = getQuad(p6x, p6y);
            switch(quad){
                case 1:
                    p6x += SHIFT_INC;
                    p6y -= SHIFT_INC;
                    break;
                case 2:
                    p6x += SHIFT_INC;
                    p6y += SHIFT_INC;
                    break;
                case 3: 
                    p6x -= SHIFT_INC;
                    p6y += SHIFT_INC;
                    break;
                case 4:
                    p6x -= SHIFT_INC;
                    p6y -= SHIFT_INC;
                default:
                    throw new NullPointerException("Error");
            }

        }

        public void shiftUp(){

        }

        public void shiftDown(){

        }
        
    }

    private int getQuad(int xPoint, int yPoint){
        if(xPoint < 400 && yPoint < 400){
            return 1;
        } else if(xPoint >= 400 && yPoint < 400){
            return 2;
        } else if(xPoint >= 400 && yPoint >= 400){
            return 3;
        } else {
            return 4;
        }
    }  
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JFrame frame = new JFrame();
                frame.add(new Spot());
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
    }
}
