package com.example.bankaldam.View.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankaldam.Helper.HelperMethod;
import com.example.bankaldam.R;
import com.example.bankaldam.View.Fragment.homeCycle.AboutAppFragment;
import com.example.bankaldam.View.Fragment.homeCycle.ContactUsFragment;
import com.example.bankaldam.View.Fragment.homeCycle.EditeUserInfoFragment;
import com.example.bankaldam.View.Fragment.homeCycle.NotificationSettengsFragment;
import com.example.bankaldam.View.Fragment.homeCycle.home.HomeContainerFragment;
import com.example.bankaldam.View.Fragment.homeCycle.home.PostsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeCycleActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.home_cycle_activity_iv_open_navigation)
    ImageView homeCycleActivityIvOpenNavigation;
    @BindView(R.id.home_cycle_activity_iv_back)
    ImageView homeCycleActivityIvBack;
    @BindView(R.id.toolbartextview)
    TextView toolbarTextView;
    @BindView(R.id.home_cycle_activity_iv_open_notification)
    ImageView homeCycleActivityIvOpenNotification;
    Unbinder unbinder;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
//       setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        HomeContainerFragment homeFragment = new HomeContainerFragment();
        HelperMethod.ReplaceFragment(getSupportFragmentManager(), homeFragment, R.id.fram_homecycle, null, null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            baseFragment.onBack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_cycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_myinfo_menu) {
            EditeUserInfoFragment editeUserInfoFragment = new EditeUserInfoFragment();
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), editeUserInfoFragment, R.id.fram_homecycle, toolbarTextView, "Edit Info");
        } else if (id == R.id.menu_notificationsettings_menu) {
            NotificationSettengsFragment notificationSettengsFragment = new NotificationSettengsFragment();
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), notificationSettengsFragment, R.id.fram_homecycle, toolbarTextView, "Notification settings");

        } else if (id == R.id.menu_favorite_menu) {
            PostsFragment postsFragment= new PostsFragment();
            postsFragment.viforite = true;
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), postsFragment, R.id.fram_homecycle, null, null);

        } else if (id == R.id.menu_home_menu) {
            HomeContainerFragment homeFragment = new HomeContainerFragment();
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), homeFragment, R.id.fram_homecycle, null, null);

        } else if (id == R.id.menu_instractions_for_use_menu) {

        } else if (id == R.id.menu_contact_us_menu) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Contact Us");
            ContactUsFragment contactUsFragment = new ContactUsFragment();
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), contactUsFragment, R.id.fram_homecycle, toolbarTextView, "Contact Us");


        } else if (id == R.id.menu_about_app_menu) {
            AboutAppFragment aboutAppFragment = new AboutAppFragment();
            HelperMethod.ReplaceFragment(getSupportFragmentManager(), aboutAppFragment, R.id.fram_homecycle, null, null);
        } else if (id == R.id.menu_evaluation_menu) {

        } else if (id == R.id.menu_sign_out_menu) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @OnClick({R.id.home_cycle_activity_iv_open_navigation, R.id.home_cycle_activity_iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_cycle_activity_iv_open_navigation:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.home_cycle_activity_iv_back:
                break;

        }
    }

    public void setVisibilityTextView(int visibility) {
        toolbarTextView.setVisibility(visibility);
    }

    public void setVisibilityOpenNavigation(int visibility) {
        homeCycleActivityIvOpenNavigation.setVisibility(visibility);
    }

    public void setVisibilityBack(int visibility) {
        homeCycleActivityIvBack.setVisibility(visibility);
    }

    public void setVisibilityOpenNotification(int visibility) {
        homeCycleActivityIvOpenNotification.setVisibility(visibility);
    }

    public void setTitle(String title) {
        toolbarTextView.setText(title);
    }

}
