package com.example.mindyourpet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mindyourpet.databinding.RemindersListFragmentBinding

class RemindersListFragment : Fragment() {

    companion object {
        fun newInstance() = RemindersListFragment()
        val reminders = listOf<Reminder>(Reminder("Drugs"), Reminder("Get Food"))
    }

    private lateinit var viewModel: RemindersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: RemindersListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.reminders_list_fragment, container, false)

        val adapter = ReminderAdapter()
        binding.reminderList.adapter = adapter

        val application = requireNotNull(this.activity).application

        val remindersListViewModel = ViewModelProvider(this, RemindersListViewModelFactory(reminders,application)).get(RemindersListViewModel::class.java)

        binding.remindersListViewModel = remindersListViewModel

        binding.lifecycleOwner = this

        remindersListViewModel.listOfReminders.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RemindersListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}