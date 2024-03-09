package com.noxis.daggerexample.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.noxis.daggerexample.R
import com.noxis.daggerexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    private var viewModelPosts: PostsViewModel? = null
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

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
    }

    companion object {
        private const val TAG = "PostsFragment"
    }
}