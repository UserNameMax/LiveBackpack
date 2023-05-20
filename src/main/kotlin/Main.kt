import domain.Logic
import domain.impl.backpack.Backpack
import domain.impl.backpack.BackpackFactory
import domain.impl.backpack.Staf
import domain.impl.functionMin.ObjectFactoryImpl
import presenter.BackpackConsolePresenter

fun main(args: Array<String>) {
    val weigh = 4
    val staf = listOf(
        Staf(weight = 4, price = 4000),
        Staf(weight = 3, price = 2000),
        Staf(weight = 1, price = 2500)
    )
    val logic = Logic(BackpackFactory(weigh,staf))
    println(logic.population.sorted())
    for (i in 1..50) {
        logic.live()
        println(logic.population.sorted())
    }
    val presenter = BackpackConsolePresenter()
    presenter.present(logic.population.sorted().first() as Backpack)
}