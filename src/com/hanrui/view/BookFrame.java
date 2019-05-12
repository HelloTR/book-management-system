package com.hanrui.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.hanrui.dialog.DeleteBookDialog;
import com.hanrui.dialog.InsertBookDialog;
import com.hanrui.dialog.UpdateBookDialog;
import com.hanrui.entity.Book;
import com.hanrui.listener.BookListener;
import com.hanrui.service.BookService;
import com.hanrui.service.impl.BookServiceImpl;
import com.hanrui.util.Tools;

/**
 * 
 ͼ����Ϣ����ϵͳ���ݿ� create table book( isbn varchar(30) primary key not null,
 * book_name varchar(50), writer varchar(30), price float(4,2), publisher
 * varchar(30), publication_date varchar(30) );
 * 
 */
public class BookFrame extends JFrame implements BookListener {

	private static final long serialVersionUID = 1L;
	/**
	 * ����Ŀ�
	 */
	private static final int width = 600;
	/**
	 * ����ĸ�
	 */
	private static final int height = 400;
	/**
	 * �����Ա�����ʽ��ʾ
	 */
	private JTable jtTable;
	/**
	 * ��������ģ��
	 */
	private DefaultTableModel dtmTableModel;
	/**
	 * ͼ��������
	 */
	private BookService bookService = new BookServiceImpl();
	/**
	 * Ҫ��ʾ��ͼ���б�
	 */
	private List<Book> bookList = null;
	/**
	 * �Ҽ������˵�
	 */
	private JPopupMenu popupMenu;
	/**
	 * �Ҽ����ͼ��ʱ��ͼ�������
	 */
	private int index = -1;

	public BookFrame() {
		// ���ô������
		setTitle("ͼ����Ϣ����ϵͳ");
		// ����Ĭ�Ϲر�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����С
		setSize(width, height);
		// ���ô����С���ɱ��ı�
		setResizable(false);
		// ���ô�����ʾλ��������
		setLocationRelativeTo(null);
		// ���ô��岼��Ϊ�߽粼��
		setLayout(new BorderLayout());
		// Ϊ�������ò˵���
		setJMenuBar(getBookMenuBar());
		// �ڴ��山����ӹ�����
		add(getToolBar(), BorderLayout.NORTH);
		// �ڴ����в����ͼ���б��ѱ�����ʽ��ʾ
		add(getJSPTable(), BorderLayout.CENTER);
		// һ��ʼȥ���ݿ��ȡ���ݲ���ʾ�ڽ�����
		refresh();
		// ���������˵�
		createPopupMenu();
	}

