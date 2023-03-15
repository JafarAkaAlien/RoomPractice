package com.leblebi.roompractice

import android.os.Bundle
import android.service.autofill.UserData
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.leblebi.roompractice.databinding.UpdateFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentUpdate:Fragment() {
    private lateinit var binding: UpdateFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UpdateFragmentBinding.inflate(inflater,container,false)
        val yazi:User? = arguments?.getParcelable("user")
        binding.updateName.setText(yazi?.name)
        binding.updateAge.setText(yazi!!.age.toString())

        val db = UserDatabase.getInstance(requireContext())

        binding.btnUpdate.setOnClickListener {
            val ad = binding.updateName
            val reqem = binding.updateAge
            if(TextUtils.isEmpty(ad.text)){
                ad.error= "Yazi yaz bura"
                ad.requestFocus()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(reqem.text)){
                reqem.error = "Yasini yaz"
                reqem.requestFocus()
                return@setOnClickListener
            }
            val user=User(yazi.id,reqem.text.toString().toInt(), ad.text.toString())
            lifecycleScope.launch(Dispatchers.IO) {
                db.userDao().update(user)
                withContext(Dispatchers.Main){

                }
            }
            findNavController().navigate(R.id.action_fragmentUpdate_to_fragmentHome)

        }

        binding.silButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                db.userDao().delete(yazi)
            }

            findNavController().navigate(R.id.action_fragmentUpdate_to_fragmentHome)
        }

        return binding.root
    }
}













