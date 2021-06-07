package e.vatsal.kesarwani.nestedrecyclerviewtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val list : ArrayList<String> = arrayListOf()
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
        list.add("Hello")
        list.add("this")
        list.add("is")
        list.add("my")
        list.add("task")
        list.add("for")
        list.add("day")
        list.add("one")
    }
}