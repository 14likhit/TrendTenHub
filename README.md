# TrendTenHub
App for getting trending github repositories

### Tech

TrendTenHub will be made using following libraries and Architectural Pattern:

* [MVVM] - Android Architechtural Pattern.
* [DataBinding] - Used Databinding
* [Retrofit] - Retrofit is used for Network Requests and Caching them.
* [Glide] - Glide is used to load images and cache them.
* [DI] - Implemented constructor dependency injection.
* [Coroutine] - Kotlin coroutine will be used to make network calls.

### Installation

Download the apk and install the app.

### Development (Tech Logic)

-Fetch the data from API
-Contains list of trending github repository. Added one sample item.
Sample API:
```sh
[
    {
        "author": "xingshaocheng",
        "name": "architect-awesome",
        "avatar": "https://github.com/xingshaocheng.png",
        "url": "https://github.com/xingshaocheng/architect-awesome",
        "description": "后端架构师技术图谱",
        "language": "Go",
        "languageColor": "#3572A5",
        "stars": 7333,
        "forks": 1546,
        "currentPeriodStars": 1528,
        "builtBy": [
            {
                "href": "https://github.com/viatsko",
                "avatar": "https://avatars0.githubusercontent.com/u/376065",
                "username": "viatsko"
            }
        ]
    },
      ...........
      ...........
    ]
```
-Load Data in Recycler View
-Two data Classes one for Repository, and one for builtBy

### Todos

 - Create Base Structure 
 - Add Retrofit Setup
 - Add Viewmodel 
 - Add unit test cases
 - Create UI
