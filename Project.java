package opencv;
/* we "Tahreem Saleem" and "Izzah Zaman" do verify that the submitted code is our own effort and that we have not copied it 
from any peer or any Internet source that has not been acknowledged. we also understand that if my 
submission fails the similarity detection, I would be awarded zero marks not only for this submission 
but the whole evaluation component. */

import java.awt.event.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import opencv.Mat2Image1.MyThread;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import java.awt.geom.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;


/*first class to set the main layout of the application , having buttons, images and mouse listener for
capturing the image through web-cam*/
/*In this class the main layout of the application is present 
 * 
 */

class Layout extends JPanel implements KeyListener, MouseListener 
{	
	public static int flag =0;
	private static final long serialVersionUID = 1L;
	static int strt=0, swp=360;
	 static int temp2=0;
	 static Mat2Image1 m2i= new Mat2Image1 ();
	static  JPanel jp=new JPanel(); 
	Image image;
	static JButton jb1;
	static JButton jb2;
	static JButton jb3;
		//adding buttons and setting background color for main layout
	{
		             
		JPanel jp2=new JPanel();
		jb1 = new JButton("New");
		jb2 = new JButton("Tutorial");
		jb3 = new JButton("Exit");
		jb1.setBackground(Color.LIGHT_GRAY);
		jb1.setForeground(Color.white);
		jb2.setBackground(Color.PINK);
		jb2.setForeground(Color.white);
		jb3.setBackground(Color.LIGHT_GRAY);
		jb3.setForeground(Color.white);
		jp.setBackground(new Color(18,167,112));
		jp2.setBackground(new Color(18,167,112));
		jp.setLayout(new GridLayout (4,1,100,100));
		jb1.addMouseListener(m2i);
		jb2.addMouseListener(m2i);
		jb3.addMouseListener(m2i);
		jp.add(jp2);
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		add(jp);
	}
	//overrding mouse events
	
	public void mouseClicked (MouseEvent me) 
	{											
		if(me.getSource().equals(jb1))				//when new button is pressed
		{
			Object[] options = {"Male","Female"};
			int n = JOptionPane.showOptionDialog(null,"Choose "+ "Your Gender?","Question",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
			
		}//if ends
		
		if(me.getSource().equals(jb3))				//if Exit Button is pressed
		{
			System.exit(0);
		}//if ends
	}//mouse clicked ends
		
	
	public void mouseEntered (MouseEvent me){}				//providing null body implementation for abstract methods
	
	public void mousePressed (MouseEvent me){}				
	
	public void mouseReleased (MouseEvent me){}
	
	public void mouseExited (MouseEvent me){}
	
	//overriding keyPressed event methods
	
	public void keyPressed (KeyEvent e)
	{
		if ((e.getKeyCode())==KeyEvent.VK_LEFT)		temp2=1;	//key movement in the main layout to select different options 
		if ((e.getKeyCode())==KeyEvent.VK_RIGHT)	temp2=2;
		if ((e.getKeyCode())==KeyEvent.VK_UP)	
		{
			temp2=3;
			but--;
		}//if ends
		if ((e.getKeyCode())==KeyEvent.VK_DOWN)	
		{
			temp2=4;
			but++;
		}//if ends
		
	}//keypressed ends
	
	public void keyTyped (KeyEvent e){}				//null body implementation
	public void keyReleased (KeyEvent e)		
	{
		temp2=0;
	}//keyReleased ends
	
	//initializing variable for drawing different shapes in the main layout
	int x=370, y=200;
	int temp=0;
	int i = 0;
	int but=1;
	int xa[]= {20,40};            //to draw head by polyline
	int ya[]= {20,40};

	public void paintComponent( Graphics g ) 		//making paint component to create different animations
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)(g);		//type casting
		if (but<1)
		{
			but=3;
		}//if ends
		if (but>3)
		{
			but=1;
		}//if ends
	
