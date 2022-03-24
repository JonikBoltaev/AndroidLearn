package ru.jonik.androidlearn

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.jonik.androidlearn.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        binding.root.setBackgroundColor(requireArguments().getInt(ARG_COLOR))

        binding.btnGoBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSecretFragment.setOnClickListener { findNavController().navigate(R.id.action_boxFragment_to_secretFragment) }
        binding.btnGenerateNumber.setOnClickListener {
            val number = Random.nextInt(100)
            parentFragmentManager.setFragmentResult(
                REQUEST_CODE,
                bundleOf(EXTRA_RANDOM_NUMBER to number)
            )
            findNavController().popBackStack()
        }
    }

    companion object {
        const val ARG_COLOR = "color"

        const val REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }
}