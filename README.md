# DoggyApp - Work in Progress
## Demo
![demo](https://user-images.githubusercontent.com/10107412/147437756-a51e6236-3ed4-4815-9cc2-efe37c3432ac.gif)
## Current Functionality
- The App currently uses `retrofit` to get a list of dog breeds from the DogApi to populate a `RecyclerView` in `MainActivity`.
- Each breed name has an `OnClickListener` that goes to the Gallery Screen.
- Gallery Screen displays pictures of selected breed in a 2 column grid `RecyclerView` with `glide`.
- Each picture in the Gallery Screen has an `OnClickListener` that toggles favorite & unfavorite.
- Favorited pictures show a gold star & non favorites show a grey star.
- Each favorited picture has it's URL added to `favList` List located in `MyApplication.kt`.
- Floating action button on Gallery Screen opens `EmailDialog` that receives an email address.
- After `email` button is clicked on `EmailDialog` app opens up installed email app to send favorites to entered email address.
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
- [x] onTouch of picture toggles favorite/unfavorite
- [ ] favorited picture identified by a heart/paw icon on corner of picture
- [ ] add persistence for favorites
- [x] floating action button to share favorites via installed email app
### Extras
- [ ] add testing
- [ ] download and persist images for offline use
- [ ] download all images of breed with progress bar using background woker

