package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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

import dao.Connect;

public class GDforms extends JFrame{
	private JPanel contentPane;
	final String headerPM[] = { "Mã PM", "Mã độc giả", "Mã sách", "Ngày mượn", "Ngày trả", "Số lượng" };
	final DefaultTableModel tb2 = new DefaultTableModel(headerPM, 0);

	private JTextField tfTimKiem;
	private JTextField tfMaPM;
	private JTextField tfMaKH;
	private JTextField tfMaSach;
	private JTextField tfNgayMuon;
	private JTextField tfNgayTra;
	private JTable tablePM;
	private JTextField tfSoLuong;

	Connect cn = new Connect();
	ResultSet rs;
	DefaultTableModel tbn = new DefaultTableModel();
	Connection conn;

	public void loadBang2() {
		try {
			conn = cn.getConnection();
			int number;
			Vector row;
			String sql = "Select * From PHIEU_MUON";
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			number = meta.getColumnCount();
			tb2.setRowCount(0);
			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++)
					row.addElement(rs.getString(i));
				tb2.addRow(row);
				tablePM.setModel(tb2);
			}
			st.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	KeyAdapter keyAction;
	MouseAdapter mouseAction;
	ActionListener themAction, suaAction, deleteAction, searchAction;

	public GDforms() {
		setTitle("QUẢN LÝ THƯ VIỆN");

		setIconImage(Toolkit.getDefaultToolkit().getImage(GDforms.class.getResource("/image/H.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 871, 418);
		contentPane.add(tabbedPane);

//		Thêm, xóa, sửa phiếu mượn: Ngân
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Phiếu mượn", new ImageIcon(GDforms.class.getResource("/image/E.png")), panel_2, null);
		panel_2.setLayout(null);

		// Tìm kiếm
		tfTimKiem = new JTextField();
		tfTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfTimKiem.setColumns(10);
		tfTimKiem.setBounds(29, 22, 250, 30);
		panel_2.add(tfTimKiem);

		// Mã phiếu mượn
		tfMaPM = new JTextField();
		tfMaPM.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaPM.setColumns(10);
		tfMaPM.setBounds(194, 91, 223, 28);
		panel_2.add(tfMaPM);

		// Mã khách hàng
		tfMaKH = new JTextField();
		tfMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaKH.setColumns(10);
		tfMaKH.setBounds(194, 129, 223, 28);
		panel_2.add(tfMaKH);

		// Mã sách
		tfMaSach = new JTextField();
		tfMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfMaSach.setColumns(10);
		tfMaSach.setBounds(194, 167, 223, 28);
		panel_2.add(tfMaSach);

		// Ngày mượn
		tfNgayMuon = new JTextField();
		tfNgayMuon.setText("YYYY/MM/DD");
		tfNgayMuon.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		tfNgayMuon.setColumns(10);
		tfNgayMuon.setBounds(194, 205, 223, 28);
		panel_2.add(tfNgayMuon);

		tfNgayTra = new JTextField();
		tfNgayTra.setText("YYYY/MM/DD");
		tfNgayTra.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		tfNgayTra.setColumns(10);
		tfNgayTra.setBounds(194, 244, 223, 28);
		panel_2.add(tfNgayTra);

		// Số lượng
		tfSoLuong = new JTextField();
		tfSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfSoLuong.setColumns(10);
		tfSoLuong.setBounds(194, 287, 109, 28);
		panel_2.add(tfSoLuong);

		JLabel lblMaPM = new JLabel("Mã phiếu mượn");
		lblMaPM.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaPM.setBounds(29, 91, 145, 28);
		panel_2.add(lblMaPM);

		JLabel lblTK = new JLabel("Nhập mã phiếu mượn ( Mã khách hàng, Mã sách)");
		lblTK.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTK.setBounds(29, 53, 298, 28);
		panel_2.add(lblTK);

		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaKH.setBounds(29, 129, 145, 28);
		panel_2.add(lblMaKH);

		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSach.setBounds(29, 167, 145, 28);
		panel_2.add(lblMaSach);

		JLabel lblNgayMuon = new JLabel("Ngày mượn");
		lblNgayMuon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayMuon.setBounds(29, 205, 145, 28);
		panel_2.add(lblNgayMuon);

		JLabel lblNgayTra = new JLabel("Ngày trả");
		lblNgayTra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayTra.setBounds(29, 243, 145, 28);
		panel_2.add(lblNgayTra);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuong.setBounds(29, 281, 145, 28);
		panel_2.add(lblSoLuong);

		// thêm
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(GDforms.class.getResource("/image/L.png")));

		themAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = cn.getConnection();
				try {
					if (tfMaPM.getText().equals("") || tfMaKH.getText().equals("") || tfMaSach.getText().equals("")
							|| tfNgayMuon.getText().equals("") || tfNgayTra.getText().equals("")
							|| tfSoLuong.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Cần nhập đủ dữ liệu!", "Empty",
								JOptionPane.WARNING_MESSAGE);
					} else {
						String sqlt = "Select Ma_Phieu_muon From PHIEU_MUON Where Ma_Phieu_muon = '" + tfMaPM.getText()
								+ "'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(sqlt);
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Mã phiếu mượn đã tồn tại!", "Empty",
									JOptionPane.WARNING_MESSAGE);
						} else {
							String sql = "Insert into PHIEU_MUON values('" + tfMaPM.getText() + "','" + tfMaKH.getText()
									+ "','" + tfMaSach.getText() + "','" + tfNgayMuon.getText() + "','"
									+ tfNgayTra.getText() + "','" + tfSoLuong.getText() + "')";
							st = conn.createStatement();
							int kq = st.executeUpdate(sql);
							if (kq > 0) {
								JOptionPane.showMessageDialog(null, "Thêm thành công", "Success",
										JOptionPane.INFORMATION_MESSAGE);
								tfMaPM.setText("");
								tfMaKH.setText("");
								tfMaSach.setText("");
								tfNgayMuon.setText("");
								tfNgayTra.setText("");
								tfSoLuong.setText("");
								loadBang2();
							}
						}
					}
					conn.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		btnThem.addActionListener(themAction);
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setBounds(304, 282, 113, 34);
		panel_2.add(btnThem);

		// Sửa
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(GDforms.class.getResource("/image/K.png")));

		suaAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = cn.getConnection();
					PreparedStatement comm = conn.prepareStatement(
							"UPDATE PHIEU_MUON set Ma_Khach_hang=?, Ma_Sach=?, Ngay_muon=?, Ngaytra=?, So_luong=? Where Ma_Phieu_muon=?");
					comm.setString(6, tfMaPM.getText());
					comm.setString(1, tfMaKH.getText());
					comm.setString(2, tfMaSach.getText());
					comm.setString(3, tfNgayMuon.getText());
					comm.setString(4, tfNgayTra.getText());
					comm.setString(5, tfSoLuong.getText());
					int ck = comm.executeUpdate();
					if (ck > 0) {
						JOptionPane.showMessageDialog(null, "Sửa thành công!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						tfMaPM.setText("");
						tfMaKH.setText("");
						tfMaSach.setText("");
						tfNgayMuon.setText("");
						tfNgayTra.setText("");
						tfSoLuong.setText("");

						tbn.setRowCount(0);
						loadBang2();
					}
					conn.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sửa thất bại!", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		btnSua.addActionListener(suaAction);
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnSua.setBounds(39, 325, 109, 34);
		panel_2.add(btnSua);

		// Xóa
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(GDforms.class.getResource("/image/J.png")));
		deleteAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = cn.getConnection();
					PreparedStatement comm = conn.prepareStatement("Delete PHIEU_MUON Where Ma_Phieu_muon= ?");
					comm.setString(1, tablePM.getValueAt(tablePM.getSelectedRow(), 0).toString());
					if (JOptionPane.showConfirmDialog(null, "Xóa phiếu mượn", "Cho phép xóa phiếu mượn",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						comm.executeUpdate();
						tfMaPM.setText("");
						tfMaKH.setText("");
						tfMaSach.setText("");
						tfNgayMuon.setText("");
						tfNgayTra.setText("");
						tfSoLuong.setText("");

						loadBang2();
					}
					conn.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Xóa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		btnXoa.addActionListener(deleteAction);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setBounds(158, 325, 109, 34);
		panel_2.add(btnXoa);

		// Thoát
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon(GDforms.class.getResource("/image/I.png")));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThoat.setBounds(277, 326, 109, 34);
		panel_2.add(btnThoat);

		JButton btnTimKiem = new JButton("Tìm");
		searchAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = cn.getConnection();

					if (tfTimKiem.getText().length() == 0)
						JOptionPane.showMessageDialog(null, "Chưa nhập từ khóa cần tìm!", "Failed",
								JOptionPane.ERROR_MESSAGE);
					else {
						PreparedStatement comm = conn.prepareStatement(
								"SELECT * from PHIEU_MUON WHERE Ma_Phieu_muon like N'%" + tfTimKiem.getText() + "%' "
										+ "or Ma_Khach_hang like N'%" + tfTimKiem.getText()
										+ "%' or Ma_Phieu_muon like N'%" + tfTimKiem.getText().trim() + "%'");
						comm.setString(1, tablePM.getValueAt(tablePM.getSelectedRow(), 0).toString());

						System.out.println(comm);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Xóa thất bại", "Failed", JOptionPane.ERROR_MESSAGE);

				}
			}
		};
		btnTimKiem.addActionListener(searchAction);
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setBounds(300, 22, 100, 30);
		panel_2.add(btnTimKiem);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(430, 60, 426, 302);
		panel_2.add(scrollPane_1);

		tablePM = new JTable();
		tablePM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = tablePM.getSelectedRow();
				if (x >= 0) {
					tfMaPM.setText(tablePM.getValueAt(x, 0) + "");
					tfMaKH.setText(tablePM.getValueAt(x, 1) + "");
					tfMaSach.setText(tablePM.getValueAt(x, 2) + "");
					tfNgayMuon.setText(tablePM.getValueAt(x, 3) + "");
					tfNgayTra.setText(tablePM.getValueAt(x, 4) + "");
					tfSoLuong.setText(tablePM.getValueAt(x, 5) + "");
				}
			}
		});
		tablePM.addMouseListener(mouseAction);
		tablePM.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã phiếu mượn", "Mã khách hàng", "Mã sách", "Ngày mượn", "Ngày trả", "Số lượng" }));
		tablePM.getColumnModel().getColumn(0).setPreferredWidth(91);
		scrollPane_1.setViewportView(tablePM);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 79, 407, 2);
		panel_2.add(separator_2);

		JLabel lblNewLabel_10 = new JLabel("QUẢN LÝ THƯ VIỆN");
		lblNewLabel_10.setForeground(Color.BLUE);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_10.setBounds(487, 10, 329, 37);
		contentPane.add(lblNewLabel_10);

		loadBang2();
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
