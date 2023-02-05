import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Options
{
  //empty constructor
	public Options()
	{

	}

  //check if normal button is clicked
  public boolean normal_check(int x,int y)
  {
    if((x >= 160) && (y >= 185) && (x < 310) && (y < 226))
      return true;
    return false;
  }

  //check if hard button is clicked
  public boolean hard_check(int x,int y)
  {
    if((x >= 160) && (y >= 245) && (x < 310) && (y < 286))
      return true;
    return false;
  }

  //check if back button is clicked
  public boolean back_check(int x,int y)
  {
    if((x >= 42) && (y >= 308) && (x < 129) && (y < 349))
      return true;
    return false;
  }

  //draw options page
  public void draw(Graphics2D g2d, int hard)
  {
    String s;
    if(hard==1)
      s="Hard";
    else
      s="Normal";

    //White text graphics
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 26));
    g2d.drawString("Back",50,340);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 32));
    g2d.drawString("Current mode: " + s,65,140);
    g2d.drawString("Normal",175,218);
    g2d.drawString("Hard",200,278);


    //header and rectangles
    g2d.setColor(new Color(0,102,0));
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    g2d.drawString("Options",65,70);
    g2d.drawRect(42,308,87,41);
    g2d.drawRect(41,307,89,43);

    g2d.drawRect(160,185,150,41);
    g2d.drawRect(159,184,152,43);
    g2d.drawRect(158,183,154,45);

    g2d.drawRect(160,245,150,41);
    g2d.drawRect(159,244,152,43);
    g2d.drawRect(158,243,154,45);
  }

}