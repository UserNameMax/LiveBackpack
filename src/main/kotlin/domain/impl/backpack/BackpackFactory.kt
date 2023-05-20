package domain.impl.backpack

import domain.MyObject
import domain.ObjectFactory
import java.lang.Exception
import kotlin.random.Random

class BackpackFactory(val weight: Int, val staf: List<Staf>) : ObjectFactory {
    override fun objects(): List<MyObject> {
        val result = mutableListOf<Backpack>()
        val correctStaf = staf.filter { it.weight<=weight }
        val r = Random
        if (correctStaf.isEmpty()){
            throw Exception("Купи рюкзак побольше")
        }
        for (i in 0..5) {
            val firstStaf = listOf(correctStaf[r.nextInt(0, correctStaf.size)])
            result.add(Backpack(weightMax = weight, staf = staf - firstStaf, insideStaf = firstStaf))
        }
        return result
    }
}