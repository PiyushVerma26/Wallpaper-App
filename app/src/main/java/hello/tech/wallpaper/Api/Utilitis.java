package hello.tech.wallpaper.Api;

import static hello.tech.wallpaper.Api.ApiInterface.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilitis {

    public static final String ApiKey = "563492ad6f917000010000010ed8cfda168b4eddb4eb275f1c947e5c";
    private static Retrofit retrofit = null;

    public static ApiInterface getApiInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
