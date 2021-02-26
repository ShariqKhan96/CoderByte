package app.bayut.coderbyte.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import app.bayut.coderbyte.R
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun formatDate(createdAt: String?): CharSequence? {

            return SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault()).format(
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(createdAt)
            )
        }

        var progressDialog: Dialog? = null
    }

    fun showLoader(context: Context, message: String) {
        progressDialog = Dialog(context)
        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.setContentView(R.layout.dialog)
        val tv: TextView =
            progressDialog!!.findViewById(R.id.message)
        tv.text = message
        progressDialog!!.getWindow()
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog!!.show()
    }

    fun dismissLoader() {
        if (progressDialog != null)
            progressDialog!!.dismiss()
    }


}