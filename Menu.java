import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Menu
{

  //empty constructor
	public Menu()
	{

	}

  //if play button is clicked
  public boolean play_check(int x,int y)
  {
    if((x >= 160) && (y >= 125) && (x < 310) && (y < 166))
      return true;
    return false;
  }
  
  //if help button is clicked
  public boolean help_check(int x,int y)
  {
    if((x >= 160) && (y >= 185) && (x < 310) && (y < 226))
      return true;
    return false;
  }

  //if options button is clicked
  public boolean option_check(int x,int y)
  {
    if((x >= 160) && (y >= 245) && (x < 310) && (y < 286))
      return true;
    return false;
  }

  //if exit button is clicked
  public boolean exit_check(int x,int y)
  {
    if((x >= 160) && (y >= 305) && (x < 310) && (y < 346))
      return true;
    return false;
  }


  //draw the menu
	public void draw(Graphics2D g2d)
	{
		
    //Menu options
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 32));
    g2d.drawString("Play",200,155);
    g2d.drawString("Help",200,215);
    g2d.drawString("Options",175,275);
    g2d.drawString("Exit",200,338);

    //Main title
    g2d.setColor(new Color(0,102,0));
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    g2d.drawString("Memory Race",75,70);

    //Rectangle outlines
    g2d.drawRect(160,125,150,41);
    g2d.drawRect(159,124,152,43);
    g2d.drawRect(158,123,154,45);

    g2d.drawRect(160,185,150,41);
    g2d.drawRect(159,184,152,43);
    g2d.drawRect(158,183,154,45);

    g2d.drawRect(160,245,150,41);
    g2d.drawRect(159,244,152,43);
    g2d.drawRect(158,243,154,45);

    g2d.drawRect(160,305,150,41);
    g2d.drawRect(159,304,152,43);
    g2d.drawRect(158,303,154,45);
    
	}
	
}