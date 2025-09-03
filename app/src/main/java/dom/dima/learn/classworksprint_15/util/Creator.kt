package dom.dima.learn.classworksprint_15.util

import android.app.Activity
import android.content.Context
import dom.dima.learn.classworksprint_15.data.MoviesRepositoryImpl
import dom.dima.learn.classworksprint_15.data.network.RetrofitNetworkClient
import dom.dima.learn.classworksprint_15.domain.api.MoviesInteractor
import dom.dima.learn.classworksprint_15.domain.api.MoviesRepository
import dom.dima.learn.classworksprint_15.domain.impl.MoviesInteractorImpl
import dom.dima.learn.classworksprint_15.presentation.MoviesSearchController
import dom.dima.learn.classworksprint_15.presentation.PosterController
import dom.dima.learn.classworksprint_15.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}