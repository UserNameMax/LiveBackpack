package domain.impl.backpack

import domain.MyObject
import jdk.jshell.spi.ExecutionControl.NotImplementedException
import kotlin.random.Random

data class Backpack(
    val weightMax: Int,
    val staf: List<Staf>,
    val insideStaf: List<Staf> = listOf()
) : MyObject() {
    override fun cros(second: MyObject): MyObject {
        if (second is Backpack) {
            val r = Random
            val commonStaf = insideStaf.filter { second.insideStaf.contains(it) }
            val otherStaf = ((insideStaf - commonStaf) + (second.insideStaf - commonStaf)).toMutableList()
            val count = (insideStaf.size + second.insideStaf.size) / 2
            val addingStaf = if (count - commonStaf.size != 0) List(count - commonStaf.size) {
                val staf = otherStaf[r.nextInt(0, otherStaf.size)]
                otherStaf.remove(staf)
                staf
            } else listOf()
            return copy(staf = staf + second.staf - addingStaf, insideStaf = commonStaf + addingStaf)
        }
        throw NotImplementedException("Not Implemented")
    }

    override fun mutate(): MyObject {
        if (staf.isEmpty()) return copy()
        val r = Random
        val mayAdd = staf.filter { it -> it.weight + insideStaf.sumOf { it.weight } <= weightMax }
        if (mayAdd.isNotEmpty()) {
            val addingStaf = mayAdd[r.nextInt(0, mayAdd.size)]
            return copy(staf = staf - addingStaf, insideStaf = insideStaf + addingStaf)
        } else {
            val deletingStaf = insideStaf[r.nextInt(0, insideStaf.size)]
            val mayAddingStaf =
                staf.filter { it -> it.weight - deletingStaf.weight + insideStaf.sumOf { it.weight } <= weightMax }
            if (mayAddingStaf.isEmpty()) return copy()
            val addingStaf = mayAddingStaf[r.nextInt(0, mayAddingStaf.size)]
            return copy(staf = staf - addingStaf + deletingStaf, insideStaf = insideStaf + addingStaf - deletingStaf)
        }
    }

    override fun compareTo(other: MyObject): Int {
        if (other is Backpack) {
            return other.insideStaf.sumOf { it.price } - insideStaf.sumOf { it.price }
        }
        throw NotImplementedException("Not Implemented")
    }

    override fun toString(): String = "вес = ${insideStaf.sumOf { it.weight }} цена = ${insideStaf.sumOf { it.price }}"
}