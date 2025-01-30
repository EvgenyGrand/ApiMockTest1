package dataBase;

import java.sql.*;

public class SqlExecutor {


    public static void createTable() {
        String sql = "CREATE TABLE qr_codes (id BIGINT AUTO_INCREMENT PRIMARY KEY, qrCode VARCHAR(255) UNIQUE)";
        try (Connection connection = ConnectionDataBase.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addQRCode(String qrCode) {
        String sql = "INSERT INTO qr_codes (qrCode) VALUES (?)";
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, qrCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getQRCodeById(Integer id) {
        String sql = "SELECT qrCode FROM qr_codes WHERE id = ?";
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getString("qrCode") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
