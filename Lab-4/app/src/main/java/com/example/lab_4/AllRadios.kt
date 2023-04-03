package com.example.lab_4

import android.content.Context

class AllRadios(context : Context?) {
    public val listofRadios = arrayOf(
        Radio(context?.resources!!.getString(R.string.r1), R.drawable.how_vinyl_made_fig__2,
            false, "https://icecast.walmradio.com:8443/classic?type=.mp3"),
        Radio(context?.resources!!.getString(R.string.r2), R.drawable.christmas_tree_cartoon_colored_clipart_free_vector,
        isPlaying = false, "https://icecast.walmradio.com:8443/christmas?type=.mp3"),
        Radio(context?.resources!!.getString(R.string.r3), R.drawable.yts_62_540x540_ed0ede552feb013821851e2885517b6f_jpg,
            isPlaying = false, "https://icecast.walmradio.com:8443/jazz?type=.mp3"),
        Radio(context?.resources!!.getString(R.string.r4), R.drawable.depositphotos_34179535_stock_illustration_70s_vintage_art_background,
        isPlaying = false, "https://0n-70s.radionetz.de/0n-70s.mp3"),
        Radio(context?.resources!!.getString(R.string.r5), R.drawable._1fkeulwoll,
        false, "https://0n-indie.radionetz.de/0n-indie.mp3"),
        Radio(context?.resources!!.getString(R.string.r6), R.drawable._1575262_fa30_4a7f_9b63_b33329a7fd50,
        false, "https://0n-80s.radionetz.de/0n-80s.mp3"),
        Radio(context?.resources!!.getString(R.string.r7), R.drawable.back_to_the_90s,
        false, "https://0n-90s.radionetz.de/0n-90s.mp3"),
        Radio(context?.resources!!.getString(R.string.r8), R.drawable.ab67616d0000b273ca3e41861d095066970e8e99,
        false, "https://0n-chillout.radionetz.de/0n-chillout.mp3"),
        Radio(context?.resources!!.getString(R.string.r9), R.drawable._7resources_1_mediumsquareat3x,
        false, "https://live.nagaswarafm.com:9200/nagaswaradance"),
        Radio(context?.resources!!.getString(R.string.r10), R.drawable.ab67616d0000b273ca3e41861d095066970e8e99,
        false, "https://0n-smoothjazz.radionetz.de/0n-smoothjazz.aac")
    );
}