package com.rhino.chronometer


/**
 * Created by alexanderjosefermingomez on 11/21/17.
 */
class Util {
    companion object {
        fun chronometerFormat(millis: Long): String {
            val h = (millis / 3600000)
            val m = (millis - h * 3600000) / 60000
            val s = (millis - h * 3600000 - m * 60000) / 1000
            val ms = (millis - h * 3600000 - m * 60000 - s * 1000)
                    return (if (m < 10) "0" + m else "" + m) + ":" + (if (s < 10) "0" + s else s) +
                            ":" + (if (ms < 100) "0" + ms else if (ms < 10) "00" + ms else ms)
        }
    }
}