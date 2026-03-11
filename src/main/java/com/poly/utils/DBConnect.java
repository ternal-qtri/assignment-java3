package com.poly.utils;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {

	private static HikariDataSource dataSource;

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Không tìm thấy MSSQL Driver", e);
		}

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=assignment_java3;encrypt=false");
		config.setUsername("sa");
		config.setPassword("Tri12092005");

		// Cấu hình pool
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setIdleTimeout(60000);
		config.setConnectionTimeout(30000);
		config.setPoolName("HikariPool-Assignment-Java3");

		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}

	public static void main(String[] args) {
		try (Connection conn = DBConnect.getConnection()) {
			if (conn != null && !conn.isClosed()) {
				System.out.println("Kết nối thành công tới MSSQL");
				System.out.println("AutoCommit: " + conn.getAutoCommit());
				System.out.println("URL: " + conn.getMetaData().getURL());
				System.out.println("User: " + conn.getMetaData().getUserName());
			} else {
				System.out.println("Kết nối thất bại!");
			}
		} catch (Exception e) {
			System.out.println("Lỗi khi kết nối DB:");
			e.printStackTrace();
		}
	}
}