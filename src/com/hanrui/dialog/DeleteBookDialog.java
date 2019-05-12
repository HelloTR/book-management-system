package com.hanrui.dialog;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hanrui.listener.BookListener;
import com.hanrui.service.BookService;
import com.hanrui.service.impl.BookServiceImpl;
import com.hanrui.util.Tools;

public class DeleteBookDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	//文本标签
	private JLabel labIsbn=null;
	//输入框
	private JTextField textIsbn=null;
	//删除和取消按钮
	private JButton btnDelete=null;
	private JButton btnCancel=null;
	//图书管理类
	private BookService bookService=new BookServiceImpl();
	//图书接口
	private BookListener listener=null;

	public DeleteBookDialog(JFrame owner,BookListener listener) {
		super(owner);
		this.listener=listener;
		initialize();
		//设置对话框为模态对话框
		setModal(true);
	}

	/**
	 * 初始化对话框
	 */
	private void initialize() {
		setTitle("删除图书");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		Container cont = getContentPane();
		
		labIsbn=new JLabel("请输入图书的ISBN号");
		textIsbn=new JTextField();
		btnDelete=new JButton("删除");
		btnCancel=new JButton("取消");
		
		labIsbn.setBounds(20, 30, 150, 25);
		textIsbn.setBounds(150, 30, 130, 25);
		btnDelete.setBounds(80, 80, 60, 25);
		btnCancel.setBounds(160, 80, 60, 25);
		
		cont.add(labIsbn);
		cont.add(textIsbn);
		cont.add(btnDelete);
		cont.add(btnCancel);
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn=textIsbn.getText().trim();
				if(Tools.isEmpty(isbn)){
					JOptionPane.showMessageDialog(null, "请输入要删除的图书的ISBN号");
					return;
				}
				if(bookService.deleteBook(isbn)){
					JOptionPane.showMessageDialog(null, "删除成功");
					textIsbn.setText("");
					listener.update();
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
