package kata7.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata7.view.BlockDisplay;

/**
 *
 * @author Luzma
 * @version 23-12-2020
 * @since Práctica de Laboratorio
 */
public class BlockPanel extends JPanel implements BlockDisplay{
    private static final int size = 100;
    private int x,y, MAX;
    private final MouseHandler handler;
    private Moved moved;

    public BlockPanel(int MAX) {
        this.MAX = MAX;
        this.handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        int m = MAX * size;
        for (int i = 0; i <= 7; i++) {
            int d = i* size;
            g.drawLine(0, d, m, d);
            g.drawLine(d, 0, d, m);
            
        }
        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{

        private boolean grabbed;

        @Override
        public void mouseClicked(MouseEvent me) {}

        @Override
        public void mousePressed(MouseEvent me) {
            if((me.getX() < x) | me.getX() > x + size) return;
            if((me.getY() < y) | me.getY() > y + size) return;
            grabbed = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            grabbed = false;
        }

        @Override
        public void mouseEntered(MouseEvent me) { }

        @Override
        public void mouseExited(MouseEvent me) {}

        @Override
        public void mouseDragged(MouseEvent me) {
            moved.to(me.getX(), me.getY());
        }

        @Override
        public void mouseMoved(MouseEvent me) {}
    
    }
}
