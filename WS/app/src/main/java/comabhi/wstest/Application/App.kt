package comabhi.wstest.Application

import android.app.Application
import io.paperdb.Paper

class App : Application() {

    private val instance : App


    init {

        instance = this
    }
    companion object {
        const val CONSTANT = 12
    }


    override fun onCreate() {
        super.onCreate()
        Paper.init(this)

    }
}