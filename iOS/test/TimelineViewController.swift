//
//  ViewController.swift
//  test
//
//  Created by Alex Vihlayew on 4/15/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class TimelineViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.

    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if self.revealViewController() != nil {
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

