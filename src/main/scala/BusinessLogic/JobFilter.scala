package BusinessLogic

object JobFilter {
  def jobsFilter(jobGroup: JobGroup, listOfJobs: List[Job]): List[Job] = {
    val rules = jobGroup.rules
    var jobs = listOfJobs
    //val ans = List()
    for (rule <- rules) {
      var ans = List[Job]()

      if (rule.operator == "equals") {
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(_.title == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(_.company == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(_.city == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          val result = jobs.filter(_.state == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(_.country == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(_.description == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          val result = jobs.filter(_.referencenumber == rule.data.toInt)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(_.url == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(_.date == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(_.category == rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(_.department == rule.data)
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }

      else if (rule.operator == "not equals") {
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(_.title != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(_.company != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(_.city != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          val result = jobs.filter(_.state != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(_.country != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(_.description != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          val result = jobs.filter(_.referencenumber != rule.data.toInt)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(_.url != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(_.date != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(_.category != rule.data)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(_.department != rule.data)
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }

      else if (rule.operator == "contains") {
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(_.title.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(_.company.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(_.city.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          val result = jobs.filter(_.state.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(_.country.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(_.description.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          //val result = jobs.filter(_.referencenumber.contains(rule.data))
          //ans = ans ++ result
          println("Contains operator can't be applied on reference number(Integer value)")
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(_.url.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(_.date.contains(rule.data)) //Doubt
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(_.category.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(_.department.contains(rule.data))
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }
      else if (rule.operator == "not contains") {
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(!_.title.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(!_.company.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(!_.city.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          val result = jobs.filter(!_.state.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(!_.country.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(!_.description.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          //val result = jobs.filter(!_.referencenumber.contains(rule.data))
          //ans = ans ++ result
          println("not contains operator can't be applied on reference number(Integer value)")
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(!_.url.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(!_.date.contains(rule.data)) //Doubt
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(!_.category.contains(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(!_.department.contains(rule.data))
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }
      else if (rule.operator == "begins with") {
        println("inside begins with")
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(_.title.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(_.company.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(_.city.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          println("inside state")
          val result = jobs.filter(_.state.startsWith(rule.data))
          //println("jobs = "+jobs)
          //println("result = "+ result)
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(_.country.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(_.description.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          //val result = jobs.filter(_.referencenumber.startsWith(rule.data))
          //ans = ans ++ result
          println("begins with operator can't be applied on reference number(Integer value)")
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(_.url.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(_.date.startsWith(rule.data)) //Doubt
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(_.category.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(_.department.startsWith(rule.data))
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }

      else if (rule.operator == "not begins with") {
        if (rule.jobAttribute == "title") {
          val result = jobs.filter(!_.title.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "company") {
          val result = jobs.filter(!_.company.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "city") {
          val result = jobs.filter(!_.city.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "state") {
          val result = jobs.filter(!_.state.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "country") {
          val result = jobs.filter(!_.country.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "description") {
          val result = jobs.filter(!_.description.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "referencenumber") {
          //val result = jobs.filter(!_.referencenumber.startsWith(rule.data))
          //ans = ans ++ result
          println("begins with operator can't be applied on reference number(Integer value)")
        }
        else if (rule.jobAttribute == "url") {
          val result = jobs.filter(!_.url.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "date") {
          val result = jobs.filter(!_.date.startsWith(rule.data)) //Doubt
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "category") {
          val result = jobs.filter(!_.category.startsWith(rule.data))
          ans = ans ++ result
        }
        else if (rule.jobAttribute == "department") {
          val result = jobs.filter(!_.department.startsWith(rule.data))
          ans = ans ++ result
        }
        else {
          println("BusinessLogic.Job Attribute Not Found")
        }
      }
      else {
        println("operator is not valid")
      }
      jobs = ans
    }
    jobs
  }
}
