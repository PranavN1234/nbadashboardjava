package com.batch.nbadashboardjava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="nba_matches")
public class Matches {

    private String date;

    @Id
    private long game_id;

    private String status;

    private String team1;
    private String team2;

    private String season;

    private int ptsHome;

    private float fgHome;

    private float ftHome;

    private float f3Home;

    private int astHome;

    private int rebHome;

    private int ptsAway;

    private float fgAway;

    private float ftAway;

    private float f3Away;

    private int astAway;

    private int rebAway;

    private String winner;

    public Matches(){

    }
    public Matches(String date, long game_id, String status, String team1, String team2, String season, int ptsHome, float fgHome, float ftHome, float f3Home, int astHome, int rebHome, int ptsAway, float fgAway, float ftAway, float f3Away, int astAway, int rebAway, String winner) {
        this.date = date;
        this.game_id = game_id;
        this.status = status;
        this.team1 = team1;
        this.team2 = team2;
        this.season = season;
        this.ptsHome = ptsHome;
        this.fgHome = fgHome;
        this.ftHome = ftHome;
        this.f3Home = f3Home;
        this.astHome = astHome;
        this.rebHome = rebHome;
        this.ptsAway = ptsAway;
        this.fgAway = fgAway;
        this.ftAway = ftAway;
        this.f3Away = f3Away;
        this.astAway = astAway;
        this.rebAway = rebAway;
        this.winner = winner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPtsHome() {
        return ptsHome;
    }

    public void setPtsHome(int ptsHome) {
        this.ptsHome = ptsHome;
    }

    public float getFgHome() {
        return fgHome;
    }

    public void setFgHome(float fgHome) {
        this.fgHome = fgHome;
    }

    public float getFtHome() {
        return ftHome;
    }

    public void setFtHome(float ftHome) {
        this.ftHome = ftHome;
    }

    public float getF3Home() {
        return f3Home;
    }

    public void setF3Home(float f3Home) {
        this.f3Home = f3Home;
    }

    public int getAstHome() {
        return astHome;
    }

    public void setAstHome(int astHome) {
        this.astHome = astHome;
    }

    public int getRebHome() {
        return rebHome;
    }

    public void setRebHome(int rebHome) {
        this.rebHome = rebHome;
    }

    public int getPtsAway() {
        return ptsAway;
    }

    public void setPtsAway(int ptsAway) {
        this.ptsAway = ptsAway;
    }

    public float getFgAway() {
        return fgAway;
    }

    public void setFgAway(float fgAway) {
        this.fgAway = fgAway;
    }

    public float getFtAway() {
        return ftAway;
    }

    public void setFtAway(float ftAway) {
        this.ftAway = ftAway;
    }

    public float getF3Away() {
        return f3Away;
    }

    public void setF3Away(float f3Away) {
        this.f3Away = f3Away;
    }

    public int getAstAway() {
        return astAway;
    }

    public void setAstAway(int astAway) {
        this.astAway = astAway;
    }

    public int getRebAway() {
        return rebAway;
    }

    public void setRebAway(int rebAway) {
        this.rebAway = rebAway;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
