package com.example.midterm

class Timer(var start : Long=0, var cur : Long=0, var iSStopped : Boolean=true ) {
    fun start() : Long {
        this.start = System.currentTimeMillis()
        return 0;
    }

    fun stop(): Long {
        this.cur = System.currentTimeMillis()
        val displayTime : Long = this.cur-this.start
        this.cur = 0
        this.start = 0
        return displayTime
    }

    fun reset() : Long {
        this.cur = System.currentTimeMillis()
        return this.cur-this.start
    }

    fun getTimeFormatted(time : Long): ArrayList<Long> {
        val list = arrayListOf<Long>()
        list.add(time/1000)
        list.add(time/1000 /60)
        list.add(time/1000 /60/ 60)

        return list
    }
}