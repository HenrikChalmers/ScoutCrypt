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
	
	public WindowWhatToDo(){
		
		initFrame();
		initPanels();
		frame.setSize(400, 75);
		frame.setVisible(true);
		initData();
		
		
	}
	private void initData(){
		crypt = LetterToCrypt.getInstance(frame);
		decrypt = CryptToLetter.getInstance(frame);
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
		JButton letterToCryptButton = new JButton("Encrypt");
		letterToCryptButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startLetterToCrypted();
			}
		});
		mainPanel.add(letterToCryptButton);
		
		JButton cryptToLetter = new JButton("Decrypt");
		cryptToLetter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startCryptedToLetter();
			}
		});
		mainPanel.add(cryptToLetter);
	}
	
	private void startLetterToCrypted(){
		if(decrypt.isVisible()){
			decrypt.setVisible(false);
		}
		crypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		crypt.setVisible(true);
	}
	
	private void startCryptedToLetter(){
		if(crypt.isVisible()){
			crypt.setVisible(false);
		}
		decrypt.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		decrypt.setVisible(true);
		
	}

}
