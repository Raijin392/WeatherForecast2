package com.example.weatherforecast2.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.weatherforecast2.R
import com.example.weatherforecast2.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main), MenuProvider {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.run {
            toolbarMain.let {
                (activity as? AppCompatActivity)?.setSupportActionBar(it)
            }
        }

        initDrawerNav(binding?.toolbarMain)

        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.CREATED)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_search -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PickOfSettlementsFragment())
                    .addToBackStack("MainFragment")
                    .commit()
                true
            }
            else -> false
        }
    }

    private fun initDrawerNav(toolbar: androidx.appcompat.widget.Toolbar?) {
        toolbar?.run {
            setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
            setNavigationOnClickListener {
                Toast.makeText(context, "Drawer Nav", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


