package com.example.kim.fishingdoc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by Ryu on 2016-08-10.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context) {
        super(context, "testDB", null, 1);

        String dbPath=context.getDatabasePath("testDB").getPath();
        Log.i("my db path = ", "" + dbPath);
        File f=new File(dbPath);

        if(f.canRead())
            Log.i("DB", "DB를 읽을수 있다");

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table testT " +
                "(id integer primary key, sido_kr char(150), sido_en char(150), latitude double, longitude double, location char(100), x integer, y integer)");
        db.execSQL("INSERT INTO testT (`id`, `sido_kr`, `sido_en`, `latitude`, `longitude`, `location`, `x`, `y`) VALUES" +
                "(1, '백령도', 'BAEKRYEONGDO', 37.950966, 124.656029, '흑산도', 21, 135)," +
                "(2, '대청도', 'DAECHEONGDO', 37.827214, 124.6999091, '흑산도', 21, 132)," +
                "(3, '순위도', 'SUNWIDO', 37.7303959, 125.2642885, '흑산도', 38, 129)," +
                "(4, '연평도', 'YEONPYEONGDO', 37.659501, 125.6898155, '흑산도', 38, 129)," +
                "(5, '덕적도', 'DEOKJEOKDO', 37.2376713, 126.1279505, '흑산도', 46, 119)," +
                "(6, '굴업도', 'GULEOBDO', 37.1915355, 125.9798551, '흑산도', 46, 119)," +
                "(7, '강화외포', 'GANGHWADO(OEPO)', 37.7063352, 126.3810139, '서산', 50, 130)," +
                "(8, '강화대교', 'GANGHWADAEGYO', 37.733756, 126.52337, '서산', 51, 131)," +
                "(9, '영종대교', 'YOUNGJONGDAEGYO', 37.528088, 126.55054, '인천', 54, 126)," +
                "(10, '인천', 'INCHEON', 37.470151, 126.616719, '인천', 53, 124)," +
                "(11, '안산', 'ANSAN', 37.3218778, 126.8308848, '인천', 53, 120)," +
                "(12, '평택', 'PYEONGTAEK', 36.961799, 126.841189, '평택', 58, 114)," +
                "(13, '대산', 'DAESAN', 37.014408, 126.420421, '서산', 51, 113)," +
                "(14, '안흥', 'ANHEUNG', 36.680346, 126.152392, '인천', 47, 108)," +
                "(15, '보령', 'BORYEONG', 36.328131, 126.505933, '보령', 52, 102)," +
                "(16, '어청도', 'EOCHEONGDO', 36.119316, 125.980439, '흑산도', 43, 95)," +
                "(17, '위도', 'WIDO', 35.602995, 126.281541, '보령', 48, 84)," +
                "(18, '장항', 'JANGHANG', 36.008772, 126.700643, '군산', 55, 93)," +
                "(19, '군산', 'GUNSAN', 35.970178, 126.616098, '군산', 55, 92)," +
                "(20, '안마도', 'ANMADO', 35.347967, 126.02881, '서산', 45, 75)," +
                "(21, '영광', 'YEONGGWANG', 35.295191, 126.400709, '보령', 51, 79)," +
                "(22, '목포', 'MOKPO', 34.780824, 126.382818, '목포', 50, 66)," +
                "(23, '흑산도', 'HEUKSANDO', 34.669827, 125.419442, '흑산도', 33, 64)," +
                "(24, '서거차도', 'SEOGEOCHADO', 34.255917, 125.908819, '무안', 44, 55)," +
                "(25, '벽파진', 'BYEOKPAJIN', 34.541653, 126.344076, '목포', 49, 60)," +
                "(26, '진도(수품)', 'JINDO', 34.38155, 126.303418, '목포', 48, 58)," +
                "(27, '완도', 'WANDO', 34.317165, 126.762638, '완도', 57, 56)," +
                "(28, '추자도', 'CHUJADO', 33.947479, 126.321084, '목포', 48, 48)," +
                "(29, '제주', 'JEJU', 33.499538, 126.519428, '제주', 53, 38)," +
                "(30, '모슬포', 'MOSEULPO', 33.216597, 126.250797, '제주(레)', 48, 32)," +
                "(31, '서귀포', 'SEOGWIPO', 33.240475, 126.561764, '서귀포', 53, 32)," +
                "(32, '성산포', 'SEONGSANPO', 33.47331, 126.932982, '제주', 60, 37)," +
                "(33, '거문도', 'GEOMUNDO', 34.047854, 127.318931, '순천', 67, 50)," +
                "(34, '녹동', 'NOKDONG', 34.522204, 127.145129, '승주', 64, 61)," +
                "(35, '고흥발포', 'GOHEUNG(BALPO)', 34.484419, 127.338823, '고흥', 67, 60)," +
                "(36, '광양', 'GWANGYANG', 34.915646, 127.68251, '거창', 74, 70)," +
                "(37, '여수', 'YEOSU', 34.745979, 127.751292, '여수', 74, 65)," +
                "(38, '삼천포', 'SAMCHEONPO', 34.926174, 128.069799, '진주', 80, 70)," +
                "(39, '통영', 'TONGYEONG', 34.861634, 128.420346, '통영', 86, 68)," +
                "(40, '마산', 'MASAN', 35.189471, 128.56659, '마산', 89, 77)," +
                "(41, '진해항', 'JINHAE', 35.130804, 128.696081, '마산', 91, 75)," +
                "(42, '거제도', 'GEOJEDO', 34.80281, 128.6973, '거제', 92, 68)," +
                "(43, '부산항신항', 'BUSAN(NEW_PORT)', 35.078365, 128.831587, '거제', 94, 74)," +
                "(44, '부산', 'BUSAN', 35.102691, 129.042626, '부산', 97, 74)," +
                "(45, '가덕도', 'GADEOKDO', 35.020072, 128.821562, '부산', 94, 74)," +
                "(46, '울산', 'ULSAN', 35.517516, 129.373129, '울산', 102, 83)," +
                "(47, '포항', 'POHANG', 36.035764, 129.406251, '포항', 102, 95)," +
                "(48, '후포', 'HUPO', 36.680684, 129.454651, '울진', 103, 109)," +
                "(49, '동해항', 'DONGHAE(PORT)', 37.489297, 129.123354, '동해', 96, 127)," +
                "(50, '묵호', 'MUKHO', 37.562357, 129.11966, '태백', 97, 127)," +
                "(51, '속초', 'SOKCHO', 38.210993, 128.597288, '속초', 87, 141)," +
                "(53, '울릉도', 'ULLEUNGDO', 37.530072, 130.853736, '울릉도', 127, 127)," +
                "(55, '영흥도', 'YEONGHEUNGDO', 37.267311, 126.460684, '인천', 51, 120)," +
                "(56, '인천 송도', 'INCHEON(SONGDO)', 37.355237, 126.658616, '인천', 55, 123)," +
                "(57, '태안', 'TAEAN', 36.779223, 126.133156, '서산', 48, 110)," +
                "(58, '서천마량', 'SEOCHOEN(MARYANG)', 36.142236, 126.495489, '군산', 53, 96)," +
                "(59, '복사초', 'BOKSACHO', 34.357191, 126.183022, '무안', 46, 58)," +
                "(60, '도농탄', 'DONONGTAN', 33.175619, 126.271502, '제주(레)', 48, 32)," +
                "(61, '미조항', 'MIJOHANG', 34.710123, 128.046696, '남해', 80, 65)," +
                "(62, '교본초', 'GYOBONCHO', 34.635195, 128.269198, '통영', 84, 63)," +
                "(63, '왕돌초', 'WANGDOLCHO', 36.680357, 129.454169, '포항', 103, 109)," +
                "(64, '속초등표', 'SOKCHO_DEUNGPYO', 38.191897, 128.603122, '속초', 87, 141)," +
                "(65, '쌍정초', 'SSANGJEONGCHO', 37.544252, 130.918655, '울릉도', 127, 129)," +
                "(66, '독도', 'DOKDO', 37.240988, 131.865281, '독도', 144, 123)," +
                "(67, '경인항', 'GYEONGIN(PORT)', 37.5961, 126.792358, '인천', 54, 127)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS testT");
        onCreate(db);
    }

}
















