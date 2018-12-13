package academy.learnprogramming.top10downloader

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.net.URL
import kotlin.properties.Delegates


class FeedEntry {
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var summary: String = ""
    var imageURL: String = ""

    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            releaseDate = $releaseDate
            imageURL = $imageURL
        """.trimIndent()
    }
}

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var textView: TextView? = null
    private var downloadData: DownloadData? = null
    private var feedUrl: String =
        "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
    private var feedLimit = 10
    private var feedCachedUrl = "INVALIDATED"
    private val STATE_URL = "feedUrl"
    private val STATE_LIMIT = "feedLimit"


    override fun onStart() {
        Log.d(TAG, "OnStart: Called")
        super.onStart()
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        Log.d(TAG, "onRestoreInstanceState: Called")
//        super.onRestoreInstanceState(savedInstanceState)
//        //   val savedString = savedInstancetState?.getString(TEXT_CONTENTS, "")
//        //   textView?.text = savedString
//        textView?.text = savedInstanceState?.getString(TEXT_CONTENTS, "")



    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Oncreate: Called")
        super.onPause()
    }



    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart: Called")
        super.onRestart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called")

        if (savedInstanceState != null) {
            feedUrl = savedInstanceState.getString(STATE_URL)
            feedLimit = savedInstanceState.getInt(STATE_LIMIT)
        }

        downloadUrl(feedUrl.format(feedLimit))
        Log.d(TAG, "onCreate: done")
    }

    private fun downloadUrl(feedUrl: String) {
        if (feedUrl != feedCachedUrl) {
            Log.d(TAG, "downloadUrl starting AsyncTask")
            downloadData = DownloadData(this, xmlListView)
            downloadData?.execute(feedUrl)
            feedCachedUrl = feedUrl
            Log.d(TAG, "downloadUrl done")
        } else {
            Log.d(TAG, "downloadUrl - URL not changed")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)

        if (feedLimit == 10) {
            menu?.findItem(R.id.mnu10)?.isChecked = true
        } else {
            menu?.findItem(R.id.mnu25)?.isChecked = true
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.mnuFree ->
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
            R.id.mnuPaid ->
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml"
            R.id.mnuSongs ->
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
            R.id.mnu10, R.id.mnu25 -> {
                if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 35 - feedLimit
                    Log.d(TAG, "onOptionsItemSelected: ${item.title} setting feedLimit to $feedLimit")
                } else {
                    Log.d(TAG, "onOptionsItemSelected: ${item.title} feedLimit unchanged")
                }
            }
            R.id.mnuRefresh -> feedCachedUrl = "INVALIDATE"
            else ->
                return super.onOptionsItemSelected(item)
        }
        downloadUrl(feedUrl.format(feedLimit))
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState: Called")
        super.onSaveInstanceState(outState)
        outState.putString(STATE_URL, feedUrl)
        outState.putInt(STATE_LIMIT,feedLimit)
    }
    override fun onDestroy() {
        super.onDestroy()
        downloadData?.cancel(true)
    }

    companion object {
        private class DownloadData(context: Context, listView: ListView) : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"

            var propContext : Context by Delegates.notNull()
            var propListView : ListView by Delegates.notNull()

            init {
                propContext = context
                propListView = listView
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                // Log.d(TAG, "onPostExecute: parameter is $result")
                val parseApplications = ParseApplications()
                parseApplications.parse(result)

                val feedAdapter = FeedAdapter(propContext, R.layout.list_record, parseApplications.applications)
                propListView.adapter = feedAdapter
            }

            override fun doInBackground(vararg url: String?): String {
//                Log.d(TAG, "doInBackground: starts with ${url[0]}")
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: Error downloading")
                }
                return rssFeed
            }

            private fun downloadXML(urlPath: String?): String {
                return URL(urlPath).readText()
            }
//                        val xmlResult = StringBuilder()
//
//                        try {
//                            val url = URL(urlPath)
//                            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
//                            val response = connection.responseCode
//                            Log.d(TAG, "downloadXML: The response code was $response")
//
////                            val reader = BufferedReader(InputStreamReader(connection.inputStream))
////                            val inputBuffer = CharArray(500)
////                            var charsRead = 0
////                            while (charsRead >= 0) {
////                                charsRead = reader.read(inputBuffer)
////                                if (charsRead > 0) {
////                                    xmlResult.append(String(inputBuffer, 0, charsRead))
////                                }
////                            }
////                            reader.close()
////                            val stream = connection.inputStream
//                            connection.inputStream.buffered().reader().use {
//                                xmlResult.append(it.readText())
//                            }
//                            Log.d(TAG, "Received ${xmlResult.length} bytes")
//                            return xmlResult.toString()
//
////                        } catch (e: MalformedURLException){
////                            Log.e(TAG, "downloadXML: Invalid URL ${e.message}")
////                        } catch (e: IOException) {
////                            Log.e(TAG, "downloadXML: Io Exception reading data: ${e.message}")
////                        } catch (e: SecurityException) {
////                            e.printStackTrace()
////                            Log.e(TAG, "download XML: Security Exception Needs permissions ${e.message}")
////                        } catch (e: Exception) {
////                            Log.e(TAG, "Unknown error: ${e.message}")
////                        }
//                        }catch (e: Exception) {
//                            val errorMessage: String = when (e) {
//                                is MalformedURLException -> "downloadXML: Invalid URL ${e.message}"
//                                is IOException -> "downloadXML: IO Exception reading data: ${e.message}"
//                                is SecurityException -> {
//                                    e.printStackTrace()
//                                    "downloadXML: Security Exception Needs permissions? ${e.message}"
//                                }
//                                else -> "Unknown error: ${e.message}"
//                            }
//
//                        }
//                        return "" // If it gets to here, there's been a problem. Return an empty string.
//                    }
//                }
        }
    }
}
