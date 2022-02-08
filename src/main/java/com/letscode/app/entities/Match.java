package com.letscode.app.entities;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return firstTeamResult == match.firstTeamResult && secondTeamResult == match.secondTeamResult && Objects.equal(firstTeam, match.firstTeam) && Objects.equal(secondTeam, match.secondTeam) && Objects.equal(matchDate, match.matchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstTeam, secondTeam, firstTeamResult, secondTeamResult, matchDate);
    }

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
