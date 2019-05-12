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
 图书信息管理系统数据库 create table book( isbn varchar(30) primary key not null,
 * book_name varchar(50), writer varchar(30), price float(4,2), publisher
 * varchar(30), publication_date varchar(30) );
 * 
 */
public class BookFrame extends JFrame implements BookListener {

	private static final long serialVersionUID = 1L;
	/**
	 * 窗体的宽
	 */
	private static final int width = 600;
	/**
	 * 窗体的高
	 */
	private static final int height = 400;
	/**
	 * 数据以表格的形式显示
	 */
	private JTable jtTable;
	/**
	 * 表格的数据模型
	 */
	private DefaultTableModel dtmTableModel;
	/**
	 * 图书管理的类
	 */
	private BookService bookService = new BookServiceImpl();
	/**
	 * 要显示的图书列表
	 */
	private List<Book> bookList = null;
	/**
	 * 右键弹出菜单
	 */
	private JPopupMenu popupMenu;
	/**
	 * 右键点击图书时该图书的索引
	 */
	private int index = -1;

	public BookFrame() {
		// 设置窗体标题
		setTitle("图书信息管理系统");
		// 设置默认关闭
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体大小
		setSize(width, height);
		// 设置窗体大小不可被改变
		setResizable(false);
		// 设置窗体显示位置在中心
		setLocationRelativeTo(null);
		// 设置窗体布局为边界布局
		setLayout(new BorderLayout());
		// 为窗体设置菜单栏
		setJMenuBar(getBookMenuBar());
		// 在窗体北部添加工具栏
		add(getToolBar(), BorderLayout.NORTH);
		// 在窗体中部添加图书列表，已表格的形式显示
		add(getJSPTable(), BorderLayout.CENTER);
		// 一开始去数据库获取数据并显示在界面上
		refresh();
		// 创建弹出菜单
		createPopupMenu();
	}

