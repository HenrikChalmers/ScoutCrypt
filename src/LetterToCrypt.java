import java.awt.AWTException;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.SliderUI;

/**
 * Allows the user to convert a full string to crypted symbols and save them.
 * @author Henrik Johansson	
 * @version 2014-03-07
 */
public class LetterToCrypt extends JFrame {
	//GUI specific
	JPanel mainPanel, cryptPanel;
	JButton confirmBtn, saveImageBtn;
	JTextField textField;
	
	//Model Specific
	private static LetterToCrypt instance = null;

	private LetterToCrypt(Component c) {
		super("Encrypt");
		initFrameGUI(c);
		initCryptPanel();
		pack();
	}
	
	public static LetterToCrypt getInstance(Component c){
		if(instance == null){
			instance = new LetterToCrypt(c);
		} else if(instance.isVisible() == false){
			//instance.setVisible(true);
		}
		return instance;
	}

	/**
	 * Initializes the frame.
	 * @param c the "main" frame.
	 */
	private void initFrameGUI(Component c){
		setLayout(new GridLayout(2,10,1,1));
		setLocationRelativeTo(c);
		setLocation(c.getX() + c.getWidth(), c.getY());
		initTopPanel();
	}
	
	/**
	 * Initializes the crypt panel.
	 */
	private void initCryptPanel() {
		cryptPanel = new JPanel();
		cryptPanel.setLayout(new FlowLayout());
		cryptPanel.setVisible(true);
		
		add(mainPanel);
		add(cryptPanel);

	}
	
	/**
	 * Initializes the top panel-
	 */
	private void initTopPanel(){
		mainPanel = new JPanel();
		
		textField = new JTextField("", 3);		//Adding the text field
		textField.setColumns(15);
		mainPanel.add(textField);
		
		//Confirm crypt button******************************************************************************
		confirmBtn = new JButton("Crypt");
		confirmBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tempString = textField.getText().replace(" ","_");
				printSymbols( tempString );
				pack();
				
			}
		});
		mainPanel.add(confirmBtn);
		//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		
		//Save Image Button**********************************************************************************
		saveImageBtn = new JButton("Save");
		saveImageBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveCryptAsImage();
			}
		});
		mainPanel.add(saveImageBtn);
		//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		mainPanel.setVisible(true);
		
		
	}
	
	/**
	 * Prints all the letters in the string as crypted symbols. 
	 * @param stringToConvert the string that is to be converted.
	 */
	private void printSymbols(String stringToConvert){
		cryptPanel.removeAll();
		if(!stringToConvert.equalsIgnoreCase("")){
			for(char ch: stringToConvert.toCharArray() ){	//Go through each character in the string
				String tempChar = ""+ ch;
				cryptPanel.add(returnSpecialSymbol(tempChar));
			}
		}else{
			System.out.println("You must add text to convert.");
		}
		
	}

	/**
	 * Enter a letter and its symbol will be returned.
	 * @param letter the letter that wants to be turned into a symbol-
	 * @return a symbol as a JLabel.
	 */
	private JLabel returnSpecialSymbol(String letter) {
		return new JLabel(new ImageIcon("changeLetterSymbol/" + letter + ".jpg"));
	}
		
	/**
	 * Saves the entire crypt as an image.
	 */
	private void saveCryptAsImage(){
		try {
			File file = new File("savedCrypts/crypt.png");
			
			BufferedImage buffImage = new Robot().createScreenCapture(new Rectangle(cryptPanel.getLocationOnScreen().x, cryptPanel.getLocationOnScreen().y, cryptPanel.getWidth(), cryptPanel.getHeight()));
			ImageIO.write(buffImage, "png" , file);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(IOException e2){
			e2.printStackTrace();
		}
	}

}
