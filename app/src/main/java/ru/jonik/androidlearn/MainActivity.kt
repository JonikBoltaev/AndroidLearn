package ru.jonik.androidlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import ru.jonik.androidlearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListViewWithSimpleGeneratedData()
    }

    // Базовая генерация даты
    private fun setupListViewWithSimpleGeneratedData() {
        val data = (1..100).map {
            mapOf(
                KEY_TITLE to "Item #$it",
                KEY_DESCRIPTION to "Description #$it"
            )
        }
        // Инициализация адаптера
        // SimpleAdapter - Требует данные определенной структуры данных (List с ключ-значение)
        val adapter = SimpleAdapter( // Принимает 5 аргументов
            this,
            data, // Передаем данные
            //TODO
            R.layout.item_custom, // Кастомный макет
            arrayOf(
                KEY_TITLE,
                KEY_DESCRIPTION
            ), // Передаем KEY_TITLE на text1 и так же для след элемента
            intArrayOf(R.id.tv_title, R.id.tv_description)
        )
        // Вызов свойства adapter и присваиваем созданный адаптер
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemTitle = data[position][KEY_TITLE]
                val selectedItemDescription = data[position][KEY_DESCRIPTION]

                val dialog = AlertDialog.Builder(this)
                    .setTitle(selectedItemTitle)
                    .setMessage(getString(R.string.item_selected_message, selectedItemDescription))
                    .setPositiveButton("Ok") { dialog, which -> }
                    .create()
                dialog.show()
            }
    }

    companion object {
        @JvmStatic
        val KEY_TITLE = "title"

        @JvmStatic
        val KEY_DESCRIPTION = "description"
    }
}

//    private fun setupListView() {

//        // Создание данных для адаптера
//        val data: List<Map<String, String>> = listOf(
//            mapOf(
//                KEY_TITLE to "1 title",
//                KEY_DESCRIPTION to "1 description"
//            ),
//            mapOf(
//                KEY_TITLE to "2 title",
//                KEY_DESCRIPTION to "2 description"
//            ),
//            mapOf(
//                KEY_TITLE to "3 title",
//                KEY_DESCRIPTION to "3 description"
//            )
//        )
//        // Инициализация адаптера
//        // SimpleAdapter - Требует данные определенной структуры данных (List с ключ-значение)
//        val adapter = SimpleAdapter( // Принимает 5 аргументов
//            this,
//            data, // Передаем данные
//            android.R.layout.simple_list_item_2, // Стандартный макетный файл для вывода каждого элемента списка. Состоит из 2-х вертикальных textView
//            arrayOf(KEY_TITLE, KEY_DESCRIPTION), // Передаем KEY_TITLE на text1 и так же для след элемента
//            intArrayOf(android.R.id.text1, android.R.id.text2)
//        )
//        // Вызов свойства adapter и присваиваем созданный адаптер
//        binding.listView.adapter = adapter
