package cn.ryanman.app.livescoreedge.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ryan on 2016/3/23.
 */
public class Game implements Parcelable {

    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String date;
    private String time;
    private int status;

    public Game(){}

    protected Game(Parcel in) {
        homeTeam = in.readString();
        awayTeam = in.readString();
        homeScore = in.readInt();
        awayScore = in.readInt();
        date = in.readString();
        time = in.readString();
        status = in.readInt();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homeTeam);
        dest.writeString(awayTeam);
        dest.writeInt(homeScore);
        dest.writeInt(awayScore);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(status);
    }

}
