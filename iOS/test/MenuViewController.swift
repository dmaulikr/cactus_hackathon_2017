//
//  MenuViewController.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class MenuViewController: UIViewController {

    // Outlets
    @IBOutlet var MenuTopButtons: [UIButton]!
    @IBOutlet weak var teamsTableView: UITableView!


    // Properties
    var teams = [TeamData]()

    override func viewDidLoad() {
        super.viewDidLoad()

        // teamsTableView setup
        teamsTableView.delegate = self
        teamsTableView.dataSource = self

        // Setting menu buttons tint color
        for menuButton in MenuTopButtons {
            menuButton.imageView?.image = menuButton.imageView?.image?.withRenderingMode(.alwaysTemplate)
            menuButton.setImage(menuButton.imageView?.image, for: .normal)
            menuButton.imageView?.tintColor = UIColor(red:0.76, green:0.76, blue:0.76, alpha:1.0)
        }

        // TEST DATA
        let team1 = TeamData(name: "Personal", membersCount: 1, teamImageURL: nil)
        let team2 = TeamData(name: "IP-63", membersCount: 28, teamImageURL: nil)
        let team3 = TeamData(name: "Polyana", membersCount: 999, teamImageURL: nil)
        teams = [team1, team2, team3]
        

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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


// MARK: - UITableViewDataSource, UITableViewDelegate

extension MenuViewController: UITableViewDataSource, UITableViewDelegate {

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return teams.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "teamCell", for: indexPath) as! TeamTableViewCell

        let teamData = teams[indexPath.row]
        cell.teamNameLabel.text = teamData.name
        cell.membersCountLabel.text = "\(teamData.membersCount) members"

        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }

}
