package com.noxis.daggerexample.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noxis.daggerexample.R
import com.noxis.daggerexample.until.Status
import com.noxis.daggerexample.until.VerticalSpaceItemDecoration
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private var viewModelPosts: PostsViewModel? = null
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModelPosts = ViewModelProvider(this, providerFactory)[PostsViewModel::class.java]
        recyclerView = view.findViewById(R.id.recycler_view)
        initRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModelPosts?.observerPosts()?.removeObservers(viewLifecycleOwner)
        viewModelPosts?.observerPosts()?.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(TAG, "PostFragment: LOADING...")
                }

                Status.SUCCESS -> {
                    it.data?.let { posts ->
                        adapter.setPosts(posts)
                    }
                }

                Status.ERROR -> {
                    Log.d(TAG, "PostFragment: ERROR... ${it.message}")
                }
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.addItemDecoration(VerticalSpaceItemDecoration(15))
        recyclerView?.adapter = adapter
    }

    companion object {
        private const val TAG = "PostsFragment"
    }
}