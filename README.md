Stater
=======
[![Build status](https://travis-ci.org/AlexeyPanchenko/stater.svg)](https://travis-ci.org/AlexeyPanchenko/stater)

Lightweight library to save state in your Activity/Fragment.
Stater also fine works with Kotlin code as it uses bytecode transformation.

Download
--------
In root `build.gradle` file:
```groovy
buildscript {
    ext.stater_version = '0.1'
    repositories {
        jcenter()
    }
    dependencies {
        ...
        classpath "ru.alexpanchenko:stater-plugin:$stater_version"
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

```
In your app `build.gradle` file add plugin:
```groovy
apply plugin: 'com.android.application'
...
apply plugin: 'stater-plugin'
```
And library:
```groovy
implementation "ru.alexpanchenko:stater:$stater_version"
```

Usage
--------
Activity
```java
import ru.alexpanchenko.stater.Stater;
import ru.alexpanchenko.stater.StateType;
import ru.alexpanchenko.stater.State;

@Stater
public class MainActivity extends AppCompatActivity {
  @State(StateType.INT)
  private int yourVar = 0;
  
  @State(StateType.BUNDLE)
  private Bundle bundleVar;
}
```
Fragment
```java
import ru.alexpanchenko.stater.Stater;
import ru.alexpanchenko.stater.StateType;
import ru.alexpanchenko.stater.State;

@Stater
public class MainFragment extends Fragment {
  @State(StateType.INT)
  private int yourVar = 0;
  
  @State(StateType.BUNDLE)
  private Bundle bundleVar;
}
```
It's all!

How it works
--------
Stater plugin transform classes with `@Stater` annotation in compile time (see `transformClassesWithStaterTransformFor...` task).
It save state in `onSaveInstanceState` and restore it in `onCreate`.
```java
protected void onCreate(@Nullable Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      this.yourVar = savedInstanceState.getInt("your/class/path_yourVar");
      this.bundleVar = savedInstanceState.bundleVar("your/class/path_bundleVar");
    }
    super.onCreate(savedInstanceState);
}

protected void onSaveInstanceState(@NonNull Bundle outState) {
    outState.putInt("your/class/path_yourVar", this.yourVar);
    outState.putBundle("your/class/path_bundleVar", this.bundleVar);
    super.onSaveInstanceState(outState);
}
```
All supported types see in `StateType` enum. It can works also with object presentation of primitives:
`Byte, Short, Character, Boolean, Integer, Long, Double, Float` and its arrays.
Transformed classes you can find in `build/intermediates/transforms/StaterTransform/yourPackage`

License
-------

    Copyright 2019 Alexey Panchenko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.