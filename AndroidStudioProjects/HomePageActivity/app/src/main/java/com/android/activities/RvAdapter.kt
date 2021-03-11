package com.android.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class RvAdapter(context:Context, iCountryList:ArrayList<String>) :
    RecyclerView.Adapter<RvAdapter.CountryRowVh>() {
    private  var mCountryList: ArrayList<String> = iCountryList
    private var context: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRowVh {
        context = parent.context
        return CountryRowVh(LayoutInflater.from(context).inflate(R.layout.row_country, null))
    }

    override fun onBindViewHolder(holder: CountryRowVh, position: Int) {
        val country:String = mCountryList[position]
        holder.countryNameTv.text = country
        holder.countryNameTv.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"List row clicked",Toast.LENGTH_LONG).show()
        })
    }

    override fun getItemCount(): Int {
        return mCountryList.size
    }



    inner class CountryRowVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryNameTv: TextView

        init {
            countryNameTv    = itemView.findViewById(R.id.id_row_country_tv)

        }
    }

}