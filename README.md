# Flickery
A Strange Image gallery only caring about width

#### Prerequisites
To use this app you must add your Flicker API Key.
In local.properties, add the following value:
  ```
   flickr.key="YOUR_API_KEY""
  ```

#### Build
Build as usual with gradle, either using android studio GUI or in project root: `./gradlew assembleDevDebug`

#### Dependencies (external)
I usually avoid dependencies, but I do make some exceptions. For this project I use Glide for image handling. 
This library is recommended in the official developer documentation (https://developer.android.com/topic/performance/graphics/load-bitmap).
As I have had a swing in the past at implementing my own image handling I know it's out of scope for this project.

#### Time constraints
Given more time I would: 
* I intended on adding dynamic search, which some of the code will reflect
* Implement caching using Room
* Implement swipe for full screen view using ViewPager
* Loading indicators
* Image optimizations (utilize BitmapPool)
* More tests than a simple Unit test
* Better network stack