package `in`.indilabz.vedican_parent.response

import com.google.gson.annotations.SerializedName
import `in`.indilabz.vedican_parent.model.QuizList

class ResultResponse {

    @SerializedName("error")
    lateinit var error : String

    @SerializedName("result")
    lateinit var QuizList : ArrayList<QuizList>

}