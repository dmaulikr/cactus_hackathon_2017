//
//  AddNewViewController.swift
//  test
//
//  Created by Alex Vihlayew on 5/13/17.
//  Copyright © 2017 Alex Vihlayew. All rights reserved.
//

import UIKit
import FirebaseStorage
import FirebaseCore
import FirebaseDatabase
import DateTimePicker

class AddNewViewController: UIViewController {

    @IBOutlet weak var saveButton: UIBarButtonItem!
    var photoURL: String!
    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var textView: UITextView!
    var attachedImage: UIImage!
    @IBOutlet weak var attachedImageView: UIImageView!
    @IBOutlet weak var timeLabel: UILabel!
    var date: Date!

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
        NetworkingManager.shared.postPost(withData: PostData(title: titleField.text!, description: textView.text, photoURLs: [], date: date))
        dismiss(animated: true, completion: nil)
    }

    @IBAction func addImages(_ sender: Any) {
        let imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        present(imagePicker, animated: true, completion: nil)
    }

    @IBAction func addTime(_ sender: Any) {
        let picker = DateTimePicker.show()
        picker.highlightColor = UIColor(red: 255.0/255.0, green: 138.0/255.0, blue: 138.0/255.0, alpha: 1)
        picker.completionHandler = { date in
            print(date)
            self.date = date
            let timeFormatter = DateFormatter()
            timeFormatter.dateStyle = .short
            timeFormatter.timeStyle = .short
            self.timeLabel.text = timeFormatter.string(from: date)
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

extension AddNewViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {

    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        attachedImage = info[UIImagePickerControllerOriginalImage] as! UIImage
        attachedImageView.image = attachedImage

        let storage = FIRStorage.storage()
        let imagesRef = storage.reference().child("photos/" + String(attachedImage.hashValue) + ".png")

        let metadata = FIRStorageMetadata()
        metadata.contentType = "image/png"

        let uploadTask = imagesRef.put(UIImagePNGRepresentation(attachedImage)!, metadata: metadata) { (metadata, error) in
            guard let metadata = metadata else {
                // Uh-oh, an error occurred!
                return
            }
            // Metadata contains file metadata such as size, content-type, and download URL.
            let downloadURL = metadata.downloadURL
            print(downloadURL)
            self.photoURL = downloadURL()?.absoluteString
            self.saveButton.isEnabled = true
        }

        dismiss(animated:true, completion: nil)
    }

    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }

}
