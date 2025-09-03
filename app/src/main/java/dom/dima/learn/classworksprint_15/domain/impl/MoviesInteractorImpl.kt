package dom.dima.learn.classworksprint_15.domain.impl

import dom.dima.learn.classworksprint_15.domain.api.MoviesInteractor
import dom.dima.learn.classworksprint_15.domain.api.MoviesRepository
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            consumer.consume(repository.searchMovies(expression))
        }
    }
}