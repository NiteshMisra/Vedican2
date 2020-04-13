package `in`.indilabz.vedican_parent.dataFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import `in`.indilabz.vedican_parent.datasource.QuizListDataSource
import `in`.indilabz.vedican_parent.extras.DetailInterface
import `in`.indilabz.vedican_parent.model.QuizList

class QuizListFactory(private var studentId : String, private var detailInterface: DetailInterface) : DataSource.Factory<Int,QuizList>() {

    private val itemLiveDataSource : MutableLiveData<PageKeyedDataSource<Int,QuizList>> = MutableLiveData()

    override fun create(): DataSource<Int, QuizList> {
        val imageDataSource = QuizListDataSource(studentId,detailInterface)
        itemLiveDataSource.postValue(imageDataSource)
        return imageDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, QuizList>> {
        return itemLiveDataSource
    }

}