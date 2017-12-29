import java.awt.Color;

//Peter Bengtson
//pBengt2
//Monday 3pm
//Uses turtles to draw animated shapes

public class ProjectOnePBengt2
{
	public static void main(String[] args)
	{
		int wDimension = 1200; //width and height of image		
		World w;
		w = new World(wDimension, wDimension);

		
		//create turtle 1
		Turtle t1;
		t1 = new Turtle(w);
		t1.penUp();
		t1.setPenColor(Color.blue);
		t1.setPenWidth(5);
		
		//create turtle 2
		Turtle t2;
		t2 = new Turtle(w);
		t2.penUp();
		t2.setPenColor(Color.red);
		t2.setPenWidth(5);
		t2.moveTo(0,0);
		
		BackgroundColor(t1, wDimension, Color.black);
		
		//display the world
		w.show();
		
		Refresh(60); //1 second pause
		
		//begin animation
		AnimateSquares(t1, wDimension, 20, 5);//make turtle one animate squares
		t1.setPenColor(Color.black); 
		AnimateSquares(t1, wDimension, 20, 1);//make turtle two animate 'remove' squares	
		t1.turn(30);
		AnimateColorHex(t1, 500, 600, 100, 1);
		BackgroundColor(t1, wDimension, Color.black);
		t1.setPenColor(Color.blue);
		AnimateStar(t1, t2, 500, 1);
		t2.moveTo(t1.getXPos(), t1.getYPos());
		FadeToBlack(t2);
		
		
		System.out.println("");
		System.out.println("End Java Execution");
	} //end of main
	
	//refresh at ~60 frames per second
	public static void Refresh(){
		try {
			Thread.sleep(17);
		} catch (InterruptedException e) {
			// Handle here
		}
	}
	
	//holds frames for longer periods of time
	public static void Refresh(int numFrames){ 
		try {
			Thread.sleep(17*numFrames);
		} catch (InterruptedException e) {
			// Handle here
		}
	}
	
	//makes turtle face upwards
	public static void FaceUp(Turtle tur){ 
		//finds position directly above turtle, and faces towards it
		int x = tur.getXPos();
		int y = tur.getYPos();
		tur.turnToFace(x, y-1);
	}//end of face upwards
	
	//draws a square with given side length
	public static void DrawSquare(Turtle tur, int sLength){ 
		tur.penDown();
		
		int x = 0;
		while (x<4){ //loops 4 times to draw each side
			tur.forward(sLength);
			tur.turnRight();
			x++;
		}
		
		tur.penUp();
	}//end of draw square
	
	//animate given number of squares starting from center at a given speed
	public static void AnimateSquares(Turtle tur, int dimension, int numSquares, int speed){ 
		int mid = dimension/2;
		
		FaceUp(tur);
		int distance = dimension/numSquares;
		int size = distance; //size is variable, distance is constant
		int i = 0;
		while (i<numSquares){//
			tur.moveTo(mid - size/2, mid + size/2);
			DrawSquare(tur, size);
			size = size + distance;
			Refresh(speed);
			i++;
		}
	}
	
	//animates a moving and changing color hexagon
	public static void AnimateColorHex(Turtle tur, int xMid, int yMid, int size, int speed){
	
		tur.moveTo(xMid-size, yMid);
		tur.penDown();	
		Color prevColor;
		prevColor = tur.getPenColor();
		Color curColor;
		curColor = new Color(0,0,0);
		
		int i = 0;
		int j = 0;
		int z = 0;
		while (z<8){
			while (j<4){
				while (i<25){
					if (z==0)
						tur.moveTo(xMid-i*5-j*125, yMid);
					if (z==1)
						tur.moveTo(xMid-i*5-j*125, yMid-i*5-j*125);
					if (z==2)
						tur.moveTo(xMid, yMid-i*5-j*125);
					if (z==3)
						tur.moveTo(xMid+i*5+j*125, yMid-i*5-j*125);
					if (z==4)
						tur.moveTo(xMid+i*5+j*125, yMid);
					if (z==5)
						tur.moveTo(xMid+i*5+j*125, yMid+i*5+j*125);
					if (z==6)
						tur.moveTo(xMid, yMid+i*5+j*125);
					if (z==7)
						tur.moveTo(xMid-i*5-j*125, yMid+i*5+j*125);
					
					DrawHexagon(tur, size, curColor);
					if (j==0)
						curColor = new Color(i*8,0,0);
					if (j==1)
						curColor = new Color(200-i*8,i*8,0);
					if (j==2)
						curColor = new Color(0,200-i*8,i*8);
					if (j==3)
						curColor = new Color(0, 0, 200-i*8);
					Refresh(speed);
					i++;
				}
				//Refresh(10);
				i=0;
				j++;
			}
			j=0;
			z++;
		}
		FaceUp(tur);
		tur.penUp();
		tur.setPenColor(prevColor);
		tur.moveTo(yMid, yMid);
	}
		
