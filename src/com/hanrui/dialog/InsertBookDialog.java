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
		setTitle("图书录入界面");
		//把确定按钮改为录入按钮
		btnOK.setText("录入");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取填写的图书信息
				String isbn = textIsbn.getText().trim();
				String bookName = textBookName.getText().trim();
				String writer = textWriter.getText().trim();
				String strPrice = textPrice.getText().trim();
				String publisher = textPublisher.getText().trim();
				String publicationDate = textPublicationDate.getText().trim();
				//判断填写的信息中是否有遗漏
				if (Tools.isEmpty(isbn) || Tools.isEmpty(bookName)
						|| Tools.isEmpty(writer) || Tools.isEmpty(strPrice)
						|| Tools.isEmpty(publisher) || Tools.isEmpty(publicationDate)) {
					JOptionPane.showMessageDialog(null, "请填写完整");
					return;
				}
				float price = 0;
				try {
					//将字符串类型的价格转换为float类型
					price = Float.parseFloat(strPrice);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的价格");
					return;
				}
				Book book = new Book(isbn, bookName, writer, price, publisher,
						publicationDate);
				execute(book);
			}
		});
		//设置对话框为模态对话框
		setModal(true);
	}

	protected void execute(Book book){
		//如果添加图书成功，则显示录入成功的信息并清空刚才所填写的图书信息
		if(bookService.addBook(book)){
			JOptionPane.showMessageDialog(null, "录入图书成功");
			textIsbn.setText("");
			textBookName.setText("");
			textWriter.setText("");
			textPrice.setText("");
			textPublisher.setText("");
			listener.update();
		}else{
			JOptionPane.showMessageDialog(null, "录入图书失败");
		}
	}
}
