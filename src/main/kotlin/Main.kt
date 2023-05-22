import domain.Logic
import domain.impl.backpack.Backpack
import domain.impl.backpack.BackpackFactory
import domain.impl.backpack.Staf
import presenter.BackpackConsolePresenter

fun main(args: Array<String>) {
    val weigh = 4
    val staf = listOf(
        Staf(weight = 4, price = 4000),
        Staf(weight = 1, price = 2000),
        Staf(weight = 3, price = 2500)
    )
    val logic = Logic(BackpackFactory(weigh,staf))
    for (i in 1..4) {
        logic.live()
    }
    BackpackConsolePresenter().present(logic.population.minOf { it } as Backpack)
}