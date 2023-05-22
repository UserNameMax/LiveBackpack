package domain

class Logic(
    objectFactory: ObjectFactory
) {
    var population: List<MyObject>

    init {
        population = objectFactory.objects().toMutableList()
    }

    fun live() {
        if (population.isNotEmpty()){
            val sortedPopulation = population.sorted()
            val newPopulation = mutableListOf<MyObject>()
            newPopulation.add(sortedPopulation[0].cros(sortedPopulation[1]))
            newPopulation.add(sortedPopulation[0].cros(sortedPopulation[2]))
            newPopulation.add(sortedPopulation[1].cros(sortedPopulation[2]))
            for (index in 0..2) {
                for (step in 0..5) {
                    newPopulation.add(newPopulation[index].mutate())
                }
            }
            population = newPopulation
        }
    }
}