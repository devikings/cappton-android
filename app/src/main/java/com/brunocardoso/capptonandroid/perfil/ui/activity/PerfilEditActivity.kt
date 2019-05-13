package com.brunocardoso.capptonandroid.perfil.ui.activity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.perfil.presenter.PerfilPresenter
import com.brunocardoso.capptonandroid.perfil.view.PerfilView
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.act_perfil_edit.*

class PerfilEditActivity : BaseActivity(), PerfilView {

    companion object {
        const val EDIT_PERFIL: Int = 101
    }

    private lateinit var presenter: PerfilPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_perfil_edit)

        initViews()
    }

    fun initViews(){
        presenter = PerfilPresenter(baseContext, this)

        val user = UserPresenter.getUser()
        edt_perfil_name.hint = user?.displayName
        tv_perfil_email.text = user?.email


        btn_profile_edit.setOnClickListener {
            val name = edt_perfil_name.text.toString()

            if(name.equals("")){

                val snackBar = Snackbar.make(it, "Please, fill all inputs and try again! ", Snackbar.LENGTH_LONG)
                snackBar.setActionTextColor(Color.WHITE)
                snackBar.view.setBackgroundColor(Color.RED)
                snackBar.show()

            }else{
                presenter.update(1)
            }
        }
    }

    override fun onSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onError(error: String) {
        val snackBar = Snackbar.make(btn_profile_edit, "Error at create schedule, try again! ", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.view.setBackgroundColor(Color.RED)
        snackBar.show()
    }
}
