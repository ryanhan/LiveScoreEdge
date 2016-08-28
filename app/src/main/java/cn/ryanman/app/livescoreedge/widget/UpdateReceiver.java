package cn.ryanman.app.livescoreedge.widget;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;

import java.util.ArrayList;

import cn.ryanman.app.livescoreedge.R;
import cn.ryanman.app.livescoreedge.model.Game;
import cn.ryanman.app.livescoreedge.utils.DBHelper;
import cn.ryanman.app.livescoreedge.utils.Value;

/**
 * Created by Ryan on 2016/3/30.
 */
public class UpdateReceiver extends BroadcastReceiver {

    private DBHelper mDbHelper;

    public UpdateReceiver() {
        mDbHelper = DBHelper.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        SlookCocktailManager mgr = SlookCocktailManager.getInstance(context);
        int[] cocktailIds = mgr.getCocktailIds(new ComponentName(context,
                EdgeSinglePlusProvider.class));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.score_edge_single_plus);

        if (action.equals(Value.COCKTAIL_LIST_ADAPTER_UPDATE_START)) {
            Log.d("BroadcastReceiver", "Receive COCKTAIL_LIST_ADAPTER_UPDATE_START");
            rv.setViewVisibility(R.id.loading_layout, View.VISIBLE);
            for (int i = 0; i < cocktailIds.length; i++) {
                mgr.updateCocktail(cocktailIds[i], rv);
            }
        } else if (action.equals(Value.COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE)) {
            Log.d("BroadcastReceiver", "Receive COCKTAIL_LIST_ADAPTER_COMPLETE_ACTION");

            ArrayList<Game> gameList = intent.getParcelableArrayListExtra(Value.GAMELIST);

            mDbHelper.clearData();
            for (Game game : gameList) {
                mDbHelper.addData(game);
            }

            for (int i = 0; i < cocktailIds.length; i++) {
                mgr.notifyCocktailViewDataChanged(cocktailIds[i], R.id.game_list);
            }
            rv.setViewVisibility(R.id.loading_layout, View.INVISIBLE);
            for (int i = 0; i < cocktailIds.length; i++) {
                mgr.updateCocktail(cocktailIds[i], rv);
            }
        } else if (action.equals(Value.COCKTAIL_LIST_ADAPTER_UPDATE_FAILED)) {
            Log.d("BroadcastReceiver", "Receive COCKTAIL_LIST_ADAPTER_UPDATE_FAILED_ACTION");

            mDbHelper.clearData();

            for (int i = 0; i < cocktailIds.length; i++) {
                mgr.notifyCocktailViewDataChanged(cocktailIds[i], R.id.game_list);
            }

            rv.setViewVisibility(R.id.loading_layout, View.INVISIBLE);
            for (int i = 0; i < cocktailIds.length; i++) {
                mgr.updateCocktail(cocktailIds[i], rv);
            }
        }
    }
}
