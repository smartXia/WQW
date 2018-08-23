package com.xiapeifu.view;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.xiapeifu.bean.Mall;
import com.xiapeifu.dao.BaseDao;

/**
 * ¼�����
 * 
 * @author Administrator
 * 
 */

public class MallViewDel extends JFrame {
	// �����Ӳ˵�
	private JMenuItem save, update, getS, del;

	// ���������ı���
	private JTextField sid, sname, sprice, snumber, slocation;

	private JRadioButton sman, swoman; // ���嵥ѡ��ť���У�Ů��

	private JButton sele; // �����ѯ��ť

	public MallViewDel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MallViewDel.class.getResource("/com/xiapeifu/images/mall.png")));
		this.setTitle("���й���ϵͳ-ɾ����Ϣ");
		initMenu(); // ��ʼ���˵���
		// ��ʼ���ı���
		sid = new JTextField(20);
		sname = new JTextField(20);
		sname.setEditable(false);// ���ɱ༭
		slocation = new JTextField(20);
		slocation.setEditable(false);
		sprice = new JTextField(20);
		sprice.setEditable(false);
		snumber = new JTextField(20);
		snumber.setEditable(false);
		// ��ʼ����ѡ��ť
		sman = new JRadioButton("��", true); // Ĭ��ѡ��
		swoman = new JRadioButton("��", false);
		ButtonGroup bg = new ButtonGroup(); // ���尴ť��
		bg.add(sman);
		bg.add(swoman);
		sman.setEnabled(false); // ���ɱ༭
		swoman.setEnabled(false);

		sele = new JButton("ɾ��"); // ��ʼ����ѯ��ť

		this.getContentPane().setLayout(new GridLayout(7, 1)); // ����6��1�����񲼾ֹ�����
		JPanel p1 = new JPanel();
		p1.add(new JLabel("����Ҫɾ���ı�ţ�"));
		p1.add(sid);
		p1.add(sele);
		this.getContentPane().add(p1);


		JPanel p2 = new JPanel();
		p2.add(new JLabel("���ƣ�"));
		p2.add(sname);
		this.getContentPane().add(p2);

		JPanel p3 = new JPanel();
		p3.add(new JLabel("�Ƿ��ۿۣ�"));
		p3.add(sman);
		p3.add(swoman);
		this.getContentPane().add(p3);

		JPanel p4 = new JPanel();
		p4.add(new JLabel("���أ�"));
		p4.add(slocation);
		this.getContentPane().add(p4);

		JPanel p5 = new JPanel();
		p5.add(new JLabel("�۸�"));
		p5.add(sprice);
		this.getContentPane().add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("������"));
		p6.add(snumber);
		this.getContentPane().add(p6);

		JPanel p7 = new JPanel();
		this.getContentPane().add(p7);
		this.getContentPane().add(p7);
		
		// ע�������
		sele.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id=sid.getText().trim();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "��������");
					return;
				}
				
				BaseDao pd = new BaseDao();
				Mall mall = pd.getMall(id);
				if (mall == null) {
					JOptionPane.showMessageDialog(null, "�����ڴ˱�ŵ���Ʒ");

					sname.setText("");					
					slocation.setText("");
					snumber.setText("");
					sprice.setText("");
					sman.setSelected(true);
					return;
				}
				System.out.println(mall.toString());
				sname.setText(mall.getName());
				snumber.setText(mall.getnumber());
				sprice.setText(mall.getPrice());
				slocation.setText(mall.getnumber());

				if (mall.getDiscount().equals("��")) {
					sman.setSelected(true);
				} else {
					swoman.setSelected(true);
				}

				
				
				int i=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ñ�ż�ȫ����Ϣ��");
				if(i==0){
					BaseDao sd = new BaseDao();
					int j=sd.del(id);
					if(j>0){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					}else{
						JOptionPane.showConfirmDialog(null, "ɾ��ʧ��");
					}
				}

			}

		});

		this.setBounds(300, 200, 570, 325); // ���ô���λ�úʹ�С���ɸ����Լ�������Ļ���������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // �رմ����¼�
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
	}
	

	// ��ʼ���˵�
	public void initMenu() {
		save = new JMenuItem("¼�볬�л�����Ϣ");
		update = new JMenuItem("�޸ĳ��л�����Ϣ");
		getS = new JMenuItem("��ѯ���л�����Ϣ");
		del = new JMenuItem("ɾ�����л�����Ϣ");

		JMenuBar menubar = new JMenuBar(); // ��������������
		JMenu menu = new JMenu("�˵�ѡ��"); // �����˵�������
		menu.add(save);
		menu.add(update);
		menu.add(getS);
		menu.add(del);
		menubar.add(menu);

		// ע���¼�������
		save.addActionListener(new StudentAction());
		update.addActionListener(new StudentAction());
		getS.addActionListener(new StudentAction());
		del.addActionListener(new StudentAction());

		this.setJMenuBar(menubar);
	}

	// �¼�
	class StudentAction extends AbstractAction {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save){
				MallViewAdd sva=new MallViewAdd();
				sva.setVisible(true);
			}else if(e.getSource()==update){
				MallViewUpdate svu=new MallViewUpdate();
				svu.setVisible(true);
			}else if(e.getSource()==getS){
				MallViewGet svg=new MallViewGet();
				svg.setVisible(true);
			}else if(e.getSource()==del){
				MallViewDel svd=new MallViewDel();
				svd.setVisible(true);
			}
		}

	}
}
