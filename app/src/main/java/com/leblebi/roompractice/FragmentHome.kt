package com.leblebi.roompractice


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

import com.leblebi.roompractice.databinding.HomeFragmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentHome:Fragment(){
    private lateinit var binding: HomeFragmentBinding
    private lateinit var itemAdapter: ItemAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater,container,false)

        val db = Room.databaseBuilder(
            requireContext(),UserDatabase::class.java,"user_table"
        ).build()
        setUpRecView()

        GlobalScope.launch {
            itemAdapter.setData(db.userDao().getAll())
        }




        binding.floatingActionButton.setOnClickListener{
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentEdit)
    }

        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.deleteButton.setOnClickListener{
        GlobalScope.launch {
            db.userDao().delete()

        }
        itemAdapter.notifyDataSetChanged()
    }


        return binding.root
    }

    private fun setUpRecView() = binding.recyclerView.apply {
        itemAdapter = ItemAdapter{
            val bundle = Bundle()
            bundle.putParcelable("user",it)
            findNavController().navigate(R.id.action_fragmentHome_to_fragmentUpdate,bundle)


        }
        adapter = itemAdapter
        layoutManager = LinearLayoutManager(context)

    }

}