package com.tentwenty.movieticket.feature.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.showtimes.ShowTimesActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView, MainAdapter.ClickListener {
    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        presenter.getData()
    }

    override fun createPresenter() = mainPresenter

    private fun initRecyclerView() {
        rcyclerMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcyclerMain.setHasFixedSize(true)
        rcyclerMain.adapter = mainAdapter
        rcyclerMain.itemAnimator = DefaultItemAnimator()
        rcyclerMain.addItemDecoration(DividerItemDecoration(this,  DividerItemDecoration.VERTICAL_LIST))
        mainAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(position: Int, v: View?) {
        val intent = Intent(this, ShowTimesActivity::class.java)
        intent.putExtra(ShowTimesActivity.BUNDLE_EXTRA_MOVIE_OBJECT,mainAdapter.getItem(position))
        startActivity(intent)
    }

    override fun populateMovies(myList: List<Movie>) {
        mainAdapter.setData(myList)
    }

}
