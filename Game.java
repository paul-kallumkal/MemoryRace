import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Random;

enum STATE 
{
  GAME, MENU, OPTIONS, HELP
}

public class Game extends JPanel implements ActionListener
{

  //game variables
  private Random rand;
  private ArrayList<Square> squares;
  private ArrayList<Integer> blocks;
  private int score;
  private int highscore;
  private int hardhighscore;
  private boolean win;
  private int hard;

  //state variables
  public STATE state;
  private Menu menu;
  private Help help;
  private Options options;
  
  //Timer variables
  private TimeBar bar;
  private final int DELAY = 27;
	private Timer timer;

	//Initializes the game.
	public void init()
	{
    //reset all values
    this.score = 0;
    this.win = true;
    this.bar.reset();
    squares.clear();
    blocks.clear();
    for(int i=0;i<12;i++)
      this.blocks.add(i);
    
    //placing squares in random places
    for(int i=0;i<5+hard;i++)
    {
      int r = rand.nextInt(blocks.size());
      squares.add(new Square(i+1,blocks.get(r)));
      blocks.remove(r);
    }

    //starting game
    this.timer.start();
    repaint();
	}

	//Updates all game objects.
  public void step(ActionEvent e)
	{
    //game over if bar cannot shrink further
    if(score>0 && !bar.shrink(hard))
    {
      this.timer.stop();
      for(int i=0;i<4+hard;i++)
        squares.get(i).vis=true;
      this.win = false;
      repaint();
    }
	}

  //If retry button is clicked
  public void retry_check(int x, int y)
  {
    if ((x >= 344) && (y >= 56) && (x < 421) && (y < 87))
      init();
  }

  //if back button is clicked
  public void back_check(int x, int y)
  {
    if((x >= 344) && (y >= 89) && (x < 421) && (y < 120))
      state = STATE.MENU;
    repaint();
  }

  //check which block area was clicked
  public int block_check(int x, int y)
  {
    int[] X = new int[]{80,180,280,380};
    int[] Y = new int[]{140,220,300};

    for(int i=0;i<4;i++)
      for(int j=0;j<3;j++)
        if ((x >= X[i]) && (y >= Y[j]) && (x < X[i] + 40) && (y < Y[j] + 40)){
          return i + 4*j;
        }
    return -1;
  }

  //Main game logic
  //Ends game if wrong filled block is clicked
  //Increments score and adds new block if correct block is clicked
  //Makes all values visible on losing
  public void win_check(int p)
  {
    if(p==-1) 
      return;
    for(int k=0;k<squares.size();k++)
    {
      if(squares.get(k).pos == p)
      {
        if(squares.get(k).val == score + 1)
        {
          score++;
          squares.remove(k);
          if(score == 1)
          {
            for(int i=0;i<4+hard;i++)
              squares.get(i).vis=false;
          }
          else
          {
            squares.get(3+hard).vis=false;
          }
          int r = rand.nextInt(blocks.size());
          squares.add(new Square(score+5+hard,blocks.get(r)));
          blocks.remove(r);
          blocks.add(p);
          bar.reset();
        }
        else if(score != 0)
        {
          for(int i=0;i<4+hard;i++)
            squares.get(i).vis=true;
          this.timer.stop();
          this.win = false;
        }
        break;
      }
    }
    repaint();
  }

	//Draws all game objects.
	public void draw(Graphics2D g2d)
	{
    //draw game state
    if(state == STATE.GAME)
    {
      //draws all sqaures
      for(Square s : squares)
        s.draw(g2d);

      //draws centered score if game is ongoing
      if(win)
      {
        int shift = 0;
        if(score>9)
          shift = -20;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g2d.drawString(Integer.toString(score),235+shift,100);
      }

      //On losing draws score, highscore, retry and back
      else
      {
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        if(hard==1)
        {
          if(score>hardhighscore)
            hardhighscore = score;
          g2d.drawString("High score: " + Integer.toString(hardhighscore),90,114);
        }
        else
        {
          if(score>highscore)
            highscore = score;
          g2d.drawString("High score: " + Integer.toString(highscore),90,114);
        }
        g2d.drawString("Your score: " + Integer.toString(score),90,80);
        
        //draws retry and back buttons
        g2d.drawString("Retry",350,80);
        g2d.drawString("Back",350,114);
        g2d.setColor(new Color(0,102,0));
        g2d.drawRect(344,56,77,31);
        g2d.drawRect(345,57,75,29);
        g2d.drawRect(344,89,77,31);
        g2d.drawRect(345,90,75,29);
      }
      bar.draw(g2d);
    }

    //draw menu
    else if(state == STATE.MENU)
    {
      menu.draw(g2d);
    }

    //draw help screen
    else if(state == STATE.HELP)
    {
      help.draw(g2d);
    }

    //draw options screen
    else if(state == STATE.OPTIONS)
    {
      options.draw(g2d,hard);
    }
	}

