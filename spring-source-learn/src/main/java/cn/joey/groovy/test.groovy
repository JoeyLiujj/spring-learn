// 用第一位开始找，那个是数字就打印出该数字
//println str.find{
//    String s -> s.isNumber()
//}

// it的意思是： 类似for循环定义中的一个循环值 i

def scriptClusor = {
    println "scriptClouser this:" + this //  闭包 this 代表闭包所处的类(文件)
    println "scriptClouser owner:"+ owner // owner 代表闭包定处的类或对象
    println "scriptClouser delegate:" + delegate // delegate 代表任意对象，默认和owner一样
}

//scriptClusor.call()
// 闭包的生命周期和外部类一样  没有return语句

//gradle介绍
//ant 和maven的对比
//groovy的核心语法
//gradle很重要 实践很多
/**
 * ant 2002
 * maven
 * gradle 很有可能取代maven 既有静态语言的特性又有冬天语言的特性
 * 构建工具
 * Jenkins
 */


//字符串和闭包的结合使用
String str2 = "the 2 and 5 equals 7"
//each
str2.each {
    String temp -> println temp
}

//find查找符合条件的第一个
println str2.find{
    String s -> s.isNumber()
}

def result = str2.any{
    String s-> s.isNumber()
}
// every闭包  每一个都是数据的话，就会返回true
println result

/**
 * 闭包的概念
 * 闭包的参数
 * 闭包的返回值
 * ----------------------
 * 正式进入gradle的学习
 * */

/**
 * groovy的核心语法
 * build script block
 * gradle api
 *
 * gradle的结构 build.gradle task  project+task组成gradle基础骨架
 * settings.gradle 配置项目基本信息
 *
 * 基础命令 clean build
 * gradlew if 指定目录下没有gradle二进制包，那么我根据你在我的文件里给的地址去下载，否则我直接去用
 *
 * buildScript{
 *    //依赖配置  gradle自身对外面插件的依赖
 * }
 * 外部(all projects){
 *      //项目本身对外部库的依赖
 * }
 * allProjects{}
 * subprojects{}
 * gradle的执行过程 生命周期 初始化阶段  配置阶段 task的依赖  执行阶段  执行task及其依赖task
 * 拓扑图 有向无环图
 *
 *
 * api
 *  project_api
 *  allprojects
 * 闭包对象 可以用{} 函数中超过两个参数以上并且最后一个参数是闭包，那么就可以把闭包放在{}中，
 *  project("my-common"){
 *      Project project ->
 *         group 'com.test'
 *         dependencies {
 *
 *         }
 *  }
 *
 *  //相当于import
 *  apply from this.file('common.gradle')
 *
 *  核心API
 *  allprojects
 *  subprojects
 *  parentprojects
 *  rootprojects
 *
 *  使用场景
 *  核心的原理是怎么用的
 *  gradle参考资料 实战gradle
 *
 */

