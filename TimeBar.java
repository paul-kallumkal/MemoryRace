import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TimeBar
{
  private int length;

  //constructor setting length value
  public TimeBar()
  {
    this.length = 200;
  }

  //draws centered rectangle
  public void draw(Graphics2D g2d)
	{
    g2d.setColor(new Color(0,102,0));
		g2d.fillRect(250-this.length/2,35,this.length,6);
  }

  //reduces length if possible or else return false
  public boolean shrink()
  {
    if(this.length == 0)
    {
      return false;
    }
    else
    {
      this.length -=2;
      return true;
    }
  }

  public void reset()
  {
    this.length = 200;
  }
}