package org.example.coffeeshopposjavaeebackend.dao.custom.impl.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql, Connection connection, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++){
            preparedStatement.setObject((i+1), args[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) preparedStatement.executeQuery();
        }else {
            return (T) (Boolean)(preparedStatement.executeUpdate() > 0);
        }
    }
}
