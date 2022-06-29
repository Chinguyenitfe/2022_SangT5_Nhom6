package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Book;

public class CrudBooks {
	Connect cn = new Connect();
	Connection conn;
	Statement st;
	ResultSet rs;
	ResultSetMetaData meta;
//	Step: 9
	public boolean addBooks(Book bookModel) {
		if (bookModel != null) {
			try {
				conn = cn.getConnection();
				String sql_check_pk = "Select Ma_Sach From SACH Where Ma_Sach = '" + bookModel.getId() + "'";
				st = conn.createStatement();
				rs = st.executeQuery(sql_check_pk);
				if (rs.next()) {
					return false;
				}
				String sql = "Insert into SACH values('" + bookModel.getId() + "','" + bookModel.getNameBook() + "','"
						+ bookModel.getNameAuthor() + "','" + bookModel.getNxb() + "','" + bookModel.getQuantity()
						+ "')";
				st = conn.createStatement();
				st.executeUpdate(sql);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	
	public void getData(DefaultTableModel tb) {
		try {
			int number = 0;
			String sql = "Select * From SACH";
			conn = cn.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			meta = rs.getMetaData();
			number = meta.getColumnCount();
			tb.setRowCount(0);
			List<String> row;
			while (rs.next()) {
				row = new Vector<>();
				for (int i = 1; i <= number; i++) {
					row.add(rs.getString(i));
				}
				tb.addRow((Vector<?>) row);
			}
			st.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
