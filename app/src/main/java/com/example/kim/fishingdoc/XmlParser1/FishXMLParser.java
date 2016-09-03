package com.example.kim.fishingdoc.XmlParser1;

/**
 * Created by Ryu on 2016-08-28.
 */

import android.os.Handler;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;


public class FishXMLParser extends XMLParser implements Runnable {
    private ArrayList<FishDatas> mDataList;
    private Handler mHandler;

    public FishXMLParser(String addr, Handler handler) {
        super(addr);
        // TODO Auto-generated constructor stub
        mHandler = handler;
    }

    public void startParsing() {
        // TODO Auto-generated method stub
        XmlPullParser parser = getXMLParser("utf-8");

        if (parser == null) {
            mDataList = null;
            Log.d("FishXMLParser", "Paser Object is null");
        } else {
            mDataList = new ArrayList<FishDatas>();
            String fishHour = null, fishDay = null, fishTemp = null, fishWfKor = null, fishPop = null;
            String fishWs = null, fishWd = null, fishReh = null;

            String tag;
            try {
                int parserEvent = parser.getEventType();
                int tagIdentifier = 0;

                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tag = parser.getName();
                            if (tag.equals("hour")) {
                                tagIdentifier = 1;
                            } else if (tag.equals("day")) {
                                tagIdentifier = 2;
                            } else if (tag.equals("temp")) {
                                tagIdentifier = 3;
                            } else if (tag.equals("wfKor")) {
                                tagIdentifier = 4;
                            } else if (tag.equals("pop")) {
                                tagIdentifier = 5;
                            } else if (tag.equals("ws")) {
                                tagIdentifier = 6;
                            } else if (tag.equals("wdKor")) {
                                tagIdentifier = 7;
                            } else if (tag.equals("reh")) {
                                tagIdentifier = 8;
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            if (tagIdentifier == 1) {
                                fishHour = parser.getText().trim();
                            } else if (tagIdentifier == 2) {
                                fishDay = parser.getText().trim();
                            } else if (tagIdentifier == 3) {
                                fishTemp = parser.getText().trim();
                            } else if (tagIdentifier == 4) {
                                fishWfKor = parser.getText().trim();
                            } else if (tagIdentifier == 5) {
                                fishPop = parser.getText().trim();
                            } else if (tagIdentifier == 6) {
                                fishWs = parser.getText().trim();
                            } else if (tagIdentifier == 7) {
                                fishWd = parser.getText().trim();
                            } else if (tagIdentifier == 8) {
                                fishReh = parser.getText().trim();

                                FishDatas data = new FishDatas(fishHour, fishDay, fishTemp, fishWfKor, fishPop, fishWs, fishWd, fishReh);
                                mDataList.add(data);
                            }
                            tagIdentifier = 0;
                            break;
                    }
                    parserEvent = parser.next();
                }
            } catch (Exception e) {
                Log.d("BusStationXMLParser", e.getMessage());
            }
        }

        Log.d("BusStationXMLResult", Integer.toString(mDataList.size()));
    }

    public ArrayList<FishDatas> getResult() {
        return mDataList;
    }

    public void run() {
        // TODO Auto-generated method stub
        startParsing();
        mHandler.sendEmptyMessage(0);
    }
}
