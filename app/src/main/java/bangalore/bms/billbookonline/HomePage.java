package bangalore.bms.billbookonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by Harshit on 01-07-2017.
 */

public class HomePage extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
     NavigationView navigation;
     ImageButton gen,sub_user,add_goods;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        add_goods=(ImageButton)findViewById(R.id.addgoods);
        gen=(ImageButton)findViewById(R.id.Gen_Bill);
        sub_user=(ImageButton) findViewById(R.id.but5) ;
        add_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(HomePage.this,Add_goods.class);
            }
        });
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go= new Intent(HomePage.this,Generate_bill.class);
                startActivity(go);

            }
        });
        sub_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go= new Intent(HomePage.this,Create_subuser.class);
                startActivity(go);

            }
        });
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation=(NavigationView)findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem mItem) {
                int id = mItem.getItemId();
                switch (id) {
                    case R.id.Profile:

                        case R.id.Gen_Bill:
                            Intent j = new Intent(HomePage.this, BillTemplateActivity.class);
                            startActivity(j);
                        break;
                    case R.id.Add:
                        Intent go = new Intent(HomePage.this, UpdateCompanyLayout.class);
                        startActivity(go);
                        break;
                    case R.id.bill:
                        Intent goBill = new Intent(HomePage.this, Generate_bill.class);
                        startActivity(goBill);
                        break;
                    case R.id.help:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);

    }


}


