package dom.dima.learn.classworksprint_15

import dom.dima.learn.classworksprint_15.data.MoviesRepositoryImpl
import dom.dima.learn.classworksprint_15.data.network.RetrofitNetworkClient
import dom.dima.learn.classworksprint_15.domain.api.MoviesInteractor
import dom.dima.learn.classworksprint_15.domain.api.MoviesRepository
import dom.dima.learn.classworksprint_15.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}