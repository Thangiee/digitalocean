package me.jeffshaw

import org.json4s._

package object digitalocean {
  private [digitalocean] implicit val formats = {
    DefaultFormats.withBigDecimal +
      InstantSerializer +
      NetworkType.Serializer +
      Status.Serializer +
      Inet4AddressSerializer +
      Inet6AddressSerializer +
      ActionResourceTypeSerializer +
      ActionStatusSerializer +
      Image.Type.Serializer +
      RegionEnum.Serializer
  }
}
