package com.example.administrator.alertdialog;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class WifiDialog extends AlertDialog implements View.OnClickListener {
    EditText mEtPasswd;
    Button mBtnCancel, mBtnConnect;
    Context mContext;
    public WifiDialog(Context context) {
        super(context);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        mEtPasswd = (EditText) findViewById(R.id.et_passwd);
        //保证EditText能弹出键盘
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        this.setCancelable(false);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnConnect = (Button) findViewById(R.id.btn_connect);
        mBtnConnect.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_connect:
                this.dismiss();
                break;
            default:
                break;
        }
    }
}