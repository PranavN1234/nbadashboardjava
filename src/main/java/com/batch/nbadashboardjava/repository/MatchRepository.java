package com.batch.nbadashboardjava.repository;

import com.batch.nbadashboardjava.model.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchRepository  {

    @Autowired
    private DataSource dataSource;

    public List<Matches> getByTeam1OrTeam2OrderByDateDesc(String teamName, Pageable pageable) {
        List<Matches> matchesList = new ArrayList<>();
        String sql = "SELECT * FROM nba_matches WHERE (team1 = ? OR team2 = ?) ORDER BY date DESC LIMIT ? OFFSET ?";

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, teamName);
            preparedStatement.setString(2, teamName);
            preparedStatement.setInt(3, pageSize);
            preparedStatement.setInt(4, pageNumber * pageSize);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Matches match = new Matches(
                            resultSet.getString("date"),
                            resultSet.getLong("game_id"),
                            resultSet.getString("status"),
                            resultSet.getString("team1"),
                            resultSet.getString("team2"),
                            resultSet.getString("season"),
                            resultSet.getInt("pts_home"),
                            resultSet.getFloat("fg_home"),
                            resultSet.getFloat("ft_home"),
                            resultSet.getFloat("f3home"),
                            resultSet.getInt("ast_home"),
                            resultSet.getInt("reb_home"),
                            resultSet.getInt("pts_away"),
                            resultSet.getFloat("fg_away"),
                            resultSet.getFloat("ft_away"),
                            resultSet.getFloat("f3away"),
                            resultSet.getInt("ast_away"),
                            resultSet.getInt("reb_away"),
                            resultSet.getString("winner")
                    );
                    matchesList.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle the exception as needed
        }
        return matchesList;
    }

    public List<Matches> findByTeamNameAndSeason(String teamName, String season) {
        List<Matches> matchesList = new ArrayList<>();
        String sql = "SELECT * FROM nba_matches WHERE (team1 = ? OR team2 = ?) AND season = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, teamName);
            preparedStatement.setString(2, teamName);
            preparedStatement.setString(3, season);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Matches match = new Matches(
                            resultSet.getString("date"),
                            resultSet.getLong("game_id"),
                            resultSet.getString("status"),
                            resultSet.getString("team1"),
                            resultSet.getString("team2"),
                            resultSet.getString("season"),
                            resultSet.getInt("pts_home"),
                            resultSet.getFloat("fg_home"),
                            resultSet.getFloat("ft_home"),
                            resultSet.getFloat("f3home"),
                            resultSet.getInt("ast_home"),
                            resultSet.getInt("reb_home"),
                            resultSet.getInt("pts_away"),
                            resultSet.getFloat("fg_away"),
                            resultSet.getFloat("ft_away"),
                            resultSet.getFloat("f3away"),
                            resultSet.getInt("ast_away"),
                            resultSet.getInt("reb_away"),
                            resultSet.getString("winner")
                    );
                    matchesList.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or handle the exception as needed
        }
        return matchesList;
    }
}


