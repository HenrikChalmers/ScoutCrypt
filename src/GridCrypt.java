import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This shows the "grid crypting", wich is a crypt where a key is split into capital letters as x coordinate 
 * and small letters is the y coordinate. The alphabet is then placed inside the grid, for example:	
 *   S C O U T
 * s a b c d e
 * c f g h i j
 * o k l m n o
 * u p q r s t 
 * t u v w x y
 * 
 * Ss = a, Cs = b etc..
 * 
 * @author Henrik Johansson
 * @version 2014-03-20
 */
public class GridCrypt extends JFrame {
	private String cryptKey;
	private HashMap<String, String> letterToSymbol, symbolToLetter;
	private boolean keyOk, arrayOk;
	private static GridCrypt instance = null;
	private JPanel topPanel, centerPanel, leftPanel, downPanel;
	private JTextField plainText, codedText;

	public static GridCrypt getInstance(Component c) {
		if (instance == null) {
			instance = new GridCrypt(c);
		}
		return instance;
	}

	/**
	 * Takes the "main" frame as parameter to know where to position this new frame.
	 * @param c the frame from "main" program.
	 */
	private GridCrypt(Component c) {
		super("Decrypt");
		gridCryptFrameInit();
		pack();
		keyOk = false;
		arrayOk = false;
	}

	/**
	 * Asks the user for a crypting key. Checks if it is ok, both with length and makes sure there are no double letters.
	 */
	public void askForKey() {	
		String tempString = JOptionPane.showInputDialog(null, "Type in encrypting key, preferable over 6 letters.\n(Otherwise not all letters will be available)", "Type in crypting key", JOptionPane.QUESTION_MESSAGE);

		if (tempString != null) {
			while (tempString.length() > 10 || !doubleLettersCheckOk(tempString)) {
				if(tempString.length()> 10){
					JOptionPane.showMessageDialog(null, "To long key,  enter a shorter (Less than 10 characters)", "ERROR: to long", JOptionPane.ERROR_MESSAGE);
				}else if(!doubleLettersCheckOk(tempString)){
					JOptionPane.showMessageDialog(null, "Not valid key, cannot contain letter duplicates.", "ERROR: not valid key", JOptionPane.ERROR_MESSAGE);
				}
				tempString = JOptionPane.showInputDialog(null, "Type in encrypting key", "Type in crypting key", JOptionPane.QUESTION_MESSAGE);
				if (tempString == null) {
					break;
				}
			}
		}
		if (tempString != null) {
			cryptKey = tempString;
			System.out.println(cryptKey);
			keyOk = true;
			setUpGUIArray(getKeyArray(cryptKey));
			pack();
		}
		
		//Fixes symbolToLetter map 
		//********************************************************************************
		symbolToLetter = new HashMap<String, String>(); 
		String alphabet = "abcdefghijklmnopqrstuvwxyzåäö ";
		char[] alphabetCharArray = alphabet.toCharArray();
		char[] bigLetters, smallLetters;
		bigLetters = tempString.toUpperCase().toCharArray();
		smallLetters = tempString.toLowerCase().toCharArray();
		int i = 0;
		for (char chSmall : smallLetters) {
			for (char chBig : bigLetters) {
				if (i < alphabetCharArray.length) {
					symbolToLetter.put("" + chBig + chSmall, "" + alphabetCharArray[i]);
					i++;
				}
			}
		}
		//********************************************************************************

		//Fixes letterToSymbol map
		//********************************************************************************
		letterToSymbol = new HashMap<String, String>(); 
		for (Map.Entry<String, String> entry : symbolToLetter.entrySet()) {
			String tempKeyString = entry.getKey();
			String tempValueString = entry.getValue();
			letterToSymbol.put(tempValueString, tempKeyString);
		}
		//********************************************************************************

	}
	
	/**
	 * Checks if a string contains duplicate letters, if so the n it will return false;
	 * @param s the string that needs to be checked.
	 * @return true if the string is OK,  false ow.
	 */
	private boolean doubleLettersCheckOk(String s){
		char[] tempCharArray = s.toCharArray();
		TreeSet<String> tempTree = new TreeSet<String>();
		for(char ch: tempCharArray){
			if(tempTree.contains(""+ch)){
				return false;
			}
			tempTree.add(""+ch);
		}
		return true;
	}

	/**
	 * Initializes the frame and the panels.
	 */
	private void gridCryptFrameInit() {
		setLayout(new BorderLayout());
		topPanel();
		centerPanel();
	}

