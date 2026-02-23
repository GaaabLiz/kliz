package io.github.gaaabliz.kliz.jvm.util

import java.awt.Desktop
import java.net.URL

class BrowserUtil {
    companion object {

        fun searchWithGoogle(p:String) {
            var baseurl:String = "https://www.google.it/search?q="
            Desktop.getDesktop().browse(URL(baseurl.plus(p.replace(" ", "+"))).toURI());
        }

        fun openIMDbPage(p:String) {
            var baseurl:String = "https://www.imdb.com/title/"
            Desktop.getDesktop().browse(URL(baseurl.plus(p.replace(" ", "+")).plus("/")).toURI());
        }

        fun searchTrailerOnYouTube(p:String) {
            var baseurl:String = "https://www.youtube.com/results?search_query="
            var textMovie:String = p.replace(" ", "+")
            var endUrl:String = "+trailer+ita"
            Desktop.getDesktop().browse(URL(baseurl.plus(textMovie).plus(endUrl)).toURI());
        }

    }
}