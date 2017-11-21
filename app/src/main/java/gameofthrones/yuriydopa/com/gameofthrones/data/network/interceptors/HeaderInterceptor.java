package gameofthrones.yuriydopa.com.gameofthrones.data.network.interceptors;

import java.io.IOException;

import gameofthrones.yuriydopa.com.gameofthrones.data.managers.DataManager;
import gameofthrones.yuriydopa.com.gameofthrones.data.managers.PreferenceManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yuriy on 22.02.17.
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("User-Agent", "DevIntensiveApp")
                .header("Cache-Control", "max-age=" + (60 * 60 * 24));

        return chain.proceed(requestBuilder.build());
    }
}