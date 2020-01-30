package model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnection {

    private String dataBaseName;
    private String userName;
    private String passWord;
    private String cloudSqlInstance;
    private HikariConfig config;
    private DataSource source;

    public DatabaseConnection(String dataBaseName, String userName, String passWord, String cloudSqlInstance) {
        this.dataBaseName = dataBaseName;
        this.userName = userName;
        this.passWord = passWord;
        this.cloudSqlInstance = cloudSqlInstance;

        config = new HikariConfig();

        // Configure which instance and what database user to connect with.
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", dataBaseName));
        config.setUsername(userName);
        config.setPassword(passWord);
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", cloudSqlInstance);
        config.addDataSourceProperty("useSSL", "false");
        source = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
