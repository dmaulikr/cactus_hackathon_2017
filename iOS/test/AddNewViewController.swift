//
//  AddNewViewController.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit

class AddNewViewController: UIViewController {

    @IBOutlet weak var textView: UITextView!
    var attachedImage: UIImage!

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        setNeedsStatusBarAppearanceUpdate()

        textView.layer.borderColor = UIColor.black.cgColor
        textView.layer.borderWidth = 1
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func cancelButton(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }

    @IBAction func saveButtonTapped(_ sender: Any) {
        // Save
    }

    @IBAction func addImages(_ sender: Any) {
        let imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        present(imagePicker, animated: true, completion: nil)
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

extension AddNewViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {

    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        attachedImage = info[UIImagePickerControllerOriginalImage] as! UIImage
        dismiss(animated:true, completion: nil)
    }

    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }

}
