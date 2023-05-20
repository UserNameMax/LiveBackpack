package domain.impl.functionMin

import domain.MyObject
import domain.ObjectFactory
import java.util.*

class ObjectFactoryImpl : ObjectFactory {
    override fun objects(): List<MyObject> {
        val result = mutableListOf<MyObject>()
        val r = Random()
        for (i in 1..10) {
            result.add(ObjectImpl(((r.nextInt()%15).toFloat())))
        }
        return result
    }
}