package com.xiapeifu.view;

import java.awt.GridLayout;
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

import java.awt.Toolkit;

/**
 * ¼�����
 * 
 * @author Administrator
 * 
 */

public class MallViewAdd extends JFrame {
	// �����Ӳ˵�
	private JMenuItem save, update, getS, del;

	// ���������ı���
	private JTextField sid, sname,sdiscount,  sprice,slocation,snumber;

	private JRadioButton sman, swoman; // ���嵥ѡ��ť��shifou��

	private JButton ok, cance; // ���尴ť

	public MallViewAdd() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MallViewAdd.class.getResource("/com/xiapeifu/images/mall.png")));
		this.setTitle("���й���ϵͳ-¼����Ϣ");
		initMenu(); // ��ʼ���˵���
		// ��ʼ���ı���
		sid = new JTextField(20);
		sname = new JTextField(20);
		slocation = new JTextField(20);
		sdiscount = new JTextField(20);
		sprice = new JTextField(20);
		snumber = new JTextField(20);
		// ��ʼ����ť
		sman = new JRadioButton("��", true); // Ĭ��ѡ��
		swoman = new JRadioButton("��", false);
		ButtonGroup bg = new ButtonGroup(); // ���尴ť��
		bg.add(sman);
		bg.add(swoman);

		// ��ʼ����ť
		ok = new JButton("¼��");
		cance = new JButton("����");

		this.getContentPane().setLayout(new GridLayout(7, 1)); // ����7��1�����񲼾ֹ�����
		JPanel p1 = new JPanel();
		p1.add(new JLabel("\u7F16\u53F7\uFF1A"));
		p1.add(sid);
		this.getContentPane().add(p1);

		JPanel p2 = new JPanel();
		p2.add(new JLabel("\u54C1\u540D\uFF1A"));
		p2.add(sname);
		this.getContentPane().add(p2);

		JPanel p3 = new JPanel();
		p3.add(new JLabel("\u662F\u5426\u6298\u6263\uFF1A"));
		p3.add(sman);
		p3.add(swoman);
		this.getContentPane().add(p3);

		JPanel p4 = new JPanel();
		p4.add(new JLabel("\u4EA7\u5730\uFF1A"));
		p4.add(slocation);
		this.getContentPane().add(p4);

		JPanel p5 = new JPanel();
		p5.add(new JLabel("\u4EF7\u683C\uFF1A"));
		p5.add(sprice);
		this.getContentPane().add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("\u6570\u91CF\uFF1A"));
		p6.add(snumber);
		this.getContentPane().add(p6);

		JPanel p7 = new JPanel();
		p7.add(ok);
		p7.add(cance);
		this.getContentPane().add(p7);

		// ע�������
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sex = "";
				String id = sid.getText().trim();
				String name = sname.getText().trim();
				String location = slocation.getText().trim();
				String price = sprice.getText().trim();
				String number = snumber.getText().trim();
				if (sman.isSelected()) { // �����ť�б�ѡ��
					sex = "��";
				} else {
					sex = "��";
				}

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "��Ų���Ϊ��");
					return;
				}
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ������Ϊ��");
					return;
				}
				if (location.equals("")) {
					JOptionPane.showMessageDialog(null, " ���ز���Ϊ��");
					return;
				}
				if (price.equals("")) {
					JOptionPane.showMessageDialog(null, "�۸���Ϊ��");
					return;
				}
				if (number.equals("")) {
					JOptionPane.showMessageDialog(null, "��������Ϊ��");
					return;
				}

				Mall mall = new Mall(id, name, sex, location, price,
						number);
				BaseDao sd = new BaseDao();
				int i = sd.add(mall);
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "¼��ɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "�������ظ�");
				}
			}

		});
		cance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid.setText("");
				sname.setText("");
				sprice.setText("");
				slocation.setText("");
				snumber.setText("");
				sman.setSelected(true);
			}

		});

		this.setBounds(250, 150, 570, 325); // ���ô���λ�úʹ�С���ɸ����Լ�������Ļ���������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // �رմ����¼�
	}

	// ��ʼ���˵�
	public void initMenu() {
		save=new JMenuItem("¼�볬�л�����Ϣ");
		update=new JMenuItem("�޸ĳ��л�����Ϣ");
		getS=new JMenuItem("��ѯ���л�����Ϣ");
		del=new JMenuItem("ɾ�����л�����Ϣ");
		

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
