# Chabok android library
Blow some breath to your app with Chabok realtime messaging and receive push notifications cross any platform with zero code. Know your users's better, push them content based on their location or track their presence/location withoud headache.
Chabok help mobile application marketers to optimize their acquisition campaigns and increase user engagement & retention.

## Installation
For installation refer to [Android docs](https://doc.chabok.io/android/introducing.html) and platform specific parts (Android).

## Release Note
You can find release note [here](https://doc.chabok.io/android/release-note.html).

## Support
Please visit [Issues](https://github.com/chabok-io/chabok-client-android/issues).


## Getting Started

These are the minimum required steps to integrate the Chabok SDK in your Android app. We assume that you are using Android Studio for your Android development. The minimum supported Android API level for the Chabok SDK integration is 16 (Jelly-bean).

### Installation

Add to your root `build.gradle`:

``` groovy
allprojects {

    repositories {
        
        maven { url "https://chabok.jfrog.io/artifactory/Chabok" }
        
    }

}
```


Add the dependency to your app `build.gradle` file.

``` groovy
dependencies {

    implementation 'io.chabok:Chabok:0.10.1'
    
}
```

#### Optional setup


##### Install Referrer

Install Referrer is an ad-tracking identifier specific to Android. Install referrer is a unique string sent to the Play Store when a user clicks on an ad. It's similar to Device IDs and Device fingerprinting.

For Google Play Referrer API support, ensure your `build.gradle` contains the following.

``` groovy
dependencies {

    implementation 'com.android.installreferrer:installreferrer: 2.2'
    
}
```

##### Firebase Cloud Messaging 

Firebase Cloud Messaging (FCM), formerly known as Google Cloud Messaging (GCM), is a cross-platform cloud solution for messages and notifications for Android.

You need to include the following code in your `build.gradle` file to get Firebase Cloud Messaging API support.

``` groovy
dependencies {

    implementation 'com.google.firebase:firebase-messaging: 23.0.0'
    
}
```

##### Huawei Push Kit

Push Kit is a messaging service provided by Huawei for Huawei devices that does not support play services.

To detect Huawei devices which support Huawei's messaging service add the following dependency to your `build.gradle`.

``` groovy
dependencies {

    implementation 'com.huawei.hms:push: 4.0.2.300'
    
}
```

### Initialization

Assigning permissions.

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

The AndroidManifest.xml file should include the following permission for apps targeting API level 31 (Android 12):


```xml
<uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```

<br/>

Initialize Chabok SDK in your `MainApplication` file:

> `Note:` Calling `Chabok.initialize()` is required for using Chabok SDK.


```java
//Java

import io.chabok.Callback;
import io.chabok.Chabok;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Chabok.initialize("CLIENT_ID", "CLIENT_SECRET", new Callback() {
            @Override
            public void onResponse(boolean success, @Nullable String message) {
                if(success) {
                    //Initialized.
                } else {
                    //Not Initialized.
                }
            }
        });
    }
}
```
```kotlin
//Kotlin

import io.chabok.Chabok  
import io.chabok.Callback  
import android.app.Application  
  
class MainApplication : Application() {  
  
    override fun onCreate() {  
        super.onCreate()  
  
        Chabok.initialize("CLIENT_ID", "CLIENT_SECRET", object : Callback {  
            override fun onResponse(success: Boolean, message: String?) {  
                if (success) {  
                    print("The Chabok SDK has been successfully initialized")  
                } else {  
                    print("$message")  
                }  
            }  
        })  
    }  
}
```

> `Note:` The application credentials (`CLIENT_ID` and `CLIENT_SECRET`) are available in your dashboard space under app info.

Place your `CLIENT_ID` and `CLIENT_SECRET` from your dashboard into the initialize method.


## Usage

&#32;
#### Set default tracker:

You can still understand the installation source in your campaign even if the stores do not support Referrer. The process is as follows:

- Create **new tracker** in your dashboard.
- Enter the **tracker ID** in the following method.

```java
//Java

Chabok.setDefaultTracker("DEFAULT_TRACKER");
```
```kotlin
//Kotlin

Chabok.setDefaultTracker("DEFAULT_TRACKER");
```

After getting the apk output, upload it to your desired store.

> `Note:` Default tracker should be called before `Chabok.initialize()`.

&#32;

<br/>

#### User

Chabok `USER_ID` is a unique ID that can be assigned to each user to identify him/her.
For example, a unique ID could be a generated UUID, a mobile number, etc.

Ideally, you should assign the unique ID to users when signing up, logging in, or on pages where their identity is known.


### Login

After initializing Chabok, use the `login` method to identify your users in the system to monitor all behaviors and attributes with user identity.
We recommend that you to use Chabok's login on otp pages.

When a user logs in, all the stored information is associated with the identified user.

To login user in the Chabok service use `login` method:

```java
//Java

Chabok.user().login("USER_ID");
```
```kotlin
//Kotlin

Chabok.user().login("USER_ID");
```

`login` method with callback:

```java
//Java

Chabok.user().login("USER_ID", new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {
                
	}
});
```
```kotlin
//Kotlin

Chabok.user().login("USER_ID", object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
        if (success){  
            print("User has successfully logged in")  
        } else {  
            print("$message")  
        }  
    }  
})
```

##### Example

When verifying user OTP codes, we should login to the Chabok platform to identify user by user ID

```java
//Java

Chabok.user().login("989100360500");
```
```kotlin
//Kotlin

Chabok.user().login("989100360500");
```

<br/>
### Logout

By calling the following method, even if the user is logged out of his/her account, you can still have the user in your system with a guest ID and interact with the user as usual.

When the user logs out of your app, call the Chabok Logout method to avoid attaching future attributes, events, and other data to this user until the login method is called again.

`logout` method can be used to log out a user from Chabok:

```java
//Java

Chabok.user().logout();
```
```kotlin
//Kotlin

Chabok.user().logout();
```

`logout` method with callback:

```java
//Java

Chabok.user().logout(new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {
		if (success){  
            print("User has successfully logged out")  
        } else {  
            print("$message")  
        }  
	}
});
```
```kotlin
//Kotlin

Chabok.user().logout(object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
	    if (success){  
            print("User has successfully logged out")  
        } else {  
            print("$message")  
        } 
    }  
})
```


#### Check user is logged in:

To check a user is logged in Chabok you can use the following method.

```java
//Java

Chabok.user().isLoggedIn();
```
```kotlin
//Kotlin

Chabok.user().isLoggedIn();
```

> `Tip:` In case you have implemented Chabok in your application, you can use the following method to check and login users who have already logged into your system but not into Chabok.


```java
//Java

if (!Chabok.user().isLoggedIn()) {
   Chabok.user().login("USER_ID")
}
```
```kotlin
//Kotlin

if (!Chabok.user().isLoggedIn()) {
   Chabok.user().login("USER_ID")
}
```

<br/>

### Analytics

#### Event

The events feature allows you to track any other user interactions that are vital to your business. Additionally, each custom event can have attributes such as amount, quantity, price, category, etc.
This data will allow you to personalize campaigns.

For tracking an `event` the process is as follows:
 
- Create a new event in your dashboard.
- Copy the ID and use it in `sendEvent` method.
  
  <br/>

To set an analytics event use `sendEvent` method:

>`Note:` Passing attributes to `event`'s method is optional.

```java
//Java
Chabok.analytics().sendEvent("ID", JSONObject);
```
```kotlin
//Kotlin

Chabok.analytics().sendEvent("ID", JSONObject?);
```

##### Example

For example we want to track add to cart event.

- Create add-to-cart event in your dashboard.
- Create your event attributes.
- Then pass the attributes with the given ID to the `sendEvent` method.

> `Note:` Event attributes could be passed as `JSONObject`, `HashMap` or `Bundle`.

```java
//Java

JSONObject body = new JSONObject();
try {
    body.put("product_id", "123456");
    body.put("name", "T-shirt");
    body.put("quantity", 10);
    body.put("category", "Clothes");
    body.put("price", 100.25);
    body.put("currency", "USD");
    body.put("chosen_colors", new String[] {"Black", "Blue", "White"});
    body.put("discounted", true);
    body.put("discount_percent", 15);
} catch (JSONException e) {
    e.printStackTrace();
}

Chabok.analytics().sendEvent("ID", body);
```
```kotlin
//Kotlin

val body = JSONObject()  
  
try {  
 body.put("product_id", "123456")  
 body.put("name", "T-shirt")  
 body.put("quantity", 10)  
 body.put("category", "Clothes")  
 body.put("price", 100.25)  
 body.put("currency", "USD")  
 body.put("chosen_colors", arrayOf("Black", "Blue", "White"))  
 body.put("discounted", true)  
 body.put("discount_percent", 15)  
} catch (e: JSONException) {  
 e.printStackTrace()  
}  
  
Chabok.analytics().sendEvent("ID", body)
```
<br/>

An example with `HashMap` type:
```java
//Java

HashMap<String, Object> body = new HashMap<>();
body.put("product_id", "123456");
body.put("name", "T-shirt");
body.put("quantity", 10);
body.put("category", "Clothes");
body.put("price", 100.25);
body.put("currency", "USD");
body.put("chosen_colors", new String[] {"Black", "Blue", "White"});
body.put("discounted", true);
body.put("discount_percent", 15);

Chabok.analytics().sendEvent("ID", body);
```
```kotlin
//Kotlin

val body: HashMap<String, Any> = HashMap()  
body["product_id"] = "123456"  
body["name"] = "T-shirt"  
body["quantity"] = 10  
body["category"] = "Clothes"  
body["price"] = 100.25  
body["currency"] = "USD"  
body["chosen_colors"] = arrayOf("Black", "Blue", "White")  
body["discounted"] = true  
body["discount_percent"] = 15  
  
Chabok.analytics().sendEvent("ID", body)
```

An example with `Bundle` type:

```java
//Java

Bundle body = new Bundle();
body.putString("product_id", "123456");
body.putString("name", "T-shirt");
body.putInt("quantity", 10);
body.putString("category", "Clothes");
body.putDouble("price", 100.25);
body.putString("currency", "USD");
body.putStringArray("chosen_colors", new String[] {"Black", "Blue", "White"});
body.putBoolean("discounted", true);
body.putInt("discount_percent", 15);

Chabok.analytics().sendEvent("ID", body);
```
```kotlin
//Kotlin

val body = Bundle()  
body.putString("product_id", "123456")  
body.putString("name", "T-shirt")  
body.putInt("quantity", 10)  
body.putString("category", "Clothes")  
body.putDouble("price", 100.25)  
body.putString("currency", "USD")  
body.putStringArray("chosen_colors", arrayOf("Black", "Blue", "White"))  
body.putBoolean("discounted", true)  
body.putInt("discount_percent", 15)  
  
Chabok.analytics().sendEvent("ID", body)
```

<br/>

Set `event` method with callback:

```java
//Java

Chabok.analytics().sendEvent("ID", body, new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {

	}
});
```
```kotlin
//Kotlin

Chabok.analytics().sendEvent("ID", body, object : Callback {
 override fun onResponse(success: Boolean, message: String?) {  
       
 }  
})
```


#### Attributes

The user attributes you collect can give you a comprehensive picture of who your users are, where they're from, what they do, and a whole lot more, depending on your business. An attribute is something like favorites, user preferences, or etc. You can segment users based on their contextual relevance and personalize marketing campaigns sent through all channels of engagement with such granular user data.

To set user attributes in the Chabok service use `setAttribute` method:

```java
//Java

Chabok.user().setAttribute("KEY","VALUE");
```
```kotlin
//Kotlin

Chabok.user().setAttribute("KEY","VALUE")
```

##### Example

For example we want to store attributes of a stock trader.

```java
//Java

Chabok.user().setAttribute("City","Karaj");
```
```kotlin
//Kotlin

Chabok.user().setAttribute("City","Karaj")
```

<br/>

Set user's attributes method with callback:

```java
//Java

Chabok.user().setAttribute("KEY","VALUE", new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {

	}
});
```
```kotlin
//Kotlin

Chabok.user().setAttribute("KEY","VALUE", object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
          
    }  
})
```

#### Profile
Use the `setProfile` method to enter user information such as first name, last name, gender, etc.

To set user's profile information in the Chabok service use `setProfile` method:

```java
//Java

Profile profile = new Profile.Builder()
                    .email("EMAIL") //e.g. dev.chabok@gmail.com
                    .mobile("MOBILE") //e.g. 989100360500
                    .name("NAME") //e.g. Hossein
                    .family("FAMILY") //e.g. Shooshtari
                    .birthDate("BIRTH_DATE") //e.g. 29-02-1372
                    .gender(GENDER) //e.g. Gender.MALE
                    .timeZone(TIME_ZONE) //e.g. +3:30
                    .build();
Chabok.user().setProfile(profile);
```
```kotlin
//Kotlin

val profile: Profile = Profile.Builder()  
	.email("EMAIL") //e.g. dev.chabok@gmail.com  
	.mobile("MOBILE") //e.g. 989100360500  
	.name("NAME") //e.g. Hossein  
	.family("FAMILY") //e.g. Shooshtari  
	.birthDate("BIRTH_DATE") //e.g. 29-02-1372  
	.gender(Gender.MALE) //e.g. Gender.MALE  
	.timeZone("TIME_ZONE") //e.g. +3:30  
.build()  
  
Chabok.user().setProfile(profile)
```

<br/>

Set user's `profile` information method with callback:

```java
Chabok.user().setProfile(profile, new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {

	}
});
```
```kotlin
//Kotlin

Chabok.user().setProfile(profile, object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
          
    }  
})
```


#### Location
Use the `setLocation` method to enter user's location.

To set user's `location` use `setLocation` method:

```java
//Java

Chabok.user().setLocation(LATITUDE, LONGITUDE);
```
```kotlin
//Kotlin

Chabok.user().setLocation(LATITUDE, LONGITUDE);
```

<br/>

Set user's `location` information method with callback:

```java
//Java

Chabok.user().setLocation(LATITUDE, LONGITUDE, new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {

	}
});
```
```kotlin
//Kotlin

Chabok.user().setLocation(LATITUDE, LONGITUDE, object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
          
    }  
})
```

### Push ID (campaign & uninstall tracking)

Push tokens are used for User engagement (Push Notification Campagin and User Journey) and client callbacks; they are also required for **uninstall** and **reinstall** tracking.

To set user `push id` in the Chabok service use `PushId ` method:

```java
//Java

Chabok.user().setPushId("PUSH_ID");
```
```kotlin
// Kotlin

Chabok.user().setPushId("PUSH_ID");
```

<br/>

Set user `PUSH_ID` method with callback:

```java
//Java

Chabok.user().setPushId("PUSH_ID", new Callback() {
	@Override
	public void onResponse(boolean success, @Nullable String message) {

	}
});
```
```kotlin
//Kotlin

Chabok.user().setPushId("PUSH_ID", object : Callback {  
    override fun onResponse(success: Boolean, message: String?) {  
          
    }  
})
```

#### How to pass push token to Chabok:

##### Firebase:

After implementing firebase library in your project you can get the token as the following code.

```java
//Java

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    ...

    @Override
    public void onNewToken(String token) {
        // Send token to chabok here.
        Chabok.user().setPushId(token);
    }

    ...

}
```

```kotlin
//Kotlin

import io.chabok.Chabok  
import com.google.firebase.messaging.FirebaseMessagingService  
  
class MyFirebaseMessagingService: FirebaseMessagingService() {  
  
	// Send token to chabok here.
	override fun onNewToken(token: String) {   
	    Chabok.user().setPushId(token);  
	}
   
}
```

<br/>
<br/>


### Debugging features


&#32;
#### Enable/Disable Chabok SDK:
```java
Chabok.disableSdk(false);
```

&#32;
#### Set log level:
```java
Chabok.setLogLevel(LogLevel.VERBOSE);
```

&#32;
#### Lock SDK's logging:
```java
Chabok.lockLogging(false);
```