	/**
	 * 创建弹出菜单，里面有修改和删除两个菜单项
	 */
	private void createPopupMenu() {
		popupMenu = new JPopupMenu();
		// 创建菜单项
		JMenuItem updateItem = new JMenuItem("修改");
		JMenuItem deleteItem = new JMenuItem("删除");
		// 为菜单项添加点击事件
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 根据索引得到要修改的图书信息
				Book book = bookList.get(index);
				// 将图书信息显示到界面上
				updateBookSurface(book);
			}
		});
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "确定", "取消" };
				int response = JOptionPane.showOptionDialog(null, "您确定要删除吗？", "警告",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						options, options[0]);
				if (response == 0) {
					Book book = bookList.get(index);
					// 得到要删除的图书的ISBN号
					String isbn = book.getIsbn();
					if (bookService.deleteBook(isbn)) {
						JOptionPane.showMessageDialog(null, "删除成功");
						bookList.remove(book);
						showTable();
					} else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});
		popupMenu.add(updateItem);
		// 添加分隔符
		popupMenu.addSeparator();
		popupMenu.add(deleteItem);
	}

	/**
	 * 创建菜单栏，该菜单栏有录入图书，删除图书，刷新，退出等菜单项
	 * 
	 * @return 创建好的菜单栏
	 */
	private JMenuBar getBookMenuBar() {
		// 菜单栏
		JMenuBar bookMenuBar = new JMenuBar();
		// 菜单
		JMenu bookMenu = new JMenu("图书管理");
		// 菜单项
		JMenuItem addBookItem = new JMenuItem("录入图书");
		JMenuItem deleteBookItem = new JMenuItem("删除图书");
		JMenuItem refreshBookItem = new JMenuItem("刷新");
		JMenuItem quitItem = new JMenuItem("退出");
		// 为菜单项添加点击事件
		addBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建录入图书对话框并显示
				new InsertBookDialog(null, BookFrame.this).setVisible(true);
			}
		});

		deleteBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建删除图书对话框并显示
				new DeleteBookDialog(null, BookFrame.this).setVisible(true);
			}
		});

		refreshBookItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 刷新
				refresh();
			}
		});

		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 退出程序
				System.exit(0);
			}
		});
		// 添加菜单项
		bookMenu.add(addBookItem);
		bookMenu.add(deleteBookItem);
		bookMenu.add(refreshBookItem);
		bookMenu.add(quitItem);
		// 添加菜单
		bookMenuBar.add(bookMenu);
		return bookMenuBar;
	}

	/**
	 * 
	 * @return 返回工具栏，该工具栏有刷新图标以及检索等功能
	 */
	private JToolBar getToolBar() {
		// 创建工具栏
		JToolBar toolBar = new JToolBar();
		// 将刷新按钮添加到工具栏中
		toolBar.add(getRefreshBtn());
		// 将检索功能的控件添加到工具栏
		toolBar.add(getSearchWidget());
		// 设置工具栏不可拖动
		toolBar.setFloatable(false);
		return toolBar;
	}

	/**
	 * 检索功能所需的控件
	 * 
	 * @return
	 */
	private JPanel getSearchWidget() {
		JPanel searchPanel = new JPanel();
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("按ISBN号检索");
		comboBox.addItem("按书名检索");
		comboBox.addItem("按作者检索");
		final JTextField textSearch = new JTextField(15);
		JButton btnSearch = new JButton("检索");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int searchIndex = comboBox.getSelectedIndex();
				String searchStr = textSearch.getText().trim();
				if (Tools.isEmpty(searchStr)) {
					JOptionPane.showMessageDialog(null, "请输入检索内容");
					return;
				}
				// 按ISBN号检索
				if (searchIndex == 0) {
					Book book = bookService.findBookByIsbn(searchStr);
					bookList.clear();
					showTable();
					if (book != null) {
						bookList.add(book);
					} else {
						JOptionPane.showMessageDialog(null, "检索失败，该ISBN号不存在");
					}
					showTable();
				} else if (searchIndex == 1) {// 按书名检索
					bookList = bookService.findBookByBookName(searchStr);
					showTable();
					if (bookList == null || bookList.size() <= 0) {
						JOptionPane.showMessageDialog(null, "检索失败，不存在该书名的图书");
					}
				} else {// 按作者检索
					bookList = bookService.findBookByWriter(searchStr);
					showTable();
					if (bookList == null || bookList.size() <= 0) {
						JOptionPane.showMessageDialog(null, "检索失败，该作者没有图书");
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
	 * @return 返回一个带图标的刷新按钮
	 */
	private JButton getRefreshBtn() {
		ImageIcon icon = new ImageIcon("images/refresh.png");
		icon.setImage(icon.getImage().getScaledInstance(25, 25,
				Image.SCALE_DEFAULT));
		// 创建刷新按钮
		JButton btnRefresh = new JButton(icon);
		btnRefresh.setToolTipText("刷新");
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
			// 创建表格数据模型，并且添加各列标题
			dtmTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				// 让表格的列表项不能被编辑
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			dtmTableModel.addColumn("ISBN");
			dtmTableModel.addColumn("书名");
			dtmTableModel.addColumn("作者");
			dtmTableModel.addColumn("价格");
			dtmTableModel.addColumn("出版社");
			dtmTableModel.addColumn("出版日期");
			jtTable = new JTable(dtmTableModel);
			// 为表格列表项添加鼠标点击事件
			jtTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 左键单击事件
					if (e.getButton() == MouseEvent.BUTTON1) {
						// 判断是否是双击
						if (e.getClickCount() == 2) {
							int selectedRow = jtTable.getSelectedRow();
							Book book = bookList.get(selectedRow);
							updateBookSurface(book);
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						// 得到当前鼠标右键点击的行数
						index = jtTable.rowAtPoint(e.getPoint());
						// 设置该行数被选中
						jtTable.setRowSelectionInterval(index, index);
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			jspTable = new JScrollPane(jtTable);
			//只能选中一行
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
		// 清空表格的内容
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
