package DictionaryCmdLine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IOdata_sql {
    private final String url = "jdbc:mysql://localhost:3306/dictionary";
    private final String username = "root";
    private final String password = "12345678";

    public List<Word> insertWord() {
        List<Word> wordList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);

            String sql_query = "SELECT DISTINCT word, detail FROM tbl_edict order by word";

            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String word_target = resultSet.getString("word");
//                System.out.print(word_target+ "   ");
                String word_explain = resultSet.getString("detail");
                word_explain = word_explain.replaceAll("<C><F><I><N><Q>@", "");
                word_explain = word_explain.replaceAll("<br />", "\n");
                word_explain = word_explain.replaceAll("</Q></N></I></F></C>", "");
//                System.out.println(word_explain);
                Word word = new Word(word_target, word_explain);
                wordList.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wordList;
    }

    public void addWord(String target, String explain) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        explain = explain.replaceAll("\n", "<br />");
        explain = "<C><F><I><N><Q>@" + explain + "</Q></N></I></F></C>";

        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql_query = "INSERT INTO tbl_edict (word, detail) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setString(1, target);
            preparedStatement.setString(2, explain);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void replaceWord(String target, String explain) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        explain = explain.replaceAll("\n", "<br />");
        explain = "<C><F><I><N><Q>@" + explain + "</Q></N></I></F></C>";
        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE tbl_edict SET detail = ? WHERE word = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, explain);
            preparedStatement.setString(2, target);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void removeWord(String target) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM tbl_edict WHERE word = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, target);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
