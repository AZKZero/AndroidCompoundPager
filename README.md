# Android CompoundPager

A custom pager component to help you easily create sliders with your own layouts and indicators.

[![Build](https://img.shields.io/badge/Build-1.0.0-orange.svg?style=flat)]()

[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

## Importing

* add ```maven { url 'https://jitpack.io' }``` to project level build.gradle file
    ```
      allprojects {
          repositories {
              google()
              jcenter()
      
              maven { url 'https://jitpack.io' }
          }
      }
    ```
 * add ```implementation 'com.github.AZKZero:AndroidCompoundPager:1.0.0``` to module level build.gradle file
 
## Usage

### In your layout XML file:
```xml
<com.azkzer0.compoundpager.pagercomponent.CompoundPager
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:viewPagerHeightWrapContent="false"
        app:viewPagerHeight="500dp"
        android:id="@+id/comp"
        app:tabIndicatorLayout="@layout/your_custom_layout" />
``` 
* **viewPagerHeightWrapContent** (*boolean*) (*Untested*) (*Default **false***)
    To set the pager height as **WRAP_CONTENT**
* **viewPagerHeight** (*dimension*) (*Default **300***) (*Unused if **viewPagerHeightWrapContent** is **true***)
    To set a fixed pager height
* **tabIndicatorLayout** (*layout reference*) (*responds to state_selected*)

### In your activity of fragment class
```java
        compoundPager.setAdapter(adapter = new CompoundPagerAdapter(getSupportFragmentManager(),fragments));
        compoundPager.setTimeInterval(5000);
```
* fragments: An ArrayList<Fragments> to populate the pager
* setTimeInterval(int milliSeconds): setting value will initiate pager auto-scroll
*If any items are addded/removed in the ArrayList<Fragments>, notifyDataSetChanged should be called on the adapter, the tab indicators should react accordingly*
