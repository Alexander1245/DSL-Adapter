package com.dart69.dsladapter

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dart69.dsl_adapter.dsl.adapter
import com.dart69.dsladapter.databinding.ItemPetBinding
import kotlin.random.Random

data class Pet(
    val id: Int,
    val name: String,
)
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onStart() {
        super.onStart()
        val petsAdapter = adapter<Pet, ItemPetBinding> {
            itemCallback { oldItem, newItem ->
                oldItem.id == newItem.id
            }
            viewHolder { pet, binding ->
                binding.textViewName.text = pet.name
                binding.root.setOnClickListener {
                    Toast.makeText(this@MainActivity, pet.id.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.apply {
            adapter = petsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}