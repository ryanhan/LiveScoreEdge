package cn.ryanman.app.livescoreedge.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailProvider;

import java.util.ArrayList;

import cn.ryanman.app.livescoreedge.R;
import cn.ryanman.app.livescoreedge.listener.OnDataFinishedListener;
import cn.ryanman.app.livescoreedge.model.Game;
import cn.ryanman.app.livescoreedge.utils.GetScoreAsyncTask;
import cn.ryanman.app.livescoreedge.utils.Value;

/**
 * Created by ryan on 16/3/23.
 */
public class EdgeSinglePlusProvider extends SlookCocktailProvider {

    @Override
    public void onVisibilityChanged(final Context context, int cocktailId, int visibility) {
        super.onVisibilityChanged(context, cocktailId, visibility);
        if (visibility == SlookCocktailManager.COCKTAIL_VISIBILITY_SHOW) {
            Log.d("LifeCycle", "EdgeSinglePlusProvider Call onVisibilityChanged.");
            refreshGameList(context);
        }
    }

    @Override
    public void onUpdate(Context context, final SlookCocktailManager cocktailManager, final int[] cocktailIds) {
        Log.d("LifeCycle", "EdgeSinglePlusProvider Call OnUpdate.");

        Intent intent = new Intent(context, CocktailListAdapterService.class);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.score_edge_single_plus);
        rv.setRemoteAdapter(R.id.game_list, intent);
        refreshGameList(context);

        for (int i = 0; i < cocktailIds.length; i++) {
            cocktailManager.updateCocktail(cocktailIds[i], rv);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    private void refreshGameList(final Context context) {
        Intent intent = new Intent();
        intent.setAction(Value.COCKTAIL_LIST_ADAPTER_UPDATE_START);
        context.sendBroadcast(intent);
        Log.d("BroadCast", "COCKTAIL_LIST_ADAPTER_UPDATE_START Sent.");


        GetScoreAsyncTask task = new GetScoreAsyncTask();
        task.setOnDataFinishedListener(new OnDataFinishedListener() {
            @Override
            public void onDataSuccessfully(Object data) {
                ArrayList<Game> gameList = (ArrayList<Game>) data;
                Log.d("gameList", "Size = " + gameList.size());
                Intent intent = new Intent();
                intent.setAction(Value.COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE);
                intent.putParcelableArrayListExtra(Value.GAMELIST, gameList);
                context.sendBroadcast(intent);
                Log.d("BroadCast", "COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE Sent.");
            }

            @Override
            public void onDataFailed() {
                Log.d("result", "Failed");
                Intent intent = new Intent();
                intent.setAction(Value.COCKTAIL_LIST_ADAPTER_UPDATE_FAILED);
                context.sendBroadcast(intent);
                Log.d("BroadCast", "COCKTAIL_LIST_ADAPTER_UPDATE_FAILED Sent.");
            }
        });
        task.execute();
    }


}
