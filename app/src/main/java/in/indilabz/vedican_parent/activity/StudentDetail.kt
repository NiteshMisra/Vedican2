@file:Suppress("DEPRECATION")

package `in`.indilabz.vedican_parent.activity

import `in`.indilabz.vedican_parent.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import `in`.indilabz.vedican_parent.adapter.ListAdapter
import `in`.indilabz.vedican_parent.databinding.ActivityStudentDetailBinding
import `in`.indilabz.vedican_parent.extras.DetailInterface
import `in`.indilabz.vedican_parent.model.StudentResult
import `in`.indilabz.vedican_parent.viewmodel.viewmodel

class StudentDetail : AppCompatActivity(), DetailInterface {

    private lateinit var studentResult : StudentResult
    private lateinit var binding : ActivityStudentDetailBinding
    private lateinit var viewModel : viewmodel
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_detail)
        viewModel = ViewModelProviders.of(this).get(viewmodel::class.java)
        val value = intent.getStringExtra("GSON")
        studentResult = Gson().fromJson(value, StudentResult::class.java)

        binding.detailRcv.layoutManager = LinearLayoutManager(this@StudentDetail)
        binding.detailRcv.setHasFixedSize(false)

        binding.quizListBack.setOnClickListener {
            onBackPressed()
        }

        binding.quizListStudentEmail.text = studentResult.email
        binding.quizListStudentName.text = studentResult.name

        fetchList()

        binding.detailRefresh.setOnRefreshListener {
            fetchList()
        }


    }

    private fun fetchList() {

        listAdapter = ListAdapter(this@StudentDetail)
        viewModel.fetchQuizList(studentResult.id.toString(),this).observe(this, Observer {
            listAdapter.submitList(it)
            listAdapter.notifyDataSetChanged()
        })

        binding.detailRcv.adapter = listAdapter

    }

    override fun showProgress() {
        binding.detailRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        binding.detailRefresh.isRefreshing = false
    }

}
