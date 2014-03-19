import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridCrypt extends JFrame {
	private String cryptKey;
	private boolean keyOk, arrayOk;
	private static GridCrypt instance = null;
	private JPanel topPanel, centerPanel, leftPanel, downPanel;
	private JTextField codedText, uncodedText;

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

		codedText = new JTextField();
		topPanel.add(codedText);

		uncodedText = new JTextField();
		topPanel.add(uncodedText);

		JButton convertToCoded = new JButton("Convert to coded");
		convertToCoded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertToCodedButton("tempstrins");
			}
		});
		topPanel.add(convertToCoded);

		JButton convertFromCoded = new JButton("Convert from coded");
		convertFromCoded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertFromCoded("tempsString");
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
			String alphabet = "abcdefghijklmnopqrstuvwxyzåäö";
			char[] alphabetArray = alphabet.toCharArray();

			String keyList[][] = new String[inKey.length()][inKey.length()];
			int currentLetter = 0;
			for (int row = 0; row < inKey.length(); row++) {
				for (int column = 0; column < inKey.length(); column++) {
					if (currentLetter < alphabet.length()) {
						keyList[row][column] = "" + alphabetArray[currentLetter];
						currentLetter++;
						System.out.println("Letter at position: " + row + column + "\n" + keyList[row][column]);
					}
				}
			}
		arrayOk = true;
		return keyList;
		}
		return null;
	}
	
	private void setUpGUIArray(String inKeyArray[][]){
		System.out.println("setUpGUIArray initialized");
		System.out.println(cryptKey);
		centerPanel.removeAll();
		centerPanel.setLayout(new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel arrayPanel = new JPanel();
		arrayPanel.setLayout(new GridLayout(cryptKey.length(), cryptKey.length()));
		Dimension buttonSize = new Dimension(30,30);
		for(int i = 0; i < cryptKey.length(); i++){
			for(int j = 0; j < cryptKey.length(); j++){
				JButton tempButton = new JButton(inKeyArray[i][j]);
				arrayPanel.add(tempButton);
				buttonSize = tempButton.getMaximumSize();
			}
		}
		fixAreaAroundGrid(c,buttonSize);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = cryptKey.length()+1;
		c.gridwidth = cryptKey.length()+1;
		centerPanel.add(arrayPanel, c);
	}
	
	private void fixAreaAroundGrid(GridBagConstraints c, Dimension buttonSize){
		char[] splittedTempCapital = cryptKey.toUpperCase().toCharArray();
		char[] splittedTempLower = cryptKey.toLowerCase().toCharArray();
		int x,y;
		x = 1;
		y = 0;
		
		
		for(char ch: splittedTempCapital){
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
			JButton tempButton2 = new JButton(""+ splittedTempLower[x-1]);
			centerPanel.add(tempButton2, c);
			
			x++;
			
		}
		
	}

	private void convertToCodedButton(String messageToConvert) {

	}

	private void convertFromCoded(String MessageToConvert) {

	}

}
