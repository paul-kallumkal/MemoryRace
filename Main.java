import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame
{
    public Main()
    {
        //Main's constructor calls the function that initializes the game.
        initUI();
    }

    private void initUI()
    {
        //Puts the Game to the center of the JFrame container.
        add(new Game());
        
        //Sets the size of the window.
        setSize(500, 400);

        //Sets whether the frame can be resized.
        setResizable(false);

        //Sets the title of the window.
        setTitle("Memory Race");

        //Close the application when the user clicks on the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Centers the window on the screen. 
        setLocationRelativeTo(null);
        
    }

    public static void main(String[] args)
    {
        //Entry point of the game
        EventQueue.invokeLater(() -> {JFrame ex = new Main(); ex.setVisible(true);});
    }
}