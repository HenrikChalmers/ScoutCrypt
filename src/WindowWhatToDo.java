import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Controlls the workflow of the entire program.
 * @author Henrik Johansson
 * @version 2014-03-20
 */
public class WindowWhatToDo {
	JFrame frame;
	LetterToCrypt crypt;
	CryptToLetter decrypt;
	GridCrypt gridCrypt;
	
	public WindowWhatToDo(){
		initFrame();
		initPanels();
		frame.setSize(400, 75);
		frame.pack();
		frame.setVisible(true);
		initData();
		
		
	}
	/**
	 * Initializes all the other windows.
	 */
	private void initData(){
		crypt = LetterToCrypt.getInstance(frame);
		decrypt = CryptToLetter.getInstance(frame);
		gridCrypt = GridCrypt.getInstance(frame);
	}
	
	/**
	 * Initializes the starting frame.
	 */
	private void initFrame(){
		frame = new JFrame("Equmenia Fristad Crypting Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0, 0);
		
	}
	
	/**
	 * Initializes the panels.
	 */
	private void initPanels(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout() );
		initButtons(mainPanel);
		frame.add(mainPanel);
	}
	
	/**
	 * Initializes the buttons in the main menu.
	 * @param mainPanel the panel where to store the buttons.
	 */
	private void initButtons(JPanel mainPanel){
		JButton letterToCryptButton = new JButton("Encrypt: Letter to Symbol");
		letterToCryptButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startLetterToCrypted();
			}
		});
		mainPanel.add(letterToCryptButton);
		
		JButton cryptToLetter = new JButton("Decrypt: Symbol to Letter");
		cryptToLetter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startCryptedToLetter();
			}
		});
		mainPanel.add(cryptToLetter);
		
		JButton gridCryptButton = new JButton("GridCrypt");
		gridCryptButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startGridCrypt();
				gridCrypt.askForKey();
			}
		});
		mainPanel.add(gridCryptButton);
	}
	
	/**
	 * Shows the letterToCrypted window, and makes the other invisible.
	 */
	private void startLetterToCrypted(){
		if(decrypt.isVisible()){
			decrypt.setVisible(false);
		}else if(gridCrypt.isVisible() ){
			gridCrypt.setVisible(false);
		}
		crypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		crypt.setVisible(true);
	}
	
	/**
	 * Shows the cryptedToLetter window, and makes the other invisible.
	 */
	private void startCryptedToLetter(){
		if(crypt.isVisible()){
			crypt.setVisible(false);
		}else if(gridCrypt.isVisible() ){
			gridCrypt.setVisible(false);
		}
		decrypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		decrypt.setVisible(true);
		
	}
	
	/**
	 * Shows the gridCrypt window, and makes the other invisible.
	 */
	private void startGridCrypt(){
		if(crypt.isVisible()){
			crypt.setVisible(false);
		}else if(decrypt.isVisible() ){
			decrypt.setVisible(false);
		}
		gridCrypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		gridCrypt.setVisible(true);
	}

}
