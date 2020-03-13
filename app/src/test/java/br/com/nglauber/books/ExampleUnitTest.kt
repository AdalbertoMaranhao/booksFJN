package br.com.nglauber.books

import android.app.SearchManager
import br.com.nglauber.books.http.BookHttp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun searchIsComplete() {
        val query = SearchManager.QUERY
        val searchResult = BookHttp.searchBook(query)
        searchResult?.items?.forEach { volume ->
            println(volume.volumeInfo.title)
        }
    }
}
