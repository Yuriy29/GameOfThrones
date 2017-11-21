package gameofthrones.yuriydopa.com.gameofthrones.data.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import gameofthrones.yuriydopa.com.gameofthrones.BuildConfig;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.interceptors.HeaderInterceptor;
import gameofthrones.yuriydopa.com.gameofthrones.utils.AppConfig;
import gameofthrones.yuriydopa.com.gameofthrones.utils.GameOfThronesApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by yuriy on 20.02.17.
 */

public class ServiceGenerator {

    private  static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass ){

        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        HttpLoggingInterceptor login = new HttpLoggingInterceptor();
        login.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(headerInterceptor);
        httpClient.addInterceptor(login);
        httpClient.connectTimeout(AppConfig.MAX_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(AppConfig.MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.cache(new Cache(GameOfThronesApplication.getContext().getCacheDir(), Integer.MAX_VALUE));

        Retrofit retrofit = sBuilder
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
