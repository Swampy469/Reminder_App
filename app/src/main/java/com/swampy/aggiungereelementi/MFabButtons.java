package com.swampy.aggiungereelementi;

import android.view.animation.OvershootInterpolator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

class MFabButtons {
    private final FloatingActionButton fab_button_1;
    private final FloatingActionButton fab_button_2;
    private final FloatingActionButton fab_button_3;
    private final OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
    private final int fabTranslationY = 100;
    private boolean isFabMenuOpen = false;
    private final int speed = 250;

    MFabButtons(FloatingActionButton fab_button_1, FloatingActionButton fab_button_2, FloatingActionButton fab_button_3) {
        this.fab_button_1 = fab_button_1;
        this.fab_button_2 = fab_button_2;
        this.fab_button_3 = fab_button_3;

        setFabTranslationY();
    }

    private void setFabTranslationY() {
        fab_button_2.setAlpha(0f);
        fab_button_3.setAlpha(0f);

        fab_button_2.setTranslationY(fabTranslationY);
        fab_button_3.setTranslationY(fabTranslationY);

    }

    private void fabMenuOpen() {
        isFabMenuOpen = !isFabMenuOpen;
        fabMainAnimation(true);
        fabOpenAnimation(fab_button_2);
        fabOpenAnimation(fab_button_3);
    }

    private void fabMenuClose() {
        isFabMenuOpen = !isFabMenuOpen;
        fabMainAnimation(false);
        fabCloseAnimation(fab_button_2);
        fabCloseAnimation(fab_button_3);
    }

    private void fabCloseAnimation(FloatingActionButton fab) {
        fab.animate().translationY(fabTranslationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(speed).start();
    }

    private void fabOpenAnimation(FloatingActionButton fab) {
        fab.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(speed).start();
    }

    private void fabMainAnimation(boolean isOpen) {
        if (isOpen) {
            fab_button_1.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(speed).start();
        } else {
            fab_button_1.animate().setInterpolator(overshootInterpolator).rotation(0f).setDuration(speed).start();
        }
    }

    public void menuCheck() {
        if (isFabMenuOpen) {
            fabMenuClose();
        } else {
            fabMenuOpen();
        }
    }
}
