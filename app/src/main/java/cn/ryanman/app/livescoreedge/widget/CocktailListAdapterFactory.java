
package cn.ryanman.app.livescoreedge.widget;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import cn.ryanman.app.livescoreedge.R;
import cn.ryanman.app.livescoreedge.utils.DBHelper;
import cn.ryanman.app.livescoreedge.utils.Value;

public class CocktailListAdapterFactory implements RemoteViewsFactory {
    static final String TAG = "CocktailListAdapter ";

    private Context mContext;
    private DBHelper mDbHelper;

    public CocktailListAdapterFactory(Context context) {
        Log.d(TAG, "CocktailListAdapterFactory constructor ");
        mContext = context;
        mDbHelper = DBHelper.getInstance();
        //refreshGameList(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDbHelper.getSize();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // TODO Auto-generated method stub
        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.score_list_item);
        try {
            contentView.setTextViewText(R.id.away_score, mDbHelper.getData(position).getAwayScore() + "");
            contentView.setTextViewText(R.id.home_score, mDbHelper.getData(position).getHomeScore() + "");

            int awayImgId = mContext.getResources().getIdentifier(Value.teamName.get(mDbHelper.getData(position).getAwayTeam()) + "_logo", "drawable", mContext.getPackageName());
            int homeImgId = mContext.getResources().getIdentifier(Value.teamName.get(mDbHelper.getData(position).getHomeTeam()) + "_logo", "drawable", mContext.getPackageName());
            contentView.setImageViewResource(R.id.away_logo, awayImgId);
            contentView.setImageViewResource(R.id.home_logo, homeImgId);
            contentView.setInt(R.id.away_layout, "setBackgroundColor", Value.teamBgColor.get(mDbHelper.getData(position).getAwayTeam()));
            contentView.setInt(R.id.home_layout, "setBackgroundColor", Value.teamBgColor.get(mDbHelper.getData(position).getHomeTeam()));

            if (mDbHelper.getData(position).getStatus() == 0) {
                contentView.setTextViewText(R.id.game_status, mDbHelper.getData(position).getTime());
            }
            if (mDbHelper.getData(position).getStatus() == 1) {
                contentView.setTextViewText(R.id.game_status, "Live");
            }
            if (mDbHelper.getData(position).getStatus() == 2) {
                contentView.setTextViewText(R.id.game_status, "Final");
            }

        } catch (IndexOutOfBoundsException e) {

        }
        return contentView;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("LifeCycle", "CocktailListAdapterFactory Call OnCreate.");
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(Value.COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE);
        //filter.addAction(Value.COCKTAIL_LIST_ADAPTER_UPDATE_START);
        //mContext.registerReceiver(changeReceiver, filter);
    }

    @Override
    public void onDataSetChanged() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("LifeCycle", "CocktailListAdapterFactory Call OnDestroy.");
        //mContext.unregisterReceiver(changeReceiver);
    }

}
