package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemOuterBinding

class OuterAdapter : RecyclerView.Adapter<OuterAdapter.ViewHolder>() {

    private var list: ArrayList<String> = arrayListOf()

    inner class ViewHolder(val binding: ItemOuterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterAdapter.ViewHolder =
        ViewHolder(
            ItemOuterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OuterAdapter.ViewHolder, position: Int) {
        val innerAdapter = InnerAdapter()
        holder.binding.apply {
            tvRowName.text = list[position]

            rvInner.adapter = innerAdapter
            innerAdapter.setData(list)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<String>) {
        val toDoDiffUtil = DiffUtils(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtils(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
