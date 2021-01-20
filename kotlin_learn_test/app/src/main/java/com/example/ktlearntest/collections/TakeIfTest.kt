package com.example.ktlearntest.collections

/**
 * https://zhuanlan.zhihu.com/p/32710058
 * 另外，对于 takeIf 的使用有一个需要小心的地方，先看看下面的代码：
// Syntactically still correct. But logically wrong!!
someObject?.takeIf{ status }.apply{ doThis() }
// The correct one (notice the nullability check ?)
someObject?.takeIf{ status }?.apply{ doThis() }
第一行中的 doThis() 方法一定会执行而无论 status 是 true 还是 false，因为 doThis() 不是 someObject 中的方法。
这里 takeIf 后的 ? 符是很容易忽视但又非常重要的。
 */

fun main() {
    val mList = mutableListOf(1, 2, 3, 4, 5, 6)
//    println(mList)

    mList
        .takeIf {
            it.size > 13
        }?.takeIf {
            it.contains(13)
        }
        .let {//注意：不加?，无论true/false这里都会执行，
            println("takeIf, $it")
        }

//    mList.filterNot {
//        it == 2
//    }.let {
//        println("filterNot, $it")//[1, 3, 4, 5, 6]
//    }
//
//    mList.filter { it == 3 }.let {
//        println("filter, $it")//[3]
//    }

    /**
     * 在可能会空的集合中取第一元素
     */
    val first = mList.firstOrNull() ?: "222"
    println("firstOrNull: $first")
}