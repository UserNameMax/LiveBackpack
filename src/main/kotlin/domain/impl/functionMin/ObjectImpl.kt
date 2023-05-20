package domain.impl.functionMin

import domain.MyObject
import jdk.jshell.spi.ExecutionControl
import java.util.*
data class ObjectImpl(
    val x: Float
) : MyObject() {
    override fun cros(second: MyObject): MyObject {
        if (second is ObjectImpl)
            return copy(x = (x + second.x)/2)
        else throw ExecutionControl.NotImplementedException("Not Implemented")
    }

    override fun mutate(): MyObject {
        return copy(x = x + Random().nextFloat())
    }

    override fun compareTo(other: MyObject): Int {
        if (other is ObjectImpl) {
            if (f(x) > f(other.x)) return 1
            if (f(x) < f(other.x)) return -1
            return 0
        }
        throw ExecutionControl.NotImplementedException("Not Implemented")
    }

    override fun toString(): String = x.toString()

    companion object {
        fun f(x: Float) = 5 * (x - 10) * (x - 10) + 20
    }
}