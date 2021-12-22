# DoggyApp - Work in Progress (not finished)
## Current Functionality
- The App currently uses `retrofit` to get a list of dog breeds from the DogApi to populate a `RecyclerView` in `MainActivity`.
- Each breed name has an `OnClickListener` that goes to the Gallery Screen.
- Gallery Screen displays pictures of selected breed with `glide`.
## TODO
### Dog Breed Screen
- [ ] modify api call to get sub breeds as well
- [x] Add a title to the top of screen (Choose a Dog Breed)
- [ ] add styling to Dog Breed Screen
- [x] add onTouch functionality to select breed and switch to Gallery Screen
- [x] add api call to get pictures from selected breed
### Gallery Screen
- [x] Add Gallery Activity
- [x] display pictures of selected breed
- [ ] onTouch of picture toggles favorite/unfavorite
- [ ] favorited picture identified by a heart/paw icon on corner of picture
- [ ] add persistence for favorites
- [ ] floating action button to share favorites via installed email app
### Extras
- [ ] add testing
- [ ] download and persist images for offline use
- [ ] download all images of breed with progress bar using background woker
## Demo on Emulator
![demo](https://user-images.githubusercontent.com/10107412/147046598-7acd5fa8-c149-4aaf-957e-7d262fa4911a.gif)

## Demo on Android Device
https://user-images.githubusercontent.com/10107412/147047530-568fc75d-d6d6-4881-b029-d5a00d58ef90.mp4
