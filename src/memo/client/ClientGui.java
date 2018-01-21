package memo.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ClientGui extends JFrame{
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextArea Jtxt;
	private JMenuBar menubar = new JMenuBar();
	private JMenu file = new JMenu("����");
	private JMenuItem newPage = new JMenuItem("���� �����");
	private JMenuItem open = new JMenuItem("����");
	private JMenuItem save = new JMenuItem("����");
	private JMenuItem saveLocation = new JMenuItem("�ٸ� �̸����� ����");
	private JMenuItem exit = new JMenuItem("������");
	
	ClientGui(){
		design();
		menu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationByPlatform(true);	//��ġ�� �ü���� ���ϵ��� ����, ������ â�Ѹ� �˾Ƽ� ��ġ ��ġ�� �ʰ� ����.
		this.setVisible(true);
	}
	void menu() {
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(open);
		file.add(save);
		file.add(saveLocation);
		file.addSeparator(); //���м�
		file.add(exit);
	}
	
	void design(){
		setContentPane(panel);
		Jtxt = new JTextArea(25,50);
		JScrollPane scroll = new JScrollPane(Jtxt);	//��ũ���߰�
		Jtxt.setLineWrap(true);			//�ڵ� �ٹٲٱ�
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//���� ��ũ�ѹٸ� ����
		panel.add(scroll,BorderLayout.CENTER);
	}
}
