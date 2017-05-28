package opencv;
/* we "Tahreem Saleem" and "Izzah Zaman" do verify that the submitted code is our own effort and that we have not copied it 
from any peer or any Internet source that has not been acknowledged. we also understand that if my 
submission fails the similarity detection, I would be awarded zero marks not only for this submission 
but the whole evaluation component. */

//this is our public class and all the classes are controlled from here
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Control extends JFrame{
	  
	static Mat2Image1 frame1;			//object of image capturing class
	static Layout layout = new Layout();//object of layout class
	static Control ct = new Control();//object of Control class
	public static void main(String[] args) 
	    {
			
			ct.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			ct.setBounds(400, 150, 600, 500);
			ct.add(layout);							//adding the layout in this class
			layout.setBackground(new Color(18,167,112));
			layout.setFocusable(true);
			layout.addKeyListener(layout);
			ct.setTitle("Meet Your Doppelganger");
			ct.setVisible(true);
			
	        EventQueue.invokeLater(new Runnable() // an anonymous class that derives from Runnable and overrides the run 
	        //method of the interface runnable it is initiated and passed to the EventQueue.invokeLater method
	        {
	           public void run() 
	           {
	                try 
	                {
	                    frame1 = new Mat2Image1(); // initiating object of class
	                } 
	                catch (Exception e) {}
	            }
	        });
	
	
}
}
