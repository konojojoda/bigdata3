package com.zx.spark

import java.text.DecimalFormat

package object project1 {
  case class CityRemark(cityName: String, cityRatio: Double) {
    val formatter = new DecimalFormat("0.00%")
    override def toString: String = s"$cityName:${formatter.format(cityRatio)}"
  }
}
