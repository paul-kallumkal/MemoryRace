import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Help
{
  private int level;
  private int tscore;
  private ArrayList<Square> sqs;


  //initialise stage 1
	public Help()
	{
    this.level = 0;
    this.tscore = 0;
    this.sqs = new ArrayList<Square>();
    this.sqs.add(new Square(1,4));
    this.sqs.add(new Square(2,5));
    this.sqs.add(new Square(4,6));
    this.sqs.add(new Square(3,7));
	}

  public void win_check(int p)
  {
    //stage 1 check
    if(level == 0)
    {
      for(int i=0;i<sqs.size();i++)
      {
        if(sqs.get(i).val == tscore + 1 && sqs.get(i).pos == p)
          {
            sqs.remove(i);
            tscore++;
            break;
          }
      }
    }

    //stage 2 check
    else if(level == 1)
    {
       for(int i=0;i<sqs.size();i++)
      {
        if(sqs.get(i).val == tscore + 1 && sqs.get(i).pos == p)
          {
            if(tscore == 0)
            {
              for(int j=0;j<4;j++)
                sqs.get(j).vis = false;
            }
            sqs.remove(i);
            tscore++;
            break;
          }
      }
    }

    //initialise second stage
    if(tscore == 4 && level == 0)
    {
      level=1;
      tscore=0;
      sqs.add(new Square(1,6));
      sqs.add(new Square(2,7));
      sqs.add(new Square(3,4));
      sqs.add(new Square(4,5));
    }

    //second stage complete
    if(tscore == 4 && level == 1)
    {
      level = 2;
    }


  }

  //check if back button is clicked
  public boolean back_check(int x,int y)
  {
    if((x >= 42) && (y >= 308) && (x < 129) && (y < 349))
      return true;
    return false;
  }

  //draw help page
  public void draw(Graphics2D g2d)
  {
    //Draws "Back" text
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 26));
    g2d.drawString("Back",50,340);

    //initial instructions
    if(level == 0)
    {
      g2d.drawString("Click the sqaures in the correct",65,120);
      g2d.drawString("order, starting with 1",65,160);
      for(Square s : sqs)
        s.draw(g2d);
    }

    //second tutorial stage
    else if(level == 1)
    {
      g2d.drawString("Now remember where they are!",65,120);
      for(Square s : sqs)
        s.draw(g2d);
    }

    //tutorial complete
    else if(level == 2)
    {
      g2d.drawString("You're now ready!",65,140);
    }

    //Draws title and back rectangle
    g2d.setColor(new Color(0,102,0));
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    g2d.drawString("Help",65,70);
    g2d.drawRect(42,308,87,41);
    g2d.drawRect(41,307,89,43);
  }

}