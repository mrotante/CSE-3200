package com.example.lab_4

import android.media.MediaPlayer
import android.os.Handler
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RadioView(
    private val radioArray: AllRadios,
    private val mediaHandler: Handler,
    private val mediaPlayer: MediaPlayer,
    private val nowPlaying : TextView
) :
    RecyclerView.Adapter<RadioView.RadioViewHolder>() {
    class RadioViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val radioTextHolder: TextView = itemView.findViewById(R.id.radioText)
        private val radioImageHolder: ImageView = itemView.findViewById(R.id.radioImage)
        private var curRadio : Int = -1
        private lateinit var handler : Handler
        private lateinit var radios : AllRadios
        private lateinit var player : MediaPlayer
        private lateinit var nowPlaying : TextView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(title: String, img : Int, num : Int, h : Handler, array : AllRadios,
                 mPlayer : MediaPlayer, playing : TextView) {
            radioTextHolder.text = title
            radioImageHolder.setImageResource(img)
            curRadio = num
            handler = h
            radios = array
            player = mPlayer
            nowPlaying = playing
        }


        override fun onClick(p0: View?) {
            for(i in 0 until radios.listofRadios.size) {
                if(!radios.listofRadios[i].isPlaying) {
                    continue
                } else {
                    player.stop()
                    player.reset()
                    radios.listofRadios[i].isPlaying = false
                    if( i == curRadio ) {
                        nowPlaying.text = "Now Playing: None"
                        return
                    }
                }
            }
            player.setDataSource(radios.listofRadios[curRadio].link)
            player.prepare()
            player.start()
            radios.listofRadios[curRadio].isPlaying = true
            nowPlaying.text = "Now Playing: " + radios.listofRadios[curRadio].radioID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.radio_layout, parent, false)
        return RadioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return radioArray.listofRadios.size
    }

    override fun onBindViewHolder(holder: RadioViewHolder, pos: Int) {
        holder.bind(radioArray.listofRadios[pos].radioID, radioArray.listofRadios[pos].imgID,
        pos, mediaHandler, radioArray, mediaPlayer, nowPlaying)
    }
}