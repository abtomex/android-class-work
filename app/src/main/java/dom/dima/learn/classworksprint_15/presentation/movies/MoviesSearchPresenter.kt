package dom.dima.learn.classworksprint_15.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dom.dima.learn.classworksprint_15.R
import dom.dima.learn.classworksprint_15.domain.api.MoviesInteractor
import dom.dima.learn.classworksprint_15.domain.models.Movie
import dom.dima.learn.classworksprint_15.ui.movies.MoviesAdapter
import dom.dima.learn.classworksprint_15.util.Creator

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
    private val adapter: MoviesAdapter,
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val movies = ArrayList<Movie>()
    private val handler = Handler(Looper.getMainLooper())

    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun onCreate() {
        adapter.movies = movies
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        view.showProgressBar(false)
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                            adapter.notifyDataSetChanged()
                            view.showMoviesList(true)
                        }
                        if (errorMessage != null) {
                            // Поменяли view на Context
                            showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                        } else if (movies.isEmpty()) {
                            // И здесь поменяли view на Context
                            showMessage(context.getString(R.string.nothing_found), "")
                        } else {
                            hideMessage()
                        }
                    }
                }
            })
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            adapter.notifyDataSetChanged()
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                // Поменяли view на Context
                Toast.makeText(context, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        // Заменили работу с элементами UI на
        // вызовы методов интерфейса
        view.showPlaceholderMessage(false)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

}