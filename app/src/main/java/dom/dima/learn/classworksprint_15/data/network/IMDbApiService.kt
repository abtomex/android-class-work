package dom.dima.learn.classworksprint_15.data.network

import dom.dima.learn.classworksprint_15.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/YOUR_API_KEY/{expression}") fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}