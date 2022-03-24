package ru.jonik.androidlearn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.jonik.androidlearn.databinding.FragmentSecretBinding

class SecretFragment : Fragment(R.layout.fragment_secret) {
    private lateinit var binding: FragmentSecretBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecretBinding.bind(view)

        binding.btnGoBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnGoRoot.setOnClickListener {
            findNavController().popBackStack(
                R.id.rootFragment,
                false
            )
        }
    }
}