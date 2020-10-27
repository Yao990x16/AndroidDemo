package pres.yao.shiyan3.net.retrofit;

import pres.yao.shiyan3.entity.TrashBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IApi {
    @GET("/txapi/lajifenlei/index")
    Call<TrashBean> get(@Query("key") String key, @Query("word") String word);
}
