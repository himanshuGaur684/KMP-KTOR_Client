package gaur.himanshu.youtube.ktorclient

import androidx.lifecycle.ViewModel
import gaur.himanshu.youtube.ktorclient.model.Post

class MainViewModel (
    private val ktorClient: KtorClient
): ViewModel() {

    suspend fun getPosts():List<Post> = ktorClient.getPosts()

    suspend fun getPost(id:Int) = ktorClient.getPost(id)

    suspend fun postPost(post:Post) = ktorClient.postPost(post)

    suspend fun putPost(post:Post) = ktorClient.putPost(post.id,post)

    suspend fun patchPost(map:MutableMap<String,String>,id:Int) = ktorClient.patchPost(map,id)

    suspend fun deletePost(id:Int) = ktorClient.deletePost(id)

}