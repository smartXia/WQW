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

public class MallViewUpdate extends JFrame {
	// 定义子菜单
	private JMenuItem save, update, getS, del;

	// 定义输入文本框
	private JTextField sid, sname, sprice, snumber, slocation, sdiscount;

	private JRadioButton sman, swoman; // 定义单选按钮（shifou

	private JButton ok, cance; // 定义按钮

	private JButton updateButton; // 录入修改

	public MallViewUpdate() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						MallViewAdd.class
								.getResource("/com/newland/shopping/images/\u8D85\u5E02.png")));
		this.setTitle("超市管理系统-修改信息");
		initMenu(); // 初始化菜单栏
		// 初始化文本框
		sid = new JTextField(20);
		sname = new JTextField(20);
		slocation = new JTextField(20);
		sdiscount = new JTextField(20);
		sprice = new JTextField(20);
		snumber = new JTextField(20);
		// 初始化按钮
		sman = new JRadioButton("是", true); // 默认选中
		swoman = new JRadioButton("否", false);
		ButtonGroup bg = new ButtonGroup(); // 定义按钮组
		bg.add(sman);
		bg.add(swoman);

		// 初始化按钮
		ok = new JButton("录入修改");
		cance = new JButton("重置");
		updateButton = new JButton("开始修改");

		this.getContentPane().setLayout(new GridLayout(7, 1)); // 设置7行1列网格布局管理器
		JPanel p1 = new JPanel();
		p1.add(new JLabel("编号："));
		p1.add(sid);
		p1.add(updateButton);
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
		// 注册监听器
		updateButton.addActionListener(new ActionListener() {

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
					sprice.setText("");
					snumber.setText("");
					sman.setSelected(true);
					return;
				}
				System.out.println(mall.toString());
				sname.setText(mall.getName());
				slocation.setText(mall.getLocaltion());
				sprice.setText(mall.getPrice());
				snumber.setText(mall.getnumber());

				if (mall.getDiscount().equals("是")) {
					sman.setSelected(true);
				} else {
					swoman.setSelected(true);
				}

				sid.setEditable(false);

			}

		});

		// 注册监听器
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String sex = "";
				String id = sid.getText().trim();
				String name = sname.getText().trim();
				String location = slocation.getText().trim();
				String price = sprice.getText().trim();
				String number = snumber.getText().trim();
				if (sman.isSelected()) { // 如果按钮是被选中
					sex = "是";
				} else {
					sex = "否";
				}

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "编号不能为空");
					return;
				}
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "商品名不能为空");
					return;
				}
				if (location.equals("")) {
					JOptionPane.showMessageDialog(null, " 产地不能为空");
					return;
				}
				if (price.equals("")) {
					JOptionPane.showMessageDialog(null, "价格不能为空");
					return;
				}
				if (number.equals("")) {
					JOptionPane.showMessageDialog(null, "数量不能为空");
					return;
				}

				Mall mall = new Mall(id, name, sex, location, price, number);
				BaseDao sd = new BaseDao();
				int i = sd.update(mall);
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "修改成功成功");
				} else {
					JOptionPane.showMessageDialog(null, "修改失败失败");
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
				sid.setEditable(true);
			}

		});

		this.setBounds(250, 150, 570, 325); // 设置窗体位置和大小，可根据自己电脑屏幕情况来调整
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗体事件
		// this.setVisible(true);
	}

	// 初始化菜单
	public void initMenu() {
		save = new JMenuItem("录入超市基本信息");
		update = new JMenuItem("修改超市基本信息");
		getS = new JMenuItem("查询超市基本信息");
		del = new JMenuItem("删除超市基本信息");

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
			if (e.getSource() == save) {
				MallViewAdd sva = new MallViewAdd();
				sva.setVisible(true);
			} else if (e.getSource() == update) {
				MallViewUpdate svu = new MallViewUpdate();
				svu.setVisible(true);
			} else if (e.getSource() == getS) {
				MallViewGet svg = new MallViewGet();
				svg.setVisible(true);
			} else if (e.getSource() == del) {
				MallViewDel svd = new MallViewDel();
				svd.setVisible(true);
			}
		}

	}
}
