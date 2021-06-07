package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemInnerBinding

class InnerAdapter : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    private var list: ArrayList<String> = arrayListOf()

    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ItemInnerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemInnerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            radioButton.text = list[position]
            radioButton.setOnClickListener {
                lastSelectedPosition = position
                notifyDataSetChanged()
            }
            radioButton.isChecked = lastSelectedPosition == position
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<String>) {
        val toDoDiffUtil = DiffUtilsInner(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtilsInner(
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