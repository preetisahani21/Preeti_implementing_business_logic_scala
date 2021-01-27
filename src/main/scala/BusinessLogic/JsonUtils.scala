package BusinessLogic


import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonUtils extends SprayJsonSupport with DefaultJsonProtocol {

  //implicit val employeeJsonFormatter: RootJsonFormat[Client] = DefaultJsonProtocol.jsonFormat4(Client)
  implicit val employeeRequestFormat: RootJsonFormat[PublisherRequest] = jsonFormat11(PublisherRequest)

}

