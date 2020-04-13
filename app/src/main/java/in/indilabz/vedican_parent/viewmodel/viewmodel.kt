package `in`.indilabz.vedican_parent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import `in`.indilabz.vedican_parent.extras.DetailInterface
import `in`.indilabz.vedican_parent.extras.HomeInterface
import `in`.indilabz.vedican_parent.extras.LoginInterface
import `in`.indilabz.vedican_parent.extras.SolutionInterface
import `in`.indilabz.vedican_parent.model.QuizList
import `in`.indilabz.vedican_parent.model.StudentResult
import `in`.indilabz.vedican_parent.repository.Repository
import `in`.indilabz.vedican_parent.response.OtpResponse
import `in`.indilabz.vedican_parent.response.RankResponse
import `in`.indilabz.vedican_parent.response.SolutionsResponse

class viewmodel: ViewModel() {

    private var repository : Repository = Repository()

    fun sendOTP(phone : String,loginInterface: LoginInterface): LiveData<OtpResponse>{
        return repository.sendOTP(phone,loginInterface)
    }

    fun fetchStudentList(phone : String, homeInterface: HomeInterface): LiveData<PagedList<StudentResult>> {
        return repository.getStudentList(phone,homeInterface)
    }

    fun fetchQuizList(studentId : String,detailInterface: DetailInterface) : LiveData<PagedList<QuizList>>{
        return repository.getQuizList(studentId,detailInterface)
    }

    fun fetchSolutions(id : String, solutionInterface: SolutionInterface) : LiveData<SolutionsResponse>{
        return repository.getSolutions(id,solutionInterface)
    }

    fun getRank(id : String,quizId : String, solutionInterface: SolutionInterface) : LiveData<RankResponse> {
        return repository.getRank(id,quizId,solutionInterface)
    }

}