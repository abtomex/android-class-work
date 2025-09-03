package dom.dima.learn.classworksprint_15.data

import dom.dima.learn.classworksprint_15.data.dto.MoviesSearchRequest
import dom.dima.learn.classworksprint_15.data.dto.MoviesSearchResponse
import dom.dima.learn.classworksprint_15.domain.api.MoviesRepository
import dom.dima.learn.classworksprint_15.domain.models.Movie
import dom.dima.learn.classworksprint_15.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}