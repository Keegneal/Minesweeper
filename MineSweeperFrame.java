import javax.swing.*;


public class MineSweeperFrame extends JFrame{
    public MineSweeperFrame(){
    setTitle("Minesweeper");
    setSize(600, 600);
    add(new MineSweeperPanel());
    setVisible(true);
}
}
