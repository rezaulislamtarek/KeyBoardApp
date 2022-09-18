package com.diatomicsoft.diatomicsoftkeybord

import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button


class MyInputMethodService: InputMethodService(), KeyboardView.OnKeyboardActionListener  {


    override fun onCreateInputView(): View? {

        val root = layoutInflater.inflate(R.layout.idee_kl, null)

        val btn = root.findViewById<Button>(R.id.btnHello)
        val kv = root.findViewById<KeyboardView>(R.id.keyboard_view)

        btn.setOnClickListener {
            Log.d("Hi","i am log")
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        val keyboard = Keyboard(this, R.xml.keys_layout)
        kv.keyboard = keyboard
        kv.setOnKeyboardActionListener(this)
        return root
    }

    override fun onPress(i: Int) {}

    override fun onRelease(i: Int) {}

    override fun onKey(primatyCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection
        if (inputConnection != null) {
            when (primatyCode) {
                Keyboard.KEYCODE_DELETE -> {
                    val selectedText = inputConnection.getSelectedText(0)
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0)
                    } else {
                        inputConnection.commitText("", 1)
                    }
                }
                else -> {
                    val code = primatyCode.toChar()
                    inputConnection.commitText(code.toString(), 1)
                }
            }
        }
    }

    override fun onText(charSequence: CharSequence?) {}

    override fun swipeLeft() {}

    override fun swipeRight() {}

    override fun swipeDown() {}

    override fun swipeUp() {}


}