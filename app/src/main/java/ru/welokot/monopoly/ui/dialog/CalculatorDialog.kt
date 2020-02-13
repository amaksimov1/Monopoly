//package ru.welokot.monopoly.ui.dialog
//
//import android.app.Dialog
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.View
//import android.view.WindowManager
//import android.widget.EditText
//import android.widget.ImageButton
//import android.widget.Toast
//
//private fun showCustomDialog() {
//    val dialog = Dialog(this)
//    dialog.setContentView(R.layout.dialog_add_post)
//    dialog.setCancelable(true)
//
//    val lp = WindowManager.LayoutParams()
//    lp.copyFrom(dialog.window!!.attributes)
//    lp.width = WindowManager.LayoutParams.MATCH_PARENT
//    lp.height = WindowManager.LayoutParams.MATCH_PARENT
//
//    val bt_submit = dialog.findViewById(R.id.bt_submit) as AppCompatButton
//    (dialog.findViewById(R.id.et_post) as EditText).addTextChangedListener(object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//        }
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//            bt_submit.setEnabled(!s.toString().trim { it <= ' ' }.isEmpty())
//        }
//
//        override fun afterTextChanged(s: Editable) {
//
//        }
//    })
//
//    bt_submit.setOnClickListener(View.OnClickListener {
//        dialog.dismiss()
//        Toast.makeText(getApplicationContext(), "Post Submitted", Toast.LENGTH_SHORT).show()
//    })
//
//    (dialog.findViewById(R.id.bt_photo) as ImageButton).setOnClickListener {
//        Toast.makeText(
//            getApplicationContext(),
//            "Post Photo Clicked",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//
//    (dialog.findViewById(R.id.bt_link) as ImageButton).setOnClickListener {
//        Toast.makeText(
//            getApplicationContext(),
//            "Post Link Clicked",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//
//    (dialog.findViewById(R.id.bt_file) as ImageButton).setOnClickListener {
//        Toast.makeText(
//            getApplicationContext(),
//            "Post File Clicked",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//
//    (dialog.findViewById(R.id.bt_setting) as ImageButton).setOnClickListener {
//        Toast.makeText(
//            getApplicationContext(),
//            "Post Setting Clicked",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//
//    dialog.show()
//    dialog.window!!.attributes = lp
//}