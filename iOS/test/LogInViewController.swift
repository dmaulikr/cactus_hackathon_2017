//
//  LogInViewController.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class LogInViewController: UIViewController {


    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()

        print("kek")
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    @IBAction func logInButtonTapped(_ sender: Any) {
        if let login = emailTextField.text, let password = passwordTextField.text {
            NetworkingManager.shared.logIn(withLogin: login, andPassword: password, completion: {
                self.performSegue(withIdentifier: "mainSegue", sender: self)
            })
        } else {
            print("Check input")
        }
    }



    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
