package opencv;
/* we "Tahreem Saleem" and "Izzah Zaman" do verify that the submitted code is our own effort and that we have not copied it 
from any peer or any Internet source that has not been acknowledged. we also understand that if my 
submission fails the similarity detection, I would be awarded zero marks not only for this submission 
but the whole evaluation component. */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import org.opencv.core.Core; //Core package includes having basic data structures, 
							//	including multi-dimensional array Mat and basic function used by modules.
import org.opencv.core.Mat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import org.opencv.highgui.Highgui; // Easy to use interface to images, videocapture, video codec, and simple GUI capabilities.
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.opencv.highgui.VideoCapture; // VideoCapture subpackage of Highgui. VideoCapture is responsible for reading video from webcam or video file. It is also responsible 
//for setting or getting video properties like frame height, frame width.


//this part of thecode is written with the help of 
//http://computervisionandjava.blogspot.com/2013/10/java-opencv-webcam.html
class Mat2Image1 extends JFrame implements MouseListener 
{
	 JPanel contentPane;  //object of JPanel
	static Mat2Image1 frame;		//object of class
	Mat mat = new Mat(); // making object of Mat array class, Mat represents an n-dimensional Arrays to store Color images
	BufferedImage img;		//object of Buffered image
	static JFrame frame2;
	byte[] dat;
	static int n;
	JButton jb4; // creating back button
	// public Mat2Image1() {}
	public void mouseClicked (MouseEvent me){
		if (me.getSource()==Layout.jb1)  //if "new" Button us pressed
		{
			Control.ct.setVisible(false);			//setting the last frame false
			Object[] options = {"Male","Female"};		//array for storing names of buttons on JOption Pane
			n = JOptionPane.showOptionDialog(null,"Choose "+ "Your Gender?","Question",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
			Control.frame1.setVisible(true);		//setting the video frame true
		}
		if (me.getSource()==Layout.jb2)			//if "tutorial" button was pressed 
		{
			Control.ct.setVisible(false); //setting the last frame false
			JFrame t = new JFrame ();		//creating a new frame to store the image of tutorial
			t.setLayout(new BorderLayout());		//set Border LAyout
			t.setTitle("Meet Your Doppelganger");
			jb4 = new JButton("Back");
			jb4.setBackground(Color.yellow);
			jb4.setForeground(Color.white);
			jb4.addMouseListener(this);
			t.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			Image tut = null;
			File tt = new File("tutorial.jpg"); 		//refrencing the image file

			try {
				tut = ImageIO.read(tt);				//reading the image
			} catch (IOException e) {}
			////setting the image in JLabel
			JLabel label = new JLabel(new ImageIcon(tut));
			t.add(label,BorderLayout.CENTER);
			t.add(jb4,BorderLayout.SOUTH);			//setting the back button on south
			t.setBounds(400, 150, 600, 500);
			t.setVisible(true);
		}
		if(me.getSource()==Layout.jb3){		//if exit button was pressed
			System.exit(0);

		}
		if (me.getSource()==jb4){		//if back button was pressed
		
			Control.ct.setVisible(true); // set the frame of main layout visible
		}
	}

// overiding mouse event methods
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public Mat2Image1(Mat mat) 
	{
		getSpace(mat);		//It validates whether the Space of Mat exists in the metadata or not.
	}
	public void getSpace(Mat mat) 
	{
		this.mat = mat;
		int w = 640, h = 480;			//setting height and width of the image 
	
		if (dat == null || dat.length != w * h * 3)
			dat = new byte[w * h * 3]; //setting the number of elements of array
		
		if (img == null || img.getWidth() != w || img.getHeight() != h|| img.getType() != BufferedImage.TYPE_3BYTE_BGR) 
			img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR); //creating buffered image
	}
	BufferedImage getImage(Mat mat)
	{
		getSpace(mat);
		mat.get(0, 0, dat); //reading pixels of in the array (row,column,array)
		img.getRaster().setDataElements(0, 0, img.getWidth(), img.getHeight(), dat);//Setting the data for a rectangle of pixels from an input Raster
		return img;
	}
	static
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //loading library from default path
	}
//opening the default camera
	VideoCapture cap = new VideoCapture(0); //Class for video capturing from video files or cameras. 
	//passing 0 because single camera


	BufferedImage getOneFrame() 
	{
		cap.read(mat);//The function grabs the next frame from the video, decodes it and stores it in the  mat
		return getImage(mat); //calling the get Image method
	}

	public void release(int i){}
//contructor of the class to set up the frame and add panel to it
	public Mat2Image1() 
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new MyThread().start();//Staring the thread

	}
//paint Component to draw the image , and ovals
	public void paint(Graphics g)
	{
		g = contentPane.getGraphics();// getGraphics() creates a graphics context for drawing to an off-screen image.
		g.drawImage(getOneFrame(), 0, 0, this);
		g.setColor(Color.green);
		g.drawOval(175, 150,230,300);
		g.drawOval(215, 260,50,25); 
		g.drawOval(315, 260,50,25); 
		g.drawOval(250, 365,75,25);
		runOnce(); // calling the Run Once Method
	}
	private boolean hasRun = false;

	public synchronized boolean runOnce()
	{	//check if it had been run before
		if (hasRun) return false;
		hasRun = true;
		//Joption Pane for confirming the image capturing
		int reply = JOptionPane.showConfirmDialog(null, "Capture Image?", "Confirm Box", JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) //if yes
		{
			capturing(); //call capturing method
			Control.frame1.setVisible(false);//setting the previous frame false
		}
		else 
		{
			System.exit(0);
		}
		return true;
	}	
	private boolean hasRun1 = false;
//this part of the code is written with the help of 
	//http://www.codeproject.com/Tips/719878/How-to-Use-OpenCV-with-Java-under-Eclipse-IDE
	public synchronized boolean capturing()
	{	
		if (hasRun1) return false;
		hasRun1 = true;
		//creating a frame
		Mat2Image1 mf = new Mat2Image1();
		mf.setVisible(false);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);			//CODE FOR CAPTURING IMAGE	
		VideoCapture camera = new VideoCapture(0);		//making object of class Video Capturing
		if(!camera.isOpened())					//function is used to check if the binding of the class to a was successful or not to use.								
		{
			System.out.println("Error");
		}//if ends
		else 
		{
			Mat frame = new Mat(); 		//object of Mat class
			//Following is an infinite loop till the frame is not obtained. If frame has been obtained, it breaks the loop
			while(true)
			{
				if (camera.read(frame)) //getting the frame of image 
				{
					Highgui.imwrite("camera.jpg", frame);//imwrite is a static method from the Highgui class. This method is used to write an image to a file.
					break;
				}//if ends
			}//while ends	
		}//else ends
		camera.release();// closing the video
		//openning the image just captured
		Image image = null;
		try 
		{
			File sourceimage = new File("camera.jpg");
			image = ImageIO.read(sourceimage);
		} 
		catch (IOException e){}
		frame2 = new JFrame(); //setting up a new frame
		oval o = new oval();							//CREATING OBJECT OF OVAL CLASS
		frame2.setBounds(400,150,600,500);			
		frame2.setTitle("Meet Your Doppelganger");
		frame2.add(o); //adding the oval class to it
		frame2.setVisible(true);
		return true;	
	}
//class that extends thread and used to repaint 
	class MyThread extends Thread
	{

		{		setDaemon(true);
		}
		public void run() 
		{
			for (;;)
			{
				repaint();
				try 
				{ 	
					Thread.sleep(30);
				} 
				catch (InterruptedException e) {}
			}  
		}
	}
}



