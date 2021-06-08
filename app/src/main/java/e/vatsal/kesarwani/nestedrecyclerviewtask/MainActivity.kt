package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list: ArrayList<Model> = arrayListOf()
    private lateinit var outerAdapter: OuterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initArray()
        binding.apply {
            rvOuter.adapter = outerAdapter
        }
        outerAdapter.setData(list)
    }

    private fun initAdapter() {
        outerAdapter = OuterAdapter()
    }

    private fun initArray() {
        list.add(Model("Hello",true))
        list.add(Model("this",false))
        list.add(Model("is",true))
        list.add(Model("my",false))
        list.add(Model("task",true))
        list.add(Model("for",false))
        list.add(Model("day",true))
        list.add(Model("one",false))
    }
}