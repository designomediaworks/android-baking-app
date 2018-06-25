package com.android.kakashi.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.kakashi.bakingapp.MockData;
import com.android.kakashi.bakingapp.R;
import com.android.kakashi.bakingapp.RecipeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepPagerActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager stepPager;
    private int recipeIndex;

    private static final String EXTRA_STEP_INDEX = "com.android.kakashi.bakingapp.step_index";
    private static final String EXTRA_RECIPE_INDEX = "com.android.kakashi.bakingapp.recipe_index";

    public static Intent start(Context packageContext, int currentIndex, int recipeIndex) {
        Intent intent = new Intent(packageContext, StepPagerActivity.class);
        intent.putExtra(EXTRA_STEP_INDEX, currentIndex);
        intent.putExtra(EXTRA_RECIPE_INDEX, recipeIndex);

        return intent;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_pager);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);

        int currentIndex = getIntent().getIntExtra(EXTRA_STEP_INDEX, 0);
        recipeIndex = getIntent().getIntExtra(EXTRA_RECIPE_INDEX, -1);

        final List<String> steps = MockData.getData();
        FragmentManager fragmentManager = getSupportFragmentManager();

        stepPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                String step = steps.get(position);
                return StepFragment.newInstance(step);
            }

            @Override
            public int getCount() {
                return steps.size();
            }
        });

        stepPager.setCurrentItem(currentIndex);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = RecipeActivity.startActivity(this, recipeIndex);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