	//Activates on mouse click
	public void mouseClick(int x, int y, int button)
	{
	
    int p=-1;
    if(button == 0)
    {
      //When game is active
      if(state == STATE.GAME)
      {
        //ongoing game
        if(win)
        {
          p = block_check(x,y);
          win_check(p);
        }

        //game over state
        else
        {
          retry_check(x,y);
          back_check(x,y);
        }
      }

      //When menu is active
      else if(state == STATE.MENU)
      {
        //Play button clicked
        if(menu.play_check(x,y))
        {
          state = STATE.GAME;
          init();
        }

        //Help button clicked
        else if(menu.help_check(x,y))
        {
          this.help = new Help();
          state = STATE.HELP;
          repaint();
        }

        //Options button clicked
        else if(menu.option_check(x,y))
        {
          state = STATE.OPTIONS;
          repaint();
        }

        //Exit button clicked
        else if(menu.exit_check(x,y))
        {
          System.exit(0);
        }
      }

      //When help is active
      else if(state == STATE.HELP)
      {
        p = block_check(x,y);
        help.win_check(p);

        //going back to the menu
        if(help.back_check(x,y))
          state = STATE.MENU;
         
        repaint();
      }

      //When options is active
      else if(state == STATE.OPTIONS)
      {

        //going back to the menu
        if(options.back_check(x,y))
        {
          state = STATE.MENU;
          repaint();
        }

        //Selecting mode as hard
        else if(options.hard_check(x,y))
        {
          hard = 1;
          state = STATE.MENU;
          repaint();
        }

        //Selecting mode as normal
        else if(options.normal_check(x,y))
        {
          hard = 0;
          state = STATE.MENU;
          repaint();
        }
      }
    }
	}

	public Game()
	{
    //Initializes the game variables and the main menu
    setBackground(Color.black);
		addMouseListener(new GameMouseListener());
    this.squares = new ArrayList<Square>();
    this.blocks = new ArrayList<Integer>();
    this.score = 0;
    this.highscore = 0;
    this.rand = new Random();
    this.bar = new TimeBar();
    this.win = true;
    this.timer = new Timer(DELAY, this);
    this.state = STATE.MENU;
    this.menu = new Menu();
    this.help = new Help();
    this.options = new Options();
    this.hard = 0;
    this.hardhighscore = 0;

	}

	//Game graphics are drawn inside this method
	@Override
	public void paintComponent(Graphics g)
	{
		//Draws JPanel window
		super.paintComponent(g);

		//The Graphics2D class extends the Graphics class.
		//It provides more sophisticated control 
		Graphics2D g2d = (Graphics2D) g;

		//Draws all game components.
		draw(g2d);

		//Animation might not be smooth on Linux without this
		Toolkit.getDefaultToolkit().sync();
	}

	//Called regularly by the timer in game
	@Override
  public void actionPerformed(ActionEvent e)
	{
		//Updates all game objects.
		step(e);

		//Regularly redraw the Game, making the animation.
		repaint();
	}

	//Detects mouse inputs and location by the user.
	private class GameMouseListener implements MouseListener
	{
		//Activates on mouse click
		@Override
		public void mouseClicked(MouseEvent e)
		{
			//Gets coordinates of mouse click
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Figures out which button on mouse was pressed.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			//Sends mouse coordinates to game function.
			mouseClick(mouseX,mouseY,mouseButton);
		}

		//Activates when mouse button is pressed.
		@Override
		public void mousePressed(MouseEvent e)
		{
		}

		//Activates when mouse button is released.
		@Override
		public void mouseReleased(MouseEvent e)
		{
		}

		//Activates when mouse cursor enters window.
		@Override
		public void mouseEntered(MouseEvent e)
		{
		}

		//Activates when mouse cursor exits window.
		@Override
		public void mouseExited(MouseEvent e)
		{
		}
	}
	
}