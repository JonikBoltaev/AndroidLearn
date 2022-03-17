package ru.jonik.androidlearn

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.jonik.androidlearn.databinding.ActivityMainBinding
import ru.jonik.androidlearn.model.Options

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

//        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(KEY_OPTIONS, options)
//    }

    private fun onOpenBoxPressed() {
        TODO("Not yet implemented")
    }

    private fun onOptionsPressed() {
        TODO("Not yet implemented")
    }

    private fun onAboutPressed() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    // Завершение текущей активити (Т.к она стартовая, то приложение закрывается)
    private fun onExitPressed() {
        finish()
    }
}