package com.seteam3.pages;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seteam3.adapters.TimetableAdapter;
import com.seteam3.ukonnekt.R;

public class TimetableView extends Fragment implements ActionBar.TabListener {

	public ViewPager viewPager;
    private TimetableAdapter mAdapter;
    private ActionBar actionBar;
    private String [] tabs = {"Todays Schedule","Upcoming Schedule"};

	public TimetableView(){}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View timetableview = inflater.inflate(R.layout.viewpagerlayout, container, false);

        return timetableview;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = (ViewPager)getActivity().findViewById(R.id.pager);
        actionBar = getActivity().getActionBar();
        mAdapter = new TimetableAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
      //Add the tabs here
        /*for(String tab_name:tabs){
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
            
        }*/
        
        actionBar.addTab(actionBar.newTab().setText("Todays Schedule").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Upcoming Schedule").setTabListener(this));
        
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
        public void onPageSelected(int position){

                //on Page change, that particular page should be selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
                public void onPageScrolled(int arg0,float arg1,int arg2){

            }
            @Override
        public void onPageScrollStateChanged(int position){

            }

        });

    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
			
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		//getActivity().getSupportFragmentManager().beginTransaction().remove(TimetableView.this).commit();
		//getActivity().getSupportFragmentManager().popBackStack();
		//getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
		//getActivity().getSupportFragmentManager().
		actionBar.removeAllTabs();
		
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		//getActivity().getSupportFragmentManager().beginTransaction().remove(TimetableView.this).commit();
	}
}
