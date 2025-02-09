package gaur.himanshu.youtube.ktorclient

import gaur.himanshu.youtube.ktorclient.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {

    fun getClient():HttpClient{
        return HttpClient {

            install(ContentNegotiation){
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout){
                socketTimeoutMillis = 3000
                connectTimeoutMillis = 3000
                requestTimeoutMillis = 3000
            }

            install(DefaultRequest){
                url {
                    host = "jsonplaceholder.typicode.com"
                    protocol = URLProtocol.HTTPS
                     headers {
                        append(HttpHeaders.Authorization,"fkasjflkasjfasklfd")
                    }
                    contentType(ContentType.Application.Json)
                }
            }

        }
    }
    // https://jsonplaceholder.typicode.com/posts/1

    suspend fun getPosts():List<Post>{
       return getClient()
            .get("/posts")
            .body<List<Post>>()
    }

    suspend fun getPost(id:Int) : Post{
       return getClient()
            .get {
                url {
                    path("/posts/$id")
                }
            }.body<Post>()
    }

    suspend fun postPost(post:Post):Post{
       return getClient()
            .post {
                url {
                    path("/posts")
                }
                contentType(ContentType.Application.Json)
                setBody(post)
            }.body<Post>()
    }

    suspend fun putPost(id:Int,post:Post) : Post{
        return getClient()
            .put {
                url {
                    path("/posts/${id}")
                }
                setBody(post)
                contentType(ContentType.Application.Json)
            }
            .body<Post>()
    }

    suspend fun patchPost(map:MutableMap<String,String>,id:Int):Post{
        return getClient()
            .patch {
                url {
                    path("/posts/${id}")
                }
                contentType(ContentType.Application.Json)
                setBody(map)
            }.body<Post>()
    }


    suspend fun deletePost(id:Int):HttpResponse{
        return getClient()
            .delete {
                url {
                    path("/posts/${id}")
                }
            }
    }


}