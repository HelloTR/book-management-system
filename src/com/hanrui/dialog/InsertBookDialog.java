package com.hanrui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.hanrui.entity.Book;
import com.hanrui.listener.BookListener;
import com.hanrui.util.Tools;

public class InsertBookDialog extends BookDialog {

	private static final long serialVersionUID = 1L;
	protected BookListener listener=null;
	
	public InsertBookDialog(JFrame owner,final BookListener listener) {
		super(owner);
		this.listener=listener;
		setTitle("ͼ��¼�����");
		//��ȷ����ť��Ϊ¼�밴ť
		btnOK.setText("¼��");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ȡ��д��ͼ����Ϣ
				String isbn = textIsbn.getText().trim();
				String bookName = textBookName.getText().trim();
				String writer = textWriter.getText().trim();
				String strPrice = textPrice.getText().trim();
				String publisher = textPublisher.getText().trim();
				String publicationDate = textPublicationDate.getText().trim();
				//�ж���д����Ϣ���Ƿ�����©
				if (Tools.isEmpty(isbn) || Tools.isEmpty(bookName)
						|| Tools.isEmpty(writer) || Tools.isEmpty(strPrice)
						|| Tools.isEmpty(publisher) || Tools.isEmpty(publicationDate)) {
					JOptionPane.showMessageDialog(null, "����д����");
					return;
				}
				float price = 0;
				try {
					//���ַ������͵ļ۸�ת��Ϊfloat����
					price = Float.parseFloat(strPrice);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ�ļ۸�");
					return;
				}
				Book book = new Book(isbn, bookName, writer, price, publisher,
						publicationDate);
				execute(book);
			}
		});
		//���öԻ���Ϊģ̬�Ի���
		setModal(true);
	}

	protected void execute(Book book){
		//������ͼ��ɹ�������ʾ¼��ɹ�����Ϣ����ող�����д��ͼ����Ϣ
		if(bookService.addBook(book)){
			JOptionPane.showMessageDialog(null, "¼��ͼ��ɹ�");
			textIsbn.setText("");
			textBookName.setText("");
			textWriter.setText("");
			textPrice.setText("");
			textPublisher.setText("");
			listener.update();
		}else{
			JOptionPane.showMessageDialog(null, "¼��ͼ��ʧ��");
		}
	}
}
