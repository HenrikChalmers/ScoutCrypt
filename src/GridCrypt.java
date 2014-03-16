import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GridCrypt extends JFrame{
	private String cryptKey;
	private String[][] keyArray;
	private static GridCrypt instance = null;
	private JPanel topPanel, subPanel;
	private JTextField codedText, uncodedText;
	
	public static GridCrypt getInstance(Component c){
		if(instance == null){
			instance = new GridCrypt(c);
		}
		return instance;
	}
	
	private GridCrypt(Component c){
		super("Decrypt");
		gridCryptFrameInit();
		
		pack();
	}
	
	public void askForKey(){
		System.out.println("Key asked");
		
	}
	
	private void gridCryptFrameInit(){
		setLayout(new BorderLayout()  );
		topPanel();
		subPanel();
	}
	
	private void topPanel(){
		topPanel = new JPanel();
		//topPanel.setBackground(Color.red);
		topPanel.setLayout(new GridLayout(2,2,1,1) );
		
		codedText = new JTextField();
		topPanel.add(codedText);
		
		uncodedText = new JTextField();
		topPanel.add(uncodedText);
		
		JButton convertToCoded = new JButton("Convert to coded");
		convertToCoded.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertToCodedButton("tempstrins");
			}
		});
		topPanel.add(convertToCoded);
		
		JButton convertFromCoded = new JButton("Convert from coded");
		convertFromCoded.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertFromCoded("tempsString");
			}
		});
		topPanel.add(convertFromCoded);
		
		topPanel.setVisible(true);
		add(topPanel, BorderLayout.NORTH);
	}
	
	private void subPanel(){
		subPanel = new JPanel();
		subPanel.setBackground(Color.black);
		add(subPanel, BorderLayout.CENTER);
	}
	
	private void fixKeyArray(String inKey){
		
	}
	
	private void convertToCodedButton(String messageToConvert){
		
	}
	
	private void convertFromCoded(String MessageToConvert){
		
	}
	
	
	
}
