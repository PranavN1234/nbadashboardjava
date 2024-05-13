package com.batch.nbadashboardjava.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="nba_teams")
public class Teams {

    @Id
    @Column(name="team_id")
    private int teamId;


    @Column(name="team_name")
    private String teamName;


    @Column(name="total_matches")
    private int totalMatches;


    @Column(name="total_wins")
    private int totalWins;

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }

    @Transient
    private List<Matches> matches;
    public Teams(){

    }

    public Teams(int teamId, String teamName, int totalMatches, int totalWins) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.totalMatches = totalMatches;
        this.totalWins = totalWins;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }
}
