package br.com.nglauber.books.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.nglauber.books.ui.fragments.BookFavoritesFragment
import br.com.nglauber.books.ui.fragments.BookListFragmnet

class BookPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> BookListFragmnet()
            else -> BookFavoritesFragment()
        }
}