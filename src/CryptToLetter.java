import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Shows a "keyboard" with the symbols, this is used to decrypt messages.
 * @author Henrik Johansson
 * @version 2014-03-20
 */
public class CryptToLetter extends JFrame {
	private static CryptToLetter instance = null;
	private JPanel topPanel, decryptPanel;
	private TextArea decryptedTextArea;
	
	private CryptToLetter(Component c) {
		super("Decrypt");
		initFrameGUI(c);
		initTopPanel();
		add(topPanel, BorderLayout.PAGE_START);
		initDecryptPanel();
		add(decryptPanel, BorderLayout.CENTER);
		setSize(800, 220);
	}

	public static CryptToLetter getInstance(Component c) {
		if (instance == null) {
			instance = new CryptToLetter(c);
		}
		return instance;
	}

	/**
	 * Sets the initial frame for te CryptToLetter GUI
	 * @param c the main frame from "main" starting program.
	 */
	private void initFrameGUI(Component c) {
		
		setLayout(new BorderLayout());
		setLocation(c.getX() + c.getWidth(), c.getY());
		initTopPanel();
	}
	
	/**
	 * Initialises the top panel, the frame is split in two parts. (This is the top part hurrdurr)
	 */
	private void initTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		//topPanel.setBackground(Color.red);
		decryptedTextArea = new TextArea(1,50);
		topPanel.add(decryptedTextArea);
	}

	/**
	 * Initialises the decrypt panel, wich is the lower one in the frame.
	 */
	private void initDecryptPanel() {
		decryptPanel = new JPanel();
		decryptPanel.setLayout(new FlowLayout() );
		initDecryptButtons();
	}

	/**
	 * Initialises the buttons that is needed for the decryption, also known as the "keyboard".
	 */
	private void initDecryptButtons() {
		
		final char[] letters = "abcdefghijklmnopqrstuvwxyzåäö".toCharArray();
		for(int i = 0; i < letters.length; i++){
			JButton tempButton = new JButton(new ImageIcon("changeLetterSymbol/" + letters[i] + ".jpg") );
			final String tempString = ""+letters[i];
			tempButton.setToolTipText(""+tempString);
			tempButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String tempText = decryptedTextArea.getText();
					decryptedTextArea.setText(tempText + tempString);
				}
			});
			decryptPanel.add(tempButton);
		}
		
		// Space is different than others due to the fact that I can't name a file " .jpg"
		JButton tempButton = new JButton(new ImageIcon("changeLetterSymbol/_.jpg") );
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tempText = decryptedTextArea.getText();
				decryptedTextArea.setText(tempText + " ");
			}
		});
		decryptPanel.add(tempButton);		
	}



}
