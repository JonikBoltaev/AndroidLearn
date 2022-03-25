package ru.jonik.androidlearn

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.jonik.androidlearn.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        binding.root.setBackgroundColor(args.color)

        binding.btnGoBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSecretFragment.setOnClickListener {
            findNavController().navigate(
                BoxFragmentDirections.actionBoxFragmentToSecretFragment()
            )
        }
        binding.btnGenerateNumber.setOnClickListener {
            val number = Random.nextInt(100)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(EXTRA_RANDOM_NUMBER, number)
            findNavController().popBackStack()
        }
    }

    companion object {
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }
}

