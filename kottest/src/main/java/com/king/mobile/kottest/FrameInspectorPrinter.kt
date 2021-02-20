package com.king.mobile.kottest

import android.util.Printer

class FrameInspectorPrinter : Printer {
    override fun println(x: String?) {
        println("-----$x")
    }
}