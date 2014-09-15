package me.jeffshaw
package digitalocean

import scala.concurrent.{Future, ExecutionContext}

package object responses {

  private[digitalocean] case class Meta(total: BigInt)

  private[digitalocean] case class Pages(
    first: Option[String],
    prev: Option[String],
    next: Option[String],
    last: Option[String]
  )

  private[digitalocean] case class ActionRef(
    id: BigInt,
    rel: String,
    href: String
  ) {
    def action(implicit client: DigitalOceanClient, ec: ExecutionContext): Future[digitalocean.Action] = {
      digitalocean.Action(id)
    }
  }

  private[digitalocean] case class Links(
    pages: Option[Pages],
    actions: Seq[ActionRef]
  )

  private[digitalocean] sealed trait Page[T] {
    def page: Seq[T]
    val meta: Option[Meta]
    val links: Option[Links]

    def size: Option[BigInt] = {
      meta.map(_.total)
    }
  }

  private [digitalocean] case class Droplet(
    droplet: digitalocean.Droplet,
    links: Links
  )

  private [digitalocean] case class Droplets(
    droplets: Seq[digitalocean.Droplet],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Droplet] {
    override val page = droplets
  }

  private [digitalocean] case class Action(action: digitalocean.Action)

  private [digitalocean] case class Actions(
    actions: Seq[digitalocean.Action],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Action] {
    override val page = actions
  }

  private [digitalocean] case class Kernel(kernel: digitalocean.Kernel)

  private [digitalocean] case class Kernels(
    kernels: Seq[digitalocean.Kernel],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Kernel] {
    override val page = kernels
  }

  private [digitalocean] case class Snapshot(snapshot: digitalocean.Image)

  private [digitalocean] case class Snapshots(
    snapshots: Seq[digitalocean.Image],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Image] {
    override val page = snapshots
  }

  private [digitalocean] case class Backups(
    backups: Seq[digitalocean.Image],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Image] {
    override val page = backups
  }

  private [digitalocean] case class Image(image: digitalocean.Image)

  private [digitalocean] case class Images(
    images: Seq[digitalocean.Image],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Image] {
    override val page = images
  }

  private [digitalocean] case class Region(region: digitalocean.Region)

  private [digitalocean] case class Regions(
    regions: Seq[digitalocean.Region],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Region] {
    override val page = regions
  }

  private [digitalocean] case class Size(size: digitalocean.Size)

  private [digitalocean] case class Sizes(
    sizes: Seq[digitalocean.Size],
    meta: Option[Meta],
    links: Option[Links]
  ) extends Page[digitalocean.Size] {
    override val page = sizes
  }
}
