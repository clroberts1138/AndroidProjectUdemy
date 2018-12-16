package academy.learnprogramming.top10downloader

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.ListView
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

private const val TAG = "DownloadData"

class DownloadData(private val callBack: DownloaderCallBack) : AsyncTask<String, Void, String>() {

    interface DownloaderCallBack {
        fun onDataAvailable(data: List<FeedEntry>)
    }

    override fun onPostExecute(result: String) {

        val parseApplications = ParseApplications()
        if (result.isNotEmpty()) {
            parseApplications.parse(result)
        }
        callBack.onDataAvailable((parseApplications.applications))
    }

    override fun doInBackground(vararg url: String): String {
        Log.d(TAG, "doInBackground: starts with ${url[0]}")
        val rssFeed = downloadXML(url[0])
        if (rssFeed.isEmpty()) {
            Log.e(TAG, "doInBackground: Error downloading")
        }
        return rssFeed
    }

    private fun downloadXML(urlPath: String): String {
        try {
            return URL(urlPath).readText()
        } catch (e: MalformedURLException) {
            Log.d(TAG, "downloadXML: Invalud URL" + e.message)
        } catch (e: IOException) {
            Log.d(TAG, "downloadXML: Io Exception reading data" + e.message)
        } catch (e: SecurityException) {
            Log.d(TAG, "downloadXML: Security exception. Needs permission? " + e.message)
            e.printStackTrace()
        }

        return ""              // Return an empty string if there was an exception
    }
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

