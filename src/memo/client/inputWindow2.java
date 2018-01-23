package memo.client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import memo.beans.memoDao;
import memo.beans.memoDto;

public class inputWindow2 extends JFrame{
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextPane textPane = new JTextPane();
	private JMenuBar menubar = new JMenuBar();
	
	//-----블럭범위변수-----
	private static int blockIndex;
	private static int blockLength;

	//-----메뉴-----
	private JMenu file = new JMenu("파일");
	private JMenu edit = new JMenu("서식");
	
	//-----파일 메뉴아이템-----
	private JMenuItem newPage = new JMenuItem("새로 만들기");
	private JMenuItem list = new JMenuItem("전체 리스트");
	private JMenuItem save = new JMenuItem("저장");
	private JMenuItem exit = new JMenuItem("끝내기");
	
	//-----서식 메뉴아이템-----
	private JMenuItem font = new JMenuItem("글꼴");
	private JMenuItem fontColor = new JMenuItem("글자색");
	private JMenuItem backColor = new JMenuItem("배경색");
	private JMenuItem fontBig = new JMenuItem("글자 크게");
	private JMenuItem fontSmall = new JMenuItem("글자 작게");
	
	inputWindow2(){
		event();
		design();
		menu();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //x키 방지(+기본제공하는 종료 옵션이 아니라 내가 원하는 거 하고 싶을 때, 이벤트 설정하면 된다.)

		//-----X키 종료 옵션 설정-----
		this.addWindowListener(new WindowAdapter() {		
			public void windowClosing(WindowEvent e) {
				int select = JOptionPane.showConfirmDialog(panel, "저장하시겠습니까?");
				//yes : 0, no : 1, cancel : 2
				if(select==1) {
					System.exit(0);
				}
				if(select==0) {
					String id = "hee";		//나중에 로그인 정보에서 가져오기
					String detail = textPane.getText();
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
		//-----메뉴 추가-----
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(list);
		file.add(save);
		file.addSeparator(); //구분선
		file.add(exit);
		menubar.add(edit);
		edit.add(font);
		edit.add(fontColor);
		edit.add(backColor);
		edit.add(fontBig);
		edit.add(fontSmall);
	}
	
	void design(){
		setContentPane(panel);
		panel.add(textPane,BorderLayout.CENTER);
	}
	
	void event() {
		//-----글자 크게-----
		fontBig.addActionListener(e->{
			Font font = textPane.getFont();
			int size = font.getSize();
			textPane.setFont(new Font("돋음", Font.PLAIN, size+5));
		});
		
		//-----글자 작게-----
		fontSmall.addActionListener(e->{
			Font font = textPane.getFont();
			int size = font.getSize();
			textPane.setFont(new Font("돋음", Font.PLAIN, size-5));
		});
		

		//-----배경색-----
		backColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "색상 선택창", Color.red);
			textPane.setBackground(c);
		});
		

		//-----글자색-----
		fontColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "색상 선택창", Color.red);
			textPane.setForeground(c);
		});
		

		//-----페이지초기화-----
		newPage.addActionListener(e->{
			textPane.setText("");
		});
		

		//-----리스트 화면전환-----
		list.addActionListener(e->{
		});
		

		//-----저장-----
		save.addActionListener(e->{
			String id = "hee";		//나중에 로그인 정보에서 가져오기
			String detail = textPane.getText();
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
		

		//-----끝내기-----
		exit.addActionListener(e->{
			System.exit(0);
		});
		

		loadBlock();
		//단축키 사용
		InputMap iMap = textPane.getInputMap();
		ActionMap aMap = textPane.getActionMap();
		
		KeyStroke fontBig = KeyStroke.getKeyStroke('E',Event.ALT_MASK+Event.SHIFT_MASK); 
		Action BigAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				loadBlock();
		    	System.out.println("text");
				//폰트설정
			    Font font = new Font("Serif", Font.ITALIC, 30);	//폰트 설정
			    setJTextPaneFont(textPane, font, Color.darkGray,blockIndex,blockLength);
			}
		};	
		iMap.put(fontBig, "enter");
		aMap.put("enter", BigAction);
	    
	}
//-----블록범위 불러오는 메소드-----	
	void loadBlock() {
	    CaretListener listener = new CaretListener() {
	      public void caretUpdate(CaretEvent caretEvent) {
	        	blockIndex = caretEvent.getDot()<caretEvent.getMark()?caretEvent.getDot():caretEvent.getMark();	//블록 시작 위치
	        	blockLength = Math.abs(caretEvent.getMark()-caretEvent.getDot());	//블록 길이
	        	System.out.println("블럭범위인식");
	      }
	    };
    	System.out.println("text");
	    textPane.addCaretListener(listener);
	}
	
	//글자 색, 글꼴 변경 메소드 블록범위 포함
	private static void setJTextPaneFont(JTextPane textPane, Font font, Color c, int blockIndex, int blockLength) {
	    MutableAttributeSet attrs = textPane.getInputAttributes();//JTextPane. getInputAttributes () TextPane의 입력 속성을 돌려줍니다.
	   
	    //폰트객체에서 불러와 StyleConstants에 채운다
	    StyleConstants.setFontFamily(attrs, font.getFamily());
	    StyleConstants.setFontSize(attrs, font.getSize());
	    StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0); 	
	    StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
	    StyleConstants.setForeground(attrs, c);	//attr에 적용할 색상 넣기

	    //StyledDocument를 이용해 textpane의 속성 변경한다. (적용 시작인덱스, 길이, 적용할 글꼴, 몰라)
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.setCharacterAttributes(blockIndex, blockLength, attrs, false);
    	System.out.println("글꼴변경");
	}

}
