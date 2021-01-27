package TestLogic
import BusinessLogic._
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods
import org.scalatest.FunSuite

class JobsDivisionTest extends FunSuite{

  //val listOfRules = listOfRules1
  implicit val formats: DefaultFormats.type = DefaultFormats

  def getEntity[T](path: String)(implicit m: Manifest[T]): T = {
    val entitySrc = scala.io.Source.fromFile(path)
    val entityStr = try entitySrc.mkString finally entitySrc.close()
    JsonMethods.parse(entityStr).extract[T]
  }

  lazy val jobs: List[Job] = getEntity[List[Job]]("jobs.json")


  val listOfRules = List(Rule("country", "equals", "India"),
    Rule("title", "contains", "Manager"),
    Rule("city", "equals", "Hyderabad"))

  var emptyJobList: List[Job] = List[Job]()
  val linkedIn: Publisher = Publisher("linkedin", isActive = true, emptyJobList)
  val stackOverflow: Publisher = Publisher("stackoverflow", isActive = true, emptyJobList)
  //val monster: Publisher = Publisher("monster")


  val jobGroup1: JobGroup = JobGroup("11", listOfRules, List(linkedIn, stackOverflow))

  val jobsInJobGroup: List[Job] = JobFilter.jobsFilter(jobGroup1, jobs)

  test("JobFilter.jobsfilter") {
    assert(JobFilter.jobsFilter(jobGroup1,jobs) === jobsInJobGroup)
  }

  test("JobFilter_EmptyListCheck.jobsFilter") {
    val listOfRules2 = List(Rule("category", "equals", "Recruitment"), Rule("category", "equals", "Support"))
    val jobGroup2 : JobGroup = JobGroup("34", listOfRules2, List(linkedIn))
    assert(JobFilter.jobsFilter(jobGroup2,jobs) === emptyJobList)
  }

  test(testName = "JobFilter_containsAndNotContainsCheck.jobsFilter") {
    val listOfRules3 = List(Rule("description", "contains", "marketing"), Rule("description", "not contains", "machine learning"))
    val jobGroup3 : JobGroup = JobGroup("35", listOfRules3, List(stackOverflow))
    val jobsInJobGroup3 = List(Job("DIGITAL MARKETING MANAGER","Joveo","Redwood City","California","United States","Grow new leads and support their journey through all stages of the marketing and sales funnels",9,"https://www.joveo.com/careers","2019-05-15","Support","Support"))
    assert(JobFilter.jobsFilter(jobGroup3,jobs) === jobsInJobGroup3)
  }

  test(testName = "JobFilter_beginsAndNotBeginsCheck.jobsFilter") {
    val listOfRules4 = List(Rule("city", "begins with", "R"), Rule("title", "not begins with", "D"), Rule("title", "not begins with", "S"))
    val jobGroup4: JobGroup = JobGroup("36", listOfRules4, List(linkedIn, stackOverflow))
    val jobsInJobGroup4 = List(Job("CUSTOMER SUCCESS MANAGER","Joveo","Redwood City","California","United States","With brilliant people and innovative AI/ML technologies, Joveo is driving the next-generation of recruitment tech solutions globally. As a Customer Success Manager, you will be a key member of our fast-growing Customer Success Team. You will be responsible for managing multiple customers and serve as their primary Joveo interface, advisor and advocate. The ideal candidate thrives in a fast-paced environment and is passionate about being a customer advocate and a trusted strategic advisor by constantly acquiring and upgrading the required skills and domain knowledge. You build strong and long-lasting relationships with your customers and your efforts will drive customer satisfaction and happiness.",2,"https://www.joveo.com/careers","2019-11-24","Recruitment","Recruitment"), Job("HR MANAGER","Joveo","Redwood City","California","United States","Create a process and manage employee on-boarding, assimilation program for US employees",10,"https://www.joveo.com/careers","2019-05-20","Support","Support"))
    assert(JobFilter.jobsFilter(jobGroup4,jobs) === jobsInJobGroup4)
  }

  test(testName = "JobsFilter_equalsAndNotequalsCheck.jobsFilter") {
    val listOfRules5 = List(Rule("referencenumber", "equals" , "8"), Rule("city", "not equals", "London"))
    val jobGroup5: JobGroup = JobGroup("37", listOfRules5, List(linkedIn))
    val jobsInJobGroup5 = List[Job]()
    assert(JobFilter.jobsFilter(jobGroup5,jobs) === jobsInJobGroup5)
  }

  test(testName = "JobsFilter_priorityCheck.jobsFilter") {
    val listOfRules6 = List(Rule("state", "equals", "California"),
      Rule("referencenumber", "equals", "9"))

    val listOfRules7 = List(Rule("referencenumber", "equals", "9"),
      Rule("state", "equals", "California"))
    val jobGroup6: JobGroup = JobGroup("22", listOfRules6, List(linkedIn))
    val jobGroup7: JobGroup = JobGroup("33", listOfRules7, List(stackOverflow))

    case class JobWithTag(job: Job, var tag: List[String])

    var jobsWithTag = List[JobWithTag]()
    val emptyStringList = List[String]()
    jobs.foreach(i => jobsWithTag = jobsWithTag :+ JobWithTag(i, emptyStringList))

    val jobsInJobGroup6: List[Job] = JobFilter.jobsFilter(jobGroup6, jobs)
    val jobsInJobGroup7: List[Job] = JobFilter.jobsFilter(jobGroup7, jobs)

    //println(jobsInJobGroup6)
    //println(jobsInJobGroup7)


    jobsWithTag.foreach(i => jobsInJobGroup6.foreach(j => if(i.job == j){i.tag = i.tag :+ jobGroup6.id }))
    jobsWithTag.foreach(i => jobsInJobGroup7.foreach(j => if(i.job == j){i.tag = i.tag :+ jobGroup7.id }))

    //println(jobsWithTag)
    //jobsWithTag.foreach(i => println(i.tag))

    jobsWithTag.foreach(i => if(i.tag.size > 1){i.tag = List(i.tag.min)})

    val testTags = jobsWithTag.map(i => i.tag)
    //println(testTags)
    val testValue = List[List[String]](List[String](), List[String](), List[String](), List[String](), List[String](), List[String](), List[String](), List[String](), List[String]("22"), List[String]())

    assert(testTags === testValue)

  }



}
