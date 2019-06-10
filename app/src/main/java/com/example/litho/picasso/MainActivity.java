package com.example.litho.picasso;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment[] FragmentArray = new Fragment[]{new PhotosFragment(), new CollectionsFragment(), new Fragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final BottomNavigationView bottomNavigationView = this.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.navigation_photos){

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,FragmentArray[0],"").addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    return  true;
                }

                else if(menuItem.getItemId() == R.id.navigation_imageSearch){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,FragmentArray[1],"").addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
                    return  true;
                }
                else if(menuItem.getItemId() == R.id.navigation_artists){
                   getSupportFragmentManager().beginTransaction().replace(R.id.container,FragmentArray[2],"").addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    return  true;
                }

                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.navigation_photos);
    }

    /*ComponentContext context = new ComponentContext(this);

    String data = getAssetJsonData(getApplicationContext());

    Type listType = new TypeToken<ArrayList<Collection>>(){}.getType();

    List<Collection> modelObjects = new Gson().fromJson(data, listType);

    *//*NetworkService networkService = new NetworkService(this);*//*

       *//* networkService.getPhotos("25", new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //Toast.makeText(getBaseContext(), "This is successful" + response.body().toString(), Toast.LENGTH_LONG).show();

                GridRecyclerConfiguration gridRecyclerConfiguration = GridRecyclerConfiguration.create().numColumns(3).orientation(1).build();

                ComponentContext contextComponent = new ComponentContext(getBaseContext());
                final Component component =
                        RecyclerCollectionComponent.create(contextComponent)
                                .disablePTR(true)
                                .recyclerConfiguration(gridRecyclerConfiguration)
                                .section(ListSection.create(new SectionContext(contextComponent)).dataModels((List<Photo>) response.body()).callback(new RefreshCallback(){
                                    @Override
                                    public void StartRefresh() {
                                        new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                                                .setTitle("Delete entry")
                                                .setMessage("Are you sure you want to delete this entry?")
                                                .setNegativeButton(android.R.string.no, null)
                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                .show();
                                    }
                                })).canMeasureRecycler(true).build();

                setContentView(LithoView.create(getBaseContext(), component));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getBaseContext(), "This call has failed", Toast.LENGTH_LONG).show();
            }
        });*//*

        *//*setContentView(LithoView.create(this, Column.create(context).child(Text.create(context).text("Loading data").textSizePx(24).textAlignment(Layout.Alignment.ALIGN_CENTER)).child(Progress.create(context).heightDip(50)
                .widthDip(50).alignSelf(YogaAlign.CENTER)).build()));*//*


    GridRecyclerConfiguration gridRecyclerConfiguration = GridRecyclerConfiguration.create().numColumns(3).orientation(1).build();

    ComponentContext contextComponent = new ComponentContext(getBaseContext());
    final Component component =
            RecyclerCollectionComponent.create(contextComponent)
                    .disablePTR(true)
                    .recyclerConfiguration(gridRecyclerConfiguration)
                    .section(ListSection.create(new SectionContext(contextComponent)).dataModels(modelObjects).callback(new RefreshCallback(){
                        @Override
                        public void StartRefresh() {

                        }
                    })).verticalFadingEdgeEnabled(false).canMeasureRecycler(true).build();

    //setContentView(LithoView.create(getBaseContext(), component));

    public static String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("collections.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;

    }*/

}

