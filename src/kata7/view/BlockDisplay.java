package kata7.view;

import kata7.model.Block;

/**
 *
 * @author Luzma
 * @version 23-12-2020
 * @since Práctica de Laboratorio
 */
public interface BlockDisplay{
    public static final int size = 100;
    public void paintBlock(int x, int y);
    
    public void on(Moved moved);
    public interface Moved{
        public void to(int x, int y);
        
    }
}