	/**
	 * ���������˵����������޸ĺ�ɾ�������˵���
	 */
	private void createPopupMenu() {
		popupMenu = new JPopupMenu();
		// �����˵���
		JMenuItem updateItem = new JMenuItem("�޸�");
		JMenuItem deleteItem = new JMenuItem("ɾ��");
		// Ϊ�˵�����ӵ���¼�
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���������õ�Ҫ�޸ĵ�ͼ����Ϣ
				Book book = bookList.get(index);
				// ��ͼ����Ϣ��ʾ��������
				updateBookSurface(book);
			}
		});
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "ȷ��", "ȡ��" };
				int response = JOptionPane.showOptionDialog(null, "��ȷ��Ҫɾ����", "����",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						options, options[0]);
				if (response == 0) {
					Book book = bookList.get(index);
					// �õ�Ҫɾ����ͼ���ISBN��
					String isbn = book.getIsbn();
					if (bookService.deleteBook(isbn)) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						bookList.remove(book);
						showTable();
					} else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
					}
				}
			}
		});
		popupMenu.add(updateItem);
		// ��ӷָ���
		popupMenu.addSeparator();
		popupMenu.add(deleteItem);
	}

	/**
	 * �����˵������ò˵�����¼��ͼ�飬ɾ��ͼ�飬ˢ�£��˳��Ȳ˵���
	 * 
	 * @return �����õĲ˵���
	 */
	private JMenuBar getBookMenuBar() {
		// �˵���
		JMenuBar bookMenuBar = new JMenuBar();
		// �˵�
		JMenu bookMenu = new JMenu("ͼ�����");
		// �˵���
		JMenuItem addBookItem = new JMenuItem("¼��ͼ��");
		JMenuItem deleteBookItem = new JMenuItem("ɾ��ͼ��");
		JMenuItem refreshBookItem = new JMenuItem("ˢ��");
		JMenuItem quitItem = new JMenuItem("�˳�");
		// Ϊ�˵�����ӵ���¼�
		addBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����¼��ͼ��Ի�����ʾ
				new InsertBookDialog(null, BookFrame.this).setVisible(true);
			}
		});

		deleteBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ɾ��ͼ��Ի�����ʾ
				new DeleteBookDialog(null, BookFrame.this).setVisible(true);
			}
		});

		refreshBookItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ˢ��
				refresh();
			}
		});

		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �˳�����
				System.exit(0);
			}
		});
		// ��Ӳ˵���
		bookMenu.add(addBookItem);
		bookMenu.add(deleteBookItem);
		bookMenu.add(refreshBookItem);
		bookMenu.add(quitItem);
		// ��Ӳ˵�
		bookMenuBar.add(bookMenu);
		return bookMenuBar;
	}

	/**
	 * 
	 * @return ���ع��������ù�������ˢ��ͼ���Լ������ȹ���
	 */
	private JToolBar getToolBar() {
		// ����������
		JToolBar toolBar = new JToolBar();
		// ��ˢ�°�ť��ӵ���������
		toolBar.add(getRefreshBtn());
		// ���������ܵĿؼ���ӵ�������
		toolBar.add(getSearchWidget());
		// ���ù����������϶�
		toolBar.setFloatable(false);
		return toolBar;
	}

	/**
	 * ������������Ŀؼ�
	 * 
	 * @return
	 */
	private JPanel getSearchWidget() {
		JPanel searchPanel = new JPanel();
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("��ISBN�ż���");
		comboBox.addItem("����������");
		comboBox.addItem("�����߼���");
		final JTextField textSearch = new JTextField(15);
		JButton btnSearch = new JButton("����");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int searchIndex = comboBox.getSelectedIndex();
				String searchStr = textSearch.getText().trim();
				if (Tools.isEmpty(searchStr)) {
					JOptionPane.showMessageDialog(null, "�������������");
					return;
				}
				// ��ISBN�ż���
				if (searchIndex == 0) {
					Book book = bookService.findBookByIsbn(searchStr);
					bookList.clear();
					showTable();
					if (book != null) {
						bookList.add(book);
					} else {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ���ISBN�Ų�����");
					}
					showTable();
				} else if (searchIndex == 1) {// ����������
					bookList = bookService.findBookByBookName(searchStr);
					showTable();
					if (bookList == null || bookList.size() <= 0) {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ������ڸ�������ͼ��");
					}
				} else {// �����߼���
					bookList = bookService.findBookByWriter(searchStr);
					showTable();
					if (bookList == null || bookList.size() <= 0) {
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�������û��ͼ��");
					}
				}
			}
		});
		searchPanel.add(comboBox);
		searchPanel.add(textSearch);
		searchPanel.add(btnSearch);
		searchPanel.setBounds(30, 30, 150, 30);
		return searchPanel;
	}

	/**
	 * 
	 * @return ����һ����ͼ���ˢ�°�ť
	 */
	private JButton getRefreshBtn() {
		ImageIcon icon = new ImageIcon("images/refresh.png");
		icon.setImage(icon.getImage().getScaledInstance(25, 25,
				Image.SCALE_DEFAULT));
		// ����ˢ�°�ť
		JButton btnRefresh = new JButton(icon);
		btnRefresh.setToolTipText("ˢ��");
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		return btnRefresh;
	}

	private JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if (jtTable == null) {
			// �����������ģ�ͣ�������Ӹ��б���
			dtmTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				// �ñ����б���ܱ��༭
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			dtmTableModel.addColumn("ISBN");
			dtmTableModel.addColumn("����");
			dtmTableModel.addColumn("����");
			dtmTableModel.addColumn("�۸�");
			dtmTableModel.addColumn("������");
			dtmTableModel.addColumn("��������");
			jtTable = new JTable(dtmTableModel);
			// Ϊ����б������������¼�
			jtTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// ��������¼�
					if (e.getButton() == MouseEvent.BUTTON1) {
						// �ж��Ƿ���˫��
						if (e.getClickCount() == 2) {
							int selectedRow = jtTable.getSelectedRow();
							Book book = bookList.get(selectedRow);
							updateBookSurface(book);
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						// �õ���ǰ����Ҽ����������
						index = jtTable.rowAtPoint(e.getPoint());
						// ���ø�������ѡ��
						jtTable.setRowSelectionInterval(index, index);
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			jspTable = new JScrollPane(jtTable);
			//ֻ��ѡ��һ��
			jtTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return jspTable;
	}

	private void updateBookSurface(Book book) {
		UpdateBookDialog showBookInfoDialog = new UpdateBookDialog(null,
				BookFrame.this);
		showBookInfoDialog.showBookInfo(book);
		showBookInfoDialog.setVisible(true);
	}

	private void showTable() {
		// ��ձ�������
		while (dtmTableModel.getRowCount() > 0) {
			dtmTableModel.removeRow(dtmTableModel.getRowCount() - 1);
		}
		if (bookList != null && bookList.size() > 0) {
			for (Book book : bookList) {
				Object[] data = new Object[] { book.getIsbn(),
						book.getBookName(), book.getWriter(), book.getPrice(),
						book.getPublisher(), book.getPublication_date() };
				dtmTableModel.addRow(data);
			}
		}
	}

	private void refresh() {
		bookList = bookService.findAllBooks();
		showTable();
	}

	@Override
	public void update() {
		refresh();
	}
}
