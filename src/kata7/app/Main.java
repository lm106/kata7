package kata7.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.model.Block;
import kata7.view.BlockDisplay;

/**
 *
 * @author Luzma
 * @version 23-12-2020
 * @since Práctica de Laboratorio
 */
public class Main extends JFrame{
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().execute();
    }
    
    
    private Block block;
    private BlockDisplay blockDisplay;
    private BlockPresenter blockPresenter;
    private Map<String, Command> commands;
    
    public Main(){
        this.setTitle("Block shifter");
        this.setSize(700, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    
    private void execute() {
        this.block = new Block();
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private BlockPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel(Block.MAX);
        this.blockDisplay = blockPanel;
        return blockPanel;
    }
    private Map<String, Command> createCommands(){
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("L", new LeftCommand(block));
        commands.put("D", new DownCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("R", new RightCommand(block));
        return commands;
    }

    private Component toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("L"));
        toolbar.add(button("D"));
        toolbar.add(button("U"));
        toolbar.add(button("R"));
        
        
        return toolbar;
    }

    private JButton button(String name) {
        JButton buton = new JButton(name);
        buton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get(name).execute();
            }
            
        });
        return buton;
    }
}
