package ru.jonik.androidlearn

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.jonik.androidlearn.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.btnGreen.setOnClickListener { openFragment(Color.rgb(123, 145, 123)) }
        binding.btnAqua.setOnClickListener { openFragment(Color.rgb(127, 255, 212)) }

        parentFragmentManager.setFragmentResultListener(
            BoxFragment.REQUEST_CODE,
            viewLifecycleOwner
        ) { _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFragment(color: Int) {
        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment,
            bundleOf(BoxFragment.ARG_COLOR to color)
        )
    }
}