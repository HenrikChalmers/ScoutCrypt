import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CryptToLetter extends JFrame{
	private static CryptToLetter instance = null;
	private JPanel topPanel, decryptPanel;
	private TextArea decryptedTextArea;
	
	private CryptToLetter(Component c){
		initFrameGUI(c);
		initTopPanel();
		add(topPanel);
		initDecryptPanel();
		add(decryptPanel);
		pack();
	}
	
	public static CryptToLetter getInstance(Component c){
		if(instance == null){
			instance = new CryptToLetter(c);
		}
		return instance;
	}
	
	private void initFrameGUI(Component c){
		new JFrame("Decrypt");
		setLayout(new GridLayout(2,10,1,1));
		setLocationRelativeTo(c);
		setLocation(c.getX() + c.getWidth(), c.getY());
		initTopPanel();
	}
	
	private void initDecryptPanel() {
		decryptPanel = new JPanel();
		decryptPanel.setLayout(new FlowLayout() );
		decryptPanel.add(new JLabel("KEYBOARD HERE LATER"));
		
	}
	
	private void initTopPanel(){
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout() );
		decryptedTextArea = new TextArea();
		topPanel.add(decryptedTextArea);
	}

}
