### generator-mvvm-module-kotlin-module

> 基于Kotlin + Jetpack全家桶开发的mvvm组件化脚手架 :)

**stack**：MVVM/Kotlin/DataBinding/Rxjava2/Retrofit2/ViewModel/LiveData/Room/Koin/ARouter/Qmui

![](https://user-gold-cdn.xitu.io/2018/11/22/1673bc1247150cea?w=1240&h=607&f=png&s=161830)

### 功能介绍：

- kotlin
- mvvm
   - Lifecycles：它持有关于组件（如 Activity 或 Fragment）生命周期状态的信息，并且允许其他对象观察此状态
   - ViewModel：以注重生命周期的方式管理界面相关的数据,为Activity 、Fragment存储数据，直到完全销毁；
   - LiveData：不用手动控制生命周期，不用担心内存泄露，数据变化时会收到通知，与ViewModel的组合使用可以说是双剑合璧，而Lifecycles贯穿其中；
   - DataBinding：将布局组件与源数据绑定，使源数据变化的同时布局组件及时同步更新，与ViewModel、LiveData、Lifecycles搭配使用，能够碰撞出极致的MVVM火花
   - Room：流畅地访问 SQLite 数据库；
   - Koin: 是一个用于kotlin的实用型轻量级依赖注入框架，采用纯kotlin编写而成，仅使用功能解析，无代理、无代码生成、无反射。koin 是一个DSL,一个轻便的容易和一个使用的API。从此告别Dagger2的困境，让依赖注入更加简单。
- 组件化
   - ARouter：module之间跳转的工具
- 登录+主页
- 单元测试vm层

### Installation：
```bash
npm install mvvm_zjx -g
mvvm-zjx-cli init XXX
```

### 公司：
- [讯飞淘云](https://www.toycloud.com)








