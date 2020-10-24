package pres.yao.shiyan3.net.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName RetrofitImpl
 * @Description TOOD
 * Date 2020/10/24 10:36
 **/
public class RetrofitImpl {
    //单例模式
    private static final RetrofitImpl sInstance = new RetrofitImpl();

    public static RetrofitImpl getInstance(){
        return sInstance;
    }

    private Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        return sInstance.mRetrofit;
    }

    private RetrofitImpl(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.tianapi.com/")//网络请求url地址
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())//数据解析器
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
