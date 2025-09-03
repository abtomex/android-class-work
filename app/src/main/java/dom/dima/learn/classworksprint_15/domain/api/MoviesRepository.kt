package dom.dima.learn.classworksprint_15.domain.api

import dom.dima.learn.classworksprint_15.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}