package comabhi.wstest.Utility

import android.content.Context
import android.net.ConnectivityManager

class CommonFunctions {

    companion object{
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }

    }
}