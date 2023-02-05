import java.awt.Graphics2D;
import java.awt.Color;

public class Square
{
  //block coordinates same for all instances
  static int[] X = new int[]{80,180,280,380};
  static int[] Y = new int[]{140,220,300};

	public int val;
  public int pos;
  public boolean vis;

  //constructor requires value and block number
	public Square(int val, int pos)
	{
    this.val = val;
    this.pos = pos;
    this.vis = true;
	}

  //draw square
	public void draw(Graphics2D g2d)
	{

    //to center the text
    int shift = 5;
    if(this.val<10)
      shift += 7;

    //sqaure graphic and text at block position
		g2d.setColor(new Color(0,102,0));
		g2d.fillRect(X[pos%4],Y[pos/4],40,40);
    if(this.vis)
    {
      g2d.setColor(Color.WHITE);
      g2d.setFont(g2d.getFont().deriveFont(24f)); 
      g2d.drawString(Integer.toString(val),X[pos%4]+shift,Y[pos/4] + 30);
    }
	}
	
}