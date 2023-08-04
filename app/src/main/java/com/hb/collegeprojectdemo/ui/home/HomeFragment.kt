package com.hb.collegeprojectdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hb.collegeprojectdemo.R
import com.hb.collegeprojectdemo.databinding.FragmentHomeBinding
import com.hb.collegeprojectdemo.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

//    private var _binding: FragmentHomeBinding? = null
    private var _binding: FragmentLoginBinding? = null
    private lateinit var homeViewModel : HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonView: Button = binding.submitButton

        setUpObservers()
        setUpListeners()
        return root
    }

    private fun setUpObservers() {
        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.submitButton.text = it
        }
    }

    private fun setUpListeners() {
      binding.submitButton.setOnClickListener{
          findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

      }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}