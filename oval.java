package opencv;
/* we "Tahreem Saleem" and "Izzah Zaman" do verify that the submitted code is our own effort and that we have not copied it 
from any peer or any Internet source that has not been acknowledged. we also understand that if my 
submission fails the similarity detection, I would be awarded zero marks not only for this submission 
but the whole evaluation component. */

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import java.io.IOException;

/*This is oval class where:
 *  The are resizing adjusting of facial elements are done
 *  setting of coordinates of elements are done
 *  Morphing is done
*/
//NOTE: YOU WILL SEE SOME OF THE CODE COMMENTED OUT THIS IS BECAUSE WE INITIALY WROTE THE CODE FOR RESIZING EACH ELEMENT
//WHICH WE REMOVED AFTER WORDS
class oval extends JPanel implements MouseListener {
	static JLabel label;						//JLabel to display the image
	static int temp2=0, temp3=0;				//used in move and resizing 
	static int flag =0;							//flag to see which button was pressed
	oval aa;										
	int a[][]= new int[650][490];				//making arrays for each element of the face so that we can point out 
	int b[][]= new int[650][490];				//where is the element present 
	int c[][]= new int[650][490];
	int d[][]= new int[650][490];
	int ax=220,ay=260, aw = 50,ah=25;			//initial value of left eye
	int bx=315,by=255, bw = 50,bh=25;			//initial value of right eye
	int cx=255,cy=360, cw = 75,ch=25;			//initial value of mouth
	int dx=175, dy=150, dw=230,dh=300 ;			////initial value of face
	 int a2[][]= new int[650][490];
	 int b2[][]= new int[650][490];
	 int c2[][]= new int[650][490];
	 int d2[][]= new int[650][490];
	int ax2=220,ay2=260, aw2 = 50,ah2=25;
	int bx2=315,by2=255, bw2 = 50,bh2=25;
	int cx2=255,cy2=360, cw2 = 75,ch2=25;
	int dx2=175, dy2=150, dw2=230,dh2=300 ;
	int temp=0, tempy, tempx, finx, finy, initx, inity;
	int i = 0;
		//making buttons for the adjustment
	JButton mle = new JButton("Move Left Eye");
	
	JButton mre = new JButton("Move Right Eye");
	
	JButton mm = new JButton("Move Mouth");
	JButton rf = new JButton("Resize Face");
	JButton ok = new JButton("Done");

	{
		addMouseListener(this); 				//adding mouse listener to all the buttons and panel
		
		mle.addMouseListener(this);
		
		mre.addMouseListener(this);
		
		mm.addMouseListener(this);
		rf.addMouseListener(this);
		ok.addMouseListener(this);
							//adding the buttons to the panel
		add(mle);
		
		add(mre);
	
		add(mm);
		add(rf);
		add(ok);
	}
	public void mouseClicked (MouseEvent me) {}				//overriding methods


	public void mouseEntered (MouseEvent me){}


