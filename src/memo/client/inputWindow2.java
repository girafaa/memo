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
	
	//-----����������-----
	private static int blockIndex;
	private static int blockLength;

	//-----�޴�-----
	private JMenu file = new JMenu("����");
	private JMenu edit = new JMenu("����");
	
	//-----���� �޴�������-----
	private JMenuItem newPage = new JMenuItem("���� �����");
	private JMenuItem list = new JMenuItem("��ü ����Ʈ");
	private JMenuItem save = new JMenuItem("����");
	private JMenuItem exit = new JMenuItem("������");
	
	//-----���� �޴�������-----
	private JMenuItem font = new JMenuItem("�۲�");
	private JMenuItem fontColor = new JMenuItem("���ڻ�");
	private JMenuItem backColor = new JMenuItem("����");
	private JMenuItem fontBig = new JMenuItem("���� ũ��");
	private JMenuItem fontSmall = new JMenuItem("���� �۰�");
	
	inputWindow2(){
		event();
		design();
		menu();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //xŰ ����(+�⺻�����ϴ� ���� �ɼ��� �ƴ϶� ���� ���ϴ� �� �ϰ� ���� ��, �̺�Ʈ �����ϸ� �ȴ�.)

		//-----XŰ ���� �ɼ� ����-----
		this.addWindowListener(new WindowAdapter() {		
			public void windowClosing(WindowEvent e) {
				int select = JOptionPane.showConfirmDialog(panel, "�����Ͻðڽ��ϱ�?");
				//yes : 0, no : 1, cancel : 2
				if(select==1) {
					System.exit(0);
				}
				if(select==0) {
					String id = "hee";		//���߿� �α��� �������� ��������
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
		setLocationByPlatform(true);	//��ġ�� �ü���� ���ϵ��� ����, ������ â�Ѹ� �˾Ƽ� ��ġ ��ġ�� �ʰ� ����.
		this.setVisible(true);
	}
	void menu() {
		//-----�޴� �߰�-----
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(list);
		file.add(save);
		file.addSeparator(); //���м�
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
		//-----���� ũ��-----
		fontBig.addActionListener(e->{
			Font font = textPane.getFont();
			int size = font.getSize();
			textPane.setFont(new Font("����", Font.PLAIN, size+5));
		});
		
		//-----���� �۰�-----
		fontSmall.addActionListener(e->{
			Font font = textPane.getFont();
			int size = font.getSize();
			textPane.setFont(new Font("����", Font.PLAIN, size-5));
		});
		

		//-----����-----
		backColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "���� ����â", Color.red);
			textPane.setBackground(c);
		});
		

		//-----���ڻ�-----
		fontColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "���� ����â", Color.red);
			textPane.setForeground(c);
		});
		

		//-----�������ʱ�ȭ-----
		newPage.addActionListener(e->{
			textPane.setText("");
		});
		

		//-----����Ʈ ȭ����ȯ-----
		list.addActionListener(e->{
		});
		

		//-----����-----
		save.addActionListener(e->{
			String id = "hee";		//���߿� �α��� �������� ��������
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
			JOptionPane.showMessageDialog(this, "���� �Ϸ�", "����", JOptionPane.PLAIN_MESSAGE);
		});
		

		//-----������-----
		exit.addActionListener(e->{
			System.exit(0);
		});
		

		loadBlock();
		//����Ű ���
		InputMap iMap = textPane.getInputMap();
		ActionMap aMap = textPane.getActionMap();
		
		KeyStroke fontBig = KeyStroke.getKeyStroke('E',Event.ALT_MASK+Event.SHIFT_MASK); 
		Action BigAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				loadBlock();
		    	System.out.println("text");
				//��Ʈ����
			    Font font = new Font("Serif", Font.ITALIC, 30);	//��Ʈ ����
			    setJTextPaneFont(textPane, font, Color.darkGray,blockIndex,blockLength);
			}
		};	
		iMap.put(fontBig, "enter");
		aMap.put("enter", BigAction);
	    
	}
//-----��Ϲ��� �ҷ����� �޼ҵ�-----	
	void loadBlock() {
	    CaretListener listener = new CaretListener() {
	      public void caretUpdate(CaretEvent caretEvent) {
	        	blockIndex = caretEvent.getDot()<caretEvent.getMark()?caretEvent.getDot():caretEvent.getMark();	//��� ���� ��ġ
	        	blockLength = Math.abs(caretEvent.getMark()-caretEvent.getDot());	//��� ����
	        	System.out.println("�������ν�");
	      }
	    };
    	System.out.println("text");
	    textPane.addCaretListener(listener);
	}
	
	//���� ��, �۲� ���� �޼ҵ� ��Ϲ��� ����
	private static void setJTextPaneFont(JTextPane textPane, Font font, Color c, int blockIndex, int blockLength) {
	    MutableAttributeSet attrs = textPane.getInputAttributes();//JTextPane. getInputAttributes () TextPane�� �Է� �Ӽ��� �����ݴϴ�.
	   
	    //��Ʈ��ü���� �ҷ��� StyleConstants�� ä���
	    StyleConstants.setFontFamily(attrs, font.getFamily());
	    StyleConstants.setFontSize(attrs, font.getSize());
	    StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0); 	
	    StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
	    StyleConstants.setForeground(attrs, c);	//attr�� ������ ���� �ֱ�

	    //StyledDocument�� �̿��� textpane�� �Ӽ� �����Ѵ�. (���� �����ε���, ����, ������ �۲�, ����)
	    StyledDocument doc = textPane.getStyledDocument();
	    doc.setCharacterAttributes(blockIndex, blockLength, attrs, false);
    	System.out.println("�۲ú���");
	}

}
