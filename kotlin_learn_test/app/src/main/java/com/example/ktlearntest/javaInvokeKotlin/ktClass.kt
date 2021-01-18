/**
 * 如果不想使用kt后缀，可以使用 @file:JvmName("UtilDemo")来实现
 */
//@file:JvmName("Util")
package com.example.ktlearntest.javaInvokeKotlin


/**
 * 让Kotlin编译器按照从左向右的顺序依次为每一个可选参数生成重载
 */
@JvmOverloads
fun joinToString(
    param1: String = "",
    param2: String,
    param3: String = "",
    param4: String = ""
): String {
    return StringBuilder()
        .append(param1)
        .append(param2)
        .append(param3)
        .append(param4)
        .toString()
}

/**
 * 伴生对象：必须声明在某个类中。
 * 每个类可以对应一个半生对象，伴生对象的成员全局独一份，伴生对象类似java中的静态成员。
 *
 * 当我们将Java文件的静态方法迁移到Kotlin中时，我们会将其放在 companion object中，
 * 但是这样处理之后在Java文件中无法直接调用，得通过companion对象实例方法来调用。
 * Kotlin提供了 @JvmStatic 注解。他会让Kotlin在编译器完成类封装后生成一个静态方法。
 */
class MyUtils {
    companion object {
        @JvmStatic
        fun getInstance(): Int {
            return 1
        }
    }

}

