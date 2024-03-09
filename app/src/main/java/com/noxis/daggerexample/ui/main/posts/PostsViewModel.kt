package com.noxis.daggerexample.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.noxis.daggerexample.SessionManager
import com.noxis.daggerexample.models.Post
import com.noxis.daggerexample.repository.main.MainApi
import com.noxis.daggerexample.until.PostsResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    private var posts: MediatorLiveData<PostsResource<List<Post>>> = MediatorLiveData()

    fun observerPosts(): LiveData<PostsResource<List<Post>>> {
        if (posts.value?.data.isNullOrEmpty()) {
            posts.value = PostsResource.Loading()
            sessionManager.getAuthUser().value?.data?.id?.let { id ->
                mainApi.getPostsFromUser(id)
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { data ->
                            posts.value = data?.let {
                                PostsResource.Success(data)
                            } ?: PostsResource.Error("Posts not found")
                        },
                        {
                            posts.value = PostsResource.Error("Something went wrong")
                        }
                    )
            }
        }
        return posts
    }

    companion object {
        private const val TAG = "PostsViewModel"
    }
}