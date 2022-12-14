<hr><div>
<a href="../.."><img align="right" height="91" src="assets/logo.png" alt="logo"></a>
<h1>hisendal</h1>
<p>Dadb usage within an Android project</p>
</div><hr>

## Preface

Example of an Android application that uses Dadb to connect to an Android device and returns its product name.
Note that the project uses an older version of Dadb and does not work with the latest versions.

## Usages

### Change the device address

Replace address in [AndroidScreenViewModel.kt](app/src/main/java/com/example/hisendal/AndroidScreenViewModel.kt).

```kotlin
private var address = mutableStateOf("192.168.X.XX")
```

### Force refreshing the adb keys

Change refresh parameter in [Device.kt](app/src/main/java/com/example/hisendal/Device.kt).

```kotlin
handler = Dadb.discover(address, keygen(refresh = true))
```

## Gallery

<a href="assets/img1.png"><img src="assets/img1.png" width="31.333%"/></a><a><img src="assets/none.png" width="2%"/></a><a href="assets/img2.png"><img src="assets/img2.png" width="31.333%"/></a>
<a><img src="assets/none.png" width="2%"/></a><a href="assets/img3.png"><img src="assets/img3.png" width="31.333%"/></a>
