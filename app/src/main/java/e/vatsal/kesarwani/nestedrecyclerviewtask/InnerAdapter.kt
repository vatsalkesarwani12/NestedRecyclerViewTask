package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemInner1Binding
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemInner2Binding

/***
 ** ignore
 **/
class InnerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val RADIO = 1
        const val CHECK = 2
    }

    private var lastSelectedPosition = -1

    inner class RadioViewHolder(val radioBinding : ItemInner1Binding) : RecyclerView.ViewHolder(radioBinding.root)

    inner class CheckViewHolder(val checkBinding : ItemInner2Binding) : RecyclerView.ViewHolder(checkBinding.root)

    private var list: ArrayList<Model> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            RADIO -> RadioViewHolder(
                ItemInner1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> CheckViewHolder(
                ItemInner2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position].radio) {
            true -> {
                (holder as RadioViewHolder).radioBinding.apply {
                    radioButton.text = list[position].name
                    radioButton.setOnClickListener {
                        lastSelectedPosition = position
                        notifyDataSetChanged()
                    }
                    radioButton.isChecked = lastSelectedPosition == position
                }
            }
            false -> {
                (holder as CheckViewHolder).checkBinding.apply {
                    checkbox.text = list[position].name
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when(list[position].radio){
            true -> RADIO
            false -> CHECK
        }
    }

    fun setData(newList: ArrayList<Model>) {
        val toDoDiffUtil = DiffUtilsInner(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtilsInner(
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