		if (but==1) y=150;
		if(but==2) y=275;
		if(but==3) y=400;
		if (swp==360)swp=-swp;
		swp=swp+20;
		g2D.setColor(new Color(129,39,1));
		Arc2D.Double arc1 = new Arc2D.Double(x,y,10,10,strt+180,swp,Arc2D.PIE);                       //drawing right circles
		g2D.fill(arc1);
		Arc2D.Double arc2 = new Arc2D.Double(x+20,y-12,20,20,strt+180,-swp,Arc2D.PIE);
		g2D.fill(arc2);
		g2D.setColor ( new Color(129,39,1));
		Arc2D.Double arc3 = new Arc2D.Double(x+45,y-10,30,30,strt,-swp,Arc2D.PIE);
		g2D.fill(arc3);
		Arc2D.Double arc4 = new Arc2D.Double(x+80,y-20,25,25,strt,swp,Arc2D.PIE);
		g2D.fill(arc4);
		if (temp<50)
		{                                     											 //drawing faces
			g2D.setColor(new Color(55,32,0));		
			Arc2D.Double arc5 = new Arc2D.Double(35,70,170,100,0,-180,Arc2D.PIE);          //guy hair
			g2D.fill(arc5);
			g2D.setColor(new Color(234,182,62));
			g2D.fill( new Ellipse2D.Double( 40, 150, 150, 250 ) );                          //Oval face
			g2D.setColor(Color.white);
			if (temp>5)g2D.fill( new Ellipse2D.Double( 60, 225, 40, 25 ) );			//1st guy eye
			if(temp>10) g2D.fill( new Ellipse2D.Double( 120, 225, 40, 25 ) );		//2nd guy eye
			g2D.setColor(Color.black);
			if (temp>15)
			{
				g2D.fillRect(60, 215, 40,10 );  //eye brow 1
				g2D.fillRect(120, 215, 40, 10); //eye brow 2}
				if(temp>20)
				{ 
					Arc2D.Double arc6 = new Arc2D.Double(70,225,15,25,0,360,Arc2D.PIE);         //eyeball left  guy
					g2D.fill(arc6);
					Arc2D.Double arc7 = new Arc2D.Double(130,225,15,25,0,360,Arc2D.PIE);         //eyeball right guy
					g2D.fill(arc7);
				}//if ends
				if(temp>25)
				{
					g2D.fillRect(100, 270, 5, 40); //nose
					g2D.fillRect(100, 310, 30, 5);} //nose
					g2D.setColor(new Color(135,31,5));
				if (temp>30)
				{
					Arc2D.Double arc8 = new Arc2D.Double(100,350,15,15,0,180,Arc2D.PIE);     //lip upper left
					g2D.fill(arc8);
					Arc2D.Double arc9 = new Arc2D.Double(113,350,15,15,0,180,Arc2D.PIE); 	//lip	upper right
					g2D.fill(arc9);
				}//if ends
			}//if ends
		}//if ends
		if (temp>50)
		{                      											  //for girl face
			g2D.setColor (new Color(56,31,3));		
			g2D.fill( new Ellipse2D.Double( 30, 120, 180, 300 ) );  	 //hair top
			g2D.fillRect(35, 225, 170,250 );  //hair sides
			g2D.setColor(new Color(247,243,115));
			g2D.fill( new Ellipse2D.Double( 40, 150, 150, 250 ) );       //Oval face
			if(temp>45){g2D.setColor(Color.white);
			g2D.fill( new Ellipse2D.Double( 60, 230, 35, 20 ) );		//1st girl eye
			g2D.fill( new Ellipse2D.Double( 120, 230, 35, 20 ) );}		//2nd girl eye
			if (temp>60)
			{
				g2D.setColor(new Color(56,31,3));
				g2D.fillRect(60, 215, 40,3 );  //eye brow 1
				g2D.fillRect(120, 215, 40,3 );
			} //eye brow 2
			if(temp>65)
			{
				Arc2D.Double arc6 = new Arc2D.Double(70,230,15,20,0,360,Arc2D.PIE);         //eyeball left  girl
				g2D.fill(arc6);
				Arc2D.Double arc7 = new Arc2D.Double(130,230,15,20,0,360,Arc2D.PIE);         //eyeball right girl
				g2D.fill(arc7);
			}//if ends
			if(temp>70)
			{
				g2D.fillRect(100, 270, 3, 40); //nose
				g2D.fillRect(100, 310, 30, 3);
			} //if ends
			if (temp>75)
			{
				g2D.setColor(Color.red);
				Arc2D.Double arc8 = new Arc2D.Double(100,350,15,15,0,180,Arc2D.PIE);     //lip upper left
				g2D.fill(arc8);
				Arc2D.Double arc9 = new Arc2D.Double(113,350,15,15,0,180,Arc2D.PIE); 	//lip	upper right
				g2D.fill(arc9);
				Arc2D.Double arca = new Arc2D.Double(100,353,28,17,0,-180,Arc2D.PIE); 	//lip	upper right
				g2D.fill(arca);
			}//if ends
			if (temp>100) temp=0;
		}//if ends
		temp++;
		try
		{
			Thread.sleep(100);
		}//try ends
		catch(Exception e){ }
		repaint(5);
	}//paintComponent ends
} // Layout ends
 
 
//


