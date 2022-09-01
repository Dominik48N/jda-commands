# JDA Commands
JDA Commands is an extension for JDA to make commands easier and clearer to develop.

With the API you can easily create commands for the JDA and clearly divide them into different classes. Slash and custom commands are also supported.

If you have questions or problems regarding the JDA Commands API, you can create an [issue](https://github.com/Dominik48N/jda-commands/issues) or use the [discussion](https://github.com/Dominik48N/jda-commands/discussions).

## Requirements
The following things are required to use JDA Commands:
* Java 8+
* [JDA](https://github.com/DV8FromTheWorld/JDA) v5.0.0-alpha.18

## Usage
How you can use the API is written in our [wiki](https://github.com/Dominik48N/jda-commands/wiki). Here you will only be briefly shown how to implement the API in Maven and Gradle.

### Maven
Add the following to your `pom.xml`:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.Dominik48N</groupId>
    <artifactId>jda-commands</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```

### Gradle
Add the following to your `build.gradle`:
```kt
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
  
dependencies {
  implementation 'com.github.Dominik48N:jda-commands:Tag'
}
```

## License
This repository is licensed with the [Apache License 2.0](https://github.com/Dominik48N/jda-commands/blob/master/LICENSE).
