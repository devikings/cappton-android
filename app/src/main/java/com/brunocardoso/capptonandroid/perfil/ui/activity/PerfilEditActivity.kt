package com.brunocardoso.capptonandroid.perfil.ui.activity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.brunocardoso.capptonandroid.R
import com.brunocardoso.capptonandroid.infra.base.BaseActivity
import com.brunocardoso.capptonandroid.infra.utils.snackbarBuilder
import com.brunocardoso.capptonandroid.user.presenter.UserPresenter
import com.brunocardoso.capptonandroid.user.view.UserView
import kotlinx.android.synthetic.main.act_perfil_edit.*

class PerfilEditActivity : BaseActivity(), UserView {

    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_perfil_edit)

        initViews()
    }

    fun initViews(){
        presenter = UserPresenter(baseContext,this)

        val user = UserPresenter.getUser()
        edt_perfil_name.hint = user?.displayName
        tv_perfil_email.text = user?.email


        btn_profile_edit.setOnClickListener {
            val name = edt_perfil_name.text.toString()

            if(name.equals("")){
                snackbarBuilder(it, "Please, fill all inputs and try again!", Color.WHITE, Color.RED)
            }else{
                presenter.updateUser(name)
            }
        }
    }

    override fun onSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onError(error: String) {
        snackbarBuilder(btn_profile_edit, "Error at create schedule, try again!", Color.WHITE, Color.RED)
    }
}
