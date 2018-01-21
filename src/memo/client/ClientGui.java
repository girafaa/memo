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
	private JMenu file = new JMenu("파일");
	private JMenuItem newPage = new JMenuItem("새로 만들기");
	private JMenuItem open = new JMenuItem("열기");
	private JMenuItem save = new JMenuItem("저장");
	private JMenuItem saveLocation = new JMenuItem("다른 이름으로 저장");
	private JMenuItem exit = new JMenuItem("끝내기");
	
	ClientGui(){
		design();
		menu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationByPlatform(true);	//위치를 운영체제가 정하도록 설정, 여러개 창켜면 알아서 위치 겹치지 않게 만듬.
		this.setVisible(true);
	}
	void menu() {
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(open);
		file.add(save);
		file.add(saveLocation);
		file.addSeparator(); //구분선
		file.add(exit);
	}
	
	void design(){
		setContentPane(panel);
		Jtxt = new JTextArea(25,50);
		JScrollPane scroll = new JScrollPane(Jtxt);	//스크롤추가
		Jtxt.setLineWrap(true);			//자동 줄바꾸기
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//수직 스크롤바만 생성
		panel.add(scroll,BorderLayout.CENTER);
	}
}