	public void mousePressed (MouseEvent me)
	{
		initx= me.getX();	 			//getting the x,y value where mouse was clicked
		inity = (me.getY());
		if( me.getSource()==ok)			//if DOne button is pressed
		{	
			cord(ax,ay,aw,ah,a); 	//get the coordinates of left eye		
			cord(bx,by,bw,bh,b);	//get the coordinates of right eye
			cord(cx,cy,cw,ch,c);	//get the coordinates of mouth
			cord(dx,dy,dw,dh,d);	//get the coordinates of face
			morph(a,b,c,d);			//passing the arrays of face elements to the morph method
			readImage();			//reading the morphed image
			

			cord(ax,ay,aw,ah,a); 	//left eye current
			cord(bx,by,bw,bh,b);	//right eye current
			cord(cx,cy,cw,ch,c);	//mouth current
			cord(dx,dy,dw,dh,d);	//face current
			morph(a,b,c,d);
			readImage();
			
		
		}                      	                  
		/*if (me.getSource()==rle)		//if the left eye resize button was pressed
		{	
			temp2=2;				//to know that we have to do resizing  
			temp3=0;				//resize button is pressed first time
			flag = 1;				//so that we know which button was pressed 
		}*/
		if (me.getSource()==mle)		//if the move left eye was pressed
		{
			temp2=1;				//to know that we have to do movement  
			temp3=0;
			flag =2;
		}  
		/*if (me.getSource()==rre)		//if the resize right eye was pressed
		{
			temp2=2;
			temp3=0;
			flag = 3;
		}*/
		if (me.getSource()==mre)		//if the move right eye was pressed
		{
			temp2=1;
			temp3=0;
			flag = 4;
		}
		/*if (me.getSource()==rm)			//if the resize mouth was pressed
		{
			temp2=2;
			temp3=0;
			flag = 5;
		}*/
		if (me.getSource()==mm)			//if the move mouth was pressed
		{
			temp2=1;
			temp3=0;
			flag = 6;
		}
		if (me.getSource()==rf)			////if the resize face was pressed
		{
			temp2=2;
			temp3=0;
			flag = 7;
		}
		//the logic to know that where the mouse was clicked in any oval, whether it was on top , bottom, left or right
		// we have specified certain areas on each side which are shape of a rectangle, and if the mouse is clicked in that rectangle
		// we know where the mouse was clicked
		
		//for the left eye
		/*if (((ay+ah-10)<inity)&&(inity<(ay+ah+5))&& ((((ax+aw)/2)+(aw/2)+70)<initx)&&(initx<(((ax+aw)/2)+(aw/2)+94)))
		{
			temp=3;     //bottom
		}
		if (((ay-7)<inity)&&(inity<(ay+7))&& ((((ax+aw)/2)+(aw/2)+70)<initx)&&(initx<(((ax+aw)/2)+(aw/2)+94)))
		{
			temp=1;    //top
		}
		if 	(((ax+aw-10)<initx)&&(initx<(ax+aw+5))&& ((((ay+ah)/2)+(ah/2)+90)<inity)&&(inity<(((ay+ah)/2)+(ah/2)+115)))
		{
			temp=2;        //right
		}
		if (((ax-10)<initx)&& (initx<(ax+10))&& ((((ay+ah)/2)+(ah/2)+90)<inity)&&(inity<(((ay+ah)/2)+(ah/2)+115)))
		{
			temp=4;          //left
		}
		
		//for the right eye
		if (((by+bh-10)<inity)&&(inity<(by+bh+5))&& ((((bx+bw)/2)+(bw/2)+120)<initx)&&(initx<(((bx+bw)/2)+(bw/2)+145)))
		{
			temp=3;     //bottom
		}
		if (((by-7)<inity)&&(inity<(by+7))&& ((((bx+bw)/2)+(bw/2)+120)<initx)&&(initx<(((bx+bw)/2)+(bw/2)+145)))
		{
			temp=1;    //top
		}
		if (((bx+bw-10)<initx)&&(initx<(bx+bw+7))&& ((((by+bh)/2)+(bh/2)+90)<inity)&&(inity<(((by+bh)/2)+(bh/2)+115)))
		{
			temp=2;        //right
		}
		if (((bx-10)<initx)&& (initx<(bx+9))&& ((((by+bh)/2)+(bh/2)+90)<inity)&&(inity<(((by+bh)/2)+(bh/2)+115)))
		{
			temp=4;          //left
		}
		
		//for the mouth
		if (((cy+ch-10)<inity)&&(inity<(cy+ch+5))&& ((((cx+cw)/2)+(cw/2)+70)<initx)&&(initx<(((cx+cw)/2)+(cw/2)+110)))
		{
			temp=3;     //bottom
		}
		if (((cy-9)<inity)&&(inity<(cy+9))&& ((((cx+cw)/2)+(cw/2)+70)<initx)&&(initx<(((cx+cw)/2)+(cw/2)+110)))
		{
			temp=1;    //top
		}
		if (((cx+cw-15)<initx)&&(initx<(cx+cw+7))&& ((((cy+ch)/2)+(ch/2)+147)<inity)&&(inity<(((cy+ch)/2)+(ch/2)+177)))
		{
			temp=2;        //right
		}
		if (((cx-10)<initx)&& (initx<(cx+10))&& ((((cy+ch)/2)+(ch/2)+147)<inity)&&(inity<(((cy+ch)/2)+(ch/2)+177)))
		{
			temp=4;          //left
		}*/

		//for the face
		if (((dy+dh-15)<inity)&&(inity<(dy+dh+15))&& ((((dx+dw)/2)-(dw/2))<initx)&&(initx<(((dx+dw)/2)+(dw/2))))
		{
			temp=3;     //bottom
		}
		if (((dy-15)<inity)&&(inity<(dy+15))&& ((((dx+dw)/2)-(dw/2))<initx)&&(initx<(((dx+dw)/2)+(dw/2))))
		{
			temp=1;    //top
		}
		if (((dx+dw-15)<initx)&&(initx<(dx+dw+15))&& ((((dy+dh)/2)-(dh/2))<inity)&&(inity<(((dy+dh)/2)+(dh/2))))
		{
			temp=2;        //right
		}
		if (((dx-15)<initx)&& (initx<(dx+15))&& ((((dy+dh)/2)-(dh/2))<inity)&&(inity<(((dy+dh)/2)+(dh/2))))
		{
			temp=4;          //left
		}
	}//mouse clicked ends
	
