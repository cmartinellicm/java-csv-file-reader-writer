package com.letscode.app.utils;

public class Team {
    String name;
    int victories;
    int draws;
    int defeats;
    int totalPoints;

    public Team(String name) {
        this.name = name;
        this.victories = 0;
        this.draws = 0;
        this.defeats = 0;
        this.totalPoints = getTotalPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    private int getTotalPoints() {
        return this.victories + this.draws + this.totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return com.google.common.base.Objects.equal(name, team.name);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name);
    }
}
