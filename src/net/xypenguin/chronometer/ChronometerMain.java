package net.xypenguin.chronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

public class ChronometerMain extends Activity implements OnClickListener,
		OnChronometerTickListener {
	private Chronometer upChrono;
	private Chronometer startChrono;
	private Chronometer restartChrono;
	private Button startButton;
	private Button stopButton;
	private TextView startText;
	private TextView stopText;

	private long totaltime;
	private long startTime;
	private long stopTime;
	private boolean check; // 連続で同じボタンを押せないように

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chronometer);

		upChrono = (Chronometer) this.findViewById(R.id.chronometer1);
		upChrono.setFormat("起動からの時間 %s");
		startChrono = (Chronometer) this.findViewById(R.id.chronometer2);
		startChrono.setFormat("　　時間 %s");
		restartChrono = (Chronometer) this.findViewById(R.id.chronometer3);
		restartChrono.setFormat("累計時間 %s");
		startButton = (Button) this.findViewById(R.id.button1);
		startButton.setOnClickListener(this);
		stopButton = (Button) this.findViewById(R.id.button2);
		stopButton.setOnClickListener(this);
		startText = (TextView) this.findViewById(R.id.textView1);
		stopText = (TextView) this.findViewById(R.id.textView2);

		upChrono.setBase(SystemClock.elapsedRealtime());
		upChrono.start();

		//起動時からsetFormat()の表示にする方法がわからなかったので
		//一瞬起動させました
		startChrono.setBase(SystemClock.elapsedRealtime());
		startChrono.start();
		startChrono.stop();
		restartChrono.setBase(SystemClock.elapsedRealtime());
		restartChrono.start();
		restartChrono.stop();

		totaltime = 0;
		check = true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {
		case R.id.button1:
			if (check) {
				startTime = SystemClock.elapsedRealtime();
				String str = startTime + "ミリ秒";
				startText.setText(str);

				startChrono.setBase(startTime);
				restartChrono.setBase(startTime - totaltime);
				startChrono.start();
				restartChrono.start();
				check = false;
			} else {

			}
			break;
		case R.id.button2:
			if (check) {

			} else {
				stopTime = SystemClock.elapsedRealtime();
				String str = stopTime + "ミリ秒";
				stopText.setText(str);

				startChrono.stop();
				restartChrono.stop();
				check = true;
				totaltime += stopTime - startTime;
			}
			break;
		default:
			break;
		}

	}

	//一秒毎に行いたい処理があればこのメソッドに書く
	@Override
	public void onChronometerTick(Chronometer chronometer) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chronometer, menu);
		return true;
	}

}
