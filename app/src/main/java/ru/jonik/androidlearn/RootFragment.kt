package ru.jonik.androidlearn

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.jonik.androidlearn.databinding.FragmentRootBinding

// TODO MVVM
class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.btnGreen.setOnClickListener { openFragment(Color.rgb(123, 145, 123), "Green") }
        binding.btnAqua.setOnClickListener { openFragment(Color.rgb(127, 255, 212), "Aqua") }

        val liveData = findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner) { randomNumber ->
            if (randomNumber != null) {
                Log.d("Jonik", "Generated random number: $randomNumber")
                Toast.makeText(
                    requireContext(),
                    "Generated random number: $randomNumber",
                    Toast.LENGTH_SHORT
                ).show()
                liveData.value = null
            }
        }
    }

    private fun openFragment(color: Int, colorName: String) {

        val directions = RootFragmentDirections.actionRootFragmentToBoxFragment(colorName, color)

        findNavController().navigate(R.id.action_rootFragment_to_boxFragment)
    }
}