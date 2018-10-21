# RevealSwitch  
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-RevealSwitch-green.svg?style=flat )]( https://android-arsenal.com/details/1/7166 )

### This library provides you cool reveal animation on toggle.

![g_20181001_0205015](https://user-images.githubusercontent.com/13314984/46262426-b0c21400-c51e-11e8-815f-54d97440d2f3.gif)



## Dependency
__Step 1:__ Add Jitpack repository to your root(project) build.gradle at the end of repositories.


```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```  
  
 __Step 2:__ Add gradle dependency to your module's build.gradle.
 
 ```
 dependencies {
   implementation 'com.github.Akashkamble:RevealSwitch:1.0.1'
}
```  
## Usage

Add RevealSwitch to your layout like file like this

```xml
<com.akash.RevealSwitch
  android:id="@+id/revealSwitch"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  .../>
```      

__To add the toggleListener add following code__

```Kotlin
switch.setToggleListener(object : OnToggleListener{
    override fun onToggle(isEnable: Boolean) {
       Log.e("TAG","isEnabled ? $isEnable")
    }
 })
```      

__To change color of enabled track__ (default color __#444444__)

![screenshot_20181001-013416-02](https://user-images.githubusercontent.com/13314984/46262183-33e16b00-c51b-11e8-9404-d8ec751b7036.jpeg)

Add this in xml

```xml
app:setEnabledTrackColor="#FF1744"
```
        
Add this in Kotlin or Java
 
 ```kotlin
 switch.setEnabledTrackColor(Color.parseColor("#FF1744"))
 ```
        
__To changle color of disabled track__ (default colot __#FFFFFF__)

![screenshot_20181001-014628-01](https://user-images.githubusercontent.com/13314984/46262229-11038680-c51c-11e8-9bf5-b8dbe272dcb8.jpeg)

Add this in xml

```xml
app:setDisabledTrackColor="#FFFFFF"
```

Add this in Kotlin or Java

```Kotlin
 switch.setDisabledTrackColor(Color.parseColor("#FFFFFF"))
```

__To add the animation duration__ (default __500ms__)

Add this line in xml

```xml
app:setAnimationDuration="700"
```

Add this in Kotlin or Java

``` Kotlin
switch.setAnimationDuration(700) //Accepts value between 500ms to 1500ms
```

## Added in version 1.0.1

__To show the border__ (default __false__)

Add this line in your layout file.

```xml
app:showBorder="true"
```

Default border color will be thumb color.

__To set borer color__

Add this line in xml

```xml
app:borderColor="#FF1744"
```

        
        

