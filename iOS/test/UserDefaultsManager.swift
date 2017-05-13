//
//  UserDefaultsManager.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class UserDefaultsManager {

    static let shared = UserDefaultsManager()
    private init() { }

    func getToken() -> String? {
        if let token = UserDefaults.standard.value(forKey: "token") as? String {
            return token
            print("Logged in successfuly")
        } else {
            print("Login failed - no token")
            return nil
        }
    }

    func setToken(_ token: String) {
        UserDefaults.standard.set(token, forKey: "token")
        print("Saving token")
    }

}
