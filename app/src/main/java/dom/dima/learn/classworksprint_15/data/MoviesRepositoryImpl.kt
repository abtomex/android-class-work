package dom.dima.learn.classworksprint_15.data

import dom.dima.learn.classworksprint_15.data.dto.MoviesSearchRequest
import dom.dima.learn.classworksprint_15.data.dto.MoviesSearchResponse
import dom.dima.learn.classworksprint_15.domain.api.MoviesRepository
import dom.dima.learn.classworksprint_15.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): List<Movie> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        if (response.resultCode == 200) {
            return (response as MoviesSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description) }
        } else {
            return emptyList()
        }
    }
}