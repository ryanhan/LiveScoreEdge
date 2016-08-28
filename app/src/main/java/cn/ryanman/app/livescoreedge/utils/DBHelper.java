
package cn.ryanman.app.livescoreedge.utils;

import java.util.ArrayList;

import cn.ryanman.app.livescoreedge.model.Game;

public class DBHelper {

    private ArrayList<Game> mDatalist = new ArrayList<Game>();

    private static DBHelper sInstance = new DBHelper();

    private Object mLock = new Object();

    public static DBHelper getInstance() {
        return sInstance;
    }

    public void addData(Game game) {
        synchronized (mLock) {
            mDatalist.add(game);
        }
    }

    public Game getData(int index) {
        synchronized (mLock) {
            try {
                return mDatalist.get(index);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
    }

    public int getSize() {
        synchronized (mLock) {
            return mDatalist.size();
        }
    }

    public void clearData() {
        synchronized (mLock) {
            mDatalist.clear();
        }
    }
}
