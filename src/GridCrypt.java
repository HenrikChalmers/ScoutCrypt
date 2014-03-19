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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	private GridCrypt(Component c) {
		super("Decrypt");
		gridCryptFrameInit();
		pack();
		keyOk = false;
		arrayOk = false;
	}

	public void askForKey() {
		String tempString = JOptionPane.showInputDialog(null, "Type in encrypting key", "Type in crypting key", JOptionPane.QUESTION_MESSAGE);

		if (tempString != null) {
			while (tempString.length() > 20) {
				JOptionPane.showMessageDialog(null, "To long key,  enter a shorter (Less than 20 char)", "title", JOptionPane.ERROR_MESSAGE);
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

		//********************************************************************************
		symbolToLetter = new HashMap<String, String>(); //Fixes symbolToLetter map 
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

		//********************************************************************************
		letterToSymbol = new HashMap<String, String>(); //Fixes letterToSymbol map
		for (Map.Entry<String, String> entry : symbolToLetter.entrySet()) {
			String tempKeyString = entry.getKey();
			String tempValueString = entry.getValue();
			letterToSymbol.put(tempValueString, tempKeyString);
		}
		//********************************************************************************

	}

	private void gridCryptFrameInit() {
		setLayout(new BorderLayout());
		topPanel();
		centerPanel();
	}

	private void topPanel() {
		topPanel = new JPanel();
		//topPanel.setBackground(Color.red);
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

	private void centerPanel() {
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
	}

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

	private void convertToCodedButton(String messageToConvert, JTextField textField) {
		char[] tempCharArray = messageToConvert.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : tempCharArray) {
			sb.append(letterToSymbol.get("" + ch) + " ");
		}
		textField.setText(sb.toString());
	}

	private void convertFromCoded(String messageToConvert, JTextField textField) {
		String[] splittedMessage = messageToConvert.split(" ");

		StringBuilder sb = new StringBuilder();
		for (String s : splittedMessage) {
			sb.append(symbolToLetter.get(s));
		}
		textField.setText(sb.toString());
	}

}
