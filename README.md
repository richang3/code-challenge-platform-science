Android App for Platform Science Code Exercise
==============================================

This is an Android App that demonstrates Platform Science Code Exercise:

Our sales team has just struck a deal with Acme Inc. to become the exclusive provider for
routing their product shipments via 3rd party trucking fleets. The catch is that we can only route
one shipment to one driver per day.
Each day we get the list of shipment destinations that are available for us to offer to drivers in
our network. Fortunately our team of highly trained data scientists have developed a
mathematical model for determining which drivers are best suited to deliver each shipment.
With that hard work done, now all we have to do is implement a program that assigns each
shipment destination to a given driver while maximizing the total suitability of all shipments to
all drivers.

The top-secret algorithm is:
● If the length of the shipment's destination street name is even, the base suitability score
(SS) is the number of vowels in the driver’s name multiplied by 1.5.
● If the length of the shipment's destination street name is odd, the base SS is the number
of consonants in the driver’s name multiplied by 1.
● If the length of the shipment's destination street name shares any common factors
(besides 1) with the length of the driver’s name, the SS is increased by 50% above the
base SS.

Write an Android application using the attached json file as input that displays a list of drivers.
When one is selected from the list display the correct shipment destination to that driver in a
way that maximizes the total SS over the set of drivers. Each driver can only have one shipment
and each shipment can only be offered to one driver

## Building the App

First, clone the repo:

`git clone git@github.com:richang3/code-challenge-platform-science.git`

Building the sample then depends on your build tools.

### Android Studio (Recommended)

(These instructions were tested with Android Studio Chipmunk

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

## Using the App
* Upon launch, the app should load a sample list of shipments and drivers using mocked client.
* The app should display a list drivers.
* When a driver is selected from the list, the address that maximizes the total SS over the set of drivers is shown.

