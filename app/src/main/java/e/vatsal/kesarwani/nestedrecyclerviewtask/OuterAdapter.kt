package e.vatsal.kesarwani.nestedrecyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import e.vatsal.kesarwani.nestedrecyclerviewtask.databinding.ItemOuterBinding

class OuterAdapter : RecyclerView.Adapter<OuterAdapter.ViewHolder>() {

    private var list: ArrayList<Model> = arrayListOf()

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
        holder.binding.apply {
            tvRowName.text = list[position].name

            when (list[position].radio) {
                true -> {
                    val innerRadioAdapter = InnerRadioAdapter()
                    rvInnerRadio.adapter = innerRadioAdapter
                    innerRadioAdapter.setData(list)
                }
                false -> {
                    val innerCheckAdapter = InnerCheckAdapter()
                    rvInnerCheck.adapter = innerCheckAdapter
                    innerCheckAdapter.setData(list)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<Model>) {
        val toDoDiffUtil = DiffUtils(list, newList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = newList
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}

class DiffUtils(
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