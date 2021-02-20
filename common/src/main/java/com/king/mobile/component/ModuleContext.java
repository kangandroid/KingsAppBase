package com.king.mobile.component;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.collection.SparseArrayCompat;

public class ModuleContext {
    private Activity context;
    private Bundle saveInstance;
    private SparseArrayCompat<ViewGroup> viewGroups = new SparseArrayCompat<>();
}
