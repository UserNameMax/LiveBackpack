package presenter

import domain.impl.backpack.Backpack

interface BackpackPresenter {
    fun present(backpack: Backpack)
}