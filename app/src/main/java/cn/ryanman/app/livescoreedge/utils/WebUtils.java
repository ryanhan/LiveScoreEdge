package cn.ryanman.app.livescoreedge.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cn.ryanman.app.livescoreedge.model.Game;

/**
 * Created by Ryan on 2016/3/23.
 */
public class WebUtils {

    public static List<Game> getGames(String date) throws Exception {

        List<Game> gameList = new ArrayList<Game>();
        URL url = new URL(Value.NBA_URL);

        String response = GetJson(url);
        if (response != null) {
            JSONObject json = new JSONObject(response);
            JSONArray list = json.getJSONObject("result").getJSONArray("list");
            if (list == null) {
                return null;
            }
            for (int i = 0; i < list.length(); i++) {
                JSONObject day = list.getJSONObject(i);
                String title = day.getString("title");
                if (title.startsWith(date)) {
                    JSONArray tr = day.getJSONArray("tr");
                    if (tr != null) {
                        for (int j = 0; j < tr.length(); j++) {
                            JSONObject gameJson = tr.getJSONObject(j);
                            Game game = new Game();
                            game.setAwayTeam(gameJson.getString("player1"));
                            game.setHomeTeam(gameJson.getString("player2"));
                            String[] dateTime = gameJson.getString("time").split(" ");
                            game.setDate(dateTime[0]);
                            game.setTime(dateTime[1]);
                            int status = gameJson.getInt("status");
                            game.setStatus(status);
                            if (status == 0) {
                                game.setAwayScore(0);
                                game.setHomeScore(0);
                            } else {
                                String score[] = gameJson.getString("score").split("-");
                                game.setAwayScore(Integer.parseInt(score[0]));
                                game.setHomeScore(Integer.parseInt(score[1]));
                            }

                            gameList.add(game);
                        }
                    }
                    break;
                }
            }
        }

        return gameList;
    }

    private static String GetJson(URL url) throws IOException {

        HttpURLConnection urlConn = null;
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.addRequestProperty("accept", "application/json");
            urlConn.setConnectTimeout(Value.CONNECT_TIMEOUT);
            urlConn.setReadTimeout(Value.READ_TIMEOUT);
            int responseCode = urlConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readInputStream(urlConn);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }

    private static String readInputStream(HttpURLConnection urlConn)
            throws IOException {
        Charset charset = Charset.forName("UTF-8");
        InputStreamReader stream = new InputStreamReader(
                urlConn.getInputStream(), charset);
        BufferedReader reader = new BufferedReader(stream);
        StringBuffer responseBuffer = new StringBuffer();
        String read = "";
        while ((read = reader.readLine()) != null) {
            responseBuffer.append(read);
        }
        return responseBuffer.toString();
    }

}