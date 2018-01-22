package memo.client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import memo.beans.memoDao;
import memo.beans.memoDto;

public class inputWindow extends JFrame{
	private JPanel panel = new JPanel(new BorderLayout());
	private JTextArea Jtxt = new JTextArea(25,50);
	private JMenuBar menubar = new JMenuBar();

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
	
	inputWindow(){
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
		
		JScrollPane scroll = new JScrollPane(Jtxt);	//��ũ���߰�
		Jtxt.setLineWrap(true);			//�ڵ� �ٹٲٱ�
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//���� ��ũ�ѹٸ� ����
		panel.add(scroll,BorderLayout.CENTER);
	}
	
	void event() {
		//-----���� ũ��-----
		fontBig.addActionListener(e->{
			Font font = Jtxt.getFont();
			int size = font.getSize();
			Jtxt.setFont(new Font("����", Font.PLAIN, size+5));
		});
		
		//-----���� �۰�-----
		fontSmall.addActionListener(e->{
			Font font = Jtxt.getFont();
			int size = font.getSize();
			Jtxt.setFont(new Font("����", Font.PLAIN, size-5));
		});
		

		//-----����-----
		backColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "���� ����â", Color.red);
			Jtxt.setBackground(c);
		});
		

		//-----���ڻ�-----
		fontColor.addActionListener(e->{
			Color c = JColorChooser.showDialog(panel, "���� ����â", Color.red);
			Jtxt.setForeground(c);
		});
		

		//-----�������ʱ�ȭ-----
		newPage.addActionListener(e->{
			Jtxt.setText("");
		});
		

		//-----����Ʈ ȭ����ȯ-----
		list.addActionListener(e->{
		});
		

		//-----����-----
		save.addActionListener(e->{
			String id = "hee";		//���߿� �α��� �������� ��������
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
			JOptionPane.showMessageDialog(this, "���� �Ϸ�", "����", JOptionPane.PLAIN_MESSAGE);
		});
		

		//-----������-----
		exit.addActionListener(e->{
			System.exit(0);
		});
		
		
		//��Ϲ��� ���� ����Ű �������� �ϸ�ȴ�.
	    CaretListener listener = new CaretListener() {
	        public void caretUpdate(CaretEvent caretEvent) {
//	          System.out.println("Dot: "+ caretEvent.getDot());
//	          System.out.println("Mark: "+caretEvent.getMark());
	          try {
				System.out.println(Jtxt.getText(caretEvent.getDot(),caretEvent.getMark()-caretEvent.getDot()));
	          } catch (BadLocationException e) {
				e.printStackTrace();
	          }
	        }
	    };
	    Jtxt.addCaretListener(listener);
		
		
	}
}
