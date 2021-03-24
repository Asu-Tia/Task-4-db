package com.codecool.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RadioCharts {

    private final String url;
    private final String user;
    private final String password;

    public RadioCharts(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getMostPlayedSong() {
        String query = "SELECT song FROM music_broadcast GROUP BY song ORDER BY SUM(times_aired) DESC, artist LIMIT 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             ResultSet rs = connection.createStatement().executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public String getMostActiveArtist() {
        String query = "SELECT artist FROM music_broadcast GROUP BY song ORDER BY COUNT(*) DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             ResultSet rs = connection.createStatement().executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
}
