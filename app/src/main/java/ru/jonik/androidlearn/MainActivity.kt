package ru.jonik.androidlearn

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.jonik.androidlearn.databinding.ActivityMainBinding
import kotlin.properties.Delegates.notNull

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var volume by notNull<Int>()
    private var color by notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDefaultDialog.setOnClickListener { showAlertDialog() }
        binding.btnSingleChoice.setOnClickListener { showSingleChoiceAlertDialog() }
        binding.btnMultiChoice.setOnClickListener { showMultiChoiceAlertDialog() }
        volume = savedInstanceState?.getInt(KEY_VOLUME) ?: 50
        color = savedInstanceState?.getInt(KEY_COLOR) ?: Color.WHITE
        updateUi()
    }

    private fun showMultiChoiceAlertDialog() {
        val colorItems = resources.getStringArray(R.array.colors)
        val colorComponents = mutableListOf(
            Color.red(this.color),
            Color.green(this.color),
            Color.blue(this.color)
        )
        val checkboxes = colorComponents
            .map { it > 0 }
            .toBooleanArray()
        val dialog = AlertDialog.Builder(this)
            .setTitle("Color setup")
            .setMultiChoiceItems(colorItems, checkboxes) { _, which, isChecked ->
                colorComponents[which] = if (isChecked) 255 else 0
                this.color = Color.rgb(
                    colorComponents[0],
                    colorComponents[1],
                    colorComponents[2]
                )
            }
            .setPositiveButton("Confirm") { _, _ ->
                this.color = color
                updateUi()
            }
            .create()
        dialog.show()
    }

    private fun showSingleChoiceAlertDialog() {
        val volumeItems = AvailableVolumeValues.createVolumeValues(volume)
        val volumeTextItems = volumeItems.values
            .map { getString(R.string.volume_description, it) }
            .toTypedArray()
        val dialog = AlertDialog.Builder(this)
            .setTitle("Volume setup")
            .setSingleChoiceItems(volumeTextItems, volumeItems.currentIndex) { dialog, which ->
                volume = volumeItems.values[which]
                updateUi()
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

    private fun updateUi() {
        binding.tvVolume.text = getString(R.string.current_volume, volume)
        binding.tvColor.setBackgroundColor(color)
    }

    private fun showAlertDialog() {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(
                    this,
                    "Positive",
                    Toast.LENGTH_SHORT
                ).show()
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                    this,
                    "Negative",
                    Toast.LENGTH_SHORT
                ).show()
                DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(
                    this,
                    "Ignored",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle(R.string.default_alert_Title)
            .setMessage(R.string.default_alert_desc)
            .setPositiveButton("YES", listener)
            .setNegativeButton("NO", listener)
            .setNeutralButton("Ignore", listener)
            .setOnCancelListener {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            }
            .setOnDismissListener {
                Toast.makeText(this, "Dismissed", Toast.LENGTH_SHORT).show()
            }
            .create()
        dialog.show()
    }

    companion object {
        @JvmStatic
        private val KEY_VOLUME = "KEY_VOLUME"

        @JvmStatic
        private val KEY_COLOR = "KEY_COLOR"
    }
}