package domain

abstract class MyObject : Comparable<MyObject> {
    abstract fun cros(second: MyObject): MyObject
    abstract fun mutate(): MyObject
    abstract override fun compareTo(other: MyObject): Int
}