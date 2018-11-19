package fr.diginamic.formation.calculatrice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private String textHisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textResult = findViewById(R.id.text_result);
        textHisto = "";

        textResult.setOnClickListener(v -> {
            Intent intent = new Intent(this, InfoActivity.class);
            intent.putExtra(InfoActivity.EXTRA_HISTO, textHisto);
            startActivity(intent);
        });

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
        findViewById(R.id.button_dot).setOnClickListener( v -> addDot(textResult.getText().toString()));
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

    public void addDot(String text){
        if(!containsOperator(text) && !text.contains(".")) {
            addOperator(".");
        }else if (text.startsWith("-") && !text.contains(".")){
            addOperator(".");
        }else if (getOperatorIndex(text) > text.lastIndexOf(".")){
            addOperator(".");
        }
    }

    public void addOperator(String operator){
        if(!endWithOperator(textResult.getText().toString()) && !textResult.getText().toString().isEmpty()){
            if(containsOperator(textResult.getText().toString()) && !operator.equals(".") && !textResult.getText().toString().startsWith("-")){
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

    public int getOperatorIndex(String text){
        if(containsOperator(text)){
            if(text.contains("+")) return text.lastIndexOf("+");
            if(text.contains("-") && !text.startsWith("-")) return text.lastIndexOf("-");
            if(text.contains("x")) return text.lastIndexOf("x");
            if(text.contains("/")) return text.lastIndexOf("/");
        }
        return -1;
    }

    public void calculate(String text){
        String[] splitText;
        String calcul = "";
        String result = "";
        if(!endWithOperator(text) && containsOperator(text)){
            if(text.contains("+")){
                splitText = text.split("\\+");
                calcul = Double.parseDouble(splitText[0]) + " + " + Double.parseDouble(splitText[1]);
                result = String.format(Locale.US, "%.2f", Double.parseDouble(splitText[0]) + Double.parseDouble(splitText[1]));
                textResult.setText(result);
            }else if(text.contains("-")){
                splitText = text.split("-");
                if(text.startsWith("-")){
                    calcul = Double.parseDouble(splitText[1]) + " - " + Double.parseDouble(splitText[2]);
                    result = String.format(Locale.US, "%.2f", Double.parseDouble("-" + splitText[1]) - Double.parseDouble(splitText[2]));
                    textResult.setText(result);
                }else{
                    calcul = Double.parseDouble(splitText[0]) + " - " + Double.parseDouble(splitText[1]);
                    result = String.format(Locale.US, "%.2f", Double.parseDouble(splitText[0]) - Double.parseDouble(splitText[1]));
                    textResult.setText(result);
                }
            }else if(text.contains("x")){
                splitText = text.split("x");
                calcul = Double.parseDouble(splitText[0]) + " * " + Double.parseDouble(splitText[1]);
                result = String.format(Locale.US, "%.2f", Double.parseDouble(splitText[0]) * Double.parseDouble(splitText[1]));
                textResult.setText(result);
            }else if(text.contains("/")){
                splitText = text.split("/");
                if(Double.parseDouble(splitText[1]) != 0){
                    calcul = Double.parseDouble(splitText[0]) + " / " + Double.parseDouble(splitText[1]);
                    result = String.format(Locale.US, "%.2f", Double.parseDouble(splitText[0]) / Double.parseDouble(splitText[1]));
                    textResult.setText(result);
                }
            }
            textHisto += calcul + " = " + result + "\n";
        }

    }
}
