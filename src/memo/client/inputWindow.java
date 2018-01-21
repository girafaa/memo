package memo.client;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import memo.beans.memoDao;
import memo.beans.memoDto;

public class inputWindow extends JFrame{
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextArea Jtxt;
	private JMenuBar menubar = new JMenuBar();
	private JMenu file = new JMenu("파일");
	private JMenuItem newPage = new JMenuItem("새로 만들기");
	private JMenuItem list = new JMenuItem("전체 리스트");
	private JMenuItem save = new JMenuItem("저장");
	private JMenuItem exit = new JMenuItem("끝내기");
	
	inputWindow(){
		event();
		design();
		menu();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //x키 방지(+기본제공하는 종료 옵션이 아니라 내가 원하는 거 하고 싶을 때, 이벤트 설정하면 된다.)
		this.addWindowListener(new WindowAdapter() {		//X키 종료 옵션 설정
			public void windowClosing(WindowEvent e) {
				int select = JOptionPane.showConfirmDialog(panel, "저장하시겠습니까?");
				//yes : 0, no : 1, cancel : 2
				if(select==1) {
					System.exit(0);
				}
				if(select==0) {
					String id = "hee";		//나중에 로그인 정보에서 가져오기
					String detail = Jtxt.getText();
					memoDto mdto = new memoDto();
					mdto.setId(id);
					mdto.setDetail(detail);
					memoDao mdao = new memoDao();
					try {
						mdao.write(mdto);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
				
			}
		});
		setSize(700, 500);
		setLocationByPlatform(true);	//위치를 운영체제가 정하도록 설정, 여러개 창켜면 알아서 위치 겹치지 않게 만듬.
		this.setVisible(true);
	}
	void menu() {
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(list);
		file.add(save);
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
	
	void event() {
		newPage.addActionListener(e->{
			Jtxt.setText("");
		});
		
		list.addActionListener(e->{
		});
		
		save.addActionListener(e->{
			String id = "hee";		//나중에 로그인 정보에서 가져오기
			String detail = Jtxt.getText();
			memoDto mdto = new memoDto();
			mdto.setId(id);
			mdto.setDetail(detail);
			memoDao mdao = new memoDao();
			try {
				mdao.write(mdto);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "저장 완료", "저장", JOptionPane.PLAIN_MESSAGE);
		});
		
		//끝내기
		exit.addActionListener(e->{
			System.exit(0);
		});
		
	}
}
