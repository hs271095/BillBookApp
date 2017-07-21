package bangalore.bms.billbookonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Harshit on 19-07-2017.
 */

public class Add_goods extends AppCompatActivity {
    private Context context;
    Button addItem,viewInt;
    EditText itemname,quant,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goods);
        context=this;
        viewInt=(Button)findViewById(R.id.ViewInventry);
        itemname=(EditText)findViewById(R.id.nameOfItem);
        quant=(EditText)findViewById(R.id.quantityOfItem);
        price=(EditText)findViewById(R.id.priceOfItem);
        addItem=(Button)findViewById(R.id.ButtonAddGoods);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemn= itemname.getText().toString();
                    String Q = quant.getText().toString();
                    String pric= price.getText().toString();
                GoodsDbhelper dataHelper =new GoodsDbhelper(context);
                dataHelper.insertData(itemn,Q,pric);
                Toast.makeText(Add_goods.this, "Data Inserted", Toast.LENGTH_SHORT).show();

            }
        });
        viewInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go =new Intent(Add_goods.this,Goods_list.class);
                startActivity(go);
            }
        });

    }
}
