import java.util.Scanner;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class seconday extends JFrame{
	private JLabel item1;
	
	public seconday(){
		super("This is title");
		setLayout(new FlowLayout());
		item1 = new JLabel("This is a sentence");
		item1.setToolTipText("YO man");
		add(item1);
	}
	
}
