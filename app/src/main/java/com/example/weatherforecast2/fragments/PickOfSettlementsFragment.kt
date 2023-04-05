package com.example.weatherforecast2.fragments

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.weatherforecast2.R
import com.example.weatherforecast2.databinding.FragmentPickOfSettlementsBinding

class PickOfSettlementsFragment : Fragment(R.layout.fragment_pick_of_settlements), MenuProvider {

    private var binding: FragmentPickOfSettlementsBinding? = null
    private var words = listOf("Kazan",
                               "Salavat",
                               "New-York",
                               "Moscow",
                               "Kukmor")

    private var arrayAdapter: ArrayAdapter<String>? = null

    private var searchView: SearchView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPickOfSettlementsBinding.bind(view)

        binding?.run {
            pickOfSettlementsToolbar.let {
                (activity as? AppCompatActivity)?.setSupportActionBar(it)
            }

            pickOfSettlementsToolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            pickOfSettlementsToolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            arrayAdapter = activity?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_list_item_1,
                    words)
            }

            listCity.adapter = arrayAdapter

        }

        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.CREATED)

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_settlements_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.action_search -> {
                searchView?.queryHint = "Enter city"
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        arrayAdapter?.filter?.filter(p0)
                        return false
                    }

                })
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}