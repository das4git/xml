package db;

import model.Article;
import service.ArticleBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final String url="jdbc:postgresql://localhost:5432/postgres";
    private static final String user="postgres";
    private static final String password="postgres";
    private static final String deleteTable = "DROP TABLE IF EXISTS TABLE1";
    private static final String createTable = "CREATE TABLE IF NOT EXISTS TABLE1 "  +
            "(ID_ART INT PRIMARY KEY, NAME VARCHAR, CODE INT , USERNAME VARCHAR, GUID VARCHAR)";
    private static final String insertFields = "INSERT INTO TABLE1 " +
            "(ID_ART, NAME, CODE, USERNAME, GUID) VALUES (?, ?, ?, ?, ?)";

    public void createDB() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url,user, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteTable);
            statement.executeUpdate(createTable);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFields(int n) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(insertFields);
            ArticleBuilder dataCreator = new ArticleBuilder();
            List<Article> dataForInsertToDb = dataCreator.
                    insertArticlesIntoDb(n);

            for (int i = 1; i < n + 1; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, dataForInsertToDb.get(i).getName());
                preparedStatement.setInt(3, dataForInsertToDb.get(i).getCode());
                preparedStatement.setString(4, dataForInsertToDb.get(i).getUserName());
                preparedStatement.setString(5, dataForInsertToDb.get(i).getGuid());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sqlQueryForGetData = "SELECT ID_ART, NAME, CODE, GUID, USERNAME FROM TABLE1";

        try (Statement statement = DriverManager.getConnection(url,user, password)
                .createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sqlQueryForGetData)) {
                while (resultSet.next()) {
                    Article article = new Article();
                    article.setName(resultSet.getString("NAME"));
                    article.setId_art(resultSet.getInt("ID_ART"));
                    article.setCode(resultSet.getInt("CODE"));
                    article.setUserName(resultSet.getString("USERNAME"));
                    article.setGuid(resultSet.getString("GUID"));
                    articles.add(article);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public void closeDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user, password);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
