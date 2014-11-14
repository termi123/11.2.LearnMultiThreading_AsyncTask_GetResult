package tranduythanh.com;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * AsyncTask<Integer, Integer, ArrayList<Integer>>
 * Integer: Truyền vào lúc thực thi tiến trình
 * Integer: Giá trị để Cập nhật giao diện
 *  ArrayList<Integer>: Kết quả trả về sau khi kết thúc tiến trình
 * @author thanhtran
 *
 */
public class MyAsyncTask extends 
			AsyncTask<Integer, Integer, ArrayList<Integer>> {

	Activity activityCha;
	ListView lv=null;
	ArrayList<Integer>arrCuaListview=new ArrayList<Integer>();
	ArrayAdapter<Integer>adapterCuaListview=null;
	public MyAsyncTask(Activity act)
	{
		//lấy MainActivity
		activityCha=act;
		//Lấy ListView của MainActivity
		lv=(ListView) activityCha.findViewById(R.id.listView1);
		//Adapter cho Listview
		adapterCuaListview=new ArrayAdapter<Integer>
				(activityCha, 
						android.R.layout.simple_list_item_1, 
						arrCuaListview);
		//gán Adapter cho listview
		lv.setAdapter(adapterCuaListview);
	}
	/**
	 * Số fibonacci thứ n trong chuỗi Fibonacci
	 * @param n
	 * @return
	 */
	public int fib(int n)
	{
		if(n<=2)return 1;
		return fib(n-1)+fib(n-2);
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		//muốn khởi tạo gì đó trước khi thực thi thì có thể làm ở đây
		Toast.makeText(activityCha, "Bắt đầu chạy tiến trình ", 
				Toast.LENGTH_LONG).show();
		
	}
	@Override
	protected ArrayList<Integer> doInBackground(Integer... arg0) {
		// TODO Auto-generated method stub
		//vậy thì khi bắt đầu thực thi đối số 1 sẽ ở đây: arg0
		int so=arg0[0];
		//khai báo ArrayList lưu trữ tập các số nguyên
		ArrayList<Integer> arrTongCacSoFib=new ArrayList<Integer>();
		for(int i=1;i<=so;i++)
		{
			SystemClock.sleep(150);
			//lấy số fibonacci tại vị trí thứ i
			int f=fib(i);
			//lưu vào danh sách
			arrTongCacSoFib.add(f);
			//cập nhập số fibonacci lên listview
			publishProgress(f);
		}
		//trả về danh sách, nó sẽ được lưu trữ trong hàm 
		//onPostExecute
		return arrTongCacSoFib;
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		//lấy giá trị truyền từ publishProgress
		arrCuaListview.add(values[0]);
		//cập nhật lại giao diện
		adapterCuaListview.notifyDataSetChanged();
	}
	@Override
	protected void onPostExecute(ArrayList<Integer> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//result chính là ArrayList lưu trữ chuỗi Fibonacci
		int tong=0;
		//vòng lặp để cộng dồn lại
		for(int v : result)
		{
			tong+=v;
		}
		Toast.makeText(activityCha, "Tong ="+tong, Toast.LENGTH_LONG).show();
		//cập nhật lên TextView
		TextView txt=(TextView) activityCha.findViewById(R.id.textView1);
		txt.setText(tong+"");
	}
}
