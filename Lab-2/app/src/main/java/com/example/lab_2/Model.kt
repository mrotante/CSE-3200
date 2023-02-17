package com.example.lab_2

import android.os.Parcel
import android.os.Parcelable


data class Model (
    var questionID : Int,
    var questions : AllQuestions,
    var score : Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("questions"),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(questionID)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }
}