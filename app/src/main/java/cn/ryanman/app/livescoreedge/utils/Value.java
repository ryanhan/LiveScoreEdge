package cn.ryanman.app.livescoreedge.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 2016/3/23.
 */
public class Value {

    public static final int CONNECT_TIMEOUT = 3000;
    public static final int READ_TIMEOUT = 5000;

    public static final String COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE = "android.intent.action.COCKTAIL_LIST_ADAPTER_UPDATE_COMPLETE";
    public static final String COCKTAIL_LIST_ADAPTER_UPDATE_START = "android.intent.action.COCKTAIL_LIST_ADAPTER_UPDATE_START";
    public static final String COCKTAIL_LIST_ADAPTER_UPDATE_FAILED = "android.intent.action.COCKTAIL_LIST_ADAPTER_UPDATE_FAILED";

    public static final String GAMELIST = "gamelist";
    public static final String NBA_URL = "http://op.juhe.cn/onebox/basketball/nba?dtype=json&=&key=bc076a0543bf7aae66c7fbb179b3ec4f";

    public static final Map<String, String> teamName = new HashMap<String, String>() {
        {
            put("老鹰", "atl");
            put("黄蜂", "cha");
            put("热火", "mia");
            put("魔术", "orl");
            put("奇才", "was");

            put("公牛", "chi");
            put("骑士", "cle");
            put("活塞", "det");
            put("步行者", "ind");
            put("雄鹿", "mil");

            put("凯尔特人", "bos");
            put("篮网", "bkn");
            put("尼克斯", "nyk");
            put("猛龙", "tor");
            put("76人", "phi");

            put("勇士", "gsw");
            put("快船", "lac");
            put("湖人", "lal");
            put("太阳", "phx");
            put("国王", "sac");

            put("掘金", "den");
            put("森林狼", "min");
            put("雷霆", "okc");
            put("开拓者", "por");
            put("爵士", "uta");

            put("小牛", "dal");
            put("火箭", "hou");
            put("灰熊", "men");
            put("鹈鹕", "nop");
            put("马刺", "sas");
        }

    };

    public static final Map<String, Integer> teamBgColor = new HashMap<String, Integer>() {
        {
//            put("老鹰", "c21c20");
//            put("黄蜂", "08063e");
//            put("热火", "8b0514");
//            put("魔术", "00379a");
//            put("奇才", "b1000e");
//
//            put("公牛", "b00023");
//            put("骑士", "55061f");
//            put("活塞", "cf002e");
//            put("步行者", "e19a00");
//            put("雄鹿", "002900");
//
//            put("凯尔特人", "00642a");
//            put("篮网", "404040");
//            put("尼克斯", "e14900");
//            put("猛龙", "af0023");
//            put("76人", "004d97");
//
//            put("勇士", "eba809");
//            put("快船", "004d79");
//            put("湖人", "df9b09");
//            put("太阳", "c74202");
//            put("国王", "0a0365");
//
//            put("掘金", "eba809");
//            put("森林狼", "003265");
//            put("雷霆", "0053b0");
//            put("开拓者", "c31c20");
//            put("爵士", "000422");
//
//            put("小牛", "001f88");
//            put("火箭", "a50011");
//            put("灰熊", "001e40");
//            put("鹈鹕", "000d3e");
//            put("马刺", "000000");
            
            put("老鹰", 0xffc21c20);
            put("黄蜂", 0xff08063e);
            put("热火", 0xff8b0514);
            put("魔术", 0xff00379a);
            put("奇才", 0xffb1000e);

            put("公牛", 0xffb00023);
            put("骑士", 0xff55061f);
            put("活塞", 0xffcf002e);
            put("步行者", 0xffe19a00);
            put("雄鹿", 0xff002900);

            put("凯尔特人", 0xff00642a);
            put("篮网", 0xff404040);
            put("尼克斯", 0xffe14900);
            put("猛龙", 0xffaf0023);
            put("76人", 0xff004d97);

            put("勇士", 0xffeba809);
            put("快船", 0xff004d79);
            put("湖人", 0xffdf9b09);
            put("太阳", 0xffc74202);
            put("国王", 0xff0a0365);

            put("掘金", 0xffeba809);
            put("森林狼", 0xff003265);
            put("雷霆", 0xff0053b0);
            put("开拓者", 0xffc31c20);
            put("爵士", 0xff000422);

            put("小牛", 0xff001f88);
            put("火箭", 0xff575757);
            put("灰熊", 0xff001e40);
            put("鹈鹕", 0xff000d3e);
            put("马刺", 0xff000000);

        }

    };
}
