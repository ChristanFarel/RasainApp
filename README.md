# Overview
<img src="https://github.com/ChristanFarel/RasainApp/blob/main/readme_asssets/1.jpeg" width=auto height=300 align="left"/>
<img src="https://github.com/ChristanFarel/RasainApp/blob/main/readme_asssets/2.jpeg" width=auto height=300 align="left"/>
<img src="https://github.com/ChristanFarel/RasainApp/blob/main/readme_asssets/3.jpeg" width=auto height=300 align="left"/>
<img src="https://github.com/ChristanFarel/RasainApp/blob/main/readme_asssets/4.jpeg" width=auto height=300 align="left"/>
<img src="https://github.com/ChristanFarel/RasainApp/blob/main/readme_asssets/5.jpeg" width=auto height=300 align="left"/>

# About Our App

# Another Rasain's Path
| Repository                                                             | Description          |
| ---------------------------------------------------------------------- | -------------------- |
| [RasainApp](https://github.com/ChristanFarel/RasainApp)                | Mobile Development.  |
| [RasainApp-backend](https://github.com/andikabahari/RasainApp-backend) | Cloud Computing.     |
| [RasainApp-ml](https://github.com/agistarakha/RasainApp-ml)            | Machine Learning.    |

# Usage

## Requirements

- Android Studio Bumblebee
- Minimum Android App SDK 21
- PC / Laptop:
  - Processor: Intel Core i3 (Recommended Intel Core i5 above).
  - RAM: 8 GB or more.
  - Screen resolution: 1280 x 800 (Full HD 1920 x 1080 recommended).

## Installation

1. Clone this repository, run `git clone https://github.com/ChristanFarel/RasainApp.git .`
2. Open in Android Studio
3. Connect your device into Android Studio. [Documentation](https://developer.android.com/codelabs/basic-android-kotlin-compose-connect-device)
4. Run your Android Studio. [Documentation](https://developer.android.com/training/basics/firstapp/running-app)

## Library

    <table>
        <thead>
            <tr>
                <th>Library name</th>
                <th>Usages</th>
                <th>Dependency</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>[Retrofit](https://square.github.io/retrofit/)</td>
                <td>A type-safe HTTP client for Android and Java</td>
                <td>```implementation "com.squareup.retrofit2:retrofit:2.9.0"```</td>
            </tr>
            <tr>
                <td>[Gson](https://github.com/google/gson)</td>
                <td>Convert json obtained from okhttp into an object</td>
                <td>```implementation "com.squareup.retrofit2:converter-gson:2.9.0"```</td>
            </tr>
            <tr>
                <td>[OkHttp](https://square.github.io/okhttp/)</td>
                <td>Create HTTP Request to the server</td>
                <td>```implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"```</td>
            </tr>
            <tr>
                <td>[ViewModel](https://developer.android.com/reference/android/arch/lifecycle/ViewModel)</td>
                <td></td>
                <td>```implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"```</td>
            </tr>
            <tr>
                <td>[LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData)</td>
                <td></td>
                <td>```implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"```</td>
            </tr>
            <tr>
                <td>[Activity KTX](https://androidx.tech/artifacts/activity/activity-ktx/)</td>
                <td></td>
                <td>```implementation "androidx.activity:activity-ktx:1.4.0"```</td>
            </tr>
            <tr>
                <td>[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)</td>
                <td></td>
                <td>```implementation "androidx.datastore:datastore-preferences:1.0.0"```</td>
            </tr>
            <tr>
                <td rowspan="2">[Glide](https://github.com/bumptech/glide)</td>
                <td rowspan="2"></td>
                <td>```implementation "com.github.bumptech.glide:glide:4.13.1"</td>
            </tr>
            <tr>
                <td>annotationProcessor "com.github.bumptech.glide:compiler:4.13.0"```</td>
            </tr>
            <tr>
                <td>[Gif](https://github.com/koral--/android-gif-drawable)</td>
                <td></td>
                <td>```implementation "pl.droidsonroids.gif:android-gif-drawable:1.2.17"```</td>
            </tr>
            <tr>
                <td rowspan="2">
                    [Room](https://developer.android.com/jetpack/androidx/releases/room?gclid=CjwKCAjwnZaVBhA6EiwAVVyv9KlHYLnyD9Aie8mZnsOryXePqeJOAWcOhcf4Dz9ECgoEeX0GIWlwQxoC59cQAvD_BwE&gclsrc=aw.ds)
                </td>
                <td rowspan="2"></td>
                <td>```implementation "androidx.room:room-runtime:2.4.2"</td>
            </tr>
            <tr>
                <td>kapt "androidx.room:room-compiler:2.4.2"```</td>
            </tr>
            <tr>
                <td rowspan="4">[CameraX](https://developer.android.com/jetpack/androidx/releases/camera)</td>
                <td rowspan="4"></td>
                <td>```def camerax_version = "1.2.0-alpha02"</td>
            </tr>
            <tr>
                <td>implementation "androidx.camera:camera-camera2:${camerax_version}"</td>
            </tr>
            <tr>
                <td>implementation "androidx.camera:camera-lifecycle:${camerax_version}"</td>
            </tr>
            <tr>
                <td>implementation "androidx.camera:camera-view:${camerax_version}"```</td>
            </tr>
            <tr>
                <td>[Core KTX](https://developer.android.com/jetpack/androidx/releases/core)</td>
                <td></td>
                <td>```implementation "androidx.core:core-ktx:1.8.0"```</td>
            </tr>
            <tr>
                <td>[Shimmer](http://facebook.github.io/shimmer-android/)</td>
                <td></td>
                <td>```implementation "com.facebook.shimmer:shimmer:0.5.0"```</td>
            </tr>
        </tbody>
    </table>
