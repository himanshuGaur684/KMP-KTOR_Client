package gaur.himanshu.youtube.ktorclient.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gaur.himanshu.youtube.ktorclient.KtorClient
import gaur.himanshu.youtube.ktorclient.MainViewModel
import gaur.himanshu.youtube.ktorclient.model.Post

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel(KtorClient())
        setContent {

            var posts by rememberSaveable { mutableStateOf(emptyList<Post>()) }
            LaunchedEffect(Unit) {
                posts = viewModel.getPosts()
                val post = viewModel.getPost(1)
                Log.d("TAGGGGGGG", "onCreate: ${post}")

                val postPost = viewModel.postPost(
                    Post(
                        body = "this is body",
                        id = 1,
                        title = "This is title",
                        userId = 2
                    )
                )
                Log.d("TAGGGGGGG POST", "onCreate: ${postPost}")

                val putPost = viewModel.putPost(
                    Post(
                        body = "this is put body",
                        id = 1,
                        title = "this is title put",
                        userId = 2
                    )
                )
                Log.d("TAGGGGGGGG", "onCreate PUT: ${putPost}")


                val map = mutableMapOf("title" to "this is patch request")
                val patch = viewModel.patchPost(
                    map,1
                )
                Log.d("TAGGGGGGG", "onCreate PATCH: ${patch}")

                val deletePost = viewModel.deletePost(1)
                Log.d("TAGGGGGGG", "onCreate DELETE: ${deletePost.status.value}")


            }
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainContent(modifier = Modifier.fillMaxSize(), list = posts)

                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, list: List<Post>) {

    LazyColumn(modifier.fillMaxSize()) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = it.id.toString())
                Spacer(Modifier.height(4.dp))
                Text(text = it.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                Text(text = it.body, style = MaterialTheme.typography.bodySmall)

            }
        }
    }

}
