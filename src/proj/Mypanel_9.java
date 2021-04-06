package proj;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mypanel_9 extends JPanel
{
Image backimage;
public Mypanel_9()
{
    try
    {
   
     backimage = new ImageIcon(getClass().getResource("z.jpg")).getImage();
                      
    }
    catch(Exception ex)
    {
        
    }
}
  @Override
    protected void paintComponent(Graphics g)
    {
        if(backimage==null)
        {
            super.paintComponent(g);
        }
        else
        {
            g.drawImage(backimage,0,0,getWidth(),getHeight(),this);
        }
    }
}
