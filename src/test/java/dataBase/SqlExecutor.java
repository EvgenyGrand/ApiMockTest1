package dataBase;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class SqlExecutor {


    @Step("Создаем таблицу в БД")
    public static void createTable() {
        String sql = "CREATE TABLE qr_codes (id BIGINT AUTO_INCREMENT PRIMARY KEY, qrCode VARCHAR(255) UNIQUE)";
        try (Connection connection = ConnectionDataBase.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Step("Вносим данные в таблицу")
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


    @Step("Получаем QR-код по значению: {id}")
    public static String getQRCodeById(Integer id) {
        String sql = "SELECT qrCode FROM qr_codes WHERE id = ?";
        String qrCode = null;

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    qrCode = resultSet.getString("qrCode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qrCode;
    }

    @Step("Проверяем соответствие фактического QR-кода ожидаемому")
    public static void checkVerifyQrCodeInDb(Integer id, String expectedQrCode) {
        String actualQrCode = getQRCodeById(id);
        Assertions.assertEquals(expectedQrCode, actualQrCode, "QR-коды не совпадают!");
        Allure.step("Фактический QR-код: " + actualQrCode + ", Ожидаемый QR-код: " + expectedQrCode);
    }

}

