# DoggyApp - Work in Progress (not finished)
## Current Functionality
The App currently uses `retrofit` to get a list of dog breeds from the DogApi to populate a `RecyclerView` in `MainActivity`.
## TODO
### Dog Breed Screen
- [ ] modify api call to get sub breeds as well
- [ ] Add a title to the top of screen (Choose a Dog Breed)
- [ ] add styling to Dog Breed Screen
- [ ] add onTouch functionality to select breed and switch to Gallery Screen
- [ ] add api call to get pictures from selected breed
### Gallery Screen
- [ ] Add Gallery Activity
- [ ] display pictures of selected breed
- [ ] onTouch of picture toggles favorite/unfavorite
- [ ] favorited picture identified by a heart/paw icon on corner of picture
- [ ] add persistence for favorites
- [ ] floating action button to share favorites via installed email app
### Extras
- [ ] add testing
- [ ] download and persist images for offline use
- [ ] download all images of breed with progress bar using background woker
## Demo on Emulator
![demo](https://user-images.githubusercontent.com/10107412/146297371-0247f12b-1f7f-4ee1-86ca-2ca747eceeb9.gif)
## Demo on Android Device

https://user-images.githubusercontent.com/10107412/146297673-369b9939-a1c3-4c34-8e64-5e02e1d38f5f.mp4
