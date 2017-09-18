package com.example.eridhobufferyrollian.beispielsql;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.eridhobufferyrollian.beispielsql.model.DateiMemo;
import com.example.eridhobufferyrollian.beispielsql.source.DateiMemoDbSource;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("deprecation")
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.eridhobufferyrollian.beispielsql", appContext.getPackageName());
    }
    @Test
    public void TestUpdateCorner() {
        DateiMemoDbSource dateiMemoDbSource = new DateiMemoDbSource();
        DateiMemo dateiMemo = new DateiMemo();
        dateiMemo.setUid(7872);
        //dateiMemo.setChecked(true);
        dateiMemo.setCornerTopRightX(0.5);
        dateiMemo.setCornerTopRightY(0.9);
        dateiMemo.setCornerBottomLeftX(0.2);
        dateiMemo.setCornerBottomLeftY(0.3);
        dateiMemo.setCornerTopLeftX(0.1);
        dateiMemo.setCornerTopLeftY(0.7);
        dateiMemo.setCornerBottomRightX(0.9);
        dateiMemo.setCornerBottomRightY(0.3);
        dateiMemo.setPunktX(0.3);
        dateiMemo.setPunktY(0.4);
        dateiMemo.setIP("277.0.0.0/8");
        dateiMemo.setCountPeers(2);
        dateiMemoDbSource.createDateiMemo(dateiMemo);

        dateiMemoDbSource.updateCornerBottomLeftX(0.5);
        dateiMemoDbSource.updateCornerBottomLeftY(0.8);
        dateiMemoDbSource.updateCornerBottomRightX(0.6);
        dateiMemoDbSource.updateCornerBottomRightY(0.4);
        dateiMemoDbSource.updateCornerTopLeftX(0.2);
        dateiMemoDbSource.updateCornerTopLeftY(0.5);
        dateiMemoDbSource.updateCornerTopRightX(0.8);
        dateiMemoDbSource.updateCornerTopRightY(0.1);
        assertEquals(0.1,dateiMemoDbSource.getCornerTopRightY(),0);
        assertEquals(0.8,dateiMemoDbSource.getCornerTopRightX(),0);
        assertEquals(0.5,dateiMemoDbSource.getCornerTopLeftY(),0);
        assertEquals(0.2,dateiMemoDbSource.getCornerTopLeftX(),0);
        assertEquals(0.4,dateiMemoDbSource.getCornerBottomRightY(),0);
        assertEquals(0.6,dateiMemoDbSource.getCornerBottomRightX(),0);
        assertEquals(0.8, dateiMemoDbSource.getCornerBottomLeftY(),0);
        assertEquals(0.5, dateiMemoDbSource.getCornerBottomLeftX(), 0);
    }
}
