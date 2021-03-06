package kata7.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Luzma
 * @version 23-12-2020
 * @since Práctica de Laboratorio
 */
public class Block {
    public static final int MAX = 7;
    private int x = 4;
    private int y = 4;
    private Timer timer;
    private List<Observer> observers = new ArrayList<>();
    
    public Block() {
        this.timer = new Timer();
        //this.timer.schedule(task(), 1000, 500);
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public void left() {
        if (x == 1) return;
        x--;
        changed();
    }
    
    public void right() {
        if (x == MAX) return;
        x++;
        changed();
    }
    
    public void up() {
        if (y == MAX) return;
        y++;
        changed();
    }
    
    public void down() {
        if (y == 1) return;
        y--;
        changed();
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                double r = Math.random();
                if (r > 0.2) return;
                if (r > 0.15) up();
                else if (r > 0.10) down();
                else if (r > 0.05) left();
                else right();
            }
        };
    }
    
    private void changed() {
        for (Observer observer : observers) 
            observer.changed();
    }
    
    public void register(Observer observer) {
        observers.add(observer);
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        changed();
    }
    
    public interface Observer {
        void changed();
    }
}