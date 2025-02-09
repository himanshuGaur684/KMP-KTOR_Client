import SwiftUI
import shared

struct ContentView: View {
    
    let greet = Greeting().greet()
    
    @StateObject private var viewModel = IoSMainViewModel()

    var body: some View {
        VStack{
            List{
                ForEach(viewModel.postList,id: \.self.id){item in
                    Text(item.title)
                }
            }
        }.task {
            await viewModel.getPosts()
        }.task {
            await viewModel.postPost(post: Post(body: "ios body", id: 1, title: "ios title", userId: 3))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
