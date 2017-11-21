package gameofthrones.yuriydopa.com.gameofthrones.data.network;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

/**
 * Created by yuriy on 20.02.17.
 */

public class PicassoCache {
    private Context mContext;
    private Picasso mPicassonInstance;

    public PicassoCache(Context mContext) {
        this.mContext = mContext;
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(mContext, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(okHttp3Downloader);

        mPicassonInstance = builder.build();
        Picasso.setSingletonInstance(mPicassonInstance);
    }

    public  Picasso getPicassoInstance(){
        if(mPicassonInstance == null){
            new PicassoCache(mContext);
            return mPicassonInstance;
        }
        return mPicassonInstance;
    }
}

