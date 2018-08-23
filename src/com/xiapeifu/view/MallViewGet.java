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
 * 录入界面
 * 
 * @author Administrator
 * 
 */

public class MallViewGet extends JFrame {
	// 定义子菜单
	private JMenuItem save, update, getS, del;

	// 定义输入文本框
	private JTextField sid, sname, sprice, snumber, slocation;

	private JRadioButton sman, swoman; // 定义单选按钮（男，女）

	private JButton sele; // 定义查询按钮

	public MallViewGet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MallViewGet.class.getResource("/com/xiapeifu/images/mall.png")));
		this.setTitle("超市管理系统-查询信息");
		initMenu(); // 初始化菜单栏
		// 初始化文本框
		sid = new JTextField(20);
		sname = new JTextField(20);
		sname.setEditable(false);// 不可编辑
		slocation = new JTextField(20);
		slocation.setEditable(false);
		sprice = new JTextField(20);
		sprice.setEditable(false);
		snumber = new JTextField(20);
		snumber.setEditable(false);
		// 初始化单选按钮
		sman = new JRadioButton("是", true); // 默认选中
		swoman = new JRadioButton("否", false);
		ButtonGroup bg = new ButtonGroup(); // 定义按钮组
		bg.add(sman);
		bg.add(swoman);
		sman.setEnabled(false); // 不可编辑
		swoman.setEnabled(false);

		sele = new JButton("查询"); // 初始化查询按钮

		this.getContentPane().setLayout(new GridLayout(7, 1)); // 设置6行1列网格布局管理器
		JPanel p1 = new JPanel();
		p1.add(new JLabel("输入要查询的编号："));
		p1.add(sid);
		p1.add(sele);
		this.getContentPane().add(p1);

		JPanel p2 = new JPanel();
		p2.add(new JLabel("名称："));
		p2.add(sname);
		this.getContentPane().add(p2);

		JPanel p3 = new JPanel();
		p3.add(new JLabel("是否折扣："));
		p3.add(sman);
		p3.add(swoman);
		this.getContentPane().add(p3);

		JPanel p4 = new JPanel();
		p4.add(new JLabel("产地："));
		p4.add(slocation);
		this.getContentPane().add(p4);

		JPanel p5 = new JPanel();
		p5.add(new JLabel("价格："));
		p5.add(sprice);
		this.getContentPane().add(p5);

		JPanel p6 = new JPanel();
		p6.add(new JLabel("数量："));
		p6.add(snumber);
		this.getContentPane().add(p6);

		JPanel p7 = new JPanel();
		this.getContentPane().add(p7);

		// 注册监听器
		sele.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				String id = sid.getText().trim();
			
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入编号");
					return;
				}
				BaseDao pd = new BaseDao();
				Mall mall = pd.getMall(id);
				if (mall == null) {
					JOptionPane.showMessageDialog(null, "不存在此编号");
					sname.setText("");					
					slocation.setText("");
					snumber.setText("");
					sprice.setText("");
					sman.setSelected(true);
					return;
				}
				
				sname.setText(mall.getName());
				
				sprice.setText(mall.getPrice());
				slocation.setText(mall.getLocaltion());
				snumber.setText(mall.getnumber());

				if (mall.getDiscount().equals("是")) {
					sman.setSelected(true);
				} else {
					swoman.setSelected(true);
				}

			}

		});

		this.setBounds(300, 200, 570, 325); // 设置窗体位置和大小，可根据自己电脑屏幕情况来调整
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 关闭窗体事件
	}

	// 初始化菜单
	public void initMenu() {
		save=new JMenuItem("录入超市基本信息");
		update=new JMenuItem("修改超市基本信息");
		getS=new JMenuItem("查询超市基本信息");
		del=new JMenuItem("删除超市基本信息");

		JMenuBar menubar = new JMenuBar(); // 创建工具栏对象
		JMenu menu = new JMenu("菜单选项"); // 创建菜单栏对象
		menu.add(save);
		menu.add(update);
		menu.add(getS);
		menu.add(del);
		menubar.add(menu);

		// 注册事件监听器
		save.addActionListener(new StudentAction());
		update.addActionListener(new StudentAction());
		getS.addActionListener(new StudentAction());
		del.addActionListener(new StudentAction());

		this.setJMenuBar(menubar);
	}

	// 事件
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
