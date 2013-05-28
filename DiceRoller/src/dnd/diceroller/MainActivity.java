package dnd.diceroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Variables for use in methods below
	//So that getting the value from the buttons is not necessary
	int numDice = 1;
	int numConstant = 0;
	boolean sign = true;
	int diceType = 4;

	
	
	public static int diceRoll(int sides) {
		return (int) (Math.random()*sides) + 1;
	}
	
//	public static void changeText(TextView t, int n) {
//		int x = diceRoll(n);
//		String s = String.valueOf(x);
//		t.setText(s);
//	}
	
	public void calculate(View v) {
		int sum = rollTheDice();
		int c = numConstant;
		if(!sign)
			c *= -1;
		int result = sum + c;
		
		TextView resultField = (TextView) findViewById(R.id.answer);
		resultField.setText(""+result);
	}
	
	public int rollTheDice() {
		StringBuffer sb = new StringBuffer();
		int sum = 0;
		for(int i = 0; i<numDice; i++) {
			int x = diceRoll(diceType);
			sum+=x;
			sb.append(x);
			sb.append(' ');
		}
		
		TextView rollsField = (TextView) findViewById(R.id.rolls);
		rollsField.setText(sb.toString());
		
		return sum;
	}
	
	public void incrementNumDice(View v) {
		Button b = (Button) v;
		numDice++;
		if(numDice>10)
			numDice=1;
		String s = ""+numDice;
		b.setText(s);
	}
	
	public void incrementConstant(View v) {
		Button b = (Button) v;
		numConstant++;
		if(numConstant>10)
			numConstant=0;
		String s = ""+numConstant;
		b.setText(s);
	}
	
//	public void switchSign(View v) {
//		Button b = (Button) v;
//		String s = b.getText().toString();
//		if(s.equals("-"))
//			b.setText("+");
//		else
//			b.setText("-");
//	}

	public void switchSign(View v) {
		Button b = (Button) v;
		if(sign)
			b.setText(R.string.minus);
		else
			b.setText(R.string.plus);
		sign = !sign;
	}
	
	public void changeDiceType(View v) {
		Button b = (Button) v;
		int s;
		if(diceType==4) {
			diceType=6;
			s=R.string.d6;
		}
		else if(diceType==6) {
			diceType=8; 
			s=R.string.d8;
		}
		else if(diceType==8) {
			diceType=10;
			s=R.string.d10;
		}
		else if(diceType==10) {
			diceType=12;
			s=R.string.d12;
		}
		else if(diceType==12) {
			diceType=20;
			s=R.string.d20;
		}
		else {
			diceType=4;
			s=R.string.d4;
		}
		
		b.setText(s);
			
	}
	
	public void reset(View v) {
		numDice = 1;
		numConstant = 0;
		
		Button numButton = (Button) findViewById(R.id.numButton);
		Button constantButton = (Button) findViewById(R.id.constantButton);
		
		numButton.setText("1");
		constantButton.setText("0");
	}
	
	public void changeDiceTypeUsingOtherButtons(View v) {
		Button b = (Button) v;
		Button diceButton = (Button) findViewById(R.id.diceButton);
		
		String text = b.getText().toString();
		diceButton.setText(text);
		text = text.substring(1);
		int num = Integer.parseInt(text);
		diceType = num;
	}

}