	public void mouseReleased (MouseEvent me)
	{
		finx = (me.getX());				//getting the x,y value where the mouse was released to 
		finy = (me.getY());
		if(flag==2)						//if the move left eye was pressed earlier
		{
			if (temp2==1)				//and we have ro do movement
			{             
				if (temp3==1)			
				{
					ax=finx-(aw/2);     //so that it doesn't move when move button is pressed
					ay=finy-2;
				}
				temp3=1;
			}
		}
		/*if(flag==1)
		{
			if (temp2==2)
			{             //if resize left eye button was pressed earlier
				if (temp3==1)
				{             //so that it doesnt move the first time
					if (temp==1)
					{                //top
						ah=ah+(inity-finy);
						ay=finy;
					}
					if (temp==2)
					{                //right
						aw=aw+(finx-initx);
					}
					if (temp==3)
					{               //bottom
						ah=ah+(finy-inity);
					}
					if (temp==4)
					{                //left
						aw=aw+(initx-finx);
						ax=finx;
					}
				}
				temp3=1;
			}
		}*/
		if(flag==4)//if move right eye button was pressed
		{
			if (temp2==1)
			{               
				if (temp3==1)
				{
					bx=finx-(bw/2);     //so that it doesn't move when move button is pressed
					by=finy-2;
				}
				temp3=1;
			}
		}
		/*if(flag==3)//if resize right eye button was pressed earlier
		{
			if (temp2==2)
			{             
				if (temp3==1)
				{             //so that it doesnt move the first time
					if (temp==1)
					{                //top
						bh=bh+(inity-finy);
						by=finy;
					}
					if (temp==2)
					{                //right
						bw=bw+(finx-initx);
					}
					if (temp==3)
					{               //bottom
						bh=bh+(finy-inity);
					}
					if (temp==4)
					{                //left
						bw=bw+(initx-finx);
						bx=finx;
					}
				}
				temp3=1;
			}
		}
*/

		if(flag==6)//if move mouth button was pressed
		{
			if (temp2==1)
			{               
				if (temp3==1)
				{
					cx=finx-(cw/2);     //so that it doesn't move when move button is pressed
					cy=finy-2;
				}
				temp3=1;
			}
		}
	/*	if(flag==5)		//if resize mouth button was pressed earlier
		{
			if (temp2==2)
			{             
				if (temp3==1)
				{             //so that it doesnt move the first time
					if (temp==1)
					{                //top
						ch=ch+(inity-finy);
						cy=finy;
					}
					if (temp==2)
					{                //right
						cw=cw+(finx-initx);
					}
					if (temp==3)
					{               //bottom
						ch=ch+(finy-inity);
					}
					if (temp==4)
					{                //left
						cw=cw+(initx-finx);
						cx=finx;
					}
				}
				temp3=1;
			}
		}*/

		if(flag==7)//if resize face was pressed earlier
		{
			if (temp2==2)
			{           
				if (temp3==1)
				{             //so that it doesnt move the first time
					if (temp==1)
					{                //top
						dh=dh+(inity-finy);
						dy=finy;
					}
					if (temp==2)
					{                //right
						dw=dw+(finx-initx);
					}
					if (temp==3)
					{               //bottom
						dh=dh+(finy-inity);
					}
					if (temp==4)
					{                //left
						dw=dw+(initx-finx);
						dx=finx;
					}
				}
				temp3=1;
			}
		}
	}//mouse released ends	


