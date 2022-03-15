package ru.jonik.androidlearn

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import ru.jonik.androidlearn.databinding.ActivityMainBinding
import ru.jonik.androidlearn.databinding.DialogAddCharacterBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Создание даты
    private val data = mutableListOf(
        Character(id = 1, name = "Sven", isCustom = false),
        Character(id = 2, name = "Juggernaut", isCustom = false),
        Character(id = 3, name = "Faceless void", isCustom = false),
        Character(id = 4, name = "Axe", isCustom = false)
    )

    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        binding.btnAdd.setOnClickListener { onAddPressed() }
    }

    private fun setupList() {
        adapter = CharacterAdapter(data) {
            deleteCharacter(it)
        }

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            showCharacterInfo(adapter.getItem(position))
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

    // Функция создания персонажа
    private fun createCharacter(name: String) {
        val character = Character(
            id = Random.nextLong(),
            name = name,
            isCustom = true
        )
        data.add(character)
        adapter.notifyDataSetChanged()
    }

    // Функция показывающая информацию о персонаже при клике
    private fun showCharacterInfo(character: Character) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(character.name)
            .setMessage("Description: ${character.name}")
            .setPositiveButton("Close") { _, _ -> }
            .create()
        dialog.show()
    }

    // Удаление персонажа
    private fun deleteCharacter(character: Character) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                data.remove(character)
                adapter.notifyDataSetChanged()
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete character")
            .setMessage("Delete the character ${character.name}")
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

}