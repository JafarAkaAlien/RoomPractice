package com.leblebi.roompractice

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.leblebi.roompractice.databinding.EditFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentEdit:Fragment() {
    private lateinit var binding : EditFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditFragmentBinding.inflate(layoutInflater,container,false)

        val db = Room.databaseBuilder(requireContext(),UserDatabase::class.java, "user_table").build()
        binding.btnYerlesdir.setOnClickListener {
            val yazi = binding.editName
            val reqem = binding.editAge
            if(TextUtils.isEmpty(yazi.text)){
                yazi.error= "Yazi yaz bura"
                yazi.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(reqem.text)){
                reqem.error = "Yasini yaz"
                reqem.requestFocus()
                return@setOnClickListener
            }
            val user=User(0,reqem.text.toString().toInt(), yazi.text.toString())
            lifecycleScope.launch(Dispatchers.IO) {
                db.userDao().insertUser(user)
                withContext(Dispatchers.Main){

                }
            }
            findNavController().navigate(R.id.action_fragmentEdit_to_fragmentHome)

        }

        return binding.root

    }


}