# calculator-espresso-tests
This project includes testing scenarios and UI tests for open-source [Calculator](https://github.com/SimpleMobileTools/Simple-Calculator)
application, what is completely written in Kotlin. Tests also written in [Kotlin](https://kotlinlang.org).


### How to run Espresso UI tests

- Clone **SimpleCalculator** from their repository
- Clone test from this repository
- Add test to **/app/src/androidTest/** folder
- Click on **Run**
- Click on **Edit Configurations**
- Click on **+**
- Choose **Android Instrumentation Tests** configuration
- Choose **app** module
- Save and run Tests
- Click on **Create New Virtual Device** in order to use emulated Android device

### Test cases

#### Check what application opens and the name is correct


    - Open the application
    - Compare given application name to real application name

    Expected result: Given application name is the same as real application name


#### Check what user can change theme and the theme is saved successfully

Test generates random theme name from the given array.

    - Open the application
    - Tap on right action bar
    - Tap on Settings
    - Tap on Customize color
    - Tap on Theme
    - Pick random theme
    - Tap on Save
    - Compare theme name with chosen one

    Expected result: Theme saves and the theme name corresponds to the chosen one

[![](https://i.imgur.com/6RDTiJz.gif)](https://i.imgur.com/6RDTiJz.gif)

#### Check user can change widget color

Test generates random HEX color code.

    - Open the application
    - Tap on right action bar
    - Tap on Settings
    - Tap on color choice box
    - Enter HEX color code
    - Tap on Save
    - Compare entered HEX code to saved one

    Expected result: Saved HEX color code should be the same as entered one.

[![](https://i.imgur.com/qsYzXmw.gif)](https://i.imgur.com/qsYzXmw.gif)   
