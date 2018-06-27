# Jodatime_Calendar
> 自定义日历，使用到了jodatime，项目使用于boxfish盒子鱼外教App中使用的日历表。由于项目的需要，会出现课程已完成，课程排课中和课程有课等显示。如果不需要显示，可以不显示。或者自定义为自己想要的效果。

![](https://ws1.sinaimg.cn/large/5cc1a78ely1fsq58l8af2j20cx0igaau.jpg)

---
## 如何添加该开源库

---
### Gradle:
### Step 1. 添加JitPack仓库
在当前项目的根目录下的 build.gradle 文件中添加如下内容:
```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
### Step 2. 添加项目依赖
```java
	dependencies {
	        implementation 'com.github.Lieeber:Jodatime_Calendar:v1.1'
  }
```

---
## 个人博客
> www.lieeber.com
