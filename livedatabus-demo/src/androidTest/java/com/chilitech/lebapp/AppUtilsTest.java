package com.chilitech.lebapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.chilitech.livedatabus.utils.AppUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by liaohailiang on 2019/3/20.
 */
@RunWith(AndroidJUnit4.class)
public class AppUtilsTest {

    @Test
    public void testGetApplicationContext() throws Exception {
        Assert.assertNotNull(AppUtils.getApp());
    }
}