package com.letscode.app.utils;

import java.time.LocalDate;

public class Match {
    String firstTeam;
    String secondTeam;
    int firstTeamResult;
    int secondTeamResult;
    LocalDate matchDate;

    public Match(String firstTeam, String secondTeam, int firstTeamResult, int secondTeamResult, LocalDate matchDate) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
        this.matchDate = matchDate;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getFirstTeamResult() {
        return firstTeamResult;
    }

    public void setFirstTeamResult(int firstTeamResult) {
        this.firstTeamResult = firstTeamResult;
    }

    public int getSecondTeamResult() {
        return secondTeamResult;
    }

    public void setSecondTeamResult(int secondTeamResult) {
        this.secondTeamResult = secondTeamResult;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Match other = (Match) obj;
//        if (firstTeam != other.firstTeam)
//            return false;
//        if (secondTeam != other.secondTeam)
//            return false;
//        if (firstTeamResult != other.firstTeamResult)
//            return false;
//        if (secondTeamResult != other.secondTeamResult)
//            return false;
//        if (matchDate != other.matchDate)
//            return false;
//        return true;
//    }

    @Override
    public String toString() {
        return "Match{" +
                "firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                ", firstTeamResult=" + firstTeamResult +
                ", secondTeamResult=" + secondTeamResult +
                ", matchDate=" + matchDate +
                '}';
    }
}
