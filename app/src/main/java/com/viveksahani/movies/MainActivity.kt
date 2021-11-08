package com.viveksahani.movies

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),MoviesItemClicked {
    private lateinit var mAdapter:MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            recyclerview.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdapter= MoviesAdapter(this)
        recyclerview.adapter=mAdapter
    }
    private fun fetchData() {
        val url = "https://api.themoviedb.org/3/movie/550?api_key=4a0620921a75acf8e7e615fd3bd54e74"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val moviesJsonArray = it.getJSONArray("articles")
                val moviesArray = ArrayList<Movies>()
                for(i in 0 until moviesJsonArray.length()) {
                    val moviesJsonObject = moviesJsonArray.getJSONObject(i)
                    val movies = Movies(
                        moviesJsonObject.getString("title"),
                        moviesJsonObject.getString("writer"),
                        moviesJsonObject.getString("url"),
                        moviesJsonObject.getString("urlToImage")
                    )
                    moviesArray.add(movies)
                }

               // mAdapter.UpdateMovies(moviesArray)
            },
            Response.ErrorListener {

            }
        )
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

   override fun onItemClicked(item: Movies) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}


