import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
	private void initData(){
		crypt = LetterToCrypt.getInstance(frame);
		decrypt = CryptToLetter.getInstance(frame);
		gridCrypt = GridCrypt.getInstance(frame);
	}
	
	private void initFrame(){
		frame = new JFrame("Equmenia Fristad Crypting Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0, 0);
		
	}
	
	private void initPanels(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout() );
		initButtons(mainPanel);
		frame.add(mainPanel);
	}
	
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
	
	private void startLetterToCrypted(){
		if(decrypt.isVisible()){
			decrypt.setVisible(false);
		}else if(gridCrypt.isVisible() ){
			gridCrypt.setVisible(false);
		}
		crypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		crypt.setVisible(true);
	}
	
	private void startCryptedToLetter(){
		if(crypt.isVisible()){
			crypt.setVisible(false);
		}else if(gridCrypt.isVisible() ){
			gridCrypt.setVisible(false);
		}
		decrypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		decrypt.setVisible(true);
		
	}
	
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
