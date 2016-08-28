package cn.ryanman.app.livescoreedge.utils;

import android.os.AsyncTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.ryanman.app.livescoreedge.listener.OnDataFinishedListener;
import cn.ryanman.app.livescoreedge.model.Game;

/**
 * Created by Ryan on 2016/3/23.
 */
public class GetScoreAsyncTask extends AsyncTask<Void, Integer, List<Game>> {

    private OnDataFinishedListener onDataFinishedListener;

    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }

    @Override
    protected List<Game> doInBackground(Void... params) {

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM-dd");
        try {
            List<Game> gameList = WebUtils.getGames(df.format(date));
            return gameList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Game> result) {
        if (result != null) {
            if (onDataFinishedListener != null) {
                onDataFinishedListener.onDataSuccessfully(result);
            }
        } else {
            if (onDataFinishedListener != null) {
                onDataFinishedListener.onDataFailed();
            }
        }
    }
}
