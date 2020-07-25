package ru.stqa.selenium;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litecart?useUnicode=true&serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id from lc_categories_info");
            ArrayList<String> categoryIds = new ArrayList<String>();
            while (rs.next()) {
                categoryIds.add(rs.getString("id"));
            }
            System.out.println(categoryIds);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
