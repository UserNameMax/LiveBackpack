package domain.impl.backpack

data class Staf(
    val price: Int,
    val weight: Int
){
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is Staf){
            return price == other.price && weight == other.weight
        }
        return false
    }
}
