package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
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

    public void showError(IOException ex) {
        System.out.println("Error" + ex.getMessage());
        System.out.println("Error Code:" + ex.getMessage());
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

    public User sqlSelectUser(int userID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM emlakDB.user WHERE userID = ?");
            statement.setInt(1, userID);
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

    public List<Advert> sqlFilterHouses(ArrayList<Integer> stateID, int categoryID, ArrayList<Integer> countRoom,ArrayList<Integer>age,
    int m2Min, int m2Max, long priceMin, long priceMax, String city, String district, String street, String sort) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    // Sonuçları döndürmek için bir liste
    List<Advert> adverts = new ArrayList<>();

    try {
        // Dinamik SQL sorgusu oluşturmak için StringBuilder kullanımı
        StringBuilder sqlBuilder = new StringBuilder("SELECT advert.advertID, advert.userID, advert.advertDate, category.categoryID, state.stateID, " +
            "roomcount.roomCount, age.age, advert.m2, advert.price, advert.city, advert.district, advert.street, advert.imageName, advert.image " +
            "FROM emlakDB.advert " +
            "JOIN category ON category.categoryID = advert.categoryID " +
            "JOIN state ON state.stateID = advert.stateID " +
            "JOIN user ON user.userID = advert.userID " +
            "JOIN roomcount ON roomcount.roomCountID = advert.roomCountID " +
            "JOIN age ON age.ageID = advert.ageID " +
            "WHERE category.categoryID = ? " +
            "AND m2 BETWEEN ? AND ? " +
            "AND price BETWEEN ? AND ? ");
        // Dinamik filtreler için SQL koşulları
        if (stateID != null && !stateID.isEmpty()) {
            sqlBuilder.append(" AND state.stateID IN (");
            for (int i = 0; i < stateID.size(); i++) {
                sqlBuilder.append("?");
                if (i < stateID.size() - 1) sqlBuilder.append(", ");
            }
            sqlBuilder.append(")");
        }
        if (countRoom != null && !countRoom.isEmpty()) {
            sqlBuilder.append(" AND roomcount.roomCountID IN (");
            for (int i = 0; i < countRoom.size(); i++) {
                sqlBuilder.append("?");
                if (i < countRoom.size() - 1) sqlBuilder.append(", ");
            }
            sqlBuilder.append(")");
        }
        if (age != null && !age.isEmpty()) {
            sqlBuilder.append(" AND age.ageID IN (");
            for (int i = 0; i < age.size(); i++) {
                sqlBuilder.append("?");
                if (i < age.size() - 1) sqlBuilder.append(", ");
            }
            sqlBuilder.append(")");
        }
        if (city != null && !city.isEmpty()) {
            sqlBuilder.append(" AND city = ?");
        }

        if (district != null && !district.isEmpty()) {
            sqlBuilder.append(" AND district = ?");
        }

        if (street != null && !street.isEmpty()) {
            sqlBuilder.append(" AND street = ?");
        }
        if(sort != null && !sort.isEmpty()){
            if(sort == "Price: High to Low")
                sqlBuilder.append(" ORDER BY price DESC");
            else if( sort == "Price: Low to High")
                sqlBuilder.append(" ORDER BY price ASC");
        }
        // SQL sorgusunu yazdır (debug için)
        String sql = sqlBuilder.toString();
        System.out.println("Generated SQL: " + sql);

        connection = getConnection();
        statement = connection.prepareStatement(sql);

        // Parametreleri doldur
        int paramIndex = 1;
        statement.setInt(paramIndex++, categoryID);
        
        statement.setInt(paramIndex++, m2Min > 0 ? m2Min : 0);
        statement.setInt(paramIndex++, m2Max > 0 ? m2Max : Integer.MAX_VALUE);

        statement.setLong(paramIndex++, priceMin > 0 ? priceMin : 0);
        statement.setLong(paramIndex++, priceMax > 0 ? priceMax : Long.MAX_VALUE);

        if (stateID != null && !stateID.isEmpty()) {
            for (int id : stateID) {
                statement.setInt(paramIndex++, id);
            }
        }
        if (countRoom != null && !countRoom.isEmpty()) {
            for (int roomCount : countRoom) {
                statement.setInt(paramIndex++, roomCount);
            }
        }
        if (age != null && !age.isEmpty()) {
            for (int ageID : age) {
                statement.setInt(paramIndex++, ageID);
            }
        }
        if (city != null && !city.isEmpty()) {
            statement.setString(paramIndex++, city);
        }

        if (district != null && !district.isEmpty()) {
            statement.setString(paramIndex++, district);
        }

        if (street != null && !street.isEmpty()) {
            statement.setString(paramIndex++, street);
        }

        rs = statement.executeQuery();

        // ResultSet'ten Advert nesnelerini oluştur ve listeye ekle
        while (rs.next()) {
            Advert advert = new Advert();
                advert.setAdvertID(rs.getInt("advertID"));
                advert.setPublisherID(rs.getInt("userID"));
                advert.setDate((rs.getDate("advertDate")).toString());
                advert.setCategoryID(rs.getInt("categoryID"));
                advert.setStateID(rs.getInt("stateID"));
                advert.setRoomCount(rs.getString("roomcount"));
                advert.setAge(rs.getString("age"));
                advert.setM2(rs.getInt("m2"));
                advert.setPrice(rs.getLong("price"));
                advert.setCity(rs.getString("city"));
                advert.setDistrict(rs.getString("district"));
                advert.setStreet(rs.getString("street"));
                advert.setImageName(rs.getString("imageName"));
                //Getting the image from sql
                InputStream is = rs.getBinaryStream("image");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                Image newImage = new Image("file:photo.jpg", 100, 150, true, true);
                advert.setImage(newImage);
            adverts.add(advert);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        showError(e);
    } catch(IOException e){
        e.printStackTrace();
        showError(e);
    }
    finally {
        try {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return adverts;
}
    
    public List<Advert> sqlFilterWorkplace(ArrayList<Integer> stateID, int categoryID, ArrayList<Integer> age, int m2Min, int m2Max, 
    long priceMin, long priceMax, String city, String district, String street, String sort){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
    
        // Sonuçları döndürmek için bir liste
        List<Advert> adverts = new ArrayList<>();
    
        try {
            // Dinamik SQL sorgusu oluşturmak için StringBuilder kullanımı
            StringBuilder sqlBuilder = new StringBuilder("SELECT advert.advertID, advert.userID, advert.advertDate, category.categoryID, state.stateID, " +
                "age.age, advert.m2, advert.price, advert.city, advert.district, advert.street, advert.imageName, advert.image " +
                "FROM emlakDB.advert " +
                "JOIN category ON category.categoryID = advert.categoryID " +
                "JOIN state ON state.stateID = advert.stateID " +
                "JOIN user ON user.userID = advert.userID " +
                "JOIN age ON age.ageID = advert.ageID " +
                "WHERE category.categoryID = ? " +
                "AND m2 BETWEEN ? AND ? " +
                "AND price BETWEEN ? AND ?");
            // Dinamik filtreler için SQL koşulları
            if (stateID != null && !stateID.isEmpty()) {
                sqlBuilder.append(" AND state.stateID IN (");
                for (int i = 0; i < stateID.size(); i++) {
                    sqlBuilder.append("?");
                    if (i < stateID.size() - 1) sqlBuilder.append(", ");
                }
                sqlBuilder.append(")");
            }
            if (age != null && !age.isEmpty()) {
                sqlBuilder.append(" AND age.ageID IN (");
                for (int i = 0; i < age.size(); i++) {
                    sqlBuilder.append("?");
                    if (i < age.size() - 1) sqlBuilder.append(", ");
                }
                sqlBuilder.append(")");
            }
            if (city != null && !city.isEmpty()) {
                sqlBuilder.append(" AND city = ?");
            }
    
            if (district != null && !district.isEmpty()) {
                sqlBuilder.append(" AND district = ?");
            }
    
            if (street != null && !street.isEmpty()) {
                sqlBuilder.append(" AND street = ?");
            }
            if(sort != null && !sort.isEmpty()){
                if(sort == "Price: High to Low")
                    sqlBuilder.append(" ORDER BY price DESC");
                else if( sort == "Price: Low to High")
                    sqlBuilder.append(" ORDER BY price ASC");
            }
            // SQL sorgusunu yazdır (debug için)
            String sql = sqlBuilder.toString();
            System.out.println("Generated SQL: " + sql);
    
            connection = getConnection();
            statement = connection.prepareStatement(sql);
    
            // Parametreleri doldur
            int paramIndex = 1;
            statement.setInt(paramIndex++, categoryID);
    
            statement.setInt(paramIndex++, m2Min > 0 ? m2Min : 0);
            statement.setInt(paramIndex++, m2Max > 0 ? m2Max : Integer.MAX_VALUE);
    
            statement.setLong(paramIndex++, priceMin > 0 ? priceMin : 0);
            statement.setLong(paramIndex++, priceMax > 0 ? priceMax : Long.MAX_VALUE);

            if (stateID != null && !stateID.isEmpty()) {
                for (int ID : stateID) {
                    statement.setInt(paramIndex++, ID);
                }
            }
            if (age != null && !age.isEmpty()) {
                for (int ageID : age) {
                    statement.setInt(paramIndex++, ageID);
                }
            }
            if (city != null && !city.isEmpty()) {
                statement.setString(paramIndex++, city);
            }
    
            if (district != null && !district.isEmpty()) {
                statement.setString(paramIndex++, district);
            }
    
            if (street != null && !street.isEmpty()) {
                statement.setString(paramIndex++, street);
            }
    
            rs = statement.executeQuery();
    
            // ResultSet'ten Advert nesnelerini oluştur ve listeye ekle
            while (rs.next()) {
                Advert advert = new Advert();
                    advert.setAdvertID(rs.getInt("advertID"));
                    advert.setPublisherID(rs.getInt("userID"));
                    advert.setDate((rs.getDate("advertDate")).toString());
                    advert.setCategoryID(rs.getInt("categoryID"));
                    advert.setStateID(rs.getInt("stateID"));
                    advert.setAge(rs.getString("age"));
                    advert.setM2(rs.getInt("m2"));
                    advert.setPrice(rs.getLong("price"));
                    advert.setCity(rs.getString("city"));
                    advert.setDistrict(rs.getString("district"));
                    advert.setStreet(rs.getString("street"));
                    advert.setImageName(rs.getString("imageName"));
                    //Getting the image from sql
                    InputStream is = rs.getBinaryStream("image");
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    byte[] content = new byte[1024];
                    int size;
                    while ((size = is.read(content)) != -1) {
                        os.write(content, 0, size);
                    }
                    os.close();
                    is.close();
                    Image newImage = new Image("file:photo.jpg", 100, 150, true, true);
                    advert.setImage(newImage);
                adverts.add(advert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError(e);
        } catch(IOException e){
            e.printStackTrace();
            showError(e);
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adverts;
    }

    public List<Advert> getFavoriteAdverts(int userID) throws SQLException {
        List<Advert> advertList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            String query = "SELECT advert.*, age.age, roomcount.roomCount " +
                    "FROM emlakDB.favorite " +
                    "JOIN emlakDB.advert ON favorite.advertID = advert.advertID " +
                    "JOIN emlakDB.roomcount ON roomcount.roomCountID = advert.roomCountID " +
                    "JOIN emlakDB.age ON age.ageID = advert.ageID " +
                    "WHERE favorite.userID = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            rs = statement.executeQuery();

            while (rs.next()) {
                //Getting the image from sql
                InputStream is = rs.getBinaryStream("image");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                Image newImage = new Image("file:photo.jpg", 100, 150, true, true);
                Advert advert = new Advert(
                        rs.getString("imageName"),
                        null, // Image yüklemesi burada yapılabilir
                        rs.getInt("stateID"),
                        rs.getInt("userID"),
                        rs.getInt("advertID"),
                        rs.getInt("categoryID"),
                        rs.getInt("m2"),
                        rs.getString("advertDate"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("street"),
                        rs.getString("roomCount"),
                        rs.getLong("price"),
                        rs.getString("age")
                );
                advert.setImage(newImage);
                advertList.add(advert);
            }
        } catch (SQLException exception) {
            showError(exception);
        } catch(IOException exception){
            showError(exception);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return advertList;
    }

    public List<Advert> getMyAdverts(int userID) throws SQLException,IOException {
        // Kullanıcının kendi ilanlarını getirir
        List<Advert> advertList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            String query = "select advert.*, age.age ,roomcount.roomCount " + //
                                "from emlakDB.advert " + //
                                "join emlakDB.roomcount on roomcount.roomCountID = advert.roomCountID " + //
                                "join emlakDB.age on age.ageID = advert.ageID " + //
                                "where advert.userID = ?; ";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            rs = statement.executeQuery();

            while (rs.next()) {
                //Getting the image from sql
                InputStream is = rs.getBinaryStream("image");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                Image newImage = new Image("file:photo.jpg", 100, 150, true, true);
                Advert advert = new Advert(
                        rs.getString("imageName"),
                        null, // Image yüklemesi burada yapılabilir
                        rs.getInt("stateID"),
                        rs.getInt("userID"),
                        rs.getInt("advertID"),
                        rs.getInt("categoryID"),
                        rs.getInt("m2"),
                        rs.getString("advertDate"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("street"),
                        rs.getString("roomCount"),
                        rs.getLong("price"),
                        rs.getString("age")
                );
                advert.setImage(newImage);
                advertList.add(advert);
            }
        } catch (SQLException exception) {
            showError(exception);
        } catch(IOException exception){
            showError(exception);
        } 
        finally {
            if (connection != null) {
                connection.close();
            }
        }

        return advertList;
    }

    public int updateUser(User updatedUser) throws SQLException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        int result = 0; // Güncelleme sonucunu döndürmek için bir kontrol değeri

        try {
            connection = getConnection();
            String sqlCode = "CALL UpdateUser(?, ?, ?, ?, ?, ?);";
            pStatement = connection.prepareStatement(sqlCode);
            pStatement.setInt(1, updatedUser.getID());
            pStatement.setString(2, updatedUser.getName());
            pStatement.setString(3, updatedUser.getSurname());
            pStatement.setString(4, updatedUser.getMail());
            pStatement.setString(5, updatedUser.getPassword());
            pStatement.setString(6, updatedUser.getTelNo());

            result = pStatement.executeUpdate();
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }
    
    public boolean insertFavorite(int userID, int advertID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isInserted = false;

        try {
            connection = getConnection();
            String sql = "INSERT INTO emlakDB.favorite (userID, advertID) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, advertID);

            int rowsAffected = statement.executeUpdate();
            isInserted = rowsAffected > 0;
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isInserted;
    }

    public boolean deleteFavorite(int userID, int advertID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDeleted = false;

        try {
            connection = getConnection();
            String sql = "DELETE FROM emlakDB.favorite WHERE userID = ? AND advertID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.setInt(2, advertID);

            int rowsAffected = statement.executeUpdate();
            isDeleted = rowsAffected > 0;
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isDeleted;
    }
    
    public ArrayList<String> sqlDistinctCity() throws SQLException{

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        ArrayList<String> cities = new ArrayList<String>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT city FROM advert;");
            rs = statement.executeQuery();
            while(rs.next()) {
                String city = rs.getString("city");
                cities.add(city);
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return cities;
    }

    public ArrayList<String> sqlDistinctDistrict() throws SQLException{

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        ArrayList<String> districts = new ArrayList<String>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT district FROM advert;");
            rs = statement.executeQuery();
            while(rs.next()) {
                String district = rs.getString("district");
                districts.add(district);
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return districts;
    }

    public ArrayList<String> sqlDistinctStreet() throws SQLException{

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        ArrayList<String> streets = new ArrayList<String>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT street FROM advert;");
            rs = statement.executeQuery();
            while (rs.next()) {
                String street = rs.getString("street");
                streets.add(street);
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return streets;
    }

    private ArrayList<Integer> sqlTopAdverts() throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> advertIDs = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT advertID, COUNT(userID) AS favoriteCount " + //
                                "FROM favorite " + //
                                "GROUP BY advertID " + //
                                "HAVING favoriteCount > 1 " + //
                                "ORDER BY favoriteCount DESC " + //
                                "LIMIT 10;");
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("advertID");
                advertIDs.add(id);
            }
        } catch (SQLException exception) {
            showError(exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return advertIDs;
    }

    public ArrayList<Advert> sqlSelectAdverts() throws SQLException, IOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> ids = sqlTopAdverts();
        ArrayList<Advert> adverts = new ArrayList<>();
        try {

            connection = getConnection();
            StringBuilder sqlBuilder = new StringBuilder("select * from AdvertView " + //
                                " where ");
            sqlBuilder.append(" AdvertView.advertID IN (");
            for (int i = 0; i < ids.size(); i++) {
                sqlBuilder.append("?");
                if (i < ids.size() - 1) sqlBuilder.append(", ");
            }
            sqlBuilder.append(")");
            
            String sql = sqlBuilder.toString();

            statement = connection.prepareStatement(sql);

            int paramIndex = 1;

            for (int id : ids) {
                statement.setInt(paramIndex++, id);
            }
            rs = statement.executeQuery();
            while (rs.next()) {
                Advert advert = new Advert();
                advert.setAdvertID(rs.getInt("advertID"));
                advert.setPublisherID(rs.getInt("userID"));
                advert.setDate((rs.getDate("advertDate")).toString());
                advert.setCategoryID(rs.getInt("categoryID"));
                advert.setStateID(rs.getInt("stateID"));
                advert.setRoomCount(rs.getString("roomCount"));
                advert.setAge(rs.getString("age"));
                advert.setM2(rs.getInt("m2"));
                advert.setPrice(rs.getLong("price"));
                advert.setCity(rs.getString("city"));
                advert.setDistrict(rs.getString("district"));
                advert.setStreet(rs.getString("street"));
                advert.setImageName(rs.getString("imageName"));
                //Getting the image from sql
                InputStream is = rs.getBinaryStream("image");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size;
                while ((size = is.read(content)) != -1) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();
                Image newImage = new Image("file:photo.jpg", 100, 150, true, true);
                advert.setImage(newImage);
                adverts.add(advert);
            }
        } catch (SQLException exception) {
            showError(exception);
        } catch(IOException exception){
            showError(exception);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        return adverts;
    }
}