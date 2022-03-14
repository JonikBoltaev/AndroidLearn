package ru.jonik.androidlearn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ru.jonik.androidlearn.databinding.ItemCustomBinding

typealias OnDeletePressedListener = (Character) -> Unit

class CharacterAdapter(
    // Передаем в конструктор список персонажей
    private val characters: List<Character>,
    private val onDeletePressedListener: OnDeletePressedListener
) : BaseAdapter(), View.OnClickListener {
    override fun getItem(position: Int): Character {
        return characters[position]
    }

    override fun getItemId(position: Int): Long {
        return characters[position].id
    }

    override fun getCount(): Int {
        return characters.size
    }

    // Преобразование элемента данных в элемент списка
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            convertView?.tag as ItemCustomBinding? ?: createBinding(parent.context)

        val character = getItem(position)
        binding.tvTitle.text = character.name
        binding.ivDelete.tag = character
        binding.ivDelete.visibility = if (character.isCustom) View.VISIBLE else View.GONE

        return binding.root
    }

    override fun onClick(v: View) {
        val character = v.tag as Character
        onDeletePressedListener.invoke(character)
    }

    private fun createBinding(context: Context?): ItemCustomBinding {
        val binding = ItemCustomBinding.inflate(LayoutInflater.from(context))
        binding.ivDelete.setOnClickListener(this)
        binding.root.tag = binding
        return binding
    }
}