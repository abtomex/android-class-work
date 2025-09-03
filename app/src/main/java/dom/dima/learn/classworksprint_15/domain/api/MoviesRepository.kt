package dom.dima.learn.classworksprint_15.domain.api

import dom.dima.learn.classworksprint_15.domain.models.Movie
import dom.dima.learn.classworksprint_15.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}