	public void mouseExited (MouseEvent me){}

		//paint method to draw the ovals and image
	public void paintComponent( Graphics g ) 
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)(g);//type casting
		Image bg = null;
		try 
		{ 
			File input = new File("camera.jpg");		//openign the file we captured earlier
			bg = ImageIO.read(input);
		}//try ends 
		catch (IOException e1) {}//catch ends
		//type casting
		g2D.drawImage(bg,0,0,this);		//drawing the image captured earlier	
		g2D.setColor(Color.green);
		g2D.drawOval(dx, dy,dw,dh); 	//drawing the ovals
		g2D.drawOval(ax, ay,aw,ah); 
		g2D.drawOval(bx, by,bw,bh); 
			g2D.drawOval(cx, cy,cw,ch); 

		try{
			Thread.sleep(10);
		}
		catch(Exception e){ }
		repaint(5);

	}//paintComponent ends
	
	//this method gets the coordinates of the oval whose parameters are passed, then sets the value of pixels
	//inside the ovals to 1; so that we know where the oval is present on the image
	public static void cord(int x,int y, int w,int h,int a[][])
	{
		int y1,x1;
		double fract1,fract2,num1,num2,den1,den2;
		for (y1=y; y1< (y+h); y1++)
		{
			for (x1=x;x1<(x+w);x1++)
			{
				num1 =(Math.pow((x1-(x+(w/2))),2));
				den1 = (Math.pow((w/2),2));
				num2 = Math.pow((-y1+((y+(h/2)))),2);
				den2 = (Math.pow((h/2),2));
				fract1= num1/den1;
				fract2 = num2/den2;
				if (((fract1 + fract2)<=1))
				{a[x1][y1]=1;}
			}
			}
	}
		//The morphing part is in this method
	//we already have saved 2 images in the folder one is a male version the other is the female version
	public void morph(int re[][],int le[][],int m[][], int f[][])
	{
		int rgb=0, red=0,blue=0,green=0,wt=640,ht=480;
		BufferedImage image;
		File file = null;
		BufferedImage image2;
		File file2 = null ;
		if(Mat2Image1.n==JOptionPane.YES_OPTION)		//if the male option was clicked
		{
			image2  = new BufferedImage(100, 100,  BufferedImage.TYPE_3BYTE_BGR);
			int r = (int) (Math.random()*3+1);		//chooses randomly from 3 pictures in tha data base
			switch (r)
			{
				case 1:
					{
						file2 = new File("Female1.jpg");	
						break;
					}
				case 2:
					{
						file2= new File("Female2.jpg");
						break;
					}
				case 3: 
				{
				file2 = new File ("Female3.jpg");
				break;
				}
			
			}
		}
		else if(Mat2Image1.n==JOptionPane.NO_OPTION)		//if female otion was clicked
		{
			image2 = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);   //creating a buffered image
			int r = (int) (Math.random()*3+1);		//chooses randomly from 3 pictures in tha data base
			switch (r)
			{
				case 1:
					{
						file2 = new File("Male1.jpg");	
						break;
					}
				case 2:
					{
						file2= new File("Male2.jpg");
						break;
					}
				case 3: 
				{
				file2 = new File ("Male3.jpg");
				break;
				}
			}
		}
		image = new BufferedImage(100, 100,  BufferedImage.TYPE_3BYTE_BGR);   //creating a buffered image
		file = new File("Camera.jpg"); 						// the already captured image
		try 
		{
			image  = ImageIO.read(file);				//read both captured and a;ready present image
			image2  = ImageIO.read(file2);
			int rgb2=0;
			for ( int y3=0; y3 < ht; y3++)			// going through the width of the images 1 pixel at a time
			{  		
				for (int x3=0; x3 <wt; x3++)		//going through the height of the pcture one at a time
				{  		
					
					if ((f[x3][y3]==1))				//if the coordinates of the face array is 1 at that spot 
					{
						rgb= (image.getRGB(x3,y3));					//getting RGB value of that pixel
						rgb2 = (image2.getRGB(x3,y3));				//getting the RGB value of the previously present image
						blue = ((rgb & 254)+(rgb2&254))/2;			//taking average of value of blue,green,red sepratly	
						green = (((rgb >>> 8)&254)+((rgb2 >>> 8)&254))/2;
						green = green <<8;
						red = (((rgb >>> 16)&254)+((rgb2 >>> 16)&254))/2;
						red= red<<16;
						image2.setRGB(x3,y3,( red|green|blue)|(254<<28));		//setting the RGB value
					}
					int numle=1;
					int numre=1;
					int nummo=1;
					int check=0;
					if (a2[x3][y3]==numle){         //if writing point for the left eye of stored image has reached

						inner:	
						for (int y=0; y<490; y++)
						{    //find the corresponding point for the image captured
							for (int x=0; x<650; x++)
							{
								if(le[x][y]==numle)
								{
									image2.setRGB(x3,y3,( image.getRGB(x, y)|(255<<24)));
									numle++;
									break inner;
								}//if ends	
							}//inner for ends
						}//outer for ends
						
					}//if ends
					
					
					if (b2[x3][y3]==numre)
					{          //if writing point for the right eye of stored image has reached
						inner1:
						for (int y=0; y<490; y++)
						{     //finding the corresponding pixel of the image captured
							for (int x=0; x<650; x++)
							{
								if(re[x][y]==numre)
								{
									image2.setRGB(x3,y3,( image.getRGB(x, y)|(255<<24)));
									numre++;
									break inner1;
								}//if ends	
							//if (le[x][y]==(numre-1))break;
							}//inner for ends
						}//outer for ends
					}//if ends
					
					if (c2[x3][y3]==nummo)
					{

						inner2:
						for (int y=0; y<490; y++)
						{
							for (int x=0; x<650; x++)
							{
								if(m[x][y]==nummo)
								{
									image2.setRGB(x3,y3,( image.getRGB(x, y)|(255<<24)));
									nummo++;
									break inner2;
								}//if ends	
							}//inner for ends
						}//outer for ends
					}//if ends
				}//inner for loop ends	
			}//outer for loop ends
			ImageIO.write(image2, "jpg", new File("Morphed.jpg"));               //new image file
		}catch(Exception e){} // Mypanel ends

	}//morph method ends
	//THIS METHOD WILL READ THE MORPHED IMAGE
	public void readImage()
	{
		Image nw = null;
		JFrame mph = new JFrame();			//creating a new frame
		File input = new File("Morphed.jpg");

		try {
			nw = ImageIO.read(input);				//reading the image
			Mat2Image1.frame2.setVisible(false);    //S	etting the visibility of the previous frame false
		} catch (IOException e) {}
		label = new JLabel(new ImageIcon(nw));		//setting the image in JLabel
		mph.add(label);								//adding the label to the frame
		mph.setBounds(400, 150, 600, 500);
		mph.setVisible(true);

	}//read image ends

}//class ends

