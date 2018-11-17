package fr.diginamic.formation.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private DecimalFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        format = new DecimalFormat("0.#########");

        textResult = findViewById(R.id.text_result);

        findViewById(R.id.button_1).setOnClickListener( v -> textResult.setText(textResult.getText()+"1"));
        findViewById(R.id.button_2).setOnClickListener( v -> textResult.setText(textResult.getText()+"2"));
        findViewById(R.id.button_3).setOnClickListener( v -> textResult.setText(textResult.getText()+"3"));
        findViewById(R.id.button_4).setOnClickListener( v -> textResult.setText(textResult.getText()+"4"));
        findViewById(R.id.button_5).setOnClickListener( v -> textResult.setText(textResult.getText()+"5"));
        findViewById(R.id.button_6).setOnClickListener( v -> textResult.setText(textResult.getText()+"6"));
        findViewById(R.id.button_7).setOnClickListener( v -> textResult.setText(textResult.getText()+"7"));
        findViewById(R.id.button_8).setOnClickListener( v -> textResult.setText(textResult.getText()+"8"));
        findViewById(R.id.button_9).setOnClickListener( v -> textResult.setText(textResult.getText()+"9"));
        findViewById(R.id.button_0).setOnClickListener( v -> textResult.setText(textResult.getText()+"0"));
        findViewById(R.id.button_dot).setOnClickListener( v -> addDot());
        findViewById(R.id.button_delete).setOnClickListener( v -> textResult.setText(""));

        findViewById(R.id.button_plus).setOnClickListener( v -> addOperator("+"));
        findViewById(R.id.button_less).setOnClickListener( v -> addOperator("-"));
        findViewById(R.id.button_mult).setOnClickListener( v -> addOperator("x"));
        findViewById(R.id.button_div).setOnClickListener( v -> addOperator("/"));

        findViewById(R.id.button_result).setOnClickListener( v -> {
            calculate(textResult.getText().toString());
            if (textResult.getText().toString().equals("0"))
                textResult.setText("");
        });

        if(textResult.getText().equals("0")) textResult.setText("");
    }

    public void addDot(){
        if(!textResult.getText().toString().endsWith(".")) addOperator(".");
    }

    public void addOperator(String operator){
        if(!textResult.getText().toString().endsWith(operator) && !textResult.getText().toString().isEmpty()){
            if(containsOperator(textResult.getText().toString())){
                calculate(textResult.getText().toString());
                textResult.setText(textResult.getText() + operator);
            }else {
                textResult.setText(textResult.getText() + operator);
            }
        }
    }

    public boolean containsOperator(String text){
        if(text.contains("+") || text.contains("-") || text.contains("x") || text.contains("/"))
            return  true;
        else
            return false;
    }

    public boolean endWithOperator(String text){
        if(text.endsWith("+") || text.endsWith("-") || text.endsWith("x") || text.endsWith("/"))
            return true;
        else
            return false;
    }

    public void calculate(String text){
        String[] splitText;
        if(text.contains("+") && !endWithOperator(text)){
            splitText = text.split("\\+");
            textResult.setText(String.valueOf(format.format(Double.parseDouble(splitText[0]) + Double.parseDouble(splitText[1]))));
        }
        if(text.contains("-") && !endWithOperator(text)){
            splitText = text.split("-");
            textResult.setText(String.valueOf(format.format(Double.parseDouble(splitText[0]) - Double.parseDouble(splitText[1]))));
        }
        if(text.contains("x") && !endWithOperator(text)){
            splitText = text.split("x");
            textResult.setText(String.valueOf(format.format(Double.parseDouble(splitText[0]) * Double.parseDouble(splitText[1]))));
        }
        if(text.contains("/") && !endWithOperator(text)){
            splitText = text.split("/");
            if(Double.parseDouble(splitText[1]) != 0)
                textResult.setText(String.valueOf(format.format(Double.parseDouble(splitText[0]) / Double.parseDouble(splitText[1]))));
        }
    }
}
