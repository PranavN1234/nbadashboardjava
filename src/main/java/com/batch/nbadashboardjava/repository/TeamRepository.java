package com.batch.nbadashboardjava.repository;

import com.batch.nbadashboardjava.model.Teams;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeamRepository {

    @Autowired
    private DataSource dataSource;

    public List<Teams> findAllTeams() {
        List<Teams> teamsList = new ArrayList<>();
        String sql = "SELECT * FROM nba_teams";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teams team = new Teams(
                        resultSet.getInt("team_id"),
                        resultSet.getString("team_name"),
                        resultSet.getInt("total_matches"),
                        resultSet.getInt("total_wins")

                );
                teamsList.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamsList;
    }

    public Teams findByTeamName(String teamName) {
        Teams team = null;
        String sql = "SELECT * FROM nba_teams WHERE team_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, teamName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    team = new Teams(
                            resultSet.getInt("team_id"),
                            resultSet.getString("team_name"),
                            resultSet.getInt("total_matches"),
                            resultSet.getInt("total_wins")

                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
}
