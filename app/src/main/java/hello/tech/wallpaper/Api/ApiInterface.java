package hello.tech.wallpaper.Api;

import static hello.tech.wallpaper.Api.Utilitis.ApiKey;

import hello.tech.wallpaper.Modals.PhotosModal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://api.pexels.com/v1/";

    @Headers("Authorization: " + ApiKey)
    @GET("curated")
    Call<PhotosModal> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page);


    @Headers("Authorization: " + ApiKey)
    @GET("search")
    Call<PhotosModal> getSearchImage(@Query("query") String query,
                                     @Query("page") int page,
                                     @Query("per_page") int per_page);


}
