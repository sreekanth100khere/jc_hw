package com.android.activities
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    var mPopulationTv:TextView? = null
    var mRegionTv:TextView? = null
    var mCapitalTv:TextView? = null
    var mFlagIv:ImageView? = null

    var mTitleTv:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var mContext:Context  = this

        var countryName: String?    = intent.getStringExtra("countryName")
        var capital: String?        = intent.getStringExtra("capital")
        var region: String?         = intent.getStringExtra("region")
        var population:Int?      = intent.getIntExtra("population",0)
        var flag:String?            = intent.getStringExtra("flag")

        mTitleTv         =   findViewById(R.id.id_details_title_tv)
        mPopulationTv    =  findViewById(R.id.id_population_value_tv)
        mRegionTv        =  findViewById(R.id.id_region_value_tv)
        mCapitalTv       =  findViewById(R.id.id_capital_value_tv)
        mFlagIv                 =  findViewById(R.id.id_flag_iv)
        mTitleTv?.text   =   "Details :" + countryName

        mPopulationTv?.text = population.toString()
        mRegionTv?.text = region
        mCapitalTv?.text = capital

        Glide.with(this).load(flag)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(mFlagIv);


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}