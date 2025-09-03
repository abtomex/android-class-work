package dom.dima.learn.classworksprint_15.ui.poster

import android.app.Activity
import android.os.Bundle
import dom.dima.learn.classworksprint_15.util.Creator
import dom.dima.learn.classworksprint_15.R

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}