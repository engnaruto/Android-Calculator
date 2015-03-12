package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends Activity {

	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button buttonDot;
	Button buttonEval;
	Button buttonSum;
	Button buttonSub;
	Button buttonMult;
	Button buttonDiv;
	Button buttonCancel;
	Button buttonLBracket;
	Button buttonRBracket;
	ImageButton buttonDelete;

	TextView screen;

	String eval;
	boolean error;
	boolean result;
	DoubleEvaluator evaluator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		InitializeView();

		evaluator = new DoubleEvaluator();
		error = false;
		result = false;
		eval = "";

		button0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("0");
			}

		});
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("1");
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("2");
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("3");
			}
		});
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("4");
			}
		});
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("5");
			}
		});
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("6");
			}
		});
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("7");
			}
		});
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("8");
			}
		});
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("9");
			}
		});
		buttonDot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen(".");
			}
		});
		buttonSum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("+");
			}
		});
		buttonSub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("-");
			}
		});
		buttonMult.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("*");
			}
		});
		buttonDiv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("/");
			}
		});
		buttonRBracket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen(")");
			}
		});
		buttonLBracket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addToScreen("(");
			}
		});

		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				eval = "";
				screen.setText("0");
			}
		});

		buttonDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (error || result) {
					eval = "";
					screen.setText("0");
					error = false;
					result = false;
				} else {
					if (!eval.equals("")) {
						eval = eval.substring(0, eval.length() - 1);
						if (eval.equals("")) {
							screen.setText("0");
						} else {
							screen.setText(eval);
						}
					} else {
						screen.setText("0");
					}
				}
			}
		});

		buttonEval.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!eval.equals("") && !error) {
					try {
						eval = evaluator.evaluate(eval).toString();
					} catch (Exception e) {
						eval = "Error";
						error = true;
					}
					if (eval.endsWith(".0")) {
						eval = eval.substring(0, eval.length() - 2);
					}
					screen.setText(eval);
					result = true;
				}
			}
		});

	}

	public void addToScreen(String s) {
		if (error) {
			eval = s;
			error = false;
			result = false;
		} else if (result) {
			if ("+-*/".indexOf(s) != -1) {
				eval += s;
			} else {
				eval = s;
			}
			error = false;
			result = false;
		} else {
			eval += s;
		}
		screen.setText(eval);
	}

	private void InitializeView() {
		button0 = (Button) findViewById(R.id.button_0);
		button1 = (Button) findViewById(R.id.button_1);
		button2 = (Button) findViewById(R.id.button_2);
		button3 = (Button) findViewById(R.id.button_3);
		button4 = (Button) findViewById(R.id.button_4);
		button5 = (Button) findViewById(R.id.button_5);
		button6 = (Button) findViewById(R.id.button_6);
		button7 = (Button) findViewById(R.id.button_7);
		button8 = (Button) findViewById(R.id.button_8);
		button9 = (Button) findViewById(R.id.button_9);
		buttonDot = (Button) findViewById(R.id.button_dot);
		buttonEval = (Button) findViewById(R.id.button_equal);
		buttonSum = (Button) findViewById(R.id.button_sum);
		buttonSub = (Button) findViewById(R.id.button_subtract);
		buttonMult = (Button) findViewById(R.id.button_multiplication);
		buttonDiv = (Button) findViewById(R.id.button_division);
		buttonCancel = (Button) findViewById(R.id.button_cancel);
		buttonLBracket = (Button) findViewById(R.id.button_lbracket);
		buttonRBracket = (Button) findViewById(R.id.button_rbracket);
		buttonDelete = (ImageButton) findViewById(R.id.button_delete);

		screen = (TextView) findViewById(R.id.textview_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
