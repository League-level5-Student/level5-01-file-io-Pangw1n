package _06_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

public class GridPanel extends JPanel implements Serializable{

    private static final long serialVersionUID = 1L;
    private int windowWidth;
    private int windowHeight;
    private int pixelWidth;
    private int pixelHeight;
    private int rows;
    private int cols;

    // 1. Create a 2D array of pixels. Do not initialize it yet.
    Pixel[][] Canvas;
    private Color color;

    public GridPanel(int w, int h, int r, int c) {
        this.windowWidth = w;
        this.windowHeight = h;
        this.rows = r;
        this.cols = c;

        this.pixelWidth = windowWidth / cols;
        this.pixelHeight = windowHeight / rows;

        color = Color.BLACK;

        setPreferredSize(new Dimension(windowWidth, windowHeight));

        // 2. Initialize the pixel array using the rows and cols variables.
        Canvas = new Pixel[c][r];

        // 3. Iterate through the array and initialize each element to a new pixel.
        for (int i = 0; i < Canvas.length; i++)
        {
        	for (int j = 0; j < Canvas[i].length; j++)
        	{
        		Canvas[i][j] = new Pixel(i, j);
        	}
        }
    }

    public void setColor(Color c) {
        color = c;
    }

    public void clickPixel(int mouseX, int mouseY) {
        // 5. Use the mouseX and mouseY variables to change the color
        //    of the pixel that was clicked. *HINT* Use the pixel's dimensions.
    	Canvas[mouseX / (windowWidth / rows)][mouseY / (windowWidth / cols)].color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        // 4. Iterate through the array.
        //    For every pixel in the list, fill in a rectangle using the pixel's color.
        //    Then, use drawRect to add a grid pattern to your display.
    	for (int i = 0; i < Canvas.length; i++)
        {
        	for (int j = 0; j < Canvas[i].length; j++)
        	{
        		Pixel pixel = Canvas[i][j];
        		g.setColor(pixel.color);
        		g.fillRect(pixel.x * (windowWidth / rows), pixel.y * (windowWidth / cols), pixelWidth, pixelWidth);
        	}
        }
    }
}
