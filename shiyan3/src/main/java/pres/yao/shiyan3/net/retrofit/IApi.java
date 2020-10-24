package pres.yao.shiyan3.net.retrofit;

import okhttp3.ResponseBody;
import pres.yao.shiyan3.gson.Trash;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IApi {
    @GET("/txapi/lajifenlei/index")
    Call<Trash> get(@Query("key") String key, @Query("word") String word);
}
