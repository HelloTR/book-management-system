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
	//�ı���ǩ
	private JLabel labIsbn=null;
	//�����
	private JTextField textIsbn=null;
	//ɾ����ȡ����ť
	private JButton btnDelete=null;
	private JButton btnCancel=null;
	//ͼ�������
	private BookService bookService=new BookServiceImpl();
	//ͼ��ӿ�
	private BookListener listener=null;

	public DeleteBookDialog(JFrame owner,BookListener listener) {
		super(owner);
		this.listener=listener;
		initialize();
		//���öԻ���Ϊģ̬�Ի���
		setModal(true);
	}

	/**
	 * ��ʼ���Ի���
	 */
	private void initialize() {
		setTitle("ɾ��ͼ��");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		Container cont = getContentPane();
		
		labIsbn=new JLabel("������ͼ���ISBN��");
		textIsbn=new JTextField();
		btnDelete=new JButton("ɾ��");
		btnCancel=new JButton("ȡ��");
		
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
					JOptionPane.showMessageDialog(null, "������Ҫɾ����ͼ���ISBN��");
					return;
				}
				if(bookService.deleteBook(isbn)){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					textIsbn.setText("");
					listener.update();
				}else{
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
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
