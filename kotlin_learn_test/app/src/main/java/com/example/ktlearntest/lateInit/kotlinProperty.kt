package com.example.ktlearntest.lateInit

/**
 * const 编译期常量
如果只读属性的值在编译期是已知的，那么可以使用 const 修饰符将其标记为编译期常量。 这种属性需要满足以下要求：
1.位于顶层或者是 object 声明 或 companion object 的一个成员
2.以 String 或原生类型值初始化
3.没有自定义 getter

 **/
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"


/**
 * 检测一个 lateinit var 是否已初始化
 * 在定义全局变量为空的时候并不是非得用问号设置为可空的，如果你可以确定一定不为空可以使用 lateinit 这个关键字来定义全局变量
 */
fun main() {
    isInitialize()
}

lateinit var initString: String
fun isInitialize() {
//    initString = "hello"
    if (::initString.isInitialized) {
        println(initString)
    } else {
        println("initString is not init!!!")
    }

//    if (initString != null) {//报错
//        println(initString)
//        println("initString != null")
//    } else {
//        println("initString is not init!!!")
//    }

}


class User(var name: String = "ddd", var age: Int = 1) {
}

fun testNullType() {
    val a: String = "aa"
    /*
    * a是非空类型，下面的给a赋值为null将会编译不通过
    * */
    //a = null
    a.length

    /**
     * ？声明是可空类型，可以赋值为null
     * */
    var b: String? = "bb"
    b = null

    /*
   * b是可空类型，直接访问可空类型将编译不通过，需要通过?.或者!!.来访问
   * */
    //b.length
    b?.length
    b!!.length

    /*
    * 不推荐这样的写法：链式的连续用!!.
    * */
    val user = User()
    user!!.name!!.subSequence(0, 5)!!.length
}

fun testNullType2() {
    val user: User? = User()

    /**
     * 每次访问都用用?.判断
     * */
    user?.name
    user?.age
    user?.toString()

    /**
     * 或者提前判断是否为空，如果不为空在这个分支里会自动转化为非空类型就可以直接访问了
     * */
    if (user != null) {
        user.name
        user.age
        user.toString()
    }

    /**
     * 还可以通过let语句，在?.let之后，如果为空不会有任何操作，只有在非空的时候才会执行let之后的操作
     * */
    user?.let {
        it.name
        it.age
        it.toString()
    }

}