package com.example.ktlearntest.open

/**
 * 在 Kotlin 中所有类都有一个共同的超类 Any，Any 有三个方法：equals()、 hashCode() 与 toString()
 *
 * 在Kotlin中，所有的类默认都是final的，如果你需要允许它可以被继承，那么你需要使用open声明：
 */
open class Animal(age: Int = 0) {

    init {
        println(age)
    }

    /**
     * 覆盖方法：
     * Kotlin的类成员默认是隐藏的，也就是无法被覆盖，如果要覆盖我们需要用到显式修饰符（open）
     */
    open fun eat() {

    }

    /**
     * 覆盖属性：
     * 属性覆盖与方法覆盖类似；在超类中声明然后在派生类中重新声明的属性必须以 override 开头，并且它们必须具有兼容的类型。
     */
    open val foot = 2
}

class Cat(age: Int) : Animal(age) {

}

class Dog: Animal() {
    /**
     * 复写方法和属性 override
     */
    override fun eat() {
        super.eat()
    }

    override val foot = 3
}