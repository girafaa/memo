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
	private JMenu file = new JMenu("����");
	private JMenuItem newPage = new JMenuItem("���� �����");
	private JMenuItem list = new JMenuItem("��ü ����Ʈ");
	private JMenuItem save = new JMenuItem("����");
	private JMenuItem exit = new JMenuItem("������");
	
	inputWindow(){
		event();
		design();
		menu();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //xŰ ����(+�⺻�����ϴ� ���� �ɼ��� �ƴ϶� ���� ���ϴ� �� �ϰ� ���� ��, �̺�Ʈ �����ϸ� �ȴ�.)
		this.addWindowListener(new WindowAdapter() {		//XŰ ���� �ɼ� ����
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
		this.setJMenuBar(menubar);
		menubar.add(file);
		file.add(newPage);
		file.add(list);
		file.add(save);
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
	
	void event() {
		newPage.addActionListener(e->{
			Jtxt.setText("");
		});
		
		list.addActionListener(e->{
		});
		
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
		
		//������
		exit.addActionListener(e->{
			System.exit(0);
		});
		
	}
}
