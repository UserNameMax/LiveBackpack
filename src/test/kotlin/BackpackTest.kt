import domain.Logic
import domain.impl.backpack.Backpack
import domain.impl.backpack.BackpackFactory
import domain.impl.backpack.Staf
import io.qameta.allure.Allure
import io.qameta.allure.Description
import io.qameta.allure.Step
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BackpackTest {
    @Test
    @Description(value = "Проверка решение простой задачи о рюкзаке")
    fun solveSampleTask() {
        val weigh = 4
        val staf = listOf(
            Staf(weight = 4, price = 4000),
            Staf(weight = 1, price = 2000),
            Staf(weight = 3, price = 2500)
        )
        val solves = List<Backpack>(5) { runLive(weigh, staf) }
        val solve = solves.min()
        assertEquals(weigh, solve.insideStaf.sumOf { it.weight })
        assertEquals(4500, solve.insideStaf.sumOf { it.price })
    }

    @Test
    @Description(value = "Проверка ограничение рюкзака на вес")
    fun fullBackpackTest() {
        val weigh = 100
        val staf = listOf(
            Staf(weight = 4, price = 4000),
            Staf(weight = 3, price = 2000),
            Staf(weight = 1, price = 2500)
        )
        val solve = runLive(weigh, staf)
        assertEquals(staf.sumOf { it.price }, solve.insideStaf.sumOf { it.price })
    }

    @Test
    @Description(value = "Проверка некоректного условия")
    fun emptyBackpack(){
        val weigh = 5
        val staf = listOf(
            Staf(weight = 40, price = 4000),
            Staf(weight = 30, price = 2000),
            Staf(weight = 10, price = 2500)
        )
        val solve = runLive(weigh, staf)
        assertTrue(solve.insideStaf.isEmpty())
    }
    @Step("Решение задачи о рюкзаке ")
    fun runLive(weigh: Int, stafs: List<Staf>): Backpack {
        val logic = Logic(BackpackFactory(weigh, stafs))
        for (i in 1..4) {
            logic.live()
        }
        val result = (logic.population.minOfOrNull { it } as? Backpack) ?: Backpack(weigh, listOf(), listOf())
        Allure.addAttachment("Результат", "text/plain", result.toString())
        return result
    }
}