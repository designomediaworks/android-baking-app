package com.android.kakashi.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.kakashi.bakingapp.R;
import com.android.kakashi.bakingapp.RecipeActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListFragment extends Fragment {
    @BindView(R.id.list_view)
    ListView listView;

    public static RecipeListFragment newInstance() {
        // vanilla for now
        return new RecipeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        // test data
        final String[] values = new String[20];
        for (int i = 0; i < values.length; i++) {
            values[i] = "Recipe Item #" + (i + 1);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                Objects.requireNonNull(getActivity()),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                values
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(
                        getActivity(),
                        "Item at " + position + " position clicked",
                        Toast.LENGTH_SHORT
                ).show();*/

                Intent fireRecipeActivity = RecipeActivity.startActivity(getActivity(), values[position]);
                startActivity(fireRecipeActivity);
            }
        });

        return view;
    }
}