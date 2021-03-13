package com.android.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DetailsActivity : AppCompatActivity() {

    var mPopulationTv:TextView? = null
    var mRegionTv:TextView? = null
    var mCapitalTv:TextView? = null
//    var mFlagIv:ImageView? = null

    var mTitleTv:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var mContext:Context  = this

        var countryName: String?    = intent.getStringExtra("countryName")
        var capital: String?        = intent.getStringExtra("capital")
        var region: String?         = intent.getStringExtra("region")
        var population:Int?      = intent.getIntExtra("population", 0)
//        var flag:String?            = intent.getStringExtra("flag")

        mTitleTv         =   findViewById(R.id.id_details_title_tv)
        mPopulationTv    =  findViewById(R.id.id_population_value_tv)
        mRegionTv        =  findViewById(R.id.id_region_value_tv)
        mCapitalTv       =  findViewById(R.id.id_capital_value_tv)
//        mFlagIv                 =  findViewById(R.id.id_flag_iv)
        mTitleTv?.text   =   "Country :" + countryName

        mPopulationTv?.text = population.toString()
        mRegionTv?.text = region
        mCapitalTv?.text = capital

        /*Glide not working
         */
//            try {
//                flag = flag?.replace("https","http",false)
//                Glide.with(this)
//                    .load(flag)
//                .into(mFlagIv!!);
//            } catch (e: IOException) {
//            }
//







    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }
}