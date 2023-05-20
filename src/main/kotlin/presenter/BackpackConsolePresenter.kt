package presenter

import domain.impl.backpack.Backpack

class BackpackConsolePresenter: BackpackPresenter {
    override fun present(backpack: Backpack) {
        backpack.insideStaf.forEach {
            println(it.toString())
        }
    }
}