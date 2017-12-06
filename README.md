# chronometer

Contents
-----------------

- [Structure](#structure)
- [Libraries](#libraries)


Structure
---------------

- [RxMVP] (https://github.com/patrick-doyle/android-rxmvp)
- [Dagger 2] (https://github.com/codepath/android_guides/wiki/Dependency-Injection-with-Dagger-2)
- [RxJava2] (https://github.com/amitshekhariitbhu/RxJava2-Android-Samples)
- [RxAndroid] (https://github.com/ReactiveX/RxAndroid)
- [Kotlin] (https://kotlinlang.org/docs/reference)


Libraries
---------------

dependencies {

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    //Android support
    implementation 'com.android.support:appcompat-v7:27.0.1'
    implementation 'com.android.support:design:27.0.1'

    //RxJava 2
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'com.jakewharton.rxbinding2:rxbinding-kotlin:2.0.0'
    implementation "com.twistedequations.rx2:rx2-savestate:2.0.2"

    //Dagger 2
    kapt 'com.google.dagger:dagger-compiler:2.11'
    implementation 'com.google.dagger:dagger:2.11'

}
