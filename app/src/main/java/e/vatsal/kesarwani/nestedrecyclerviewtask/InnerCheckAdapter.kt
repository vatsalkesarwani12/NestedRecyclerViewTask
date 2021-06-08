package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemInner2Binding

class InnerCheckAdapter : RecyclerView.Adapter<InnerCheckAdapter.ViewHolder>() {

    private var list: ArrayList<Model> = arrayListOf()

    inner class ViewHolder(val binding: ItemInner2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemInner2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            checkbox.text = list[position].name
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<Model>) {
        val toDoDiffUtil = DiffUtilsInnerCheck(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtilsInnerCheck(
    private val oldList: List<Model>,
    private val newList: List<Model>
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
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}