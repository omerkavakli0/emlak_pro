package com.example;

import java.sql.*;
import java.util.List;

import model.Advert;
import model.User;

public class DbHelper {
    String sqlUser = "root"; //SQL login datas
    String sqlPassword = "Mysql@123";
    String sqlUrl = "jdbc:mysql://localhost:3306/emlakDB";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
    }

    public void showError(SQLException ex) {
        System.out.println("Error" + ex.getMessage());
        System.out.println("Error Code:" + ex.getErrorCode());
    }

    public User sqlCheckUser(String userMail, String userPassword) throws SQLException {
        //Checking username  is tooken or not
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM emlakDB.user WHERE userMail = ? and userPassword = ?");
            statement.setString(1, userMail);
            statement.setString(2, userPassword);
            rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userSurname"),
                        rs.getString("userMail"),
                        rs.getString("userPassword"),
                        rs.getString("telNo")
                );
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }

    public int sqlInsertUser(User registerUser) throws SQLException{
        //Inserting user to SQL
        Connection connection = null;
        PreparedStatement pStatement = null;
        int kontrol = 0;
        try {
            connection = getConnection();

            String sqlCode = "insert into emlakDB.user(userName, userSurname, userMail, userPassword, telNo) values(?,?,?,?,?)";
            pStatement = connection.prepareStatement(sqlCode);
            pStatement.setString(1, registerUser.getName());
            pStatement.setString(2, registerUser.getSurname());
            pStatement.setString(3, registerUser.getMail());
            pStatement.setString(4, registerUser.getPassword());
            pStatement.setString(5, registerUser.getTelNo());
            kontrol = pStatement.executeUpdate();
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return kontrol;
    }

    /*public List<Advert> sqlSelectAdverts(
        int categoryID, int stateID, int roomCountID, int ageID, int minM2, int maxM2, long minPrice, long maxPrice, String city, String district, String street) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT user.userMail, advertID, advertDate, category.category, " +
             "state.state, roomCount.roomCount, age.age, m2, price, city, district, street " +
             "FROM emlakDB.advert " +
             "JOIN category ON category.categoryID = advert.categoryID " +
             "JOIN state ON state.stateID = advert.stateID " +
             "JOIN user ON user.userID = advert.userID " +
             "JOIN roomCount ON roomCount.roomCountID = advert.roomCountID " +
             "JOIN age ON age.ageID = advert.ageID " +
             "WHERE categoryID = ? AND stateID = ? AND roomCountID = ? AND ageID = ? " +
             "AND m2 BETWEEN ? AND ? AND price BETWEEN ? AND ? AND city = ? AND district = ? AND street = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.setInt(2, stateID);
            ps.setInt(3, roomCountID);
            ps.setInt(4, ageID);
            ps.setInt(5, minM2);
            ps.setInt(6, maxM2);
            ps.setDouble(7, minPrice);
            ps.setDouble(8, maxPrice);
            ps.setString(9, city);
            ps.setString(10, district);
            ps.setString(11, street);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //adverleri al
                //System.out.println("User Mail: " + rs.getString("userMail"));
                //System.out.println("Price: " + rs.getDouble("price"));
                // Diğer sütunları da çekebilirsiniz
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        

    }*/
}
