package com.hanrui.dialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.hanrui.entity.Book;
import com.hanrui.listener.BookListener;
/**
 * ��Ϊ�󲿷ִ�����ͬ������ֱ�Ӽ̳�¼��ͼ��ĶԻ���
 * @author Administrator
 *
 */
public class UpdateBookDialog extends InsertBookDialog {

	private static final long serialVersionUID = 1L;

	public UpdateBookDialog(JFrame owner,final BookListener listener) {
		super(owner,listener);
		//����ISBN����������޸�
		textIsbn.setEditable(false);
		btnOK.setText("�޸�");
	}
	
	/**
	 * ��Ϊֻ�иò��ֲ�ͬ�����Խ�����д
	 */
	@Override
	protected void execute(Book book) {
		if(bookService.updateBook(book)){
			JOptionPane.showMessageDialog(null, "�޸�ͼ��ɹ�");
			//����޸ĳɹ��������ͼ��
			listener.update();
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, "�޸�ͼ��ʧ��");
		}
	}
	
	/**
	 * ��ʾͼ����Ϣ
	 * @param book	Ҫ��ʾ��ͼ�����
	 */
	public void showBookInfo(Book book){
		textIsbn.setText(book.getIsbn());
		textBookName.setText(book.getBookName());
		textWriter.setText(book.getWriter());
		textPrice.setText(book.getPrice()+"");
		textPublisher.setText(book.getPublisher());
		textPublicationDate.setText(book.getPublication_date());
	}
	
}
