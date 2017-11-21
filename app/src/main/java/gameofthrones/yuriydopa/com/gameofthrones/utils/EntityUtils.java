package gameofthrones.yuriydopa.com.gameofthrones.utils;

/**
 * Created by yuriy on 19.02.17.
 */

public class EntityUtils {

    public static String cutIdFromUrl(String url) {
        String[] s = url.split("/");
        return s[s.length - 1];
    }
}
