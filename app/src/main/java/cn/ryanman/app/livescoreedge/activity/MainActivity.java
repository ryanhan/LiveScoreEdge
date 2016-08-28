package cn.ryanman.app.livescoreedge.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.look.Slook;

import cn.ryanman.app.livescoreedge.R;

/**
 * Created by ryan on 16/3/23.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Slook slook = new Slook();
        TextView info = (TextView) findViewById(R.id.information);
        try {
            slook.initialize(this);
            if (slook.isFeatureEnabled(Slook.COCKTAIL_PANEL)) {
                info.setText("Support COCKTAIL_PANEL");
            }
        } catch (SsdkUnsupportedException e) {
            info.setText(e.toString());
            return;
        }

    }
}