//public class MySQLiteOpenHelper extends SQLiteOpenHelper {
//
//    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
//        // SQLiteOpenHelper 가 최초 실행 되었을 때
//        String sql = "create table diaryTable (" +
//                "_id integer primary key autoincrement, " +
//                "number integer, " +
//                "title text, " +
//                "body text);";
//        /*
//        String sql = "create table student (" +
//                "_id integer primary key autoincrement, " +
//                "name text, " +
//                "age integer, " +
//                "address text);";
//        */
//        db.execSQL(sql);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // db = 적용할 db, old/new 구 버전/신버전
//        // TODO Auto-generated method stub
//        /*
//         * db 버전이 업그레이드 되었을 때 실행되는 메소드
//         * 이 부분은 사용에 조심해야 하는 일이 많이 있다. 버전이 1인 사용자가 2로 바뀌면
//         * 한번의 수정만 하면 되지만 버전이 3으로 되면 1인 사용자가 2, 3을 거쳐야 하고
//         * 2인 사용자는 3 까지만 거치면 된다. 이렇게 증가할 수록 수정할 일이 많아지므로
//         * 적절히 사용해야 하며 가능하면 최초 설계 시에 완벽을 기하는 것이 가장 좋을 것이다.
//         * 테스트에서는 기존의 데이터를 모두 지우고 다시 만드는 형태로 하겠다.
//         */
//
//        String sql = "drop table if exists diaryTable";
//        db.execSQL(sql);
//        onCreate(db); // 테이블을 지웠으므로 다시 테이블을 만들어주는 과정
//    }

