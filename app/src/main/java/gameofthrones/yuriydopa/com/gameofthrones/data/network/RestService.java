package gameofthrones.yuriydopa.com.gameofthrones.data.network;

import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.CharactertResp;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.HouseListResp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuriy on 19.02.17.
 */

public interface RestService {

    @GET("houses/{houseId}")
    Call<HouseListResp> getHose(@Path("houseId")String houseId);

    @GET("characters/{memberId}")
    Call<CharactertResp> getCharacters(@Path("memberId")String memberId);
}
