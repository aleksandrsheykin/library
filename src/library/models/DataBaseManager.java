package library.models;

import java.sql.*;

/**
 * Created by Olesya on 13.04.2017.
 */
public class DataBaseManager {
    public Connection initConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                            "jdbc:postgresql://localhost/library", "postgres", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void select() {
        Connection connection = initConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from book");

            while (result.next()) {
                System.out.println(result.getInt("book_year"));
                System.out.println(result.getString("book_title"));
                System.out.println(result.getString("book_author"));
                System.out.println(result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert() {
        Connection connection = initConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO book(\n" +
                            " book_author, book_title, book_isbn, book_year)\n" +
                            " VALUES (?,?, ?, ?)");
            preparedStatement.setString(1, "Mick Jagger");
            preparedStatement.setString(2, "Rock-n-roll");
            preparedStatement.setString(3, "234-234-234");
            preparedStatement.setInt(4, 2017);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    }

    public void delete() {
    }
}