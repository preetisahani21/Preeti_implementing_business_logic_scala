package BusinessLogic



import BusinessLogic.JobsRepo.GenericObservable
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods



case class Client(id: String, name: String, inboundFeedUrl: String, jobGroups: List[JobGroup])
case class JobGroup(id: String, rules: List[Rule], sponsoredPublishers: List[Publisher])
case class Publisher(id: String, isActive: Boolean, var outboundFileName: List[Job])
case class Rule(jobAttribute: String, operator: String, data: String)
case class Job( title: String, company: String, city: String, state: String, country: String, description: String, referencenumber: Int, url: String, date: String, category: String, department : String)





object JobsDivision extends App{
  implicit val formats = DefaultFormats

  def getEntity[T](path: String)(implicit m: Manifest[T]): T = {
    val entitySrc = scala.io.Source.fromFile(path)
    val entityStr = try entitySrc.mkString finally entitySrc.close()
    JsonMethods.parse(entityStr).extract[T]
  }

  lazy val jobs = getEntity[List[Job]]("jobs.json")


  for(i <- jobs) {
    println(i)
  }


  val listOfRules1 = List(Rule("country", "equals", "India"),
    Rule("title", "contains", "Manager"),
    Rule("city", "equals", "Hyderabad"))

  val listOfRules2 = List(Rule("state", "equals", "California"),
    Rule("referencenumber", "equals", "9"))

  val listOfRules3 = List(Rule("referencenumber", "equals", "9"),
    Rule("state", "equals", "California"))

  val listOfRules4 = List(Rule("description", "contains", "support"), Rule("description", "not contains", "big data"))

  println("yo")

  println(listOfRules1)
  println(listOfRules2)
  println(listOfRules3)



  var emptyJobList = List[Job]()
  val linkedIn = Publisher("linkedin", isActive = true, emptyJobList)
  val stackOverflow = Publisher("stackoverflow", isActive = true, emptyJobList)
  val monster = Publisher("monster", isActive = true, emptyJobList)
  val indeed = Publisher("indeed", isActive = true, emptyJobList)

  val jobGroup1 = JobGroup("11", listOfRules1, List(linkedIn, stackOverflow))
  val jobGroup2 = JobGroup("22", listOfRules2, List(stackOverflow, monster))
  val jobGroup3 = JobGroup("33", listOfRules3, List(linkedIn, indeed))
  //val jobGroup4 : JobGroup = JobGroup("35", listOfRules4, List(stackOverflow))
  val listOfRules5 = List(Rule("city", "begins with", "R"), Rule("title", "not begins with", "D"), Rule("title", "not begins with", "S"))
  val jobGroup5: JobGroup = JobGroup("36", listOfRules5, List(linkedIn, stackOverflow))
  val jobsInJobGroup5 = JobFilter.jobsFilter(jobGroup5,jobs)
  println(jobsInJobGroup5)

  println("xo")

  println(jobGroup1)
  println(jobGroup2)
  println(jobGroup3)

  val jobsInJobGroup1 = JobFilter.jobsFilter(jobGroup1, jobs)
  val jobsInJobGroup2 = JobFilter.jobsFilter(jobGroup2, jobs)
  val jobsInJobGroup3 = JobFilter.jobsFilter(jobGroup3, jobs)
  val jobsInJobGroup4 = JobFilter.jobsFilter(jobGroup5, jobs)
  // job => job.id list of applicable job groups,

  println("xoxo")
  //println(jobsInJobGroup1)
  println(jobsInJobGroup2)
  println(jobsInJobGroup3)
  println(jobsInJobGroup4)

  case class JobWithTag(job: Job, var tag: List[String])


  var jobsWithTag = List[JobWithTag]()
  val emptyStringList = List[String]()

  jobs.foreach(i => jobsWithTag = jobsWithTag :+ JobWithTag(i, emptyStringList))

  jobsWithTag.foreach(i => jobsInJobGroup1.foreach(j => if(i.job == j){i.tag = i.tag :+ jobGroup1.id }))
  jobsWithTag.foreach(i => jobsInJobGroup2.foreach(j => if(i.job == j){i.tag = i.tag :+ jobGroup2.id }))
  jobsWithTag.foreach(i => jobsInJobGroup3.foreach(j => if(i.job == j){i.tag = i.tag :+ jobGroup3.id }))


  println("xoxoxo")

  jobsWithTag.foreach(i => println(i.tag))

  println("xoxoxoxo")

  jobsWithTag.foreach(i => if(i.tag.size > 1){i.tag = List(i.tag.min)
    println(i.tag)})



  case class Person(name: String, age: Int)
  val preeti = Person("Preeti", 23)
  val renu = Person("Renu", 23)

  val ans = JsonUtil.toJson(preeti)
  println(ans)


  val publishers = List[Publisher](linkedIn, stackOverflow, monster, indeed)
  val jobGroups = List(jobGroup1, jobGroup2, jobGroup3)
  jobsWithTag.foreach( x =>
    jobGroups.foreach(y => if(x.tag.nonEmpty && x.tag.head == y.id) {y.sponsoredPublishers.foreach(z => z.outboundFileName = z.outboundFileName :+ x.job )})
  )

  println(publishers)



  publishers.foreach(i => i.outboundFileName.foreach(j => JobsRepo.collection.insertOne(j).results()))

  publishers.foreach(x => println(JsonUtil.toJson(x.outboundFileName)))

  publishers.foreach(x => JsonUtil.writeJson(x.outboundFileName, x.id+".json"))




}

