package _06_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import _05_Serialization.MinecraftCreeper;

public class PixelArtMaker implements MouseListener{
    private static final String DATA_FILE = "src/_06_Pixel_Art_Save_State/save.dat";
	private JFrame window;
    private GridInputPanel gip;
    private GridPanel gp;
    private JPanel saveload;
    private JButton saveButton;
    private JButton loadButton;
    ColorSelectionPanel csp;

    public void start() {
        gip = new GridInputPanel(this);	
        window = new JFrame("Pixel Art");
        window.setLayout(new FlowLayout());
        window.setResizable(false);

        window.add(gip);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void submitGridData(int w, int h, int r, int c) {
        gp = new GridPanel(w, h, r, c);
        csp = new ColorSelectionPanel();
        
        saveload = new JPanel();
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        saveButton.addActionListener((e)->save(gp));
        loadButton.addActionListener((e)->load());
        saveload.add(saveButton);
        saveload.add(loadButton);
        
        window.remove(gip);
        window.add(gp);
        window.add(csp);
        window.add(saveload);
        gp.repaint();
        gp.addMouseListener(this);
        window.pack();
    }

    public static void main(String[] args) {
        new PixelArtMaker().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gp.setColor(csp.getSelectedColor());
        System.out.println(csp.getSelectedColor());
        gp.clickPixel(e.getX(), e.getY());
        gp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    public void save(GridPanel data)
    {
    	try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void load()
    {
    	GridPanel grid = loadFile();
    	if (grid != null)
    	{
    		gp = grid;
            csp = new ColorSelectionPanel();
            window.remove(gip);
            window.remove(gp);
            window.remove(csp);
            window.add(gp);
            window.add(csp);
            
            saveload = new JPanel();
            saveButton = new JButton("Save");
            loadButton = new JButton("Load");
            saveButton.addActionListener((e)->save(gp));
            loadButton.addActionListener((e)->load());
            saveload.add(saveButton);
            saveload.add(loadButton);
            window.add(saveload);
            
            gp.repaint();
            gp.addMouseListener(this);
            window.pack();
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Save file is empty");
    		return;
    	}
    }
    
    public GridPanel loadFile()
    {
    	try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
    }
}
