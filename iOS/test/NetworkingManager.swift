//
//  NetworkingManager.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class NetworkingManager {

    static let shared = NetworkingManager()
    private init() { }

    // Properties
    let baseURL = "http://178.62.238.65:8841/"

    func logIn(withLogin login: String, andPassword password: String, completion: (()->())?) {
        print("kek")

        var request = URLRequest(url: URL(string: baseURL + "login/")!)
        request.httpMethod = "POST"
        request.setValue(UserDefaultsManager.shared.getToken(), forHTTPHeaderField: "UserToken")
        let dictionary: [String: Any] = ["email": login,
                                         "password": password]
        do {
            let bodyData = try JSONSerialization.data(withJSONObject: dictionary, options: .prettyPrinted)
            request.httpBody = bodyData
            request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        } catch {
            print("EBAT888111", error)
        }

        URLSession.shared.dataTask(with: request) { (data, response, error) in

            print("DATA:")
            print(data)
            print(response)
            print(error)

            do {
                let responseDictionary = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as? [String: Any]
                print(responseDictionary ?? "NO RESPONSE")
                if let token = responseDictionary?["token"] as? String {
                    UserDefaultsManager.shared.setToken(token)
                    DispatchQueue.main.async {
                        if completion != nil {
                            completion!()
                        }
                    }

                }
            } catch {
                print("Error serializing", error)
            }

        }.resume()

    }

    func register(userWithName name: String, withLogin login: String, andPassword password: String, completion: (()->())?) {

        var request = URLRequest(url: URL(string: baseURL + "register/")!)
        request.httpMethod = "POST"
        request.setValue(UserDefaultsManager.shared.getToken(), forHTTPHeaderField: "UserToken")
        let dictionary: [String: Any] = ["name": name,
                                         "email": login,
                                         "password": password]
        do {
            let bodyData = try JSONSerialization.data(withJSONObject: dictionary, options: .prettyPrinted)
            request.httpBody = bodyData
            request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        } catch {
            print("EBAT888111", error)
        }

        URLSession.shared.dataTask(with: request) { (data, response, error) in

            print("DATA:")
            print(data)
            print(response)
            print(error)

            do {
                let responseDictionary = try JSONSerialization.jsonObject(with: data!, options: .allowFragments)
                print(responseDictionary)
            } catch {
                print("Error serializing", error)
            }

            NetworkingManager.shared.logIn(withLogin: login, andPassword: password, completion: completion)
            print(UserDefaultsManager.shared.getToken() ?? " ERROR: NO TOKEN")

        }.resume()
        
    }

    func getPosts(forUserWithID: Int, inClass: TimelineViewController) {

        var request = URLRequest(url: URL(string: baseURL + "api/timeline/")!)
        request.httpMethod = "GET"
        request.setValue(UserDefaultsManager.shared.getToken(), forHTTPHeaderField: "UserToken")
        request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")

        URLSession.shared.dataTask(with: request) { (data, response, error) in

            print("DATA:")
            print(data)
            print(response)
            print(error)

            do {
                let responseDictionaryArr = try JSONSerialization.jsonObject(with: data!, options: .allowFragments)
                print(responseDictionaryArr)

                let data = responseDictionaryArr as! Array<[String: Any]>

                var arrOfPostData = [PostData]()

                for entry in data {
                    let titleString = entry["title"] as! String
                    let descriptionString = entry["description"] as! String
                    let project_id = entry["project_id"] as! Int
                    let timestamp = entry["timestamp"] as! Double
                    let urls = entry["photo_url"] as! String
                    let newPostData = PostData(title: titleString, description: descriptionString, photoURLs: [urls], date: Date(timeIntervalSince1970: timestamp))
                    arrOfPostData.append(newPostData)
                }

                inClass.posts = arrOfPostData

            } catch {
                print("Error serializing", error)
            }
            
        }.resume()

    }

    func postPost(withData data: PostData) {

        var request = URLRequest(url: URL(string: baseURL + "api/tasks/add/")!)
        request.httpMethod = "POST"
        request.setValue(UserDefaultsManager.shared.getToken(), forHTTPHeaderField: "UserToken")
        let dictionary: [String: Any] = ["title": data.title,
                                         "description": data.description,
                                         "photo_url": data.photoURLs?.first,
                                         "stamp": data.date.timeIntervalSince1970]
        do {
            let bodyData = try JSONSerialization.data(withJSONObject: dictionary, options: .prettyPrinted)
            request.httpBody = bodyData
            request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        } catch {
            print("EBAT888111", error)
        }

        URLSession.shared.dataTask(with: request) { (data, response, error) in

            print("DATA:")
            print(data)
            print(response)
            print(error)

            do {
                let responseDictionary = try JSONSerialization.jsonObject(with: data!, options: .allowFragments)
                print(responseDictionary)
            } catch {
                print("Error serializing", error)
            }

        }.resume()

    }


}
