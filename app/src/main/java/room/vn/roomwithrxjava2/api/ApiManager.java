package room.vn.roomwithrxjava2.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static Retrofit sRetrofit;

    public static <T> T createApiService(Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }

    private static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            synchronized (ApiManager.class) {
                if (sRetrofit == null) {
                    sRetrofit = init();
                }
            }
        }
        return sRetrofit;
    }

    private static final int TIME_OUT = 15;

    private static Retrofit init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(logging)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .client(httpBuilder.build())
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }
}
