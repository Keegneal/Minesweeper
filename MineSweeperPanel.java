import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MineSweeperPanel extends JPanel{
    

    private JButton [][] board;
    private int row =8;
    private int col =8;
    private boolean [][] mines;
    private int unrevealedCells;
    public MineSweeperPanel(){

        setLayout(new GridLayout(row, col));
        unrevealedCells = row * col - 5;
        
        board = new JButton[row][col];
        mines = new boolean[row][col];

        setMines();

        for(int i =0; i < row; i++){
            for(int j = 0; j< col; j++){
                board[i][j] = new JButton("");
                add(board[i][j]);

                int newRow = i;
                int newCol = j;
                
                board[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        if(e.getButton() == MouseEvent.BUTTON1){
                            System.out.println("left click detected");
                            if(board[newRow][newCol].getText().equals("")){
                                
                                revealCell(newRow, newCol);
                                
                            }
                        }
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                                
                                System.out.println("rightclick detected");
                            if (board[newRow][newCol].getText().equals("")) {
                                board[newRow][newCol].setText("F");
                             } else if (board[newRow][newCol].getText().equals("F")) {
                                 board[newRow][newCol].setText("");
                        }
                    }
                }
                });
            }
        }
        setMines();

    }
      
      public void setMines() {
        Random rand = new Random();
        int numMines = 5;
        while(0 < numMines){
            int row = rand.nextInt(8);
            int col = rand.nextInt(8);
            if(!mines[row][col])
            mines[row][col] = true;
            numMines--;
        }   
    }
    public void revealCell(int row, int col){
        int mineCount=0;
                 if(mines[row][col]){
                board[row][col].setText("BOMB");
                gameOver();
             }
             else{
               
                mineCount = surroundMines(row, col);
                board[row][col].setText(Integer.toString(mineCount));
                unrevealedCells--;

                // Check if the player has won
                if (unrevealedCells == 0) {
                    gameWon();
                }
                
              }
               if (mineCount == 0) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newRow = row + i;
                        int newCol = col + j;
                        if (newRow >= 0 && newRow < this.row && newCol >= 0 && newCol < this.col) {
                            if (board[newRow][newCol].getText().equals("")) {
                                revealCell(newRow, newCol);
                            }
                        }
                    }
                }
            }
            
        }

        public int surroundMines(int row, int col) {
            int mineCount = 0;
        
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (newRow >= 0 && newRow < this.row && newCol >= 0 && newCol < this.col) {
                        if (mines[newRow][newCol]) {
                            mineCount++;
                        }
                    }
                }
            }
            return mineCount;
        }
    
    public void gameOver() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mines[i][j]) {
                    board[i][j].setText("BOMB"); 
                }
            }
        }
        JOptionPane.showMessageDialog(null, "GAME OVER");
    } 
     public void gameWon() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mines[i][j]) {
                    board[i][j].setText(" "); 
                }
            }
        }
        JOptionPane.showMessageDialog(null, "WINNER WINNER CHICKEN DINNER");
    }
}
      

        

    

   

