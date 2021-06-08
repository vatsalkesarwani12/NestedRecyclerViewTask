package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemInner1Binding

class InnerRadioAdapter : RecyclerView.Adapter<InnerRadioAdapter.ViewHolder>() {

    private var list: ArrayList<Model> = arrayListOf()

    private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ItemInner1Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemInner1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            radioButton.text = list[position].name
            radioButton.setOnClickListener {
                lastSelectedPosition = position
                notifyDataSetChanged()
            }
            radioButton.isChecked = lastSelectedPosition == position
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<Model>) {
        val toDoDiffUtil = DiffUtilsInnerRadio(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtilsInnerRadio(
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