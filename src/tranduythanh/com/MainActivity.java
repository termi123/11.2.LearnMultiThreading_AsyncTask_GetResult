package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b=(Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doStart();
			}
		});
	}
	private void doStart()
	{
		//tạo 1 tiến trình
		MyAsyncTask tt=new MyAsyncTask(this);
		
		EditText edt=(EditText) findViewById(R.id.editText1);
		//lấy giá trị nhập từu EditText
		int so=Integer.parseInt(edt.getText()+"");
		//thực thi tiến trình với đối số truyền vào là so
		//nó được dùng trong đối số của doInBackground
		tt.execute(so);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