	/**
	 * Initializes the top panel.
	 */
	private void topPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 2, 1, 1));

		plainText = new JTextField();
		topPanel.add(plainText);

		codedText = new JTextField();
		topPanel.add(codedText);

		JButton convertToCoded = new JButton("Convert to coded");
		convertToCoded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertToCodedButton(plainText.getText(), codedText);
			}
		});
		topPanel.add(convertToCoded);

		JButton convertFromCoded = new JButton("Convert from coded");
		convertFromCoded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertFromCoded(codedText.getText(), plainText);
			}
		});
		topPanel.add(convertFromCoded);

		topPanel.setVisible(true);
		add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * Initializes the centerPanel.
	 */
	private void centerPanel() {
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Sets up the key array using a user provided key.
	 * @param inKey the key that the user has provided.
	 * @return a keyArray
	 */
	private String[][] getKeyArray(String inKey) {
		if (keyOk) {
			String alphabet = "abcdefghijklmnopqrstuvwxyzåäö_";
			char[] alphabetArray = alphabet.toCharArray();

			String keyList[][] = new String[inKey.length()][inKey.length()];
			int currentLetter = 0;
			for (int row = 0; row < inKey.length(); row++) {
				for (int column = 0; column < inKey.length(); column++) {
					if (currentLetter < alphabet.length()) {
						keyList[row][column] = "" + alphabetArray[currentLetter];
						currentLetter++;
					}
				}
			}
			arrayOk = true;
			return keyList;
		}
		return null;
	}

	/**
	 * Sets up the GUI for the grid array.
	 * @param inKeyArray the key array.
	 */
	private void setUpGUIArray(String inKeyArray[][]) {
		System.out.println("setUpGUIArray initialized");
		System.out.println(cryptKey);
		centerPanel.removeAll();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel arrayPanel = new JPanel();
		arrayPanel.setLayout(new GridLayout(cryptKey.length(), cryptKey.length()));
		Dimension buttonSize = new Dimension(30, 30);
		for (int i = 0; i < cryptKey.length(); i++) {
			for (int j = 0; j < cryptKey.length(); j++) {
				JButton tempButton = new JButton(inKeyArray[i][j]);
				arrayPanel.add(tempButton);
				buttonSize = tempButton.getMaximumSize();
			}
		}
		fixAreaAroundGrid(c, buttonSize);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = cryptKey.length() + 1;
		c.gridwidth = cryptKey.length() + 1;
		centerPanel.add(arrayPanel, c);
	}

	/**
	 * Sets up the capital and small letters around the letter grid.
	 * @param c the constraints for the buttons.
	 * @param buttonSize the size of the previous buttons.
	 */
	private void fixAreaAroundGrid(GridBagConstraints c, Dimension buttonSize) {
		char[] splittedTempCapital = cryptKey.toUpperCase().toCharArray();
		char[] splittedTempLower = cryptKey.toLowerCase().toCharArray();
		int x, y;
		x = 1;
		y = 0;

		for (char ch : splittedTempCapital) {
			c.gridx = x;
			c.gridy = y;
			c.fill = GridBagConstraints.VERTICAL;
			JPanel pan = new JPanel();
			JButton tempButton = new JButton("" + ch);

			pan.add(tempButton);
			centerPanel.add(pan, c);

			c.gridx = y;
			c.gridy = x;
			c.fill = GridBagConstraints.HORIZONTAL;
			JPanel pan2 = new JPanel();
			JButton tempButton2 = new JButton("" + splittedTempLower[x - 1]);
			centerPanel.add(tempButton2, c);

			x++;

		}

	}

	/**
	 * The action that is performed when you click at the convertToCoded button.
	 * @param messageToConvert the message that is to be converted.
	 * @param textField the textfield that is to be changed after conversion.
	 */
	private void convertToCodedButton(String messageToConvert, JTextField textField) {
		char[] tempCharArray = messageToConvert.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : tempCharArray) {
			sb.append(letterToSymbol.get("" + ch) + " ");
		}
		textField.setText(sb.toString());
	}

	/**
	 * The action performed when you click at the convertFromCoded button.
	 * @param messageToConvert the message that is tobe converted.
	 * @param textField the textfield that is to be changed after conversion.
	 */
	private void convertFromCoded(String messageToConvert, JTextField textField) {
		String[] splittedMessage = messageToConvert.split(" ");

		StringBuilder sb = new StringBuilder();
		for (String s : splittedMessage) {
			sb.append(symbolToLetter.get(s));
		}
		textField.setText(sb.toString());
	}

}
