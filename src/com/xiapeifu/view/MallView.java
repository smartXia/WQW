package com.xiapeifu.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.forwhat.FocusTraversalOnArray;

public class MallView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4247064855209872007L;
	// 定义子菜单
	private JMenuItem save, update, getS, del;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	public MallView() {
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 23));
		getContentPane().setForeground(Color.RED);
		setBackground(Color.RED);
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MallView.class.getResource("/com/newland/shopping/images/\u8D85\u5E02.png")));
		this.setTitle("\u5341\u91CC\u6625\u98CE\u4E0D\u5982\u4F60");
		save=new JMenuItem("function1");
		update=new JMenuItem("function1");
		getS=new JMenuItem("function1");
		del=new JMenuItem("function1");	
		JMenuBar menubar = new JMenuBar(); // 创建工具栏对象
		JMenu menu = new JMenu("\u652F\u6301\u529F\u80FD"); // 创建菜单栏对象
		menu.setBackground(Color.DARK_GRAY);
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 25));
		menu.setForeground(Color.RED);
		menu.add(save);
		menu.add(update);
		menu.add(getS);
		menu.add(del);
		menubar.add(menu);
		
		// 注册事件监听器
		save.addActionListener(new MallAction());
		update.addActionListener(new MallAction());
		getS.addActionListener(new MallAction());
		del.addActionListener(new MallAction());
		
		this.setJMenuBar(menubar);
		this.setBounds(400, 600,604, 420);
		getContentPane().setLayout(null);
		
		label = new JLabel("\u5B98\u65B9\u652F\u6301\u4FE1\u606F");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("宋体", Font.PLAIN, 26));
		label.setBounds(182, 15, 261, 62);
		getContentPane().add(label);
		
		label_1 = new JLabel("\u652F\u6301\u529F\u80FD\uFF1A");
		label_1.setBounds(31, 238, 105, 21);
		getContentPane().add(label_1);
		
		lblNewLabel = new JLabel("function1");
		lblNewLabel.setBounds(120, 237, 81, 21);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("function2");
		lblNewLabel_1.setBounds(220, 240, 81, 21);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("function3");
		lblNewLabel_2.setBounds(335, 238, 81, 21);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("function4");
		lblNewLabel_3.setBounds(431, 238, 81, 21);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("\u00A9ALIMAMA MUX, powered by alimama THX.\u6CD5\u5F8B\u58F0\u660E");
		lblNewLabel_4.setBounds(91, 260, 501, 36);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(MallView.class.getResource("/com/newland/shopping/images/\u8D85\u5E02 (1).png")));
		lblNewLabel_5.setBounds(171, 76, 246, 151);
		getContentPane().add(lblNewLabel_5);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), menubar, menu, save, update, getS, del}));
	}

	public static void main(String[] args) {
		MallView sv=new MallView();
		sv.setVisible(true);
	}
	
	// 事件跳转
	class MallAction extends AbstractAction{

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
