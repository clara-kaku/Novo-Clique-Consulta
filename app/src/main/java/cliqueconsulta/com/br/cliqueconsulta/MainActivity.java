package cliqueconsulta.com.br.cliqueconsulta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        NavigationView nv = findViewById(R.id.navView);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem item) {
        Fragment myFragment = null;
        Class fragmentClass;

        switch (item.getItemId()) {
            case R.id.inicio:
                fragmentClass = InicioFragment.class;
                break;
            case R.id.agendar:
                fragmentClass = AgendarFragment.class;
                break;
            case R.id.consMarcadas:
                fragmentClass = ConsMarcadasFragment.class;
                break;
            case R.id.info:
                fragmentClass = InfoFragment.class;
                break;
            default:
                fragmentClass = InicioFragment.class;
                break;
        }

        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, myFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());

        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navView) {
        // Set first fragment to appear
        selectItemDrawer(navView.getMenu().getItem(0));

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
