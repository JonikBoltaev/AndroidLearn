package ru.jonik.androidlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.jonik.androidlearn.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVersionName.text = BuildConfig.VERSION_NAME
        binding.tvVersionCode.text = BuildConfig.VERSION_CODE.toString()
        binding.btnBack.setOnClickListener { finish() }
    }
}