	//Animates the drawing of a star loop using two turtles of different color
	public static void AnimateStar(Turtle tur1, Turtle tur2, int size, int speed){
			
		int x = tur1.getXPos();
		int y = tur1.getYPos();			
		
		tur2.moveTo(x,y);
		
		tur1.penDown();
		tur2.penDown();
		
		int i = 0;
		int j = 0;
		int k = 0;
		while (k <10){
			tur1.turn(36);
			while (i<5){
				while (j<size/250){
					tur2.moveTo(x,y);
					tur1.forward(250);
					j++;
					Refresh(5);
					x = tur1.getXPos();
					y = tur1.getYPos();
				}
				tur1.turn(144);
				i++;
				j=0;
			}
			k++;
			i=0;
		}
		tur1.penUp();
		tur2.penUp();
	}
		
	//draws a star of a given size	
	public static void DrawStar(Turtle tur, int size){	
		int i = 0; //initialize counter
		
		tur.penDown();
		
		while (i<5){
			tur.forward(size);
			tur.turn(144);
			i++; //increase counter by 1
		}
		
		tur.penUp();
	} //end of DrawStar	
	
	//Draws a hexagon of a given size and color
	public static void DrawHexagon(Turtle tur, int sLength, Color tempCol){	
		Color col;
		col = new Color(0,0,0);
		col = tur.getPenColor();
		tur.setPenColor(tempCol);
		
		tur.penDown(); //sets pen down to draw letter		
		
		int i = 0;
		while (i<6){			
			tur.forward(sLength);
			tur.turn(60);
		i++;
		}
		
		tur.penUp();
		tur.setPenColor(col);
	} //end of DrawHexagon		
	
	//draws a rectange of given dimensions
	public static void DrawRectangle(Turtle tur, int rWidth, int rHeight){	
		FaceUp(tur);
		
		tur.penDown(); //sets pen down to draw letter		
		
		int i = 0;
		while (i<2){
		tur.forward(rHeight);
		tur.turnRight();
		tur.forward(rWidth);
		tur.turnRight();
		i++;
		}
		
		tur.penUp();
		
		FaceUp(tur);
		
	} //end of DrawRectangle
		
	//uses turtle to change entire world color
	public static void BackgroundColor(Turtle tur, int dimension, Color backColor){ 
		int x = tur.getXPos();
		int y = tur.getYPos();
		
		Color col;
		col = new Color(0,0,0);
		col = tur.getPenColor();
		tur.setPenColor(backColor);
		
		int pSize = tur.getPenWidth();
		tur.setPenWidth(dimension);
		
		tur.penDown();
		tur.moveTo(0,dimension/2);
		tur.moveTo(dimension, dimension/2);
		
		tur.setPenColor(col);
		tur.setPenWidth(pSize);	
		tur.penUp();
		tur.moveTo(x,y);
	}
	
	//fades looped star shape to black
	public static void FadeToBlack(Turtle tur){
		Color tempCol;
		tempCol = new Color(0,200,0);
		
		tur.setPenWidth(tur.getPenWidth()+2);
		int i = 0;
		int j = 0;
		while (i<200){
			while (j<10){
				DrawStar(tur, 500);				
				j++;
				tur.turn(36);
			}
			tempCol = new Color(200-i,0,0);
			tur.setPenColor(tempCol);
			Refresh();
			i++;
			j=0;
		}
		tur.setPenWidth(tur.getPenWidth()-2);
	}
} //end of class