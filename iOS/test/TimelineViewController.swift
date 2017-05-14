//
//  ViewController.swift
//  test
//
//  Created by Alex Vihlayew on 4/15/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit
import SDWebImage

class TimelineViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!

    var posts = [PostData]() {
        didSet {
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        tableView.dataSource = self
        tableView.delegate = self

        NetworkingManager.shared.getPosts(forUserWithID: 0, inClass: self)
        tableView.reloadData()
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

extension TimelineViewController: UITableViewDelegate, UITableViewDataSource {

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "postCell", for: indexPath) as! TimelineTableViewCell

        let postData = posts[indexPath.row]
        cell.titleLabel.text = postData.title
        cell.descriptionLabel.text = postData.description
        let formatter = DateFormatter()
        formatter.timeStyle = .short
        formatter.dateStyle = .short
        cell.timeLabel.text = formatter.string(from: postData.date)

   

        if indexPath.row == posts.count - 1 {
            cell.topLine.alpha = 1
            cell.bottomLine.alpha = 0
        } else if indexPath.row == 0 {
            cell.topLine.alpha = 0
            cell.bottomLine.alpha = 1
        } else {
            cell.topLine.alpha = 1
            cell.bottomLine.alpha = 1
        }

        return cell
    }

}
