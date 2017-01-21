package com.achyut.movielistingapp.rest;

/**
 * Created by bhand on 1/19/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.achyut.movielistingapp.PopularMovieFragment;
import com.achyut.movielistingapp.UpcomingMovieFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null ;
        switch (position) {
            case 0:
                fragment = UpcomingMovieFragment.newInstance(position + 1);
                break;

            case 1:
                fragment = PopularMovieFragment.newInstance(position + 1);
                break;


            default:
                fragment = UpcomingMovieFragment.newInstance(position + 1);
                break;

        }
        return fragment;

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Upcoming";
            case 1:
                return "Popular";
            case 2:
                return "Top Rated";
        }
        return null;
    }
}
