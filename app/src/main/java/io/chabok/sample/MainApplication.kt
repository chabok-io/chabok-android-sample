package io.chabok.sample

import android.app.Application
import android.widget.Toast
import io.chabok.Callback
import io.chabok.Chabok
import io.chabok.utils.log.LogLevel

class MainApplication : Application() {

    var chabokClientId = "61d1689cn784235f2c955b7a"
    var chabokClientSecret = "4uQAkADYK3GHfIhf8934hf8934hfHWYjfIk1Ejp"

    override fun onCreate() {
        super.onCreate()

        Chabok.disableSdk(false)
        Chabok.setLogLevel(LogLevel.VERBOSE)
        Chabok.lockLogging(false)

        Chabok.initialize(chabokClientId, chabokClientSecret, object : Callback {
            override fun onResponse(success: Boolean, message: String?) {
                Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
//                MainActivity.showInitState(success)
            }
        })
    }
}