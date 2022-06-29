package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String Url = "jdbc:sqlserver://localhost:1433;databaseName=QLTV;user=sa;password=ngan119211";
			conn = DriverManager.getConnection(Url);
			System.out.println("Thành công");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return conn;
		
	}
	public static void main(String[] args) {
		new Connect().getConnection();
	}
}