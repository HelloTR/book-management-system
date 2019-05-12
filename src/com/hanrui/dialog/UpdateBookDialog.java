package com.hanrui.dialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.hanrui.entity.Book;
import com.hanrui.listener.BookListener;
/**
 * 因为大部分代码相同，所以直接继承录入图书的对话框
 * @author Administrator
 *
 */
public class UpdateBookDialog extends InsertBookDialog {

	private static final long serialVersionUID = 1L;

	public UpdateBookDialog(JFrame owner,final BookListener listener) {
		super(owner,listener);
		//设置ISBN的输入框不能修改
		textIsbn.setEditable(false);
		btnOK.setText("修改");
	}
	
	/**
	 * 因为只有该部分不同，所以进行重写
	 */
	@Override
	protected void execute(Book book) {
		if(bookService.updateBook(book)){
			JOptionPane.showMessageDialog(null, "修改图书成功");
			//如果修改成功，则更新图书
			listener.update();
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, "修改图书失败");
		}
	}
	
	/**
	 * 显示图书信息
	 * @param book	要显示的图书对象
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
