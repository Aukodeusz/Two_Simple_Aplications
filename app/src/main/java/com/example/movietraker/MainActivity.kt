

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private var itemList: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        itemAdapter = ItemAdapter(itemList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter

        val titleEditText: EditText = findViewById(R.id.editText_title)
        val genreEditText: EditText = findViewById(R.id.editText_genre)
        val descriptionEditText: EditText = findViewById(R.id.editText_description)
        val ratingSeekBar: SeekBar = findViewById(R.id.seekBar_rating)
        val addButton: Button = findViewById(R.id.button_add)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        loadData()

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val genre = genreEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val rating = ratingSeekBar.progress
            val isMovie = radioGroup.checkedRadioButtonId == R.id.radio_movie

            val newItem = Item(title, genre, description, rating, isMovie)
            itemList.add(newItem)
            itemAdapter.notifyDataSetChanged()
            saveData()
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(itemList)
        editor.putString("item list", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("item list", null)
        val type = object : TypeToken<ArrayList<Item>>() {}.type
        itemList = gson.fromJson(json, type) ?: mutableListOf()
    }
}
