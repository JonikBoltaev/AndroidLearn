package ru.jonik.androidlearn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.jonik.androidlearn.databinding.ActivityMainBinding
import ru.jonik.androidlearn.model.Options
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var options: Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenBox.setOnClickListener { onOpenBoxPressed() }
        binding.btnOptions.setOnClickListener { onOptionsPressed() }
        binding.btnAbout.setOnClickListener { onAboutPressed() }
        binding.btnExit.setOnClickListener { onExitPressed() }

        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == OPTIONS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            options = data?.getParcelableExtra(OptionsActivity.EXTRA_OPTIONS)
                ?: throw IllegalArgumentException("Can't get the updated data from Options")
        }
    }

    private fun onOpenBoxPressed() {
        val intent = Intent(this, BoxSelectionActivity::class.java)
        intent.putExtra(OptionsActivity.EXTRA_OPTIONS, options)
        startActivity(intent)
    }

    private fun onOptionsPressed() {
        val intent = Intent(this, OptionsActivity::class.java)
        intent.putExtra(OptionsActivity.EXTRA_OPTIONS, options)
        startActivityForResult(intent, OPTIONS_REQUEST_CODE)
    }

    private fun onAboutPressed() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    // Завершение текущей активити (Т.к она стартовая, то приложение закрывается)
    private fun onExitPressed() {
        finish()
    }

    companion object {
        @JvmStatic
        private val KEY_OPTIONS = "OPTIONS"

        @JvmStatic
        private val OPTIONS_REQUEST_CODE = 1
    }
}