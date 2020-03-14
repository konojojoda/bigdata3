package com.zx.spark

import java.text.DecimalFormat

object test {
  def main(args: Array[String]): Unit = {
    val format = new DecimalFormat("1110")
    println(format.format(4))
  }
}
