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
	//�ı���ǩ
	private JLabel labIsbn = null;
	private JLabel labBookName = null;
	private JLabel labWriter = null;
	private JLabel labPrice = null;
	private JLabel labPublisher = null;
	private JLabel labPublicationDate = null;
	//�ı�������Լ�ʱ��ؼ�������������Ҫ�õ������Զ���Ϊprotected
	protected JTextField textIsbn = null;
	protected JTextField textBookName = null;
	protected JTextField textWriter = null;
	protected JTextField textPrice = null;
	protected JTextField textPublisher = null;
	protected DatePanel textPublicationDate = null;
	//ȷ����ȡ����ť
	protected JButton btnOK = null;
	protected JButton btnCancel = null;
	//ͼ�������
	protected BookService bookService = new BookServiceImpl();

	public BookDialog(JFrame owner) {
		super(owner);
		initialize();
	}

	/**
	 * �ؼ���ʼ��
	 */
	private void initialize() {
		//���öԻ���Ĵ�С
		setSize(300, 300);
		//���öԻ�����ʾ��λ��
		setLocationRelativeTo(null);
		//���öԻ����С���ɱ��ı�
		setResizable(false);
		//��ȡ�Ի���Ŀؼ�����
		Container cont = getContentPane();
		//��ʼ���ı���ǩ
		labIsbn = new JLabel("ISBN :");
		labBookName = new JLabel("���� :");
		labWriter = new JLabel("���� :");
		labPrice = new JLabel("�۸� :");
		labPublisher = new JLabel("������ :");
		labPublicationDate = new JLabel("�������� :");
		//��ʼ��������Լ�ʱ��ؼ�
		textIsbn = new JTextField();
		textBookName = new JTextField();
		textWriter = new JTextField();
		textPrice = new JTextField();
		textPublisher = new JTextField();
		textPublicationDate = new DatePanel();
		//��ʼ����ť
		btnOK = new JButton("ȷ��");
		btnCancel = new JButton("ȡ��");
		//���ÿؼ���ʾ��λ�ã�ǰ���Ǹ�������x�����y���꣬�����ǿؼ��Ŀ�Ⱥ͸߶�
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
		//Ϊȡ����ť��ӵ���¼�������ȷ����ť���������в�ͬ�Ĺ��ܣ���������Ͳ�������
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رնԻ���
				dispose();
			}
		});
		//������������
		cont.setLayout(null);
		//��ӿؼ�
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
