//
//  IoSMainViewModel.swift
//  iosApp
//
//  Created by Himanshu Gaur on 09/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

class IoSMainViewModel : ObservableObject{
    
    @Published var postList : [Post] = []
    
    @Published var post : Post?
    
    let viewModel = MainViewModel(ktorClient: KtorClient())
    
    
    func postPost(post:Post) async{
        do{
            let post = try await viewModel.postPost(post: post)
            print(post)
            DispatchQueue.main.async{
                self.post = post
            }
        }catch{
            print(error)
        }
    }
    
    func getPosts() async {
        do{
           let list = try await  viewModel.getPosts()
            print(list)
            DispatchQueue.main.async {
                self.postList = list
            }
        }catch{
            print(error)
        }
    }
}

