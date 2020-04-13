package `in`.indilabz.vedican_parent.response

import com.google.gson.annotations.SerializedName
import `in`.indilabz.vedican_parent.model.StudentResult
import org.jetbrains.annotations.Nullable

class studentResponse {

    @SerializedName("error")
    lateinit var error : String

    @Nullable
    @SerializedName("result")
    lateinit var result: List<StudentResult>

}