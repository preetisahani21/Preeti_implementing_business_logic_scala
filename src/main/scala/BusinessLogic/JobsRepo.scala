//package BusinessLogic
//
//
//
//import org.mongodb.scala.bson.conversions.Bson
//import org.mongodb.scala.model.Updates.{combine, set}
//import org.mongodb.scala.{Completed, MongoCollection}
////import org.utils.JsonUtils
//
//import scala.concurrent.Future
//
//object JobsRepo extends JsonUtils {
//
//  val jobDoc: MongoCollection[PublisherRequest] = DbConfig.jobs
//
//  def createCollection(): Unit = {
//    DbConfig.database.createCollection("publishers").subscribe(
//      (result: Completed) => println(s"$result"),
//      (e: Throwable) => println(e.getLocalizedMessage),
//      () => println("complete"))
//  }
//
//  def insertData(job: PublisherRequest): Future[Completed] = {
//    jobDoc.insertOne(job).toFuture().onComplete()
//  }
//
////  def findAll(): Future[Seq[Client]] = {
////    clientDoc.find().toFuture()
////  }
////
////  def findSome(pageNumber: Int, messagesPerPage: Int): Future[Seq[Client]] = {
////    clientDoc.find().skip((pageNumber - 1) * messagesPerPage).limit(messagesPerPage).sort(ascending("name")).toFuture()
////  }
////
////  def update(cl: Client, id: String): Future[Client] = {
////    clientDoc.findOneAndUpdate(equal("_id", id), setBsonValue(cl), FindOneAndUpdateOptions().upsert(true)).toFuture()
////  }
////
////  def delete(id: String): Future[DeleteResult] = {
////    clientDoc.deleteOne(equal("id", id)).toFuture()
////  }
//
//  //case class Job( title: String, company: String, city: String, state: String, country: String, description: String, referencenumber: Int, url: String, date: String, category: String, department : String)
//
//  private def setBsonValue(job: PublisherRequest): Bson = {
//    combine(
//      set("title", job.title),
//      set("company", job.company),
//      set("city", job.city),
//      set("state", job.state),
//      set("country", job.country),
//      set("description", job.description),
//      set("referencenumber", job.referencenumber),
//      set("url", job.url),
//      set("date", job.date),
//      set("category", job.category),
//      set("department", job.department)
//    )
//  }
package BusinessLogic

import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration



object JobsRepo {

  val codecRegistry = fromRegistries(fromProviders(classOf[Job]), DEFAULT_CODEC_REGISTRY)

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("test").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Job] = database.getCollection("publishers")
  collection.drop().results()


  implicit class DocumentObservable[C](val observable: Observable[Document]) extends ImplicitObservable[Document] {
    override val converter: (Document) => String = (doc) => doc.toJson
  }

  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] {
    override val converter: (C) => String = (doc) => doc.toString
  }

  trait ImplicitObservable[C] {
    val observable: Observable[C]
    val converter: (C) => String

    def results(): Seq[C] = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))

    def headResult() = Await.result(observable.head(), Duration(10, TimeUnit.SECONDS))

    def printResults(initial: String = ""): Unit = {
      if (initial.nonEmpty) print(initial)
      results().foreach(res => println(converter(res)))
    }

    def printHeadResult(initial: String = ""): Unit = println(s"${initial}${converter(headResult())}")
  }

}


