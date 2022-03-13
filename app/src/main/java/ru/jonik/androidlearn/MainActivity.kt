package ru.jonik.androidlearn

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import ru.jonik.androidlearn.databinding.ActivityMainBinding
import ru.jonik.androidlearn.databinding.DialogAddCharacterBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListViewWithArrayAdapter()
        binding.btnAdd.setOnClickListener { onAddPressed() }

    }


    // Создание даты
    private fun setupListViewWithArrayAdapter() {
        val data = mutableListOf(
            Character(id = UUID.randomUUID().toString(), name = "Sven"),
            Character(id = UUID.randomUUID().toString(), name = "Juggernaut"),
            Character(id = UUID.randomUUID().toString(), name = "Faceless void"),
            Character(id = UUID.randomUUID().toString(), name = "Axe")
        )
        //Создание адаптера
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            data
        )

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                deleteCharacter(it)
            }
        }
    }

    // Создание слушателя
    private fun onAddPressed() {
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Create character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { d, which ->
                val name = dialogBinding.edCharacterName.text.toString()
                if (name.isNotBlank()) {
                    createCharacter(name)
                }
            }
            .create()
        dialog.show()
    }

    private fun createCharacter(name: String) {
        val character = Character(
            id = UUID.randomUUID().toString(),
            name = name
        )
        adapter.add(character)
    }

    // Удаление персонажа
    private fun deleteCharacter(character: Character) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                adapter.remove(character)
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete character")
            .setMessage("Delete the character: $character")
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

    class Character(
        val id: String,
        val name: String
    ) {
        override fun toString(): String {
            return name
        }
    }
}