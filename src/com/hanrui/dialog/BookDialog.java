package com.hanrui.dialog;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.hanrui.service.BookService;
import com.hanrui.service.impl.BookServiceImpl;
import com.hanrui.view.DatePanel;

public class BookDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	//文本标签
	private JLabel labIsbn = null;
	private JLabel labBookName = null;
	private JLabel labWriter = null;
	private JLabel labPrice = null;
	private JLabel labPublisher = null;
	private JLabel labPublicationDate = null;
	//文本输入框以及时间控件，由于子类需要用到，所以定义为protected
	protected JTextField textIsbn = null;
	protected JTextField textBookName = null;
	protected JTextField textWriter = null;
	protected JTextField textPrice = null;
	protected JTextField textPublisher = null;
	protected DatePanel textPublicationDate = null;
	//确定和取消按钮
	protected JButton btnOK = null;
	protected JButton btnCancel = null;
	//图书管理类
	protected BookService bookService = new BookServiceImpl();

	public BookDialog(JFrame owner) {
		super(owner);
		initialize();
	}

	/**
	 * 控件初始化
	 */
	private void initialize() {
		//设置对话框的大小
		setSize(300, 300);
		//设置对话框显示的位置
		setLocationRelativeTo(null);
		//设置对话框大小不可被改变
		setResizable(false);
		//获取对话框的控件容器
		Container cont = getContentPane();
		//初始化文本标签
		labIsbn = new JLabel("ISBN :");
		labBookName = new JLabel("书名 :");
		labWriter = new JLabel("作者 :");
		labPrice = new JLabel("价格 :");
		labPublisher = new JLabel("出版社 :");
		labPublicationDate = new JLabel("出版日期 :");
		//初始化输入框以及时间控件
		textIsbn = new JTextField();
		textBookName = new JTextField();
		textWriter = new JTextField();
		textPrice = new JTextField();
		textPublisher = new JTextField();
		textPublicationDate = new DatePanel();
		//初始化按钮
		btnOK = new JButton("确定");
		btnCancel = new JButton("取消");
		//设置控件显示的位置，前面是父容器的x坐标和y坐标，后面是控件的宽度和高度
		labIsbn.setBounds(50, 20, 50, 25);
		labBookName.setBounds(50, 50, 50, 25);
		labWriter.setBounds(50, 80, 50, 25);
		labPrice.setBounds(50, 110, 50, 25);
		labPublisher.setBounds(50, 140, 50, 25);
		labPublicationDate.setBounds(50, 170, 80, 25);
		
		textIsbn.setBounds(110, 20, 150, 25);
		textBookName.setBounds(110, 50, 150, 25);
		textWriter.setBounds(110, 80, 150, 25);
		textPrice.setBounds(110, 110, 150, 25);
		textPublisher.setBounds(110, 140, 150, 25);
		textPublicationDate.setBounds(110, 170, 150, 25);
		
		btnOK.setBounds(80, 220, 60, 24);
		btnCancel.setBounds(160, 220, 60, 24);
		//为取消按钮添加点击事件，由于确定按钮在子类中有不同的功能，所以这里就不做处理
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭对话框
				dispose();
			}
		});
		//设置容器布局
		cont.setLayout(null);
		//添加控件
		cont.add(labIsbn);
		cont.add(labBookName);
		cont.add(labWriter);
		cont.add(labPrice);
		cont.add(labPublisher);
		cont.add(labPublicationDate);
		
		cont.add(textIsbn);
		cont.add(textBookName);
		cont.add(textWriter);
		cont.add(textPrice);
		cont.add(textPublisher);
		cont.add(textPublicationDate);
		
		cont.add(btnOK);
		cont.add(btnCancel);
	}

}
