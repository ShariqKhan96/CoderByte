package app.bayut.coderbyte.framework

import app.bayut.coderbyte.domain.datamodels.Book

interface BookSelectionObserver {
    fun onBookSelected(book: Book)
}