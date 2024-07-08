package com.myapp.viewmodetest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import java.io.Closeable


class MainActivityVM : ViewModel() {
    var number = 0

    fun addNumber(){
        number++
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun addCloseable(closeable: Closeable) {
        super.addCloseable(closeable)
    }

    override fun addCloseable(closeable: AutoCloseable) {
        super.addCloseable(closeable)
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}
