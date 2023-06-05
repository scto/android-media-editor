package org.editapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.editapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResultFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var model: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _model: EditViewModel by activityViewModels {
            EditViewModelFactory(
                requireActivity().application,
            )
        }
        model = _model

        // Handle back pressed button
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                model.goToFirstFragment()
            }
        })


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.uiState.collect { uiState ->
                    binding.textviewSecond.text = uiState.messageSecondFragment
                    if(uiState.videoEncodingProgress != null && uiState.videoEncodingProgress != 100){
                        binding.progressEncoding.progress = uiState.videoEncodingProgress
                        binding.progressEncoding.visibility = View.VISIBLE
                        binding.buttonCancelVideoProcessing.visibility = View.VISIBLE
                    } else {
                        binding.progressEncoding.visibility = View.INVISIBLE
                        binding.buttonCancelVideoProcessing.visibility = View.GONE
                    }

                    if(uiState.fragment == 1) findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}