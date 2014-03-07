import java.awt.AWTException;
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
 * @author Henrik Johansson	
 * @version 2014-03-07
 */
public class CryptScoutModel {
	//GUI specific

	JFrame frame;
	JPanel mainPanel, cryptPanel;
	JButton confirmBtn, saveImageBtn;
	JTextField textField;



	public CryptScoutModel() {
		initFrameGUI();
		initCryptPanel();
		frame.pack();
		
		
	}

	private void initFrameGUI(){
		frame = new JFrame("ScoutCrypt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2,10,1,1));
	
		
		initTopPanel();
		frame.setVisible(true);
	}
	
	private void initCryptPanel() {
		cryptPanel = new JPanel();
		cryptPanel.setLayout(new FlowLayout());
		cryptPanel.setVisible(true);
		
		frame.add(mainPanel);
		frame.add(cryptPanel);

	}
	
	private void initTopPanel(){
		mainPanel = new JPanel();
		
		textField = new JTextField("", 3);		//Adding the text field
		textField.setColumns(15);
		mainPanel.add(textField);
		
		//Confirm crypt button******************************************************************************
		confirmBtn = new JButton("Crypt");
		confirmBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printSymbols( textField.getText() );
				frame.pack();
				
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

	private JLabel returnSpecialSymbol(String letter) {
		return new JLabel(new ImageIcon("images/" + letter + ".jpg"));
	}
		
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
