package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CrudBooks;
import controller.Connect;
import model.Book;

public class GDforms extends JFrame {

	private JPanel contentPane;
	final String header[] = { "Mã sách", "Tên sách", "Tên tác giả", "Nhà xuất bản", "Số lượng" };
	final DefaultTableModel tb = new DefaultTableModel(header, 0);

	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_10;

//	Step:7
	private Book bookModel;
	private CrudBooks controller;

	public void loadBang() {
		controller.getData(tb);
		table.setModel(tb);
	}

	MouseAdapter mouseAction;

	public GDforms() {
		bookModel = new Book();
		this.init();
	}

	// Step: 6
	public boolean checkValue() {
		if (!textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")
				&& !textField_4.getText().equals("") && !textField_10.getText().equals("")) {
			return true;
		}
		return false;
	}

	// Step: 8
	public void setModelBook() {
		bookModel.setId(textField_1.getText());
		bookModel.setNameBook(textField_2.getText());
		bookModel.setNameAuthor(textField_4.getText());
		bookModel.setNxb(textField_3.getText());
		bookModel.setQuantity(Integer.parseInt(textField_10.getText()));
	}

	public void init() {
		setTitle("QUẢN LÝ THƯ VIỆN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GDforms.class.getResource("/image/H.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 465);
		ActionListener crudAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strComman = e.getActionCommand();
				if (strComman.equals("AddBook")) {
					controller = new CrudBooks();
					if (checkValue()) {
						setModelBook();
						if (!controller.addBooks(bookModel)) {
							JOptionPane.showMessageDialog(null, "Sách đã tồn tại!!!", "Failed",
									JOptionPane.ERROR_MESSAGE);
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_10.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Thêm thành công!!!", "Susscess",
									JOptionPane.OK_OPTION);
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_10.setText("");
						}
						loadBang();
					} else {
						int pressOK = JOptionPane.showConfirmDialog(null, "Chưa nhập thông tin!!!", "Empty",
								JOptionPane.OK_OPTION);
						if (pressOK == 0 || pressOK != 0) {
							loadBang();
						}
					}
				}
			}
		};

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 871, 418);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Sách", new ImageIcon(GDforms.class.getResource("/image/D.png")), panel, null);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setBounds(282, 10, 340, 31);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nhập Tên sách (Tên tác giả, Nhà xuất bản)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(306, 47, 279, 23);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 846, 2);
		panel.add(separator);

		table = new JTable();
		mouseAction = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = table.getSelectedRow();
				if (x >= 0) {
					textField_1.setText(table.getValueAt(x, 0) + "");
					textField_2.setText(table.getValueAt(x, 1) + "");
					textField_3.setText(table.getValueAt(x, 2) + "");
					textField_4.setText(table.getValueAt(x, 3) + "");
					textField_10.setText(table.getValueAt(x, 4) + "");
				}
			}
		};
		table.addMouseListener(mouseAction);

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Sách", "Tên Sách", "Tên tác giả", "Nhà xuất bản", "Số Lượng" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(91);
		table.getColumnModel().getColumn(1).setPreferredWidth(167);
		table.getColumnModel().getColumn(2).setPreferredWidth(191);
		table.getColumnModel().getColumn(3).setPreferredWidth(135);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 113, 452, 227);
		panel.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Danh sách sách");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 90, 122, 23);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(568, 117, 155, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(568, 166, 155, 31);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(568, 217, 155, 31);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(568, 268, 155, 31);
		panel.add(textField_4);

		JLabel lblNewLabel_2 = new JLabel("Mã sách");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(475, 120, 83, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Tên sách");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(475, 166, 83, 28);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Nhà Xuất bản");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(472, 217, 97, 28);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Tên tác giả");
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(475, 268, 83, 28);
		panel.add(lblNewLabel_2_3);

		JButton btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1.setIcon(new ImageIcon(GDforms.class.getResource("/image/L.png")));
		btnNewButton_1.setActionCommand("AddBook");
//		Step: 5
		btnNewButton_1.addActionListener(crudAction);

		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(733, 118, 105, 32);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Sửa");
		btnNewButton_1_1.setIcon(new ImageIcon(GDforms.class.getResource("/image/K.png")));
		btnNewButton_1_1.setActionCommand("UpdateBook");
		btnNewButton_1_1.addActionListener(crudAction);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(733, 164, 105, 32);
		panel.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Xoá");
		btnNewButton_1_2.setIcon(new ImageIcon(GDforms.class.getResource("/image/J.png")));
		btnNewButton_1_2.setActionCommand("DeleteBook");
		btnNewButton_1_2.addActionListener(crudAction);
		btnNewButton_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1_2.setBounds(733, 215, 105, 32);
		panel.add(btnNewButton_1_2);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(568, 320, 155, 31);
		panel.add(textField_10);

		JLabel lblNewLabel_2_3_2 = new JLabel("Số lượng");
		lblNewLabel_2_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2_3_2.setBounds(475, 320, 83, 28);
		panel.add(lblNewLabel_2_3_2);

		JButton btnNewButton_3 = new JButton("Thoát");
		btnNewButton_3.setIcon(new ImageIcon(GDforms.class.getResource("/image/I.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3.setBounds(733, 267, 105, 31);
		panel.add(btnNewButton_3);

		JLabel lblNewLabel_11 = new JLabel("Tìm kiếm");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(205, 10, 67, 31);
		panel.add(lblNewLabel_11);

		JButton btnNewButton = new JButton("Tìm Kiếm");
		btnNewButton.setBounds(649, 10, 100, 31);
		btnNewButton.setActionCommand("Find");
		panel.add(btnNewButton);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GDforms frame = new GDforms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
